package rankit.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rankit.RankitApp;
import rankit.model.Match;
import rankit.model.Player;
import rankit.model.Team;
import rankit.model.event.CreatePlayerEvent;
import rankit.model.event.Event;
import rankit.model.event.RegisterMatchEvent;

public class RankitEngine {

		private Logger logger = LoggerFactory.getLogger(RankitApp.class);
		private Map<Integer, Player> players = new HashMap<>();
		private List<Match> matches = new ArrayList<>();
		private boolean matches_need_sorting = false;

		public void applyEvent(Event event) {
			ArrayList<Event> eventList = new ArrayList<Event>();
			eventList.add(event);
			applyEvents(eventList);		
		}

		public void applyEvents(List<Event> list) {
			for (Event event : list) {
				if( !event.isCancelled() ){
					if (event instanceof CreatePlayerEvent) {
						CreatePlayerEvent playerEvent = ((CreatePlayerEvent) event);
						addNewPlayer(new Player(playerEvent.getPlayerName(), playerEvent.getPlayerId()));
					} else if (event instanceof RegisterMatchEvent) {
						Match match = ((RegisterMatchEvent) event).getMatch();
						if( match.time == null ) {
							match.time = event.getEventTime();
						}
						registerMatch(match);
					} else {
						logger.error("Unhandled event i applyEvents " + event.getClass().getName());
					}
				}
			}
		}
		
		public synchronized CreatePlayerEvent newAndApplyCreatePlayerEvent(String playerName)		
		{
			CreatePlayerEvent createPlayerEvent = new CreatePlayerEvent(findUnusedPlayerId(), playerName);
			applyEvent(createPlayerEvent);
			return createPlayerEvent;			
		}
		
		public synchronized RegisterMatchEvent newAndApplyRegisterMatchEvent(Match match) {
			RegisterMatchEvent registerMatchEvent = new RegisterMatchEvent(match);
			applyEvent(registerMatchEvent);
			return registerMatchEvent;
		}

		public List<Player> getSortedPlayerlist() {
			List<Player> playerList = new ArrayList<Player>(players.values());
			playerList.sort((p1, p2) -> p2.getPoints() - p1.getPoints());
			return playerList;
		}
		
		public List<Match> getSortedMatchList( int numElements ) {
			sortMatchesIfNeeded();
			List<Match> latestMatches = matches.subList( 0, Math.min(matches.size(), numElements) );
			return latestMatches;			
		}
		
		private int findUnusedPlayerId() {
			int tryThisId = (int) Math.floor( Math.random()*100000000+100 );
			
			boolean playerExists = true;
			
			for(int i=0;i<100;i++) {
				playerExists = players.containsKey(new Integer(tryThisId));
				if(!playerExists) {
					break;
				} else {
					tryThisId++;
				}
			}
			
			if(playerExists)
				throw new RuntimeException("Couldn't find unused playerId!");
			
			return tryThisId;
		}

		private void addNewPlayer(Player newPlayer) {
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

		public Player getPlayer(int id) {
			return players.get(id);
		}

		public List<Match> getLatestMatches(int id) {
			int numWanted = 3;
			ArrayList<Match> latestMatches = (new ArrayList<>());
			for( int i = matches.size(); i > 0; i--) {
				Match match = matches.get(i-1);
				if(matchHasPlayer(match, id)) {
					latestMatches.add(match);
				}
				if(latestMatches.size() >= numWanted) {
					break;
				}
			}
			return latestMatches;
		}

		private boolean matchHasPlayer(Match match, int id) {
			return teamHasPlayer(match.team1, id) || teamHasPlayer(match.team2, id);
		}

		private boolean teamHasPlayer(Team team, int id) {
			return IntStream.of(team.players).anyMatch(x -> x == id);
		}
	}
	

