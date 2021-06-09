package de.cuelex.logger.thread;

import de.cuelex.launcher.Launcher;
import de.cuelex.logger.ConsoleLogger;
import de.cuelex.logger.LoggerType;
import de.cuelex.main.TavaniaCloud;

import java.util.Scanner;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: TavaniaCloud/de.cuelex.logger.thread
    Date: 23.05.2021
    
*/
public class RunningThread implements Runnable{
    @Override
    public void run() {
        TavaniaCloud.getInstance().getCommandExecuter().implementCommands();
        while (TavaniaCloud.getInstance().getDatabaseHandler().isDatabaseConfigured()) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            boolean exists = TavaniaCloud.getInstance().getCommandExecuter().dispatchCommand(input);
            if (!exists)
                ConsoleLogger.getInstance().log(LoggerType.ERROR, Launcher.class, "Command not found! Use 'help' for more informations.");
        }
    }
}
