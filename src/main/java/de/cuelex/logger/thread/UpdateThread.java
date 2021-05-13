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
        String latestVersion = HomeCloud.getInstance().getJsonWebDataHandler().getJsonObject().getString("business-version");
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, UpdateThread.class, "Latest version: " + latestVersion);
    }
}
