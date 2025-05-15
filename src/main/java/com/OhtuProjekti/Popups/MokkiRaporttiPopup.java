package com.OhtuProjekti.Popups;

import com.OhtuProjekti.Classes.Lasku;
import com.OhtuProjekti.Classes.Mokki;
import com.OhtuProjekti.Classes.Varaus;
import com.OhtuProjekti.DBManager;
import com.OhtuProjekti.Utils;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.time.YearMonth;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.Comparator;

public class MokkiRaporttiPopup extends SuperPopup {
    Mokki mokki;


    public void createPopup(Mokki mokkiInput){
        this.mokki = mokkiInput;

        super.createPopupSuper("Raportti mökistä: " + mokki.nimi);

        ArrayList<Varaus> varausList = new ArrayList<>(DBManager.varaukset);


        varausList.sort(new VarausAlkupaivaComparator());
        String firstVarauspaiva = varausList.getFirst().alkupaiva;
        String lastVarauspaiva = varausList.getLast().loppupaiva;

        YearMonth currentYearMonth;
        YearMonth lastYearMonth;

        currentYearMonth = YearMonth.parse(firstVarauspaiva.substring(0,firstVarauspaiva.length()-3));
        lastYearMonth = YearMonth.parse(lastVarauspaiva.substring(0,lastVarauspaiva.length()-3));

        VBox vBox = new VBox();

        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10, 10, 10, 10));

        while (currentYearMonth.isBefore(lastYearMonth.plusMonths(1))) {
            int occupiedDays = 0;
            for (Varaus varaus : varausList) {
                if (varaus.mokkiID == mokki.mokkiID){
                    occupiedDays = occupiedDays + Utils.calculateNumberOfDays(currentYearMonth, varaus.alkupaiva, varaus.loppupaiva);
                }
            };
            double tulot = occupiedDays * mokki.hintaPerYo;
            double occupancy = (double) occupiedDays / currentYearMonth.lengthOfMonth();

            vBox.getChildren().add(createReportRow(currentYearMonth, tulot, occupancy, occupiedDays));
            currentYearMonth = currentYearMonth.plusMonths(1);
        }
        centerPane.getChildren().add(vBox);








    }

    private Text createReportRow(YearMonth yearMonth, double tulot, double occupancy, int occupiedDays){
        String occupancyString = String.format("%.2f", occupancy*100.0);
        Text reportRow = new Text(yearMonth.toString()
                + ": "
                + " varattuna: " + occupiedDays + " päivää,"
                + " varausprosentti: " + occupancyString + " %"
                + ", varausten tulot: " + tulot + " €"
        );
        return reportRow;
    }


    public class VarausAlkupaivaComparator implements Comparator<Varaus> {
        @Override
        public int compare(Varaus v1, Varaus v2) {
            return v1.alkupaiva.compareTo(v2.alkupaiva);
        }
    }




}
