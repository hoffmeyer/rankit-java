package rankit.model;

import java.util.Date;

public class Match {
	public Team team1;
	public Team team2;
	public double points;
	public Date time;
	
	@Override
	public String toString() {

		return "Match@"+time.toString()+" ("+team1.score+"-"+team2.score+") ";
	}
}