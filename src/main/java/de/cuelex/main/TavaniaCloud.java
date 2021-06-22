package de.cuelex.main;

import de.cuelex.database.DatabaseHandler;
import de.cuelex.database.mysql.MySQLClientManager;
import de.cuelex.database.mysql.MySQLConnectionHandler;
import de.cuelex.database.mysql.MySQLHandler;
import de.cuelex.database.mysql.async.MySQLClientAsnycHandler;
import de.cuelex.database.redis.RedisConnectionHandler;
import de.cuelex.launcher.Launcher;
import de.cuelex.logger.command.CommandExecuter;
import de.cuelex.logger.thread.TavaniaThread;
import de.cuelex.user.client.TavaniaClientHandler;
import de.cuelex.util.JSONWebDataHandler;
import de.cuelex.util.VersionManager;
import de.cuelex.util.YamlFileHandler;
import de.cuelex.util.api.GoogleAuthenticationHandler;

import java.util.Date;
import java.util.GregorianCalendar;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: TavaniaCloud/de.cuelex.main
    Date: 19.11.2020
    
*/
public class TavaniaCloud {

    public static TavaniaCloud instance = new TavaniaCloud();
    private final Launcher launcher = new Launcher();
    private final TavaniaThread tavaniaThread = new TavaniaThread();

    public Launcher getLauncher() {
        return launcher;
    }

    private final GoogleAuthenticationHandler googleAuthenticationHandler = new GoogleAuthenticationHandler();

    public TavaniaThread getTavaniaThread() {
        return tavaniaThread;
    }

    private final CommandExecuter commandExecuter = new CommandExecuter();

    public GoogleAuthenticationHandler getGoogleAuthenticationHandler() {
        return googleAuthenticationHandler;
    }

    private final JSONWebDataHandler jsonWebDataHandler = new JSONWebDataHandler();

    public CommandExecuter getCommandExecuter() {
        return commandExecuter;
    }

    public static TavaniaCloud getInstance() {
        return instance;
    }

    public JSONWebDataHandler getJsonWebDataHandler() {
        return jsonWebDataHandler;
    }

    public VersionManager versionManager = new VersionManager();

    public VersionManager getVersionManager() {
        return versionManager;
    }

    public MySQLConnectionHandler mySQLConnectionHandler = new MySQLConnectionHandler();

    public MySQLConnectionHandler getMySQLConnectionHandler() {
        return mySQLConnectionHandler;
    }

    public DatabaseHandler databaseHandler = new DatabaseHandler();
    public YamlFileHandler yamlFileHandler = new YamlFileHandler();

    public YamlFileHandler getYamlFileHandler() {
        return yamlFileHandler;
    }

    public DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }

    public RedisConnectionHandler redisConnectionHandler = new RedisConnectionHandler();

    public RedisConnectionHandler getRedisConnectionHandler() {
        return redisConnectionHandler;
    }

    public TavaniaClientHandler tavaniaClientHandler = new TavaniaClientHandler();

    public TavaniaClientHandler getTavaniaClientHandler() {
        return tavaniaClientHandler;
    }

    public MySQLHandler mySQLHandler = new MySQLHandler();

    public MySQLHandler getMySQLHandler() {
        return mySQLHandler;
    }

    public MySQLClientManager mySQLClientManager = new MySQLClientManager();
    public MySQLClientAsnycHandler mySQLClientAsnycHandler = new MySQLClientAsnycHandler();

    public MySQLClientManager getMySQLClientManager() {
        return mySQLClientManager;
    }

    public MySQLClientAsnycHandler getMySQLClientAsnycHandler() {
        return mySQLClientAsnycHandler;
    }

    public static void main(String[] args) {
        getInstance().getLauncher().start();
    }

    public Date getGregorianDate() {
        Date date = new Date();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setGregorianChange(date);
        return gregorianCalendar.getTime();
    }

}
