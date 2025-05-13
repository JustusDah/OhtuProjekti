package com.OhtuProjekti.Classes;

import java.time.LocalDate;
import java.util.Date;

public class Varaus {
    public int varausID;
    public int asiakasID;
    public int mokkiID;
    public String alkupaiva;
    public String loppupaiva;


    public Varaus(int varausID, int asiakasID, int mokkiID, String alkupaiva, String loppupaiva) {
        this.varausID = varausID;
        this.asiakasID = asiakasID;
        this.mokkiID = mokkiID;
        this.alkupaiva = alkupaiva;
        this.loppupaiva = loppupaiva;
    }

    public Varaus(int asiakasID, int mokkiID, String alkupaiva, String loppupaiva) {
        this.asiakasID = asiakasID;
        this.mokkiID = mokkiID;
        this.alkupaiva = alkupaiva;
        this.loppupaiva = loppupaiva;
    }

    public int varauksenKesto(){
        return 0;

    }

}
