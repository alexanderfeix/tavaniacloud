package de.cuelex.database.mysql;

import de.cuelex.main.TavaniaCloud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

    /**
     * Creating a new Cloud-Instance
     *
     * @param id      Log-id
     * @param date    Log-date
     * @param message Log-message
     */
    public void createCloudInstance(int id, String date, String message) {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("INSERT INTO TavaniaCloud (Id, StartingDate, Message) VALUES (?, ?, ?)");
            ps.setInt(1, id);
            ps.setString(2, date);
            ps.setString(3, message);
            ps.execute();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Get the Starting-Date by using the id
     *
     * @param id Log-id
     * @return Log-name
     */
    public String getDate(int id) {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("SELECT * FROM TavaniaCloud WHERE Id = ?");
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            result.next();
            String output = result.getString("StartingDate");
            result.close();
            ps.close();
            return output;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * Get the Cloud-Message by using the id
     *
     * @param id Log-id
     * @return Log-name
     */
    public String getMessage(int id) {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("SELECT * FROM TavaniaCloud WHERE Id = ?");
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            result.next();
            String output = result.getString("Message");
            result.close();
            ps.close();
            return output;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * Setting the Cloud-Message
     *
     * @param id      Log-id
     * @param message Log-message
     */
    public void setMessage(int id, String message) {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("UPDATE TavaniaCloud SET Message = ? WHERE Id = ?");
            ps.setString(1, message);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Deleting a entry in database by using the id
     *
     * @param id Log-id
     */
    public void delete(int id) {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("DELETE FROM TavaniaCloud WHERE Id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Counting all entries
     *
     * @return Integer
     */
    public int countAll() {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("SELECT COUNT(Id) FROM TavaniaCloud");
            ResultSet resultSet = ps.executeQuery();
            int output = 0;
            while (resultSet.next()) {
                output = resultSet.getInt(1);
            }
            resultSet.close();
            ps.close();
            return output;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * Getting all datas from a database-table
     *
     * @return ArrayList
     */
    public ArrayList<String> getAll() {
        ArrayList<String> members = new ArrayList<>();
        try {
            Statement statement = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM TavaniaCloud");
            while (resultSet.next()) {
                members.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }


}
