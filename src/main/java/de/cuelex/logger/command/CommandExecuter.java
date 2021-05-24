package de.cuelex.logger.command;

import de.cuelex.logger.ConsoleLogger;

import java.util.Arrays;
import java.util.HashMap;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: TavaniaCloud/de.cuelex.logger.command
    Date: 19.11.2020
    
*/
public class CommandExecuter {

    private final HashMap<String, Command> commandHashMap = new HashMap<String, Command>();

    public void register(final Command command) {
        commandHashMap.put(command.getName(), command);
        for (String alias : command.getAlias()) {
            commandHashMap.put(alias, command);
        }
    }

    public void unregister(final Command command) {
        commandHashMap.remove(command.getName(), command);
        for (String alias : command.getAlias()) {
            commandHashMap.remove(alias, command);
        }
    }

    public boolean dispatchCommand(final String commandLine) {
        final String[] split = commandLine.split(" ", -1);
        final String[] args = Arrays.copyOfRange(split, 1, split.length);
        final String commandName = split[0];
        final Command command = commandHashMap.get(commandName);
        if (command == null) return false;
        command.execute(ConsoleLogger.getInstance(), commandName, args);
        return true;
    }

    public void implementCommands() {
        register(new StatusCommand("status"));
        register(new VersionCommand("version"));
        register(new StopCommand("stop"));
        register(new RestartCommand("restart"));
    }

    public HashMap<String, Command> getCommandHashMap() {
        return commandHashMap;
    }

}
