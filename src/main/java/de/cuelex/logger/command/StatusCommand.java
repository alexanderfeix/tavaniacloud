package de.cuelex.logger.command;

import de.cuelex.launcher.Launcher;
import de.cuelex.logger.ConsoleLogger;
import de.cuelex.logger.ConsoleLoggerType;
import de.cuelex.main.HomeCloud;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: HomeCloud/de.cuelex.logger.command
    Date: 19.11.2020
    
*/
public class StatusCommand extends Command{
    public StatusCommand(String name, String... alias) {
        super(name, alias);
    }

    @Override
    public void execute(ConsoleLogger logger, String name, String... args) {
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, StatusCommand.class, "Current status of HomeCloud:");
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, StatusCommand.class, "VERSION: " + Launcher.getInstance().getVersion());
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, StatusCommand.class, "STATUS: " + Launcher.getInstance().isCloudRunning());
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, StatusCommand.class, "Starting-Date: " + HomeCloud.getInstance().getLauncher().getStartingDate());
    }
}
