package de.cuelex.logger.command;

import de.cuelex.logger.ConsoleLogger;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: TavaniaCloud/de.cuelex.logger.command
    Date: 19.11.2020
    
*/
public abstract class Command {

    private final String name;
    private final String[] alias;

    public Command(String name, String... alias) {
        this.name = name;
        this.alias = alias;
    }
    public abstract void execute(final ConsoleLogger logger, final String name, final String... args);
    public String getName() {
        return name;
    }
    public String[] getAlias() {
        return alias;
    }
}
