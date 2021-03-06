package de.cuelex.database;

import de.cuelex.logger.ConsoleLogger;
import de.cuelex.logger.LoggerType;
import de.cuelex.logger.thread.RunningThread;
import de.cuelex.main.TavaniaCloud;
import org.simpleyaml.configuration.file.YamlFile;
import org.simpleyaml.exceptions.InvalidConfigurationException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: TavaniaCloud/de.cuelex.database
    Date: 20.05.2021
    
*/
public class DatabaseHandler {

    private String hostname;
    private String database;
    private int port;
    private String username;
    private String password;

    public boolean databaseConfigured;
    private final List<Enum> databaseTypes = Arrays.asList(DatabaseType.values());
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

    public void configureDatabase() {
        ConsoleLogger.getInstance().log(LoggerType.INFORMATION, DatabaseHandler.class, "First things first! The connection to your database must be configured.");
        ConsoleLogger.getInstance().log(LoggerType.INFORMATION, DatabaseHandler.class, "MAKE SURE YOU TYPE IN EVERYTHING CORRECTLY, OTHERWISE YOU HAVE TO REINSTALL THE APPLICATION! ");
        ConsoleLogger.getInstance().log(LoggerType.INFORMATION, DatabaseHandler.class, "Make sure you have the information about your: ");
        ConsoleLogger.getInstance().log(LoggerType.INFORMATION, DatabaseHandler.class, "- Database-Type (MySQL/Redis)");
        ConsoleLogger.getInstance().log(LoggerType.INFORMATION, DatabaseHandler.class, "- Hostname (String)");
        ConsoleLogger.getInstance().log(LoggerType.INFORMATION, DatabaseHandler.class, "- Name of database (String)");
        ConsoleLogger.getInstance().log(LoggerType.INFORMATION, DatabaseHandler.class, "- Port (Integer)");
        ConsoleLogger.getInstance().log(LoggerType.INFORMATION, DatabaseHandler.class, "- Username (String)");
        ConsoleLogger.getInstance().log(LoggerType.INFORMATION, DatabaseHandler.class, "- Password (String)");
        ConsoleLogger.getInstance().log(LoggerType.INFORMATION, DatabaseHandler.class, "Your password will not get saved on the webserver! It will get saved encrypted in the database.");
        ConsoleLogger.getInstance().log(LoggerType.INFORMATION, DatabaseHandler.class, "What is your database-type?");
        Scanner scanner = new Scanner(System.in);
        String databaseType = scanner.nextLine();
        if (getDatabaseTypes().toString().contains(databaseType.toUpperCase())) {
            setDatabaseType(databaseType.toUpperCase());
            ConsoleLogger.getInstance().log(LoggerType.INFORMATION, DatabaseHandler.class, "You've set your database-type to: " + getDatabaseType());
        } else {
            ConsoleLogger.getInstance().log(LoggerType.INFORMATION, DatabaseHandler.class, "This database-type is unfortunately not supported!");
            return;
        }
        ConsoleLogger.getInstance().log(LoggerType.INFORMATION, DatabaseHandler.class, "What is your hostname?");
        Scanner scannerHostname = new Scanner(System.in);
        String hostname = scannerHostname.nextLine();
        TavaniaCloud.getInstance().getDatabaseHandler().setHostname(hostname);
        ConsoleLogger.getInstance().log(LoggerType.INFORMATION, DatabaseHandler.class, "What is the name of your database?");
        Scanner scannerDatabase = new Scanner(System.in);
        String database = scannerDatabase.nextLine();
        TavaniaCloud.getInstance().getDatabaseHandler().setDatabase(database);
        ConsoleLogger.getInstance().log(LoggerType.INFORMATION, DatabaseHandler.class, "What is your port?");
        Scanner scannerPort = new Scanner(System.in);
        int port;
        do {
            while (!scannerPort.hasNextInt()) {
                ConsoleLogger.getInstance().log(LoggerType.ERROR, DatabaseHandler.class, "Please enter a valid port!");
                scannerPort.next();
            }
             port = scannerPort.nextInt();
        }while (port <= 0);
        TavaniaCloud.getInstance().getDatabaseHandler().setPort(port);
        ConsoleLogger.getInstance().log(LoggerType.INFORMATION, DatabaseHandler.class, "What is your username?");
        Scanner scannerUsername = new Scanner(System.in);
        String username = scannerUsername.nextLine();
        TavaniaCloud.getInstance().getDatabaseHandler().setUsername(username);
        ConsoleLogger.getInstance().log(LoggerType.INFORMATION, DatabaseHandler.class, "What is your password?");
        Scanner scannerPassword = new Scanner(System.in);
        String password = scannerPassword.nextLine();
        TavaniaCloud.getInstance().getDatabaseHandler().setPassword(password);
        ConsoleLogger.getInstance().log(LoggerType.INFORMATION, DatabaseHandler.class, "Done! Connecting to database...");
        try{
            saveConfigurations();
            connectToDatabase();
        }catch (Exception e){
            ConsoleLogger.getInstance().log(LoggerType.ERROR, DatabaseHandler.class, "Could not connect to the database.");
        }
    }

    /**
     * creating database entry in config.yml
     */
    private void initializeData(){
        YamlFile yamlFile = TavaniaCloud.getInstance().getYamlFileHandler().getConfigFile();
        try {
            yamlFile.load();
            this.hostname = yamlFile.getString(getDatabaseType() + ".Hostname");
            this.database = yamlFile.getString(getDatabaseType() + ".Database");
            this.port = yamlFile.getInt(getDatabaseType() + ".Port");
            this.username = yamlFile.getString(getDatabaseType() + ".Username");
            this.password = yamlFile.getString(getDatabaseType() + ".Password");
        } catch (InvalidConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }

    public void connectToDatabase() {
        initializeData();
        if (getDatabaseType().equalsIgnoreCase("MYSQL")) {
            TavaniaCloud.getInstance().getMySQLConnectionHandler().connect();
            /*
                Creating database entry if not exists
             */
            TavaniaCloud.getInstance().getMySQLCloudAsyncHandler().createSQLTableAsync();
            TavaniaCloud.getInstance().getMySQLLogAsyncHandler().createSQLTableAsync();
            TavaniaCloud.getInstance().getMySQLLogAsyncHandler().createLogAsync(0, TavaniaCloud.getInstance().getGregorianDate().toString(), "Connected to MySQL-Database successfully.");
        } else if (getDatabaseType().equalsIgnoreCase("REDIS")) {
            TavaniaCloud.getInstance().getRedisConnectionHandler().connect();
            TavaniaCloud.getInstance().getRedisConnectionHandler().openConnection();
        }
        /* TODO: ENABLE WHEN DATABASE IS IN USE
        if (!TavaniaCloud.getInstance().getTavaniaThread().getRunningThreads().toString().contains("RunningThread")) {
            TavaniaCloud.getInstance().getTavaniaThread().startThread(new RunningThread(), "RunningThread");
        }

         */
    }

    /**
     * save database configurations in config.yml
     */
    public void saveConfigurations(){
        YamlFile yamlFile = TavaniaCloud.getInstance().getYamlFileHandler().getConfigFile();
        yamlFile.set(getDatabaseType() + ".Hostname", hostname);
        yamlFile.set(getDatabaseType() + ".Database", database);
        yamlFile.set(getDatabaseType() + ".Port", port);
        yamlFile.set(getDatabaseType() + ".Username", username);
        yamlFile.set(getDatabaseType() + ".Password", password);
        try {
            yamlFile.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
