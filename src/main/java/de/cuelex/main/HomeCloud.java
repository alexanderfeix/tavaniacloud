package de.cuelex.main;

import de.cuelex.database.DatabaseHandler;
import de.cuelex.database.mysql.MySQLConnectionHandler;
import de.cuelex.launcher.Launcher;
import de.cuelex.logger.command.CommandExecuter;
import de.cuelex.logger.thread.TavaniaThread;
import de.cuelex.util.JSONWebDataHandler;
import de.cuelex.util.VersionManager;
import de.cuelex.util.api.GoogleAuthenticationHandler;

import java.util.Date;
import java.util.GregorianCalendar;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: HomeCloud/de.cuelex.main
    Date: 19.11.2020
    
*/
public class HomeCloud {

    public static HomeCloud instance = new HomeCloud();
    public static HomeCloud getInstance() {
        return instance;
    }
    private Launcher launcher = new Launcher();
    public Launcher getLauncher() {
        return launcher;
    }
    private TavaniaThread tavaniaThread = new TavaniaThread();
    public TavaniaThread getTavaniaThread() {
        return tavaniaThread;
    }
    private GoogleAuthenticationHandler googleAuthenticationHandler = new GoogleAuthenticationHandler();
    public GoogleAuthenticationHandler getGoogleAuthenticationHandler() {
        return googleAuthenticationHandler;
    }
    private CommandExecuter commandExecuter = new CommandExecuter();
    public CommandExecuter getCommandExecuter() {
        return commandExecuter;
    }
    private JSONWebDataHandler jsonWebDataHandler = new JSONWebDataHandler();
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

    public DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
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
