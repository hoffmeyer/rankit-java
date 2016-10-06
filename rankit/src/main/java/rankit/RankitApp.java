package rankit;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rankit.logic.Scoring;
import rankit.model.Match;
import rankit.model.Player;
import rankit.model.event.CreatePlayerEvent;
import rankit.model.event.Event;
import rankit.model.event.RegisterMatchEvent;
import ro.pippo.core.Application;
import ro.pippo.core.RedirectHandler;

public class RankitApp extends Application {

	private Logger logger = LoggerFactory.getLogger(RankitApp.class);
	private Map<Integer, Player> players = new HashMap<>();
	private List<Match> matches = new ArrayList<>();
	private boolean matches_need_sorting = false;
	private DatabaseUtil _db;

	public RankitApp(DatabaseUtil db) {
		_db = db;
	}

	@Override
	protected void onInit() {

		initList();

        /*
         *  audit filter
         */
        ALL("/.*", routeContext -> {
		    logger.info("Request for {} '{}'", routeContext.getRequestMethod(), routeContext.getRequestUri());
		    routeContext.next();
		});
        
        /*
         * root redirect
         */
        GET("/", new RedirectHandler("/index.html"));

		GET("/api/list", (routeContext) -> {
			routeContext.json().send(getSortedPlayerlist());
		});

		POST("/api/player", (routeContext) -> {
			Player newPlayer = routeContext.createEntityFromBody(Player.class);
			if (newPlayer != null) {
				addNewPlayer(newPlayer);
				_db.saveEvent(new CreatePlayerEvent(newPlayer.getId(), newPlayer.getName()));
				routeContext.json().send(getSortedPlayerlist());
			} else {
				routeContext.status(501).send("Attribute name is undefined");
			}
		});
		
		GET("/api/match", (routeContext) -> {
			sortMatchesIfNeeded();
			List<Match> latestMatches = matches.subList( 0, Math.min(matches.size(), 100) );
		
			routeContext.json().send(latestMatches);
		});

		POST("/api/match", (routeContext) -> {
			Match match = routeContext.createEntityFromBody(Match.class);
			if(match != null){
				if( match.time == null ) {
					match.time = new Date();
				}				
				registerMatch(match);
				_db.saveEvent(new RegisterMatchEvent(match));
				routeContext.json().send(getSortedPlayerlist());
			} else {
				routeContext.status(501).send("Attribute match is incorrect");
			}

		});
	}

	private List<Player> getSortedPlayerlist() {
		List<Player> playerList = new ArrayList<Player>(players.values());
		playerList.sort((p1, p2) -> p2.getPoints() - p1.getPoints());
		return playerList;
	}

	@Override
	protected void onDestroy() {
		_db.close();
	}

	private void addNewPlayer(Player newPlayer) {
		int id = players.size() + 1;
		newPlayer.setId(id);
		newPlayer.addPoints(1000);
		players.put(newPlayer.getId(), newPlayer);
	}

	private void registerMatch(Match match) {
		Scoring.score(match, players);
		matches.add(match);
		matches_need_sorting = true;
	}

	// Synchronized to avoid competetive sorting
	private synchronized void sortMatchesIfNeeded() {
		if(matches_need_sorting == false)
			return;				
		matches.sort( (o1, o2) -> o2.time.compareTo(o1.time));		
		matches_need_sorting = false;
	}
	
	private void initList() {
		applyEvents(_db.getEvents());
	}

	private void applyEvents(List<Event> list) {
		for (Event event : list) {
			if (event instanceof CreatePlayerEvent) {
				CreatePlayerEvent playerEvent = ((CreatePlayerEvent) event);
				addNewPlayer(new Player(playerEvent.getPlayerName()));
			} else if (event instanceof RegisterMatchEvent) {
				Match match = ((RegisterMatchEvent) event).getMatch();
				if( match.time == null ) {
					match.time = event.getEventTime();
				}
				registerMatch(match);
			} else {
				logger.error("Unhanled event i applyEvents " + event.getClass().getName());
			}
		}
	}
}
