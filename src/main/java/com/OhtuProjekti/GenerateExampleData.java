package com.OhtuProjekti;

import com.OhtuProjekti.Classes.Asiakas;
import com.OhtuProjekti.Classes.Lasku;
import com.OhtuProjekti.Classes.Mokki;
import com.OhtuProjekti.Classes.Varaus;

public class GenerateExampleData {
    public static void main(String[] args) {
        generateExampleData();
    }

    /**
     * Used to populate the database with example data.
     */
    public static void generateExampleData(){
        DBManager.insertAsiakas(new Asiakas("Keijo Keijonen", "Keijontie 5", "0401234567", "keijo@gmail.com"));
        DBManager.insertAsiakas(new Asiakas("Maija Malli", "Mallikatu 3", "0507654321", "maija@example.com"));
        DBManager.insertAsiakas(new Asiakas("Teppo Testaaja", "Testikatu 9", "0441122334", "teppo@testi.fi"));
        DBManager.insertAsiakas(new Asiakas("Asko Asiakas", "Asiakkaantie 1", "0400123123", "asko@asiakas.fi"));

        DBManager.insertMokki(new Mokki("Metsämökki", "Metsäpolku 1", "Sähkö, Vesi, Sauna", 120.0, 4));
        DBManager.insertMokki(new Mokki( "Järvimökki", "Järventie 2", "Sähkö, Vesi, Vene", 150.0, 6));
        DBManager.insertMokki(new Mokki( "Tunturimökki", "Tunturikuja 3", "Sähkö, Takka", 100.0, 3));

        DBManager.insertVarausWithLaskuTest(new Varaus(1, 1, "2025-05-12", "2025-05-18"));
        DBManager.insertVarausWithLaskuTest(new Varaus(2, 2, "2025-03-10", "2025-03-15"));
        DBManager.insertVarausWithLaskuTest(new Varaus(3, 3, "2025-05-20", "2025-05-25"));
        DBManager.insertVarausWithLaskuTest(new Varaus(4, 1, "2025-04-20", "2025-04-23"));
        DBManager.insertVarausWithLaskuTest(new Varaus(2, 1, "2025-04-12", "2025-04-13"));
        DBManager.insertVarausWithLaskuTest(new Varaus(2, 1, "2025-05-22", "2025-05-26"));
        DBManager.insertVarausWithLaskuTest(new Varaus(2, 2, "2025-02-21", "2025-02-23"));
        DBManager.insertVarausWithLaskuTest(new Varaus(1, 3, "2025-03-10", "2025-03-23"));


        DBManager.insertLasku(new Lasku( 100.0, "2025-05-30", 1, 0));
        DBManager.insertLasku(new Lasku(55.0, "2025-03-30", 2, 1));
        DBManager.insertLasku(new Lasku( 60.0, "2025-05-02", 4, 1));
        DBManager.insertLasku(new Lasku(500.0, "2025-05-25", 6, 0));

        System.out.println("Example data generated");
    }
}
