package de.cuelex.logger.thread;

import de.cuelex.logger.ConsoleLogger;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: TavaniaCloud/de.cuelex.logger.thread
    Date: 15.08.2021
    
*/
public class RestartThread implements Runnable {
    @Override
    public void run() {
        ConsoleLogger.getInstance().log(RestartThread.class, "Restarting application...");
        //Restartinh application

    }
}
