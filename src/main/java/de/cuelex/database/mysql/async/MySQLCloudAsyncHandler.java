package de.cuelex.database.mysql.async;

import de.cuelex.main.TavaniaCloud;

import java.util.concurrent.CompletableFuture;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: TavaniaCloud/de.cuelex.database.mysql.async
    Date: 09.09.2021
    
*/
public class MySQLCloudAsyncHandler {

    public void createSQLTableAsync() {
        CompletableFuture.runAsync(() -> TavaniaCloud.getInstance().getMySQLCloudManager().createSQLTable());
    }

    public void createInstanceAsync(int id, String date, String message) {
        CompletableFuture.runAsync(() -> TavaniaCloud.getInstance().getMySQLCloudManager().createCloudInstance(id, date, message));
    }

    /**
     * Getting the key async
     *
     * @param key Must be the key in the database
     * @param id  client-id
     */
    public void getAsyncById(String key, int id) {
        switch (key) {
            case "date":
                CompletableFuture.runAsync(() -> TavaniaCloud.getInstance().getMySQLCloudManager().getDate(id));
                break;
            case "message":
                CompletableFuture.runAsync(() -> TavaniaCloud.getInstance().getMySQLCloudManager().getMessage(id));
            default:
                break;
        }
    }

    public void setAsyncById(String key, int id, String value) {
        if ("message".equals(key)) {
            CompletableFuture.runAsync(() -> TavaniaCloud.getInstance().getMySQLCloudManager().setMessage(id, value));
        }
    }

    /**
     * Getting all entries asynchronous
     */
    public void getAllAsync() {
        CompletableFuture.runAsync(() -> TavaniaCloud.getInstance().getMySQLCloudManager().getAll());
    }

    /**
     * Deleting an entry asynchronous
     *
     * @param id client-id
     */
    public void deleteAsyncById(int id) {
        CompletableFuture.runAsync(() -> TavaniaCloud.getInstance().getMySQLCloudManager().delete(id));
    }

}
