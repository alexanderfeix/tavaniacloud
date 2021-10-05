package de.cuelex.database.mysql;

import de.cuelex.main.TavaniaCloud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQLBlacklistManager {

    /**
     * Creating MySQL-Table
     */
    public void createSQLTable() {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("CREATE TABLE IF NOT EXISTS Blacklist (ClientId INT, ClientName VARCHAR(100), BanDate VARCHAR(100), BanReason VARCHAR(100))");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Check if a Client is already banned/on the blacklist
     *
     * @param id the Client name
     * @return true or false (banned or not)
     */
    public boolean isClientBanned(int id) {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("SELECT * FROM Blacklist WHERE ClientId = ?");
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
     * Creating a new Cloud-Instance
     *
     * @param clientId id by the client is always the same
     * @param clientName clientname
     * @param banDate GregorianDate when the client was banned
     * @param banReason BanReasen why the client is banned for
     */
    public void registerBlacklistEntry(int clientId, String clientName, String banDate, String banReason) {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("INSERT INTO Blacklist (ClientId, ClientName, BanDate, BanReason) VALUES (?, ?, ?, ?)");
            ps.setInt(1, clientId);
            ps.setString(2, clientName);
            ps.setString(3, banDate);
            ps.setString(4, banReason);
            ps.execute();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Get the Client-Name by using the id
     *
     * @param clientId Client-id
     * @return Client-Name
     */
    public String getClientName(int clientId) {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("SELECT * FROM Blacklist WHERE ClientId = ?");
            ps.setInt(1, clientId);
            ResultSet result = ps.executeQuery();
            result.next();
            String output = result.getString("ClientName");
            result.close();
            ps.close();
            return output;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * Get the Ban-Date by using the id
     *
     * @param clientId Client-id
     * @return Client-Name
     */
    public String getBanDate(int clientId) {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("SELECT * FROM Blacklist WHERE ClientId = ?");
            ps.setInt(1, clientId);
            ResultSet result = ps.executeQuery();
            result.next();
            String output = result.getString("BanDate");
            result.close();
            ps.close();
            return output;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * Get the Ban-Reason by using the id
     *
     * @param clientId Client-id
     * @return Client-Name
     */
    public String getBanReason(int clientId) {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("SELECT * FROM Blacklist WHERE ClientId = ?");
            ps.setInt(1, clientId);
            ResultSet result = ps.executeQuery();
            result.next();
            String output = result.getString("BanReason");
            result.close();
            ps.close();
            return output;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * Deleting a entry in database by using the id/Unban a client/Removing a client from the blacklist
     *
     * @param id Client-id
     */
    public void unbanClient(int id) {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("DELETE FROM Blacklist WHERE ClientId = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Counting all entries/Counting all banned clients
     *
     * @return Integer
     */
    public int countAll() {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("SELECT COUNT(ClientId) FROM Blacklist");
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
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Blacklist");
            while (resultSet.next()) {
                members.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

}
