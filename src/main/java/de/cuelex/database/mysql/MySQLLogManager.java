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
public class MySQLLogManager {

    /**
     * Creating MySQL-Table
     */
    public void createSQLTable() {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("CREATE TABLE IF NOT EXISTS Logs (LogId INT, LogDate VARCHAR(100), Message VARCHAR(100))");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Check if a Log is already existing
     *
     * @param id the Log name
     * @return true or false
     */
    public boolean isLogExisting(int id) {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("SELECT * FROM Logs WHERE LogId = ?");
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            boolean isExisting = result.next();
            result.close();
            ps.close();
            return isExisting;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Creating a new Log
     *
     * @param id      Log-id
     * @param date    Log-date
     * @param message Log-message
     */
    public void createLog(int id, String date, String message) {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("INSERT INTO Logs (LogId, LogName, Message) VALUES (?, ?, ?)");
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
     * Get the Log-name by using the id
     *
     * @param id Log-id
     * @return Log-name
     */
    public String getDate(int id) {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("SELECT * FROM Logs WHERE LogId = ?");
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            result.next();
            String output = result.getString("LogDate");
            result.close();
            ps.close();
            return output;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * Get the Log-name by using the id
     *
     * @param id Log-id
     * @return Log-message
     */
    public String getMessage(int id) {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("SELECT * FROM Logs WHERE LogId = ?");
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
     * Getting the message by using the date
     *
     * @param date Log-date
     * @return Log-message
     */
    public int getMessage(String date) {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("SELECT * FROM Logs WHERE LogDate = ?");
            ps.setString(1, date);
            ResultSet result = ps.executeQuery();
            result.next();
            int output = result.getInt("Message");
            result.close();
            ps.close();
            return output;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * Setting the Log-name
     *
     * @param id      Log-id
     * @param message Log-message
     */
    public void setMessage(int id, String message) {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("UPDATE Logs SET Message = ? WHERE LogId = ?");
            ps.setString(1, message);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Setting the Log-date
     *
     * @param id   Log-id
     * @param date Log-date
     */
    public void setDate(int id, String date) {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("UPDATE Logs SET LogDate = ? WHERE LogId = ?");
            ps.setString(1, date);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Deleting a entry in a database by using the name
     *
     * @param name Log-name
     */
    public void delete(String name) {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("DELETE FROM Logs WHERE LogName = ?");
            ps.setString(1, name);
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
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("DELETE FROM Logs WHERE LogId = ?");
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
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("SELECT COUNT(LogId) FROM Logs");
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
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Logs");
            while (resultSet.next()) {
                members.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }


}
