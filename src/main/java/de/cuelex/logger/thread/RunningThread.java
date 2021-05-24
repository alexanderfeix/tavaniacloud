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
    Date: 23.05.2021
    
*/
public class RunningThread implements Runnable{
    @Override
    public void run() {
        HomeCloud.getInstance().getCommandExecuter().implementCommands();
        while (HomeCloud.getInstance().getDatabaseHandler().isDatabaseConfigured()) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            boolean exists = HomeCloud.getInstance().getCommandExecuter().dispatchCommand(input);
            if (!exists)
                ConsoleLogger.getInstance().log(ConsoleLoggerType.ERROR, Launcher.class,"Command not found! Use 'help' for more informations.");
        }
    }
}
