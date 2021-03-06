package de.cuelex.logger.thread;

import de.cuelex.launcher.Launcher;
import de.cuelex.logger.ConsoleLogger;
import de.cuelex.logger.LoggerType;
import de.cuelex.main.TavaniaCloud;
import de.cuelex.network.NettyServerConnection;
import org.simpleyaml.configuration.file.YamlFile;

import java.io.IOException;
import java.util.Scanner;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: TavaniaCloud/de.cuelex.logger.thread
    Date: 19.11.2020
    
*/
public class StartingThread implements Runnable{
    public void run() {
        ConsoleLogger.getInstance().log(LoggerType.INFORMATION, Launcher.class, "Starting TavaniaCloud by Cuelex...");
        ConsoleLogger.getInstance().log(LoggerType.INFORMATION, Launcher.class, "Current version: " + TavaniaCloud.getInstance().getVersionManager().getCurrentVersion());
        ConsoleLogger.getInstance().log(LoggerType.INFORMATION, Launcher.class, "Copyright 2020-2021 Alexander Feix");
        TavaniaCloud.getInstance().getLauncher().setStartingDate(TavaniaCloud.getInstance().getGregorianDate().toString());
        ConsoleLogger.getInstance().log(LoggerType.SUCCESS, Launcher.class, "Started TavaniaCloud successfully!");
        createConfigFiles();
        //authorize();
        /*
        if (!TavaniaCloud.getInstance().getDatabaseHandler().isDatabaseConfigured()) {
            TavaniaCloud.getInstance().getDatabaseHandler().configureDatabase();
        } else {
            TavaniaCloud.getInstance().getDatabaseHandler().setDatabaseConfigured(true);
            TavaniaCloud.getInstance().getDatabaseHandler().connectToDatabase();
        }
        */
        //TODO: DELETE WHEN DATABASE IS IN USE
        if (!TavaniaCloud.getInstance().getTavaniaThread().getRunningThreads().toString().contains("RunningThread")) {
            TavaniaCloud.getInstance().getTavaniaThread().startThread(new RunningThread(), "RunningThread");
        }
        new NettyServerConnection().startServer(23500, true);
    }

    /**
     * authentication via GoogleAuthenticator
     */
    private void authorize(){
        TavaniaCloud.getInstance().getGoogleAuthenticationHandler().a();
        Scanner authScanner = new Scanner(System.in);
        String authNextLine = authScanner.nextLine();
        TavaniaCloud.getInstance().getGoogleAuthenticationHandler().b(Integer.parseInt(authNextLine));
    }

    private void createConfigFiles(){
        YamlFile yamlFile = TavaniaCloud.getInstance().getYamlFileHandler().getConfigFile();
        if (!yamlFile.exists()) {
            try {
                yamlFile.set("DatabaseConfiguration", false);
                yamlFile.set("DatabaseType", "/");
                yamlFile.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        TavaniaCloud.getInstance().getYamlFileHandler().loadConfigFile();
        TavaniaCloud.getInstance().getDatabaseHandler().setDatabaseConfigured(TavaniaCloud.getInstance().getYamlFileHandler().getConfigFile().getBoolean("DatabaseConfiguration"));
        TavaniaCloud.getInstance().getDatabaseHandler().setDatabaseType(TavaniaCloud.getInstance().getYamlFileHandler().getConfigFile().getString("DatabaseType"));
    }
}
