package com.OhtuProjekti.Popups;

import com.OhtuProjekti.Classes.Mokki;
import com.OhtuProjekti.Classes.Varaus;
import com.OhtuProjekti.DBManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.sql.Date;

public class InsertVarausPopup extends SuperPopup{
    public void createPopup(){
        super.createPopupSuper("Lisää mökki");

        GridPane grid = new GridPane();
        //TextField idField = new TextField();
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



        Button insertButton = new Button("Tallenna mökki");
        insertButton.setOnAction(e -> {
            try {
                int asiakasId = Integer.valueOf(asiakasIdField.getText());
                int mokkiId = Integer.valueOf(mokkiIdField.getText());
                String alkupaiva = alkupaivaField.getText();
                String loppupaiva = loppupaivaField.getText();

                Varaus varaus = new Varaus( asiakasId, mokkiId, Date.valueOf(alkupaiva), Date.valueOf(loppupaiva));
                DBManager.insertVaraus(varaus);
            } catch (Exception _) {
            }
            this.closePopup();
        });

        bottomRow.getChildren().add(insertButton);


    }
}
