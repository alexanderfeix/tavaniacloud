package de.cuelex.logger.thread;

import de.cuelex.launcher.Launcher;
import de.cuelex.logger.ConsoleLogger;
import de.cuelex.logger.ConsoleLoggerType;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: HomeCloud/de.cuelex.logger.thread
    Date: 19.11.2020
    
*/
public class StoppingThread implements Runnable{
    @Override
    public void run() {
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, Launcher.class,"Stopping HomeCloud...");
        ConsoleLogger.getInstance().log(ConsoleLoggerType.SUCCESS, Launcher.class,"Stopped HomeCloud successfully!");
    }
}