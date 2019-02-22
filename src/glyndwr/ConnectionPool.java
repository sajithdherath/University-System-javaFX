package glyndwr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by sajith on 12/22/18
 */
public class ConnectionPool {

    private static ConnectionPool connectionPool;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String JDBC_DB_URL = "jdbc:mysql://localhost:3306/glyndwr_uni";

    static final String USERNAME = "root";
    static final String PASSWORD = "root";

    private Connection connection;
    private ConnectionPool() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_DB_URL,USERNAME,PASSWORD);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static ConnectionPool getInstance() {
        if (connectionPool == null) {
            connectionPool = new ConnectionPool();
        }
        return connectionPool;
    }

    public Connection getConnection() {
        return connection;
    }
}
