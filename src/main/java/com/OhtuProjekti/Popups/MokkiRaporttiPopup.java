package com.OhtuProjekti.Popups;

import com.OhtuProjekti.Classes.Mokki;

public class MokkiRaporttiPopup extends SuperPopup {
    Mokki mokki;


    public void createPopup(Mokki mokkiInput){
        this.mokki = mokkiInput;
        super.createPopupSuper("Raportti mökistä: " + mokki.nimi);




    }





}
