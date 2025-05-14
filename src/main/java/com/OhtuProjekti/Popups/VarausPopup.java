package com.OhtuProjekti.Popups;

import com.OhtuProjekti.Classes.Varaus;
import com.OhtuProjekti.DBManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class VarausPopup extends SuperPopup{
    private Varaus varausOriginal;

    public void createPopup(Varaus varausInput){
        varausOriginal = varausInput;

        super.createPopupSuper("Muokkaa mökkiä");

        GridPane grid = new GridPane();
        TextField idField = new TextField(String.valueOf(varausOriginal.varausID));
        idField.setEditable(false);
        TextField asiakasIdField = new TextField(String.valueOf(varausOriginal.asiakasID));
        TextField mokkiIdField = new TextField(String.valueOf(varausOriginal.mokkiID));
        TextField alkupaivaField = new TextField(String.valueOf(varausOriginal.alkupaiva));
        TextField loppupaivaField = new TextField(String.valueOf(varausOriginal.loppupaiva));


        grid.add(new Label("Varaus ID:"), 0, 1);
        grid.add(idField, 1, 1);
        grid.add(new Label("Asiakas ID:"), 0, 2);
        grid.add(asiakasIdField, 1, 2);
        grid.add(new Label("Mökki ID:"), 0, 3);
        grid.add(mokkiIdField, 1, 3);
        grid.add(new Label("Alkupäivä:"), 0, 4);
        grid.add(alkupaivaField, 1, 4);
        grid.add(new Label("Loppupäivä:"), 0, 5);
        grid.add(loppupaivaField, 1, 5);

        this.centerPane.getChildren().add(grid);


        Button cancelButton = new Button("Peruuta muutokset");
        cancelButton.setOnAction(e -> {
            try {
                asiakasIdField.setText(String.valueOf(varausOriginal.asiakasID));
                mokkiIdField.setText(String.valueOf(varausOriginal.mokkiID));
                alkupaivaField.setText(varausOriginal.alkupaiva);
                loppupaivaField.setText(varausOriginal.loppupaiva);

            } catch (Exception _) {
            }
        });


        Button saveButton = new Button("Tallenna muutokset");
        saveButton.setOnAction(e -> {
            try {
                int asiakasID = Integer.parseInt(asiakasIdField.getText());
                int mokkiID = Integer.parseInt(mokkiIdField.getText());
                String alkupaiva = alkupaivaField.getText();
                String loppupaiva = loppupaivaField.getText();

                Varaus varaus = new Varaus(varausOriginal.varausID, asiakasID, mokkiID, alkupaiva, loppupaiva);
                DBManager.updateVaraus(varaus);
            } catch (Exception _) {
            }
            this.closePopup();
        });

        bottomRow.getChildren().addAll(cancelButton, saveButton);


    }
}