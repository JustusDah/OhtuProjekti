package com.OhtuProjekti.Classes;

public class Asiakas {
    public int asiakasID;
    public String nimi;
    public String osoite;
    public String puhnro;
    public String sahkoposti;

    public Asiakas(int asiakasID, String nimi, String osoite, String puhnro, String sahkoposti){
        this.asiakasID = asiakasID;
        this.nimi = nimi;
        this.osoite = osoite;
        this.puhnro = puhnro;
        this.sahkoposti = sahkoposti;
    }

    public Asiakas(String nimi, String osoite, String puhnro, String sahkoposti) {
        this.nimi = nimi;
        this.osoite = osoite;
        this.puhnro = puhnro;
        this.sahkoposti = sahkoposti;
    }
}
