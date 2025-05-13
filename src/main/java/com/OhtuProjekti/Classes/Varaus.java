package com.OhtuProjekti.Classes;

import java.util.Date;

public class Varaus {
    public int varausID;
    public int asiakasID;
    public int mokkiID;
    public Date alkupaiva;
    public Date loppupaiva;


    public Varaus(int varausID, int asiakasID, int mokkiID, Date alkupaiva, Date loppupaiva) {
        this.varausID = varausID;
        this.asiakasID = asiakasID;
        this.mokkiID = mokkiID;
        this.alkupaiva = alkupaiva;
        this.loppupaiva = loppupaiva;
    }

    public Varaus(int asiakasID, int mokkiID, Date alkupaiva, Date loppupaiva) {
        this.asiakasID = asiakasID;
        this.mokkiID = mokkiID;
        this.alkupaiva = alkupaiva;
        this.loppupaiva = loppupaiva;
    }

    public int varauksenKesto(){
        return 0;

    }

}
