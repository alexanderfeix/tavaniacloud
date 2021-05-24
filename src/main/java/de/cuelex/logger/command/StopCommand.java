package de.cuelex.logger.command;

import de.cuelex.logger.ConsoleLogger;
import de.cuelex.logger.ConsoleLoggerType;
import de.cuelex.main.TavaniaCloud;

import java.util.HashMap;
import java.util.Scanner;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: TavaniaCloud/de.cuelex.logger.command
    Date: 10.12.2020
    
*/
public class StopCommand extends Command{
    private final HashMap<String, Boolean> confirmedStopping = new HashMap<>();

    public StopCommand(String name, String... alias) {
        super(name, alias);
    }

    @Override
    public void execute(ConsoleLogger logger, String name, String... args) {
        ConsoleLogger.getInstance().log(ConsoleLoggerType.WARNING, StopCommand.class, "Are you sure to stop TavaniaCloud?");
        ConsoleLogger.getInstance().log(ConsoleLoggerType.WARNING, StopCommand.class, "YES / NO");
        Scanner scanner = new Scanner(System.in);
        confirmedStopping.put("admin", false);
        String input = scanner.nextLine();
        if(input.equalsIgnoreCase("yes")){
            confirmedStopping.put("admin", true);
            ConsoleLogger.getInstance().log(ConsoleLoggerType.SUCCESS, StopCommand.class, "Confirmed stopping.");
            TavaniaCloud.getInstance().getLauncher().stop();
        }else{
            confirmedStopping.remove("admin");
            ConsoleLogger.getInstance().log(ConsoleLoggerType.SUCCESS, StopCommand.class, "Stopping canceled.");
        }

    }
}
