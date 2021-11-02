package de.cuelex.database.mysql.async;

import de.cuelex.main.TavaniaCloud;

import java.util.concurrent.CompletableFuture;

public class MySQLBlacklistHandler {

    public void createSQLTableAsync() {
        CompletableFuture.runAsync(() -> TavaniaCloud.getInstance().getMySQLBlacklistManager().createSQLTable());
    }

    public void banClientAsync(int clientId, String clientName, String banDate, String banResason) {
        CompletableFuture.runAsync(() -> TavaniaCloud.getInstance().getMySQLBlacklistManager().registerBlacklistEntry(clientId, clientName, banDate, banResason));
    }

    /**
     * Getting the key async
     *
     * @param key      Must be the key in the database
     * @param clientId client-id
     */
    public void getAsyncById(String key, int clientId) {
        switch (key) {
            case "clientname":
                CompletableFuture.runAsync(() -> TavaniaCloud.getInstance().getMySQLBlacklistManager().getClientName(clientId));
                break;
            case "bandate":
                CompletableFuture.runAsync(() -> TavaniaCloud.getInstance().getMySQLBlacklistManager().getBanDate(clientId));
            case "banreason":
                CompletableFuture.runAsync(() -> TavaniaCloud.getInstance().getMySQLBlacklistManager().getBanReason(clientId));
            default:
                break;
        }
    }

    /**
     * Getting all entries asynchronous
     */
    public void getAllAsync() {
        CompletableFuture.runAsync(() -> TavaniaCloud.getInstance().getMySQLBlacklistManager().getAll());
    }

    /**
     * Deleting an entry asynchronous
     *
     * @param id client-id
     */
    public void deleteAsyncById(int id, String reason) {
        CompletableFuture.runAsync(() -> TavaniaCloud.getInstance().getMySQLBlacklistManager().unbanClient(id, reason));
    }

}
