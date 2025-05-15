package com.OhtuProjekti.Popups;

import com.OhtuProjekti.Classes.Lasku;
import com.OhtuProjekti.DBManager;
import com.OhtuProjekti.Utils;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Comparator;

public class KokonaistuloRaporttiPopup extends SuperPopup {

    public void createPopup(){
        super.createPopupSuper("Kokonaistulot kuukausittain");

        ArrayList<Lasku> laskutList = new ArrayList<>(DBManager.laskut.stream().toList());

        YearMonth currentYearMonth;
        YearMonth lastYearMonth;
        laskutList.sort(new LaskuErapaivaComparator());
        String firstErapaiva = laskutList.getFirst().erapaiva;
        String lastErapaiva = laskutList.getLast().erapaiva;
        currentYearMonth = YearMonth.parse(firstErapaiva.substring(0,firstErapaiva.length()-3));

        lastYearMonth = YearMonth.parse(lastErapaiva.substring(0,lastErapaiva.length()-3));

        VBox vBox = new VBox();

        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10, 10, 10, 10));



        while (currentYearMonth.isBefore(lastYearMonth.plusMonths(1))) {
            double tulot = 0;
            for (Lasku lasku : laskutList) {
                YearMonth laskuYearMonth = Utils.calculateYearMonthFromString(lasku.erapaiva);
                if (laskuYearMonth.equals(currentYearMonth)){
                    if (lasku.maksettu == 1) {
                        tulot = tulot + lasku.summa;
                    }
                }
            }

            vBox.getChildren().add(createReportRow(currentYearMonth, tulot));
            currentYearMonth = currentYearMonth.plusMonths(1);
        }
        centerPane.getChildren().add(vBox);




    }

    private Text createReportRow(YearMonth yearMonth, double tulot){
        Text reportRow = new Text(yearMonth.toString()
                + ": "
                + tulot + " €"
        );
        return reportRow;
    }




    public class LaskuErapaivaComparator implements Comparator<Lasku> {
        @Override
        public int compare(Lasku l1, Lasku l2) {
            return l1.erapaiva.compareTo(l2.erapaiva);
        }
    }

}
