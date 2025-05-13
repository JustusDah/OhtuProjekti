package com.OhtuProjekti;


import com.OhtuProjekti.Classes.Asiakas;
import com.OhtuProjekti.Classes.Lasku;
import com.OhtuProjekti.Classes.Mokki;
import com.OhtuProjekti.Classes.Varaus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private static final String DB_URL = "jdbc:sqlite:mokki_db.sqlite";

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

    public static void  insertAsiakas(Asiakas a){
        String sql = "INSERT INTO Asiakas (Nimi, Osoite, Puhelin, Sahkoposti) VALUES (?, ?, ?, ?)";

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
        return list;
    }

    public static void insertMokki(Mokki m) {
        String sql = "INSERT INTO Mokki ( Nimi, Osoite, Varustelu, HintaPerYo, Kapasiteetti) VALUES (?, ?, ?, ?, ?)";

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
        return list;
    }

    public static void insertVaraus(Varaus v) {
        String sql = "INSERT INTO Varaus (VarausID, AsiakasID, MokkiID) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, v.varausID);
            pstmt.setInt(2, v.asiakasID);
            pstmt.setInt(3, v.mokkiID);

            pstmt.executeUpdate();
            System.out.println("Varaus inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Error inserting Varaus: " + e.getMessage());
        }
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
                        rs.getDate("Alkupaiva"),
                        rs.getDate("Loppupaiva")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching Varaukset: " + e.getMessage());
        }
        return list;
    }

    public static void insertLasku(Lasku l) {
        String sql = "INSERT INTO Lasku (Summa, Erapaiva, VarausID, Maksettu) VALUES (?, ?, ?, ?)";

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
        } catch (SQLException e) {
            System.out.println("Error updating Mökki: " + e.getMessage());
        }
    }
}
