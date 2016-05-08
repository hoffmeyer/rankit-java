package rankit;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import rankit.model.event.Event;

public class DatabaseUtil {
	
	private static Logger logger = Logger.getLogger("DatabaseUtil");
	private Connection _db;

	public DatabaseUtil( Connection db ) {
		_db = db;
		initDb();
	}

	private void initDb() {
		logger.info("Initializing database");
		try {
			Statement statement = _db.createStatement();
			String sql = 	"CREATE TABLE IF NOT EXISTS events (" +
					  	 	"	id integer NOT NULL," +
					  	 	"	data jsonb" +
					  	 	");";
			statement.executeUpdate(sql);
	        statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			_db.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void saveEvent(Event event) {
	}

}
