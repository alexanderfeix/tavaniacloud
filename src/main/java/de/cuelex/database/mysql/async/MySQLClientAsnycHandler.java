package de.cuelex.database.mysql.async;

import de.cuelex.main.TavaniaCloud;

import java.util.concurrent.CompletableFuture;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: TavaniaCloud/de.cuelex.database.mysql.async
    Date: 22.06.2021
    
*/
public class MySQLClientAsnycHandler {

    public void createClientAsync(int clientId, String ipAddress, String username, String location, String connectionDate) {
        CompletableFuture.runAsync(() -> TavaniaCloud.getInstance().getMySQLClientManager().createClient(clientId, ipAddress, username, location, connectionDate));
    }

    /**
     * Getting the key async
     *
     * @param key      Must be the key in the database
     * @param clientId client-id
     */
    public void getAsyncById(String key, int clientId) {
        switch (key) {
            case "ipAddress":
                CompletableFuture.runAsync(() -> TavaniaCloud.getInstance().getMySQLClientManager().getIpAddress(clientId));
                break;
            case "username":
                CompletableFuture.runAsync(() -> TavaniaCloud.getInstance().getMySQLClientManager().getUsername(clientId));
            case "location":
                CompletableFuture.runAsync(() -> TavaniaCloud.getInstance().getMySQLClientManager().getLocation(clientId));
            case "connectionDate":
                CompletableFuture.runAsync(() -> TavaniaCloud.getInstance().getMySQLClientManager().getConnectionDate(clientId));
            default:
                break;
        }
    }

    /**
     * Getting all entries asynchronous
     */
    public void getAllAsync() {
        CompletableFuture.runAsync(() -> TavaniaCloud.getInstance().getMySQLClientManager().getAll());
    }

    /**
     * Deleting an entry asynchronous
     *
     * @param id client-id
     */
    public void deleteAsyncById(int id) {
        CompletableFuture.runAsync(() -> TavaniaCloud.getInstance().getMySQLClientManager().delete(id));
    }
}
