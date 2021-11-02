/*
 * Copyright (c) 2021 to Alexander Feix. All rights reserved.
 */

package de.cuelex.main;

import de.cuelex.launcher.Launcher;

import java.util.Date;
import java.util.GregorianCalendar;

public class TavaniaClient {

    public static TavaniaClient instance = new TavaniaClient();

    public static TavaniaClient getInstance() {
        return instance;
    }
    public Launcher launcher = new Launcher();

    public Launcher getLauncher() {
        return launcher;
    }

    public static void main(String[] args) {
        getInstance().getLauncher().start();
    }
    public Date getGregorianDate() {
        Date date = new Date();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setGregorianChange(date);
        return gregorianCalendar.getTime();
    }

}
