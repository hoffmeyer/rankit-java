package rankit.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import rankit.model.Match;
import rankit.model.Player;

public class RankitMetaTest {
	
	
	@Test
	public void scoringTest() {
		
		RankitTestEngine engine = new RankitTestEngine();
		
		engine.addPlayer("Anders");
		engine.addPlayer("Benny");
		engine.addPlayer("Carl");
		engine.addPlayer("Daniel");
		
		engine.addMatch("Anders", "Benny", 3, "Carl", "Daniel", 2);
		engine.addMatch("Anders", null, 3, "Carl", null, 0);
		
		List<Player> sortedPlayerlist = engine.getSortedPlayerlist();
		List<Match> matchlist = engine.getSortedMatchList();
		
//		System.out.println(sortedPlayerlist);
//		System.out.println(matchlist);
		
		Assert.assertEquals( 1048, 	engine.getPlayer("Anders").getPoints() );
		Assert.assertEquals( 1025, 	engine.getPlayer("Benny").getPoints() );
		Assert.assertEquals( 951, 	engine.getPlayer("Carl").getPoints() );
		Assert.assertEquals( 975, 	engine.getPlayer("Daniel").getPoints() );
	}

}

