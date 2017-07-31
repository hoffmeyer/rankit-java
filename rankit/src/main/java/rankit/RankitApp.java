package rankit;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rankit.logic.RankitEngine;
import rankit.model.Match;
import rankit.model.Player;
import rankit.model.event.CreatePlayerEvent;
import rankit.model.event.RegisterMatchEvent;
import ro.pippo.core.Application;
import ro.pippo.core.RedirectHandler;

public class RankitApp extends Application {
	
	private RankitEngine engine = new RankitEngine();
		
	private Logger logger = LoggerFactory.getLogger(RankitApp.class);
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
		    routeContext.getResponse().header("Access-Control-Allow-Origin", "*");
		    routeContext.next();
		});
        
        /*
         * root redirect
         */
        GET("/", new RedirectHandler("/index.html"));

		GET("/api/list", (routeContext) -> {
			routeContext.json().send(engine.getSortedPlayerlist());
		});
		
		GET("/api/player/{id}", (routeContext) -> {
			int id = routeContext.getParameter("id").toInt(100);
			Player player = engine.getPlayer(id);
			routeContext.json().send(player);
		});

		GET("/api/player/{id}/latestMatches", (routeContext) -> {
			int id = routeContext.getParameter("id").toInt(100);
			List<Match> matches = engine.getLatestMatches(id);
			routeContext.json().send(matches);
		});

		POST("/api/player", (routeContext) -> {

			Player newPlayer = routeContext.createEntityFromBody(Player.class);
			if (newPlayer == null) {
				routeContext.status(501).send("Attribute name is undefined");
				return;			
			}
			
			CreatePlayerEvent createPlayerEvent = engine.newAndApplyCreatePlayerEvent( newPlayer.getName() );
			_db.saveEvent(createPlayerEvent);
			
			routeContext.json().send(engine.getSortedPlayerlist());

		});
		
		GET("/api/match/{num}", (routeContext) -> {
			int num = routeContext.getParameter("num").toInt(100);
			List<Match> latestMatches = engine.getSortedMatchList(num);
			routeContext.json().send(latestMatches);
		});

		POST("/api/match", (routeContext) -> {
			Match match = routeContext.createEntityFromBody(Match.class);
			if(match == null){ 
				routeContext.status(501).send("Attribute match is incorrect");			
			}
			
			if( match.time == null ) {
				match.time = new Date();
			}				
			
			RegisterMatchEvent registerMatchEvent = engine.newAndApplyRegisterMatchEvent(match);
			_db.saveEvent(registerMatchEvent);
			
			routeContext.json().send(engine.getSortedPlayerlist());

		});
	}

	@Override
	protected void onDestroy() {
		_db.close();
	}
	
	private void initList() {
		engine.applyEvents(_db.getEvents());
	}

	
}
