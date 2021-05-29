package de.cuelex.user.client;

import de.cuelex.logger.ConsoleLogger;
import de.cuelex.logger.ConsoleLoggerType;

/*

   Copyright © 2019 Alexander F.
   Twitter: @Taventiksch
   Location: TavaniaCloud/de.cuelex.user.clients
   Date: 29.05.2021

*/
public class TavaniaClientHandler {

    public void registerClient(int id, String ipAddress, String username, String location, String connectionDate) {
        TavaniaClient tavaniaClient = new TavaniaClient(id, ipAddress, username, location, connectionDate);
        TavaniaClient.clients.add(tavaniaClient);
        TavaniaClient.clientId.put(id, tavaniaClient);
        ConsoleLogger.getInstance().log(ConsoleLoggerType.SUCCESS, TavaniaClientHandler.class, username + "successfully registered.");
    }

    public void removeClient(int id) {
        TavaniaClient tavaniaClient = null;
        try {
            tavaniaClient = TavaniaClient.clientId.get(id);
        } catch (Exception exception) {
            ConsoleLogger.getInstance().log(ConsoleLoggerType.ERROR, TavaniaClientHandler.class, "User with ID: " + id + " is not registered.");
            return;
        }
        TavaniaClient.clients.remove(tavaniaClient);
        TavaniaClient.clientId.remove(id);
        ConsoleLogger.getInstance().log(ConsoleLoggerType.SUCCESS, TavaniaClientHandler.class, "User with ID: " + id + " successfully removed.");
    }

    public void listClients() {
        if (TavaniaClient.clients == null) {
            ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, TavaniaClientHandler.class, "No clients are currently connected.");
            return;
        }
        ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, TavaniaClientHandler.class, "Connected clients:");
        for (int i = 0; i < TavaniaClient.clients.size(); i++) {
            TavaniaClient tavaniaClient = TavaniaClient.clients.get(i);
            ConsoleLogger.getInstance().log(ConsoleLoggerType.INFORMATION, TavaniaClientHandler.class, tavaniaClient.getId() + " | " + tavaniaClient.getIpAddress() + " | " + tavaniaClient.getUsername() + " | " + tavaniaClient.getLocation() + " | " + tavaniaClient.getConnectionDate());
        }
    }
}
