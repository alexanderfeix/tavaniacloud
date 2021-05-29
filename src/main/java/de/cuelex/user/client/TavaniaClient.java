package de.cuelex.user.client;

import java.util.ArrayList;
import java.util.HashMap;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: TavaniaCloud/de.cuelex.user.clients
    Date: 29.05.2021
    
*/
public class TavaniaClient {

    public static ArrayList<TavaniaClient> clients = new ArrayList<>();
    public static HashMap<Integer, TavaniaClient> clientId = new HashMap<>();
    public int id;
    public String ipAddress;
    public String username;
    public String location;
    public String connectionDate;

    public TavaniaClient(int id, String ipAddress, String username, String location, String connectionDate) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.username = username;
        this.location = location;
        this.connectionDate = connectionDate;
    }

    public static ArrayList<TavaniaClient> getClients() {
        return clients;
    }

    public int getId() {
        return id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getUsername() {
        return username;
    }

    public String getLocation() {
        return location;
    }

    public String getConnectionDate() {
        return connectionDate;
    }
}
