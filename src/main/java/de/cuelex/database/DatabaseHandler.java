package de.cuelex.database;

import de.cuelex.logger.ConsoleLogger;
import de.cuelex.logger.ConsoleLoggerType;
import de.cuelex.main.HomeCloud;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: HomeCloud/de.cuelex.database
    Date: 20.05.2021
    
*/
public class DatabaseHandler {
    private boolean databaseConfigured = false;
    private List<Enum> databaseTypes = Arrays.asList(DatabaseType.values());
    public List<Enum> getDatabaseTypes() {
        return databaseTypes;
    }
    public boolean isDatabaseConfigured() {
        return databaseConfigured;
    }
    public void setDatabaseConfigured(boolean databaseConfigured) {
        this.databaseConfigured = databaseConfigured;
    }

    private String databaseType;
    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }
    public String getDatabaseType() {
        return databaseType;
    }

    public void configureDatabase(){
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, DatabaseHandler.class, "First things first! The connection to your database must be configured.");
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, DatabaseHandler.class, "MAKE SURE YOU TYPE IN EVERYTHING CORRECTLY, OTHERWISE YOU HAVE TO REINSTALL THE APPLICATION! ");
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, DatabaseHandler.class, "Make sure you have the information about your: ");
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, DatabaseHandler.class, "- Database-Type (MySQL/Redis)");
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, DatabaseHandler.class, "- Hostname (String)");
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, DatabaseHandler.class, "- Name of database (String)");
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, DatabaseHandler.class, "- Port (Integer)");
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, DatabaseHandler.class, "- Username (String)");
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, DatabaseHandler.class, "- Password (String)");
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, DatabaseHandler.class, "Your password will not get saved on the webserver! It will get saved encrypted in the database.");
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, DatabaseHandler.class, "What is your database-type?");
        Scanner scanner = new Scanner(System.in);
        String databaseType = scanner.nextLine();
        if(getDatabaseTypes().toString().contains(databaseType.toUpperCase())){
            setDatabaseType(databaseType.toUpperCase());
            ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, DatabaseHandler.class, "You've set your database-type to: " + databaseType.toUpperCase());
        }else{
            ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, DatabaseHandler.class, "The database-type is unfortunately not supported!");
            return;
        }
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, DatabaseHandler.class, "What is your hostname?");
        Scanner scannerHostname = new Scanner(System.in);
        String hostname = scannerHostname.nextLine();
        HomeCloud.getInstance().getMySQLConnectionHandler().setHostname(hostname);
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, DatabaseHandler.class, "What is the name of your database?");
        Scanner scannerDatabase = new Scanner(System.in);
        String database = scannerDatabase.nextLine();
        HomeCloud.getInstance().getMySQLConnectionHandler().setDatabase(database);
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, DatabaseHandler.class, "What is your port?");
        Scanner scannerPort = new Scanner(System.in);
        int port;
        do {
            while (!scannerPort.hasNextInt()) {
                ConsoleLogger.getInstance().log(ConsoleLoggerType.ERROR, DatabaseHandler.class, "Please enter a positive number!");
                scannerPort.next();
            }
             port = scannerPort.nextInt();
        }while (port <= 0);
        HomeCloud.getInstance().getMySQLConnectionHandler().setPort(port);
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, DatabaseHandler.class, "What is your username?");
        Scanner scannerUsername = new Scanner(System.in);
        String username = scannerUsername.nextLine();
        HomeCloud.getInstance().getMySQLConnectionHandler().setUsername(username);
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, DatabaseHandler.class, "What is your password?");
        Scanner scannerPassword = new Scanner(System.in);
        String password = scannerPassword.nextLine();
        HomeCloud.getInstance().getMySQLConnectionHandler().setPassword(password);
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, DatabaseHandler.class, "Done! Connecting with database...");
        try{
            connectToDatabase();
        }catch (Exception e){
            ConsoleLogger.getInstance().log(ConsoleLoggerType.ERROR, DatabaseHandler.class, "Could not connect to the database.");
        }
    }

    public void connectToDatabase(){
        if(getDatabaseType().equalsIgnoreCase("MYSQL")){
            HomeCloud.getInstance().mySQLConnectionHandler.connect();
        }else if(getDatabaseType().equalsIgnoreCase("REDIS")){
            ConsoleLogger.getInstance().log(ConsoleLoggerType.ERROR, DatabaseHandler.class, "Redis-Support is in development!");
        }
    }
}
