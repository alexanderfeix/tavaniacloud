package de.cuelex.logger;

import java.util.Date;
import java.util.GregorianCalendar;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: HomeCloud/de.cuelex.logger
    Date: 19.11.2020
    
*/
public class ConsoleLogger {

    public static ConsoleLogger instance = new ConsoleLogger();
    public static ConsoleLogger getInstance(){
        return instance;
    }

    public void log(ConsoleLoggerType consoleLoggerType, Class currentclass, String logmessage){
        Date date = new Date();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setGregorianChange(date);
        System.out.println("» | [" + gregorianCalendar.getTime() + "] | " + consoleLoggerType + " | " + currentclass + " | » " + logmessage);
    }

    public void log(Class currentclass, String logmessage){
        Date date = new Date();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setGregorianChange(date);
        System.out.println("» | [" + gregorianCalendar.getTime() + "] | UNKNOWN • " + currentclass + " | » " + logmessage);
    }



}
