package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager
{
  private static DBManager instance;
  private static final String DB_IP = "localhost";
  private static final String DB_PORT = "3306";
  private static final String DB_NAME = "nomnom";
  private static final String DB_USERNAME = "root";
  private static final String DB_PASSWORD = "carebear57";
  private static Connection connection = null;  
  private static final String URL = "jdbc:mysql://"+ DB_IP +":" + DB_PORT + "/" + DB_NAME ;
  
  private DBManager()
  {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    try
    {
      connection = DriverManager.getConnection(URL, DB_USERNAME,DB_PASSWORD);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public static synchronized DBManager getInstance() {
    if (instance == null) {
      instance = new DBManager();
    }
    return instance;
  }
  
  public Connection getConnection() throws Exception {
    if (connection == null) {
      throw new Exception("Not connected to DB.");
    }
    return connection;
  }
}
