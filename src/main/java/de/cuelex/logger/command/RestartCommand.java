package de.cuelex.logger.command;

import de.cuelex.logger.ConsoleLogger;
import de.cuelex.logger.LoggerType;
import de.cuelex.main.TavaniaCloud;

import java.util.HashMap;
import java.util.Scanner;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: TavaniaCloud/de.cuelex.logger.command
    Date: 10.12.2020
    
*/
public class RestartCommand extends Command{
    private final HashMap<String, Boolean> confirmedRestarting = new HashMap<>();
    public RestartCommand(String name, String... alias) {
        super(name, alias);
    }

    @Override
    public void execute(ConsoleLogger logger, String name, String... args) {
        ConsoleLogger.getInstance().log(LoggerType.WARNING, StopCommand.class, "Are you sure to restart TavaniaCloud?");
        ConsoleLogger.getInstance().log(LoggerType.WARNING, StopCommand.class, "YES / NO");
        Scanner scanner = new Scanner(System.in);
        confirmedRestarting.put("admin", false);
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("yes")) {
            confirmedRestarting.put("admin", true);
            ConsoleLogger.getInstance().log(LoggerType.SUCCESS, StopCommand.class, "Restarting TavaniaCloud.");
            TavaniaCloud.getInstance().getLauncher().restart();
        } else {
            confirmedRestarting.remove("admin");
            ConsoleLogger.getInstance().log(LoggerType.SUCCESS, StopCommand.class, "Restarting canceled.");
        }
    }
}
