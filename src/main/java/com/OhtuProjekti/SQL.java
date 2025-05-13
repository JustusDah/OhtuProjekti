package com.OhtuProjekti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQL {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:mokki_db.sqlite";

        String[] statements = {

                "CREATE TABLE Asiakas (" +
                        "AsiakasID INTEGER PRIMARY KEY," +
                        "Nimi TEXT," +
                        "Osoite TEXT," +
                        "Puhelin TEXT," +
                        "Sahkoposti TEXT" +
                        ");",

                "CREATE TABLE Mokki (" +
                        "MokkiID INTEGER PRIMARY KEY," +
                        "Nimi TEXT," +
                        "Osoite TEXT," +
                        "Varustelu TEXT," +
                        "HintaPerYo REAL," +
                        "Kapasiteetti INTEGER" +
                        ");",

                "CREATE TABLE Varaus (" +
                        "VarausID INTEGER PRIMARY KEY," +
                        "AsiakasID INTEGER," +
                        "MokkiID INTEGER," +
                        "Alkupaiva TEXT," +
                        "Loppupaiva TEXT," +
                        "FOREIGN KEY (AsiakasID) REFERENCES Asiakas(AsiakasID)," +
                        "FOREIGN KEY (MokkiID) REFERENCES Mokki(MokkiID)" +
                        ");",


                "CREATE TABLE Lasku (" +
                        "LaskuID INTEGER PRIMARY KEY," +
                        "Summa REAL," +
                        "Erapaiva TEXT," +
                        "VarausID INTEGER," +
                        "Maksettu INTEGER," +
                        "FOREIGN KEY (VarausID) REFERENCES Varaus(VarausID)" +
                        ");"
        };

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            for (String sql : statements) {
                stmt.execute(sql);
            }

            System.out.println("Database schema created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

