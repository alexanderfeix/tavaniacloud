package de.cuelex.logger.thread;

import de.cuelex.launcher.Launcher;
import de.cuelex.logger.ConsoleLogger;
import de.cuelex.logger.ConsoleLoggerType;
import de.cuelex.main.HomeCloud;

import java.util.Scanner;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: HomeCloud/de.cuelex.logger.thread
    Date: 19.11.2020
    
*/
public class StartingThread implements Runnable{
    public void run() {
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, Launcher.class,"Starting HomeCloud by TavaniaGroup...");
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, Launcher.class,"Current version: " + HomeCloud.getInstance().getLauncher().getVersion());
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, Launcher.class,"Copyright 2020 Alexander Feix");
        HomeCloud.getInstance().getLauncher().setStartingDate(HomeCloud.getInstance().getGregorianDate().toString());


        ConsoleLogger.getInstance().log(ConsoleLoggerType.SUCCESS, Launcher.class,"Started HomeCloud successfully!");
    }
}
