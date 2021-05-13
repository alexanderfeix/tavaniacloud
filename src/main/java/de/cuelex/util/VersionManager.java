package de.cuelex.util;

/*

    Copyright © 2019-2021 Alexander F.
    Twitter: @Taventiksch
    Location: HomeCloud/de.cuelex.util
    Date: 26.01.2021
    
*/
public class VersionManager {

    private String currentVersion;
    public String getCurrentVersion() {
        return currentVersion;
    }
    private String latestVersion;
    public String getLatestVersion() {
        return latestVersion;
    }
    public void setLatestVersion(String latestVersion) {
        this.latestVersion = latestVersion;
    }
}
