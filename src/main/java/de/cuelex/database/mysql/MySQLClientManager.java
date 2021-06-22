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
    Date: 22.06.2021
    
*/
public class MySQLClientManager {

    /**
     * Creating MySQL-Table
     */
    public void createSQLTable() {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("CREATE TABLE IF NOT EXISTS Clients (ClientId INT, IpAddress VARCHAR(100), Username VARCHAR(100), Location VARCHAR(100), ConnectionDate VARCHAR(100))");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Check if a Client is already existing
     *
     * @param id the Client name
     * @return true or false
     */
    public boolean isClientExisting(int id) {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("SELECT * FROM Clients WHERE ClientId = ?");
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
     * Creating a new Client
     *
     * @param id             Client-id
     * @param ipAddress      Client-IP
     * @param username       Client-username
     * @param location       Client-location
     * @param connectionDate Client-connectionDate
     */
    public void createClient(int id, String ipAddress, String username, String location, String connectionDate) {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("INSERT INTO Clients (ClientId, IpAddress, Username, Location, ConnectionDate) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, id);
            ps.setString(2, ipAddress);
            ps.setString(3, username);
            ps.setString(3, location);
            ps.setString(3, connectionDate);
            ps.execute();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Get the Client-IpAddress by using the id
     *
     * @param id Client-id
     * @return Client-IpAddress
     */
    public String getIpAddress(int id) {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("SELECT * FROM Clients WHERE ClientId = ?");
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            result.next();
            String output = result.getString("IpAddress");
            result.close();
            ps.close();
            return output;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * Get the Client-Username by using the id
     *
     * @param id Client-id
     * @return Client-Username
     */
    public String getUsername(int id) {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("SELECT * FROM Clients WHERE ClientId = ?");
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            result.next();
            String output = result.getString("Username");
            result.close();
            ps.close();
            return output;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * Get the Client-Location by using the id
     *
     * @param id Client-id
     * @return Client-Location
     */
    public String getLocation(int id) {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("SELECT * FROM Clients WHERE ClientId = ?");
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            result.next();
            String output = result.getString("Location");
            result.close();
            ps.close();
            return output;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * Get the Client-ConnectionDate by using the id
     *
     * @param id Client-id
     * @return Client-Location
     */
    public String getConnectionDate(int id) {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("SELECT * FROM Clients WHERE ClientId = ?");
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            result.next();
            String output = result.getString("ConnectionDate");
            result.close();
            ps.close();
            return output;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * Deleting a entry in a database by using the name
     *
     * @param name Client-Username
     */
    public void delete(String name) {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("DELETE FROM Clients WHERE Username = ?");
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
     * @param id Client-id
     */
    public void delete(int id) {
        try {
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("DELETE FROM Clients WHERE ClientId = ?");
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
            PreparedStatement ps = TavaniaCloud.getInstance().getMySQLConnectionHandler().connection.prepareStatement("SELECT COUNT(ClientId) FROM Clients");
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
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Clients");
            while (resultSet.next()) {
                members.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }
}
