package rankit.logic;

import java.util.Map;

import rankit.model.Match;
import rankit.model.Player;
import rankit.model.Team;

public class Scoring {
	
	public static void score( Match match, Map<Integer, Player> players ){
		double pointsInPlay = 50;
		double averageScoreTeam1 = getAverageTeamScore(match.team1, players);
		double averageScoreTeam2 = getAverageTeamScore(match.team2, players);
		double dist = getDistribution(averageScoreTeam1, averageScoreTeam2);

		Team favouriteTeam = null;
		Team underdogTeam = null;
		if(averageScoreTeam1 > averageScoreTeam2){
			favouriteTeam = match.team1;
			underdogTeam = match.team2;
		} else {
			favouriteTeam = match.team2;
			underdogTeam = match.team1;
		}
		
		boolean favouriteTeamIsWinner = favouriteTeam.score > underdogTeam.score;
		boolean itsADraw = favouriteTeam.score == underdogTeam.score;
		
		double points = 0;
		if(itsADraw){
            transferPoints(favouriteTeam, underdogTeam, points, players);
        } else if (favouriteTeamIsWinner){
            points = pointsInPlay * (1 - dist);
            transferPoints(underdogTeam, favouriteTeam, points, players);
        } else {
            points = pointsInPlay * dist;
            transferPoints(favouriteTeam, underdogTeam, points, players);
        }
		
		match.points = points;
	}
	
	private static void transferPoints(Team fromTeam, Team toTeam, double points, Map<Integer, Player> players) {
		for (int id : fromTeam.players ) {
			Player player = players.get(id);
			player.addPoints(-points);
			retisterLoss(player);
		}
		for (int id : toTeam.players ) {
			Player player = players.get(id);
			player.addPoints(points);
			registerWin(player);
		}
	}

	private static void registerWin(Player player) {
		player.setGamesPlayed(player.getGamesPlayed()+1);
		player.setCurrentWinsInRow(player.getCurrentWinsInRow()+1);
		player.setCurrentLossesInRow(0);
		player.setWonGames(player.getWonGames()+1);
	}

	private static void retisterLoss(Player player) {
		player.setGamesPlayed(player.getGamesPlayed()+1);
		player.setCurrentLossesInRow(player.getCurrentLossesInRow()+1);
		player.setCurrentWinsInRow(0);
		player.setLostGames(player.getLostGames()+1);
	}

	private static double getAverageTeamScore( Team team, Map<Integer, Player> players ){
		int totalPoints = 0;
		for (int id : team.players) {
			totalPoints += players.get(id).getPoints();
		}
		return totalPoints/team.players.length;
	}
	
	private static double getDistribution (double score1, double score2) {
        double scoreDiff = Math.abs(score1 - score2);
        if(scoreDiff == 0) return 0.50;
        if(scoreDiff <= 25) return 0.51;
        if(scoreDiff <= 50) return 0.53;
        if(scoreDiff <= 75) return 0.54;
        if(scoreDiff <= 100) return 0.56;
        if(scoreDiff <= 150) return 0.59;
        if(scoreDiff <= 200) return 0.61;
        if(scoreDiff <= 250) return 0.64;
        if(scoreDiff <= 300) return 0.67;
        if(scoreDiff <= 350) return 0.69;
        if(scoreDiff <= 400) return 0.72;
        if(scoreDiff <= 450) return 0.74;
        if(scoreDiff <= 500) return 0.76;
        if(scoreDiff <= 600) return 0.80;
        if(scoreDiff <= 700) return 0.83;
        if(scoreDiff <= 800) return 0.86;
        if(scoreDiff <= 900) return 0.89;
        if(scoreDiff <= 1000) return 0.91;
        if(scoreDiff <= 1100) return 0.93;
        if(scoreDiff <= 1200) return 0.94;
        if(scoreDiff <= 1300) return 0.95;
        if(scoreDiff <= 1400) return 0.96;
        if(scoreDiff <= 1500) return 0.97;
        if(scoreDiff <= 1700) return 0.98;
        return 1;
    };


}
