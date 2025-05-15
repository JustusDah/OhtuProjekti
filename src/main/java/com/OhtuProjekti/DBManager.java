package com.OhtuProjekti;


import com.OhtuProjekti.Classes.Asiakas;
import com.OhtuProjekti.Classes.Lasku;
import com.OhtuProjekti.Classes.Mokki;
import com.OhtuProjekti.Classes.Varaus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.sqlite.core.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    public static ObservableList<Asiakas> asiakkaat = FXCollections.observableArrayList();
    public static ObservableList<Lasku> laskut = FXCollections.observableArrayList();
    public static ObservableList<Mokki> mokkis = FXCollections.observableArrayList();
    public static ObservableList<Varaus> varaukset = FXCollections.observableArrayList();

    private static final String DB_URL = "jdbc:sqlite:mokki_db.sqlite";
    public static ObservableList<Object> asiakass;

    public static List<String[]> executeQuery(String sql) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            List<String[]> results = new ArrayList<>();
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            String[] columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = meta.getColumnName(i);
            }
            results.add(columnNames);

            while (rs.next()) {
                String[] row = new String[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getString(i);
                }
                results.add(row);
            }

            return results;
        }
    }

    public static int executeUpdate(String sql) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            return stmt.executeUpdate(sql);
        }
    }

    public static void getAllData() {
        getAllMokit();
        getAllAsiakkaat();
        getAllLaskut();
        getAllVaraukset();
    }

    public static void  insertAsiakas(Asiakas a){
        String sql = "INSERT INTO Asiakas (Nimi, Osoite, Puhelin, Sahkoposti) VALUES (?, ?, ?, ?)";

        System.out.println("Inserting Asiakas");
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, a.nimi);
            pstmt.setString(2, a.osoite);
            pstmt.setString(3, a.puhnro);
            pstmt.setString(4, a.sahkoposti);

            pstmt.executeUpdate();
            System.out.println("Asiakas inserted succesfully.");
        } catch (SQLException e){
            System.out.println("Error inserting Asiakas: " + e.getMessage());
        }
        DBManager.getAllAsiakkaat();
    }

    public static List<Asiakas> getAllAsiakkaat() {
        List<Asiakas> list = new ArrayList<>();
        String sql = "SELECT * FROM Asiakas";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Asiakas(
                        rs.getInt("AsiakasID"),
                        rs.getString("Nimi"),
                        rs.getString("Osoite"),
                        rs.getString("Puhelin"),
                        rs.getString("Sahkoposti")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching Asiakkaat: " + e.getMessage());
        }
        asiakkaat.clear();
        asiakkaat.addAll(list);

        return list;
    }

    public static void updateAsiakas(Asiakas a) {
        String sql = "UPDATE Asiakas SET Nimi = ?, Osoite = ?, Sahkoposti = ?, Puhelin = ? WHERE AsiakasID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, a.nimi);
            pstmt.setString(2, a.osoite);
            pstmt.setString(3, a.sahkoposti);
            pstmt.setString(4, a.puhnro);
            pstmt.setInt(5, a.asiakasID);

            pstmt.executeUpdate();
            System.out.println("Asiakas updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating Asiakas: " + e.getMessage());
        }
        DBManager.getAllAsiakkaat();
    }

    public static void insertMokki(Mokki m) {
        String sql = "INSERT INTO Mokki ( Nimi, Osoite, Varustelu, HintaPerYo, Kapasiteetti) VALUES (?, ?, ?, ?, ?)";

        System.out.println("Inserting Mokki");
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            //pstmt.setInt(1, m.mokkiID);
            pstmt.setString(1, m.nimi);
            pstmt.setString(2, m.osoite);
            pstmt.setString(3, m.varustelu);
            pstmt.setDouble(4, m.hintaPerYo);
            pstmt.setInt(5, m.kapasiteetti);

            pstmt.executeUpdate();
            System.out.println("Mökki inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Error inserting Mökki: " + e.getMessage());
        }
        DBManager.getAllMokit();
    }

    public static List<Mokki> getAllMokit() {
        List<Mokki> list = new ArrayList<>();
        String sql = "SELECT * FROM Mokki";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Mokki(
                        rs.getInt("MokkiID"),
                        rs.getString("Nimi"),
                        rs.getString("Osoite"),
                        rs.getString("Varustelu"),
                        rs.getDouble("HintaPerYo"),
                        rs.getInt("Kapasiteetti")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching Mökit: " + e.getMessage());
        }
        mokkis.clear();
        mokkis.addAll(list);
        return list;
    }

    public static void updateMokki(Mokki m) {
        String sql = "UPDATE Mokki SET Nimi = ?, Osoite = ?, Varustelu = ?, HintaPerYo = ?, Kapasiteetti = ? WHERE MokkiID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, m.nimi);
            pstmt.setString(2, m.osoite);
            pstmt.setString(3, m.varustelu);
            pstmt.setDouble(4, m.hintaPerYo);
            pstmt.setInt(5, m.kapasiteetti);
            pstmt.setInt(6, m.mokkiID);

            pstmt.executeUpdate();
            System.out.println("Mökki updated successfully.");
            DBManager.getAllMokit();
        } catch (SQLException e) {
            System.out.println("Error updating Mökki: " + e.getMessage());
        }
        DBManager.getAllMokit();
    }

    public static void insertVaraus(Varaus v) {
        String sql = "INSERT INTO Varaus (AsiakasID, MokkiID, alkupaiva, loppupaiva) VALUES (?, ?, ?, ?)";

        System.out.println("Inserting Varaus");
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, v.asiakasID);
            pstmt.setInt(2, v.mokkiID);
            pstmt.setString(3, v.alkupaiva);
            pstmt.setString(4, v.loppupaiva);

            pstmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println("Error inserting Varaus: " + e.getMessage());
        }
        DBManager.getAllVaraukset();
    }

    public static List<Varaus> getAllVaraukset() {
        List<Varaus> list = new ArrayList<>();
        String sql = "SELECT * FROM Varaus";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Varaus(
                        rs.getInt("VarausID"),
                        rs.getInt("AsiakasID"),
                        rs.getInt("MokkiID"),
                        rs.getString("Alkupaiva"),
                        rs.getString("Loppupaiva")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching Varaukset: " + e.getMessage());
        }
        varaukset.clear();
        varaukset.addAll(list);
        return list;
    }

    public static void updateVaraus(Varaus v) {
        String sql = "UPDATE Varaus SET AsiakasID = ?, MokkiID = ?, Alkupaiva = ?, Loppupaiva = ? WHERE VarausID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, v.asiakasID);
            pstmt.setInt(2, v.mokkiID);
            pstmt.setString(3, v.alkupaiva.toString());
            pstmt.setString(4, v.loppupaiva.toString());
            pstmt.setInt(5, v.varausID);

            pstmt.executeUpdate();
            System.out.println("Varaus updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating Varaus: " + e.getMessage());
        }
        DBManager.getAllVaraukset();
    }

    public static void insertLasku(Lasku l) {
        String sql = "INSERT INTO Lasku (Summa, Erapaiva, VarausID, Maksettu) VALUES (?, ?, ?, ?)";

        System.out.println("Inserting Lasku");
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, l.summa);
            pstmt.setString(2, l.erapaiva);
            pstmt.setInt(3, l.varausID);
            pstmt.setInt(4, l.maksettu);

            pstmt.executeUpdate();
            System.out.println("Lasku inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Error inserting Lasku: " + e.getMessage());
        }
        DBManager.getAllLaskut();
    }

    public static List<Lasku> getAllLaskut() {
        List<Lasku> list = new ArrayList<>();
        String sql = "SELECT * FROM Lasku";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Lasku(
                        rs.getInt("LaskuID"),
                        rs.getDouble("Summa"),
                        rs.getString("Erapaiva"),
                        rs.getInt("VarausID"),
                        rs.getInt("Maksettu")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching Laskut: " + e.getMessage());
        }
        laskut.clear();
        laskut.addAll(list);
        return list;
    }

    public static void updateLasku(Lasku l) {
        String sql = "UPDATE Lasku SET Summa = ?, Erapaiva = ?, VarausID = ?, Maksettu = ? WHERE LaskuID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, l.summa);
            pstmt.setString(2, l.erapaiva);
            pstmt.setInt(3, l.varausID);
            pstmt.setInt(4, l.maksettu);
            pstmt.setInt(5, l.laskuID);

            pstmt.executeUpdate();
            System.out.println("Lasku updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating Lasku: " + e.getMessage());
        }
        DBManager.getAllLaskut();
    }

    public static void deleteAsiakas(int asiakasID) {
        String sql = "DELETE FROM Asiakas WHERE AsiakasID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, asiakasID);
            pstmt.executeUpdate();
            System.out.println("Asiakas deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting Asiakas: " + e.getMessage());
        }
        getAllAsiakkaat();
    }

    public static void deleteMokki(int mokkiID) {
        String sql = "DELETE FROM Mokki WHERE MokkiID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, mokkiID);
            pstmt.executeUpdate();
            System.out.println("Mökki deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting Mökki: " + e.getMessage());
        }
        getAllMokit();
    }

    public static void deleteVaraus(int varausID) {
        String sql = "DELETE FROM Varaus WHERE VarausID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, varausID);
            pstmt.executeUpdate();
            System.out.println("Varaus deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting Varaus: " + e.getMessage());
        }
        getAllVaraukset();
    }

    public static void deleteLasku(int laskuID) {
        String sql = "DELETE FROM Lasku WHERE LaskuID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, laskuID);
            pstmt.executeUpdate();
            System.out.println("Lasku deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting Lasku: " + e.getMessage());
        }
        getAllLaskut();
    }




}
