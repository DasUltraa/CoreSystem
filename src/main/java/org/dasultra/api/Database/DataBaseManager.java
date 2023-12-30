package org.dasultra.api.Database;

import org.dasultra.api.ServerAPI;

import java.sql.*;
import java.util.UUID;

import static org.dasultra.listener.ScoreboardListener.updateScoreBoard;

public class DataBaseManager {

    private final static String host = ServerAPI.getHost();
    private final static String port = ServerAPI.getPort();
    private final static String database = ServerAPI.getDatabase();
    private final static String username = ServerAPI.getUsername();
    private final static String password = ServerAPI.getPassword();

    private static Connection connection;

    public static boolean isConnected() {
        return connection != null;
    }

    public static void connect() throws SQLException {
        if (!isConnected()) {
            connection = DriverManager.getConnection("jdbc:mysql://"
                    + host + ":" + port + "/" + database + "?autoReconnect=true" +
                    "&characterEncoding=utf8&useUnicode=true" +
                    "&sessionVariables=storage_engine%3DInnoDB" +
                    "&interactiveClient=true&dontTrackOpenResources=true", username, password);
            createCoinTable();
        }
    }

    public static void disconnect() throws SQLException {
        if (isConnected()) {
            connection.close();
        }
    }

    public static void createCoinTable() {
        try {

            Statement statement = connection.createStatement();

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS economy (UUID VARCHAR(200) NOT NULL , MONEY INT NOT NULL, BANK INT NOT NULL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean existMoneyPlayer(UUID uuid) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM economy WHERE UUID=?");
            statement.setString(1, uuid.toString());
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static void createMoneyPlayer(UUID uuid) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM economy WHERE UUID=?");
            statement.setString(1, uuid.toString());
            ResultSet result = statement.executeQuery();
            result.next();
            if (!existMoneyPlayer(uuid)) {
                PreparedStatement insert = connection
                        .prepareStatement("INSERT INTO economy (UUID,MONEY,BANK) VALUES (?,?,?)");
                insert.setString(1, uuid.toString());
                insert.setInt(2, 1000);
                insert.setInt(3, 0);
                insert.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateCoins(UUID uuid, double value) {

        double parse = Double.parseDouble(ServerAPI.renderValueForSave(value).replace(",", "."));
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE economy SET MONEY=? WHERE UUID=?");
            statement.setDouble(1, parse);
            statement.setString(2, uuid.toString());
            statement.executeUpdate();
            updateScoreBoard();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static double getMoney(UUID uuid) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM economy WHERE UUID=?");
            statement.setString(1, uuid.toString());
            ResultSet result = statement.executeQuery();
            result.next();
            return result.getDouble("MONEY");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

}
