package rankit.test;

import java.util.List;

import org.eclipse.jetty.io.NetworkTrafficSelectChannelEndPoint;
import org.junit.Assert;
import org.junit.Test;

import rankit.model.Match;
import rankit.model.Player;

public class RankitMetaTest {
	
	
	@Test
	public void simpleScoringTest() {
		
		RankitTestEngine engine = new RankitTestEngine();
		
		engine.addPlayer("Anders");
		engine.addPlayer("Benny");
		engine.addPlayer("Carl");
		engine.addPlayer("Daniel");
		
		engine.addMatch("Anders", "Benny", 3, "Carl", "Daniel", 2);
		engine.addMatch("Anders", null, 3, "Carl", null, 0);
		
		engine.addPlayer("Ewe");
		
		List<Player> sortedPlayerlist = engine.getSortedPlayerlist();
		List<Match> matchlist = engine.getSortedMatchList();
		
//		System.out.println(sortedPlayerlist);
//		System.out.println(matchlist);
		
		Assert.assertEquals( 1048, 	engine.getPlayer("Anders").getPoints() );
		Assert.assertEquals( 1025, 	engine.getPlayer("Benny").getPoints() );
		Assert.assertEquals( 952, 	engine.getPlayer("Carl").getPoints() );
		Assert.assertEquals( 975, 	engine.getPlayer("Daniel").getPoints() );		
		Assert.assertEquals( 1000, 	engine.getPlayer("Ewe").getPoints() );
	}

	
	@Test
	public void scoringConsistencyTest() {
		
		RankitTestEngine engine = new RankitTestEngine();
		
		engine.addPlayer("AlwaysWin");
		engine.addPlayer("NeverWin");

		int alwaysWinPoints = engine.getPlayer("AlwaysWin").getPoints();
		int neverWinPoints = engine.getPlayer("NeverWin").getPoints();

		for( int i=1; i<100 ; i++ ) {

			System.out.println(alwaysWinPoints+" / "+neverWinPoints);

			engine.addMatch("AlwaysWin", null, 3, "NeverWin", null, 0);

			alwaysWinPoints = engine.getPlayer("AlwaysWin").getPoints();
			neverWinPoints 	= engine.getPlayer("NeverWin").getPoints();

			Assert.assertEquals(2000, alwaysWinPoints + neverWinPoints );

		}

	}

}

