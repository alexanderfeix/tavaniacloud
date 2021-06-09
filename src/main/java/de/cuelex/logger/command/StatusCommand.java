package de.cuelex.logger.command;

import de.cuelex.launcher.Launcher;
import de.cuelex.logger.ConsoleLogger;
import de.cuelex.logger.LoggerType;
import de.cuelex.main.TavaniaCloud;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: TavaniaCloud/de.cuelex.logger.command
    Date: 19.11.2020
    
*/
public class StatusCommand extends Command{
    public StatusCommand(String name, String... alias) {
        super(name, alias);
    }

    @Override
    public void execute(ConsoleLogger logger, String name, String... args) {
        ConsoleLogger.getInstance().log(LoggerType.INFORMATION, StatusCommand.class, "Current status of TavaniaCloud:");
        ConsoleLogger.getInstance().log(LoggerType.INFORMATION, StatusCommand.class, "VERSION: " + TavaniaCloud.getInstance().getVersionManager().getCurrentVersion());
        ConsoleLogger.getInstance().log(LoggerType.INFORMATION, StatusCommand.class, "STATUS: " + Launcher.getInstance().isCloudRunning());
        ConsoleLogger.getInstance().log(LoggerType.INFORMATION, StatusCommand.class, "Starting-Date: " + TavaniaCloud.getInstance().getLauncher().getStartingDate());
    }
}
