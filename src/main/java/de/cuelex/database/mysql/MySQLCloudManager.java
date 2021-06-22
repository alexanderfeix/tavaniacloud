package de.cuelex.database.mysql;

import de.cuelex.main.TavaniaCloud;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: TavaniaCloud/de.cuelex.database.mysql
    Date: 09.06.2021
    
*/
public class MySQLCloudManager {

    /**
     * Creating MySQL-Table
     */
    public void createSQLTable() {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("CREATE TABLE IF NOT EXISTS TavaniaCloud (Id INT, StartingDate VARCHAR(100), Message VARCHAR(100))");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
