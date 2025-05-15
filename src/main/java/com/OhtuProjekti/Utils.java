package com.OhtuProjekti;

import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Various constants and functions used globally
 */
public class Utils {

    /**
     * Color of the background
     */
    public static final String BACKGROUNDCOLOR =
            "-fx-background-color: #ffffff";
            //"-fx-background-color: #030303";

    /**
     * Window width
     */
    public static final double SCREEN_WIDTH = 1200;
    /**
     * Window height
     */
    public static final double SCREEN_HEIGHT = 800;

    public static final double POPUP_WIDTH = 600;
    public static final double POPUP_HEIGHT = 500;

    public static final Color TITLE_TEXT_COLOR = Color.DEEPPINK;

    public static double CalculateNightsAndPrice(int varausId, double hintaPerYo){
        String alotusPaivaStr = DBManager.varaukset.filtered(varaus -> varaus.varausID == varausId).getFirst().alkupaiva;
        String lopetusPaivaStr = DBManager.varaukset.filtered(varaus -> varaus.varausID == varausId).getFirst().loppupaiva;


        LocalDate alkuPaiva = LocalDate.parse(alotusPaivaStr);
        LocalDate loppuPaiva = LocalDate.parse(lopetusPaivaStr);

        long yot = ChronoUnit.DAYS.between(alkuPaiva, loppuPaiva);
        double tuotto = yot * hintaPerYo;

        System.out.println(tuotto);

        return tuotto;
    }

}
