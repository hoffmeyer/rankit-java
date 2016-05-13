package rankit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import rankit.model.event.CreatePlayerEvent;
import rankit.model.event.Event;
import rankit.model.event.RegisterMatchEvent;

public class DatabaseUtil {

	private static Logger logger = Logger.getLogger("DatabaseUtil");
	private Connection _db;
	private ObjectMapper _jsonMapper;

	public DatabaseUtil(Connection db) {
		_db = db;
		_jsonMapper = new ObjectMapper();
		initDb();
	}

	// TODO: move to external SQL script
	private void initDb() {
		logger.info("Initializing database");
		try {
			Statement s = _db.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS events (" + "	id integer NOT NULL," + "	data jsonb" + ");";
			s.executeUpdate(sql);
			s.close();

		} catch (SQLException e) {
			logger.severe("Failed to create table events");
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
		try {
			Statement s = _db.createStatement();
			String sql = "INSERT INTO EVENTS (id, data) VALUES (" + event.getId() + ", '"
					+ _jsonMapper.writeValueAsString(event) + "')";
			s.executeUpdate(sql);
			s.close();
		} catch (SQLException e) {
			logger.severe("Failed to save event");
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			logger.severe("Failed to convert event to json");
			e.printStackTrace();
		}
	}

	public List<Event> getEvents() {
		logger.info("Loading events....");
		ArrayList<Event> events = new ArrayList<>();
		try {
			Statement s = _db.createStatement();
			String sql = "SELECT * FROM events";
			ResultSet rs = s.executeQuery(sql);
			int numEvents = 0;
			while (rs.next()) {
				String data = rs.getString("data");
				Event event = null;
				if(data.contains(CreatePlayerEvent.class.getName())){
					event = _jsonMapper.readValue(data, CreatePlayerEvent.class);
				} else if(data.contains(RegisterMatchEvent.class.getName())){
					event = _jsonMapper.readValue(data, RegisterMatchEvent.class);
				} else {
					logger.warning("Unknown event loaded from database: " + data);
				}
				if(event != null){
					events.add(event);
					numEvents++;
				}
			}
			logger.info("Loaded " + numEvents + " events from db");
		} catch (SQLException e) {
			logger.severe("Failed to get event list from db");
			e.printStackTrace();
		} catch (JsonParseException e) {
			logger.severe("Failed to get event list from db");
			e.printStackTrace();
		} catch (JsonMappingException e) {
			logger.severe("Failed to get event list from db");
			e.printStackTrace();
		} catch (IOException e) {
			logger.severe("Failed to get event list from db");
			e.printStackTrace();
		}
		return events;
	}

}
