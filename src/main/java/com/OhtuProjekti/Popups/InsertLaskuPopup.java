package com.OhtuProjekti.Popups;

import com.OhtuProjekti.Classes.Lasku;
import com.OhtuProjekti.DBManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class InsertLaskuPopup extends SuperPopup{
    public void createPopup(){
        super.createPopupSuper("Lisää lasku");

        GridPane grid = new GridPane();
        TextField idField = new TextField();
        TextField erapaivaField = new TextField();
        TextField varausIdField = new TextField();
        TextField maksettuField = new TextField();

        grid.add(new Label("Summa:"), 0, 1);
        grid.add(erapaivaField, 1, 1);
        grid.add(new Label("Eräpäivä:"), 0, 2);
        grid.add(varausIdField, 1, 2);
        grid.add(new Label("varausID:"), 0, 3);
        grid.add(maksettuField, 1, 3);
        grid.add(new Label("maksettu:"), 0, 4);

        this.centerPane.getChildren().add(grid);



        Button insertButton = new Button("Tallenna lasku");
        insertButton.setOnAction(e -> {
            try {
                double summa = Double.parseDouble(idField.getText());
                String erapaiva = erapaivaField.getText();
                int varausID = Integer.parseInt(varausIdField.getText());
                int maksettu = Integer.parseInt(maksettuField.getText());

                Lasku lasku = new Lasku(summa, erapaiva, varausID, maksettu);
                DBManager.insertLasku(lasku);
            } catch (Exception _) {
            }
            this.closePopup();
        });

        bottomRow.getChildren().add(insertButton);


    }
}
