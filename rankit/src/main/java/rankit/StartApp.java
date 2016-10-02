package rankit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import ro.pippo.core.Pippo;

public class StartApp {

	public static void main(String[] args) {
		
		Logger logger = Logger.getLogger("StartApp");

		try {
			// TODO: load driver like this instead: java -Djdbc.drivers=org.postgresql.Driver example.ImageViewer
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			logger.log(Level.SEVERE, "Database driver not found", e);
		}

		String URL = "jdbc:postgresql://localhost:5432/postgres";
		String USER = "postgres";
		String PASS = "postgres";
		
	    for(String arg : args) {
	    	URL  = getArg(arg, "url",  URL);
	    	USER = getArg(arg, "user", USER);
	    	PASS = getArg(arg, "pass", PASS);		        
	    }
	    
	    System.out.println("DB: "+URL+"\nUSER:"+USER+"\n");

		Connection db;

		try {
			db = DriverManager.getConnection(URL, USER, PASS);
			Pippo pippo = new Pippo( new RankitApp (new DatabaseUtil(db)));
			pippo.getApplication().addPublicResourceRoute("/");
			pippo.start();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Database connection failed", e);
		}
	}
	
	// Decode args with the pattern: -url=xxxxxxx -user=yyyyyy -pass=zzzzzz 
	private static String getArg(String arg, String name, String _default) {
		
		if(arg.startsWith("-"+name+"=")) {
			return arg.substring(name.length()+2);
		} else {
			return _default;
		}
		
		
	}

}
