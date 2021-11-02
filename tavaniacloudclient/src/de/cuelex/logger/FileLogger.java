package de.cuelex.logger;

import org.simpleyaml.configuration.file.YamlFile;
import org.simpleyaml.exceptions.InvalidConfigurationException;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

public class FileLogger {

    public static FileLogger instance = new FileLogger();
    public final YamlFile loggerFile = new YamlFile("loggerfile.yml");
    public static FileLogger getInstance() {
        return instance;
    }

    public void log(LoggerType loggerType, Class currentclass, String logmessage) {
        Date date = new Date();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setGregorianChange(date);
        try {
            loggerFile.load();
            loggerFile.set(gregorianCalendar.getTime().toString(),  loggerType + " | " + logmessage);
            loggerFile.save();
        } catch (InvalidConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }

}
