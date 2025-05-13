package com.OhtuProjekti.Popups;

import com.OhtuProjekti.Classes.Lasku;
import com.OhtuProjekti.DBManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LaskuPopup extends SuperPopup{
    private Lasku laskuOriginal;

    public void createPopup(Lasku laskuInput){
        laskuOriginal = laskuInput;

        super.createPopupSuper("Muokkaa laskua");

        GridPane grid = new GridPane();
        TextField sumField = new TextField(String.valueOf(laskuOriginal.summa));
        TextField erapaivaField = new TextField(laskuOriginal.erapaiva);
        TextField varausIDField  = new TextField(String.valueOf(laskuOriginal.varausID));
        TextField maksettuField = new TextField( String.valueOf(laskuOriginal.maksettu));


        grid.add(new Label("Summa:"), 0, 1);
        grid.add(sumField, 1, 1);
        grid.add(new Label("Eräpäivä:"), 0, 2);
        grid.add(erapaivaField, 1, 2);
        grid.add(new Label("VarausID:"), 0, 3);
        grid.add(varausIDField, 1, 3);
        grid.add(new Label("Maksettu:"), 0, 4);
        grid.add(maksettuField, 1, 4);

        this.centerPane.getChildren().add(grid);




        Button saveButton = new Button("Tallenna muutokset");
        saveButton.setOnAction(e -> {
            try {
                double summa = Double.parseDouble(sumField.getText());
                String erapaiva = erapaivaField.getText();
                int varausid = Integer.parseInt(varausIDField.getText());
                int maksettu = Integer.parseInt(maksettuField.getText());

                Lasku lasku = new Lasku(laskuOriginal.laskuID, summa, erapaiva, varausid, maksettu);
                //DBManager.updateAsiakas(asiakas);
            } catch (Exception _) {
            }
            this.closePopup();
        });

        bottomRow.getChildren().add(saveButton);


    }
}