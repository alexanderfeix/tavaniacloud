package de.cuelex.logger.thread;

import de.cuelex.logger.ConsoleLogger;
import de.cuelex.logger.ConsoleLoggerType;
import de.cuelex.main.HomeCloud;

/*

    Copyright © 2019-2021 Alexander F.
    Twitter: @Taventiksch
    Location: HomeCloud/de.cuelex.logger.thread
    Date: 26.01.2021
    
*/
public class UpdateThread implements Runnable{
    @Override
    public void run() {
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, UpdateThread.class, "Checking version...");
        /*
            Getting data from website to check the newest available version
         */
        HomeCloud.getInstance().getJsonWebDataHandler().getDataFromWesbite("https://cuelex.de/version.json");
        String latestVersion = HomeCloud.getInstance().getJsonWebDataHandler().getJsonObject().getString("homecloud-version");
        /*
            Checking if current version is equal to the latest
         */
        if(HomeCloud.getInstance().getVersionManager().getCurrentVersion().equalsIgnoreCase(latestVersion)){
            ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, UpdateThread.class, "Latest version " + latestVersion + " installed!");
        }else{
            ConsoleLogger.getInstance().log(ConsoleLoggerType.WARNING, UpdateThread.class, "New update available! Check out: https://cuelex.de/homecloud");
        }
    }
}
