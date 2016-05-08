package rankit;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rankit.model.Match;
import rankit.model.Player;
import rankit.model.event.CreatePlayerEvent;
import ro.pippo.core.Application;

public class RankitApp extends Application {

	private Map<Integer, Player> players = new HashMap<>();
	private DatabaseUtil _db;

	public RankitApp(DatabaseUtil db) {
		_db = db;
	}

	@Override
	protected void onInit() {

		initList();

		GET("/list", (routeContext) -> {
			List<Player> playerList = new ArrayList<Player>(players.values());
			playerList.sort((p1, p2) -> p2.getPoints() - p1.getPoints());
			routeContext.json().send(playerList);
		});

		POST("/player", (routeContext) -> {
			Player newPlayer = routeContext.createEntityFromBody(Player.class);
			if (newPlayer != null) {
				int id = players.size() + 1;
				newPlayer.setId(id);
				newPlayer.addPoints(1000);
				_db.saveEvent( new CreatePlayerEvent(newPlayer));
				players.put(newPlayer.getId(), newPlayer);
				routeContext.send("OK");
			} else {
				routeContext.status(501).send("Attribute name is undefined");
			}
		});

		POST("/match", (routeContext) -> {
			Match match = routeContext.createEntityFromBody(Match.class);
			if (match.team1.score > match.team2.score) {
				addPoints(match.team1.players, 50);
				addPoints(match.team2.players, -50);
			}
		});
	}

	@Override
	protected void onDestroy() {
		_db.close();
	}

	private void addPoints(int[] playerIds, int points) {
		for (int id : playerIds) {
			Player player = players.get(id);
			player.addPoints(points);
		}
	}

	private void initList() {
		players.put(1, new Player(1, "The Hoff", 1000));
		players.put(2, new Player(2, "Heidi", 900));
		players.put(3, new Player(3, "Hunden", 1200));
	}
}
