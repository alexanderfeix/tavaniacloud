package de.cuelex.database.mysql;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: TavaniaCloud/de.cuelex.database.mysql
    Date: 23.05.2021
    
*/
public class MySQLHandler {

    public MySQLLogManager mySQLLogManager = new MySQLLogManager();
    public MySQLCloudManager mySQLCloudManager = new MySQLCloudManager();

    public MySQLLogManager getMySQLLogManager() {
        return mySQLLogManager;
    }

    public MySQLCloudManager getMySQLCloudManager() {
        return mySQLCloudManager;
    }

    public void createDatabases() {
        this.mySQLLogManager.createSQLTable();
        //this.mySQLCloudManager.createSQLTable();
    }
}
