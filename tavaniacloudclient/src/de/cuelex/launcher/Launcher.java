/*
 * Copyright (c) 2021 to Alexander Feix. All rights reserved.
 */

package de.cuelex.launcher;

import de.cuelex.logger.FileLogger;
import de.cuelex.logger.LoggerType;

public class Launcher {

    public void start(){
        FileLogger.getInstance().log(LoggerType.INFORMATION, Launcher.class, "Starting application...");
    }

    public void stop(){

    }

}
