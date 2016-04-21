package rankit;

import java.util.ArrayList;
import java.util.List;

import ro.pippo.core.Application;

public class RankitApp extends Application {
	
	private List<Player> players = new ArrayList<>();

	@Override
	protected void onInit() {
		
		initList();

		GET("/list", (routeContext) -> routeContext.json().send( players ) );

		POST("/player", (routeContext) ->{
			NewPlayer newPlayer = routeContext.createEntityFromBody(NewPlayer.class);
			if( newPlayer != null ){
				players.add(new Player(players.size() + 1, newPlayer.name, 1000));
				routeContext.send("OK");
			}
			routeContext.status(501).send("Attribute name is undefined");
		} );

	}
	
	public static class NewPlayer {
		public String name;
	}
	
	private void initList(){
		players.add(new Player(1, "The Hoff", 1000));
		players.add(new Player(2, "Heidi", 1000));
		players.add(new Player(3, "Hunden", 1000));
	}
}
