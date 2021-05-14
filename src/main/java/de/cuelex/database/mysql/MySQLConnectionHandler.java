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

    public Connection c = null;
    private String hostname;
    private int port;
    private String username;
    private String password;

    /**
     * Connecting to MySQL-Database
     */
    public void connect() {
        hostname  = null;
        port = 0;
        username = null;
        password = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + port + "/TavaniaCloud?autoReconnect=true", username, password);
        } catch (SQLException e) {
            ConsoleLogger.getInstance().log(ConsoleLoggerType.ERROR, MySQLConnectionHandler.class, "Could not connect to MySQL-Database. Please check the config.json file.");
        }
    }

    /**
     * Disconnecting from MySQL-Database
     */
    public void disconnect() {
        if (c != null) {
            try {
                c.close();
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
            Statement stmt = c.createStatement();
            stmt.executeUpdate(qry);
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            disconnect();
            connect();
        }
    }

}
