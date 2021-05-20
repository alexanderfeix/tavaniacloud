package de.cuelex.database.mysql;

import de.cuelex.logger.ConsoleLogger;
import de.cuelex.logger.ConsoleLoggerType;
import de.cuelex.main.HomeCloud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: HomeCloud/de.cuelex.database.mysql
    Date: 14.05.2021
    
*/
public class MySQLConnectionHandler {

    public Connection connection;
    private String hostname;
    private String database;
    private int port;
    private String username;
    private String password;

    /**
     * Connecting to MySQL-Database
     */
    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + port + "/" + database + "?autoReconnect=true", username, password);
        } catch (SQLException  e) {
            e.printStackTrace();
            ConsoleLogger.getInstance().log(ConsoleLoggerType.ERROR, MySQLConnectionHandler.class, "Could not connect to MySQL-Database. Please check the config.json file.");
        }
    }

    /**
     * Disconnecting from MySQL-Database
     */
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Pushes a command and returns a result
     *
     * @param qry is the command
     */
    public void query(String qry) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(qry);
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            disconnect();
            connect();
        }
    }

    /**
     * Getters and setters
     * @return private user-data
     */

    public String getHostname() {
        return hostname;
    }

    public String getDatabase() {
        return database;
    }

    public int getPort() {
        return port;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
