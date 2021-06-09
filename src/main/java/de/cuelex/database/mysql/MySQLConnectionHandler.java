package de.cuelex.database.mysql;

import de.cuelex.logger.ConsoleLogger;
import de.cuelex.logger.LoggerType;
import de.cuelex.main.TavaniaCloud;
import org.simpleyaml.configuration.file.YamlFile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: TavaniaCloud/de.cuelex.database.mysql
    Date: 14.05.2021
    
*/
public class MySQLConnectionHandler {

    public Connection connection;

    /**
     * Connecting to MySQL-Database
     */
    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + TavaniaCloud.getInstance().getDatabaseHandler().getHostname() + ":" + TavaniaCloud.getInstance().getDatabaseHandler().getPort() + "/" + TavaniaCloud.getInstance().getDatabaseHandler().getDatabase() + "?autoReconnect=true", TavaniaCloud.getInstance().getDatabaseHandler().getUsername(), TavaniaCloud.getInstance().getDatabaseHandler().getPassword());
            ConsoleLogger.getInstance().log(LoggerType.SUCCESS, MySQLConnectionHandler.class, "Successfully connected to database!");
            //Set mysql-configured true in config file
            YamlFile yamlFile = TavaniaCloud.getInstance().getYamlFileHandler().getConfigFile();
            yamlFile.set("DatabaseConfiguration", true);
            yamlFile.set("DatabaseType", "MYSQL");
            yamlFile.save();
            TavaniaCloud.getInstance().getDatabaseHandler().saveConfigurations();
            TavaniaCloud.getInstance().getDatabaseHandler().setDatabaseConfigured(true);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            ConsoleLogger.getInstance().log(LoggerType.ERROR, MySQLConnectionHandler.class, "Could not connect to MySQL-Database. Please check your configurations.");
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
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
