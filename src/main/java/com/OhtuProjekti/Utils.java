package com.OhtuProjekti;

import com.OhtuProjekti.Classes.Varaus;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.time.YearMonth;
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

    public static double calculateNightsAndPrice(int varausId){
        Varaus varaus = DBManager.varaukset.filtered(varauss -> varauss.varausID == varausId).getFirst();
        String alotusPaivaStr = varaus.alkupaiva;
        String lopetusPaivaStr = varaus.loppupaiva;

        LocalDate alkuPaiva = LocalDate.parse(alotusPaivaStr);
        LocalDate loppuPaiva = LocalDate.parse(lopetusPaivaStr);

        double hintaPerYo = DBManager.mokkis.filtered(mokki -> mokki.mokkiID == varaus.mokkiID).getFirst().hintaPerYo;

        long yot = ChronoUnit.DAYS.between(alkuPaiva, loppuPaiva);
        double tuotto = yot * hintaPerYo;

        System.out.println(tuotto);

        return tuotto;
    }

    public static double calculatePriceFromDates(String alkuPaiva, String loppuPaiva, int mokkiId){
        LocalDate alkPaiva = LocalDate.parse(alkuPaiva);
        LocalDate loppPaiva = LocalDate.parse(loppuPaiva);

        double hintaPerYo = DBManager.mokkis.filtered(mokki -> mokki.mokkiID == mokkiId).getFirst().hintaPerYo;

        long yot = ChronoUnit.DAYS.between(alkPaiva, loppPaiva);
        double tuotto = yot * hintaPerYo;

        return tuotto;
    }

    public static YearMonth calculateYearMonthFromString(String input){
        LocalDate localDate = LocalDate.parse(input);
        YearMonth yearMonth = YearMonth.of(localDate.getYear(), localDate.getMonthValue());
        return yearMonth;
    }

    public static int calculateNumberOfDays(YearMonth yearMonth, String alkupaiva, String loppupaiva) {
        int output = 0;
        LocalDate alkupaivaLocalDate = LocalDate.parse(alkupaiva);
        LocalDate loppupaivaLocalDate = LocalDate.parse(loppupaiva);

        while (alkupaivaLocalDate.isBefore(loppupaivaLocalDate)){
            if (YearMonth.from(alkupaivaLocalDate).equals(yearMonth)) {
                output++;
            }
            alkupaivaLocalDate = alkupaivaLocalDate.plusDays(1);
        }
        return output;


    }

}
