package com.OhtuProjekti.Classes;

public class Mokki {
    public int mokkiID;
    public String nimi;
    public String osoite;
    public String varustelu;
    public double hintaPerYo;
    public int kapasiteetti;

    public Mokki(int mokkiID, String nimi, String osoite, String varustelu, double hintaPerYo, int kapasiteetti){
        this.mokkiID = mokkiID;
        this.nimi = nimi;
        this.osoite = osoite;
        this.varustelu = varustelu;
        this.hintaPerYo = hintaPerYo;
        this.kapasiteetti = kapasiteetti;
    }

    public Mokki(String nimi, String osoite, String varustelu, double hintaPerYo, int kapasiteetti) {
        this.nimi = nimi;
        this.osoite = osoite;
        this.varustelu = varustelu;
        this.hintaPerYo = hintaPerYo;
        this.kapasiteetti = kapasiteetti;
    }
}
