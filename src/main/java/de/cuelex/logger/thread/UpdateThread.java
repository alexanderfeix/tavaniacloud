package de.cuelex.logger.thread;

import de.cuelex.logger.ConsoleLogger;
import de.cuelex.logger.LoggerType;

/*

    Copyright © 2019-2021 Alexander F.
    Twitter: @Taventiksch
    Location: TavaniaCloud/de.cuelex.logger.thread
    Date: 26.01.2021
    
*/
public class UpdateThread implements Runnable{
    @Override
    public void run() {
        ConsoleLogger.getInstance().log(LoggerType.INFORMATION, UpdateThread.class, "Checking version...");
        /*
            Getting data from website to check the newest available version
         */
        //TODO: TavaniaCloud.getInstance().getJsonWebDataHandler().getDataFromWesbite("https://cuelex.de/version.json");
        //TODO: String latestVersion = TavaniaCloud.getInstance().getJsonWebDataHandler().getJsonObject().getString("homecloud-version");
        /*
            Checking if current version is equal to the latest
         */
        //TODO: if (TavaniaCloud.getInstance().getVersionManager().getCurrentVersion().equalsIgnoreCase(latestVersion)) {
        //TODO:   ConsoleLogger.getInstance().log(LoggerType.INFORMATION, UpdateThread.class, "Latest version " + latestVersion + " installed!");
        //TODO: } else {
        //TODO:    ConsoleLogger.getInstance().log(LoggerType.WARNING, UpdateThread.class, "New update available! Check out: https://cuelex.de/homecloud");
        //TODO:  }
    }
}
