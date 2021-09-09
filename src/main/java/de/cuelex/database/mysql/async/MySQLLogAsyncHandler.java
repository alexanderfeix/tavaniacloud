package de.cuelex.database.mysql.async;

import de.cuelex.main.TavaniaCloud;

import java.util.concurrent.CompletableFuture;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: TavaniaCloud/de.cuelex.database.mysql.async
    Date: 09.09.2021
    
*/
public class MySQLLogAsyncHandler {

    public void createSQLTableAsync() {
        CompletableFuture.runAsync(() -> TavaniaCloud.getInstance().getMySQLLogManager().createSQLTable());
    }

    public void createLogAsync(int logId, String date, String message) {
        CompletableFuture.runAsync(() -> TavaniaCloud.getInstance().getMySQLLogManager().createLog(logId, date, message));
    }

    /**
     * Getting the key async
     *
     * @param key   Must be the key in the database
     * @param logId client-id
     */
    public void getAsyncById(String key, int logId) {
        switch (key) {
            case "date":
                CompletableFuture.runAsync(() -> TavaniaCloud.getInstance().getMySQLLogManager().getDate(logId));
                break;
            case "message":
                CompletableFuture.runAsync(() -> TavaniaCloud.getInstance().getMySQLLogManager().getMessage(logId));
            default:
                break;
        }
    }

    /**
     * Getting all entries asynchronous
     */
    public void getAllAsync() {
        CompletableFuture.runAsync(() -> TavaniaCloud.getInstance().getMySQLLogManager().getAll());
    }

    /**
     * Deleting an entry asynchronous
     *
     * @param id client-id
     */
    public void deleteAsyncById(int id) {
        CompletableFuture.runAsync(() -> TavaniaCloud.getInstance().getMySQLLogManager().delete(id));
    }

}
