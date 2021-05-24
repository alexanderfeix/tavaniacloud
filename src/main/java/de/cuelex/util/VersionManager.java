package de.cuelex.util;

/*

    Copyright © 2019-2021 Alexander F.
    Twitter: @Taventiksch
    Location: TavaniaCloud/de.cuelex.util
    Date: 26.01.2021
    
*/
public class VersionManager {

    public String getCurrentVersion() {
        return "A-0.0.4";
    }
    private String latestVersion;
    public String getLatestVersion() {
        return latestVersion;
    }
    public void setLatestVersion(String latestVersion) {
        this.latestVersion = latestVersion;
    }
}
