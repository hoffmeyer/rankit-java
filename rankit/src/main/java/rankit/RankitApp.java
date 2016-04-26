package rankit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rankit.model.Player;
import ro.pippo.core.Application;

public class RankitApp extends Application {
	
	private Map<Integer, Player> players = new HashMap<>();

	@Override
	protected void onInit() {
		
		initList();

		GET("/list", (routeContext) -> {
			List<Player> playerList = new ArrayList<Player>(players.values());
			playerList.sort((p1, p2) -> p2.getPoints() - p1.getPoints());
			routeContext.json().send( playerList );
		});

		POST("/player", (routeContext) ->{
			NewPlayer newPlayer = routeContext.createEntityFromBody(NewPlayer.class);
			if( newPlayer != null ){
				int id = players.size() + 1;
				players.put(id, new Player(id, newPlayer.name, 1000));
				routeContext.send("OK");
			} else {
				routeContext.status(501).send("Attribute name is undefined");
			}
		} );
		
		POST("/match", (routeContext) ->{
			Match match = routeContext.createEntityFromBody(Match.class);
			if( match.team1.score > match.team2.score) {
				addPoints(match.team1.players, 50);
				addPoints(match.team2.players, -50);
			}
		});

	}
	
	private void addPoints(int[] playerIds, int points) {
		for( int id : playerIds) {
			Player player = players.get(id);
			player.setPoints(player.getPoints() + points);
		}
	}

	public static class NewPlayer {
		public String name;
	}
	
	public static class Team {
		public int[] players;
		public int score;
	}
	
	public static class Match {
		public Team team1;
		public Team team2;
	}
	
	private void initList(){
		players.put(1, new Player(1, "The Hoff", 1000));
		players.put(2, new Player(2, "Heidi", 900));
		players.put(3, new Player(3, "Hunden", 1200));
	}
}
