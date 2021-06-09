package de.cuelex.logger;

import de.cuelex.main.TavaniaCloud;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.CompletableFuture;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: TavaniaCloud/de.cuelex.logger
    Date: 09.06.2021
    
*/
public class SQLLogger {

    public static SQLLogger instance = new SQLLogger();

    public static SQLLogger getInstance() {
        return instance;
    }

    /**
     * Logging into the database
     *
     * @param loggerType the priority
     * @param message    custom message for the database
     */
    public void log(LoggerType loggerType, String message) {
        Date date = new Date();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setGregorianChange(date);
        if (TavaniaCloud.getInstance().getMySQLConnectionHandler().connection != null) {
            CompletableFuture.runAsync(() -> TavaniaCloud.getInstance().getMySQLHandler().getMySQLLogManager().createLog(TavaniaCloud.getInstance().getMySQLHandler().getMySQLLogManager().countAll() + 1, gregorianCalendar.getTime().toString(), loggerType + " | " + message));
        }
    }


}
