package de.cuelex.logger.thread;

import de.cuelex.logger.ConsoleLogger;
import de.cuelex.logger.ConsoleLoggerType;
import de.cuelex.main.TavaniaCloud;

/*

    Copyright © 2019-2021 Alexander F.
    Twitter: @Taventiksch
    Location: TavaniaCloud/de.cuelex.logger.thread
    Date: 26.01.2021
    
*/
public class UpdateThread implements Runnable{
    @Override
    public void run() {
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, UpdateThread.class, "Checking version...");
        /*
            Getting data from website to check the newest available version
         */
        TavaniaCloud.getInstance().getJsonWebDataHandler().getDataFromWesbite("https://cuelex.de/version.json");
        String latestVersion = TavaniaCloud.getInstance().getJsonWebDataHandler().getJsonObject().getString("homecloud-version");
        /*
            Checking if current version is equal to the latest
         */
        if (TavaniaCloud.getInstance().getVersionManager().getCurrentVersion().equalsIgnoreCase(latestVersion)) {
            ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, UpdateThread.class, "Latest version " + latestVersion + " installed!");
        } else {
            ConsoleLogger.getInstance().log(ConsoleLoggerType.WARNING, UpdateThread.class, "New update available! Check out: https://cuelex.de/homecloud");
        }
    }
}
