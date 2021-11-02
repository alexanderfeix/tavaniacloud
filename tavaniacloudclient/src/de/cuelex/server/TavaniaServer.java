/*
 * Copyright (c) 2021 to Alexander Feix. All rights reserved.
 */

package de.cuelex.server;

import java.util.ArrayList;
import java.util.HashMap;

public class TavaniaServer {

    public static HashMap<Integer, TavaniaServer> serverId = new HashMap<>();
    public static HashMap<String, TavaniaServer> serverIpAddress = new HashMap<>();

    public String serverName;
    public String ipAddress;
    public String connectionDate;
    public String location;
    public int id;

    public TavaniaServer(int id, String ipAddress, String serverName, String location, String connectionDate) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.serverName = serverName;
        this.location = location;
        this.connectionDate = connectionDate;
        serverId.put(id, this);
        serverIpAddress.put(ipAddress, this);
    }

    public int getId() {
        return id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getServerName() {
        return serverName;
    }

    public String getLocation() {
        return location;
    }

    public String getConnectionDate() {
        return connectionDate;
    }
}
