package bot;

import java.sql.*;


public class ConnectionToBd {
  public static final String USERNAME = "root";
  public static final String PASSWORD = "root";
  public static final String URL = "jdbc:mysql://localhost:3306/mydb";
  public static Statement statement;
  public static  Connection connection;

  static {
    try {
      connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
    }
    catch (SQLException throwable) {
      throwable.printStackTrace();
      throw new RuntimeException();
    }
  }
  static {
    try {
      statement = connection.createStatement();
    }
    catch (SQLException throwable) {
      throwable.printStackTrace();
      throw new RuntimeException();
    }
  }

  public Connection getConnection() {
    return connection;
  }
}
