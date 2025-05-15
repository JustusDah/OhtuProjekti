package com.OhtuProjekti;

import com.OhtuProjekti.Classes.Asiakas;
import com.OhtuProjekti.Classes.Lasku;
import com.OhtuProjekti.Classes.Mokki;
import com.OhtuProjekti.Classes.Varaus;

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

    public static void generateExampleData(){
        DBManager.insertAsiakas(new Asiakas("Keijo Keijonen", "Keijontie 5", "0401234567", "keijo@gmail.com"));
        DBManager.insertAsiakas(new Asiakas("Maija Malli", "Mallikatu 3", "0507654321", "maija@example.com"));
        DBManager.insertAsiakas(new Asiakas("Teppo Testaaja", "Testikatu 9", "0441122334", "teppo@testi.fi"));

        DBManager.insertMokki(new Mokki(1, "Metsämökki", "Metsäpolku 1", "Sähkö, Vesi, Sauna", 120.0, 4));
        DBManager.insertMokki(new Mokki(2, "Järvimökki", "Järventie 2", "Sähkö, Vesi, Vene", 150.0, 6));
        DBManager.insertMokki(new Mokki(3, "Tunturimökki", "Tunturikuja 3", "Sähkö, Takka", 100.0, 3));

        DBManager.insertVaraus(new Varaus(1, 1, 1, "2025-06-01", "2025-06-05"));
        DBManager.insertVaraus(new Varaus(2, 2, 2, "2025-07-10", "2025-07-15"));
        DBManager.insertVaraus(new Varaus(3, 3, 3, "2025-08-20", "2025-08-25"));

        DBManager.insertLasku(new Lasku(1, 480.0, "2025-06-10", 1, 0));
        DBManager.insertLasku(new Lasku(2, 750.0, "2025-07-20", 2, 1));
        DBManager.insertLasku(new Lasku(3, 500.0, "2025-08-30", 3, 0));

        System.out.println("Example data generated");
    }
}

