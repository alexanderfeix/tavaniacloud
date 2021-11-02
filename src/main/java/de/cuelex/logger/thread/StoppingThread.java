package de.cuelex.logger.thread;

import de.cuelex.launcher.Launcher;
import de.cuelex.logger.ConsoleLogger;
import de.cuelex.logger.LoggerType;

import java.util.HashMap;
import java.util.Scanner;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: TavaniaCloud/de.cuelex.logger.thread
    Date: 19.11.2020
    
*/
public class StoppingThread implements Runnable{


    boolean exitMethod = false;
    @Override
    public void run() {
        /*
        while(!exitMethod) {
            ConsoleLogger.getInstance().log(LoggerType.INFORMATION, StoppingThread.class, "Are you sure to stop TavaniaCloud? <yes> to confirm.");
            Scanner scanner = new Scanner(System.in);
            String next = scanner.next();
            if(next.equalsIgnoreCase("yes")){
                ConsoleLogger.getInstance().log(LoggerType.INFORMATION, Launcher.class, "Stopping TavaniaCloud...");
                System.exit(0);
                ConsoleLogger.getInstance().log(LoggerType.SUCCESS, Launcher.class, "Stopped TavaniaCloud successfully!");
            }else if (next.equalsIgnoreCase("no")){
                exitMethod = true;
            }
        }
         */
    }
}
