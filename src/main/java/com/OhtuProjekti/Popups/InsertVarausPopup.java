package com.OhtuProjekti.Popups;

import com.OhtuProjekti.Classes.Lasku;
import com.OhtuProjekti.Classes.Mokki;
import com.OhtuProjekti.Classes.Varaus;
import com.OhtuProjekti.DBManager;
import com.OhtuProjekti.Utils;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.sql.Date;

public class InsertVarausPopup extends SuperPopup{
    public void createPopup(){
        super.createPopupSuper("Lisää varaus");

        GridPane grid = new GridPane();
        TextField asiakasIdField = new TextField();
        TextField mokkiIdField = new TextField();
        TextField alkupaivaField = new TextField();
        TextField loppupaivaField = new TextField();

        grid.add(new Label("AsiakasId:"), 0, 1);
        grid.add(asiakasIdField, 1, 1);
        grid.add(new Label("MokkiId:"), 0, 2);
        grid.add(mokkiIdField, 1, 2);
        grid.add(new Label("Alkupaiva (yyyy-MM-dd):"), 0, 3);
        grid.add(alkupaivaField, 1, 3);
        grid.add(new Label("Loppupaiva (yyyy-MM-dd):"), 0, 4);
        grid.add(loppupaivaField, 1, 4);

        this.centerPane.getChildren().add(grid);



        Button insertButton = new Button("Tallenna varaus");
        insertButton.setOnAction(e -> {
            try {
                int asiakasId = Integer.parseInt(asiakasIdField.getText());
                int mokkiId = Integer.parseInt(mokkiIdField.getText());
                String alkupaiva = alkupaivaField.getText();
                String loppupaiva = loppupaivaField.getText();

                Varaus varaus = new Varaus( asiakasId, mokkiId, alkupaiva, loppupaiva);
                DBManager.insertVaraus(varaus);
                Lasku lasku = new Lasku()
            } catch (Exception _) {
            }
            this.closePopup();
        });

        bottomRow.getChildren().add(insertButton);


    }
}
