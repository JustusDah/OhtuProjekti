package com.OhtuProjekti.Popups;

import com.OhtuProjekti.Classes.Lasku;
import com.OhtuProjekti.DBManager;
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
        laskutList.sort(new LaskuErapaivaComparator());
        String firstErapaiva = laskutList.getFirst().erapaiva;
        currentYearMonth = YearMonth.parse(firstErapaiva.substring(0,firstErapaiva.length()-3));
        VBox vBox = new VBox();

        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10, 10, 10, 10));

        System.out.println(currentYearMonth);

        while (currentYearMonth.isBefore(YearMonth.now())) {
            double tulot =

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
        System.out.println(yearMonth);
        return reportRow;
    }




    public class LaskuErapaivaComparator implements Comparator<Lasku> {
        @Override
        public int compare(Lasku l1, Lasku l2) {
            return l1.erapaiva.compareTo(l2.erapaiva);
        }
    }

}
