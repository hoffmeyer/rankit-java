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
		Connection db;

		try {
			db = DriverManager.getConnection(URL, USER, PASS);
			Pippo pippo = new Pippo( new RankitApp (new DatabaseUtil(db)));
			pippo.start();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Database connection failed", e);
		}
	}

}
