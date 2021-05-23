package de.cuelex.logger.thread;

import de.cuelex.launcher.Launcher;
import de.cuelex.logger.ConsoleLogger;
import de.cuelex.logger.ConsoleLoggerType;
import de.cuelex.main.HomeCloud;
import org.simpleyaml.configuration.file.YamlFile;
import org.simpleyaml.exceptions.InvalidConfigurationException;

import java.io.IOException;
import java.util.Scanner;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: HomeCloud/de.cuelex.logger.thread
    Date: 19.11.2020
    
*/
public class StartingThread implements Runnable{
    public void run() {
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, Launcher.class,"Starting HomeCloud by Cuelex...");
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, Launcher.class,"Current version: " + HomeCloud.getInstance().getVersionManager().getCurrentVersion());
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, Launcher.class,"Copyright 2020-2021 Alexander Feix");
        HomeCloud.getInstance().getLauncher().setStartingDate(HomeCloud.getInstance().getGregorianDate().toString());
        ConsoleLogger.getInstance().log(ConsoleLoggerType.SUCCESS, Launcher.class,"Started HomeCloud successfully!");
        createConfigFiles();
        //authorize();
        if(!HomeCloud.getInstance().getDatabaseHandler().isDatabaseConfigured()){
            HomeCloud.getInstance().getDatabaseHandler().configureDatabase();
        }else{
            HomeCloud.getInstance().getDatabaseHandler().setDatabaseConfigured(true);
            System.out.println(HomeCloud.getInstance().getDatabaseHandler().isDatabaseConfigured());
            HomeCloud.getInstance().getDatabaseHandler().connectToDatabase();
        }
    }

    private void authorize(){
        HomeCloud.getInstance().getGoogleAuthenticationHandler().a();
        Scanner authScanner = new Scanner(System.in);
        String authNextLine = authScanner.nextLine();
        HomeCloud.getInstance().getGoogleAuthenticationHandler().b(Integer.parseInt(authNextLine));
    }

    private void createConfigFiles(){
        YamlFile yamlFile = HomeCloud.getInstance().getYamlFileHandler().getConfigFile();
        if(!yamlFile.exists()){
            try {
                yamlFile.set("DatabaseConfiguration", false);
                yamlFile.set("DatabaseType", "/");
                yamlFile.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        HomeCloud.getInstance().getYamlFileHandler().loadConfigFile();
        HomeCloud.getInstance().getDatabaseHandler().setDatabaseConfigured(HomeCloud.getInstance().getYamlFileHandler().getConfigFile().getBoolean("DatabaseConfiguration"));
        HomeCloud.getInstance().getDatabaseHandler().setDatabaseType(HomeCloud.getInstance().getYamlFileHandler().getConfigFile().getString("DatabaseType"));
    }
}
