package de.cuelex.logger.command;

import de.cuelex.logger.ConsoleLogger;
import de.cuelex.logger.ConsoleLoggerType;
import de.cuelex.main.TavaniaCloud;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: TavaniaCloud/de.cuelex.logger.command
    Date: 10.12.2020
    
*/
public class VersionCommand extends Command{

    public VersionCommand(String name, String... alias) {
        super(name, alias);
    }

    @Override
    public void execute(ConsoleLogger logger, String name, String... args) {
        if(args.length == 0){
            ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, VersionCommand.class, "Current version: " + TavaniaCloud.getInstance().getVersionManager().getCurrentVersion());
        }
    }
}
