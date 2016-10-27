package rankit.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import rankit.RankitEngine;
import rankit.model.Match;
import rankit.model.Player;
import rankit.model.Team;

public class RankitTestEngine extends RankitEngine {
	
	public void addPlayer(String name) {		
		newAndApplyCreatePlayerEvent(name);		
	}

	public void addMatch(String name_1_a, String name_1_b, int score_1, String name_2_a, String name_2_b, int score_2) {
		
		Match match = new Match();
		match.team1 = new Team();
		match.team2 = new Team();
				
		List<Player> sortedPlayerlist = getSortedPlayerlist();
		
		List<Player> team1 = new ArrayList<Player>();
		List<Player> team2 = new ArrayList<Player>();
		for(Player player:sortedPlayerlist) {
			if( player.getName().equals(name_1_a) || player.getName().equals(name_1_b)) {
				team1.add(player);
			}
			if( player.getName().equals(name_2_a) || player.getName().equals(name_2_b)) {
				team2.add(player);
			}			
		}		
		
		int[] team1players = team1.stream().mapToInt( p -> p.getId() ).toArray();
		int[] team2players = team2.stream().mapToInt( p -> p.getId() ).toArray();
		
		match.team1.players = team1players; 
		match.team2.players = team2players;
		match.team1.score = score_1;
		match.team2.score = score_2;

		newAndApplyRegisterMatchEvent(match);
	}
	
	public Player getPlayer(String name) {
		List<Player> sortedPlayerlist = getSortedPlayerlist();
	
		for(Player player:sortedPlayerlist) {
			if( player.getName().equals(name) ) {
				return player;
			}		
		}
		
		return null;
	}
	
}

