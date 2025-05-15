package com.OhtuProjekti.Popups;

import com.OhtuProjekti.Classes.Asiakas;
import com.OhtuProjekti.Classes.Mokki;
import com.OhtuProjekti.Classes.Varaus;
import com.OhtuProjekti.DBManager;
import com.OhtuProjekti.Utils;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.NoSuchElementException;

public class VarausPopup extends SuperPopup{
    private Varaus varausOriginal;

    private boolean confirmDelete = false;

    public void createPopup(Varaus varausInput){
        varausOriginal = varausInput;

        super.createPopupSuper("Muokkaa varausta");

        GridPane grid = new GridPane();
        TextField idField = new TextField(String.valueOf(varausOriginal.varausID));
        idField.setEditable(false);
        TextField asiakasIdField = new TextField(String.valueOf(varausOriginal.asiakasID));
        TextField mokkiIdField = new TextField(String.valueOf(varausOriginal.mokkiID));
        TextField alkupaivaField = new TextField(String.valueOf(varausOriginal.alkupaiva));
        TextField loppupaivaField = new TextField(String.valueOf(varausOriginal.loppupaiva));

        Button naytaAsiakasButton = new Button("Näytä asiakas");
        naytaAsiakasButton.setOnAction(e -> {
            try {
                AsiakasPopup popup = new AsiakasPopup();
                Asiakas asiakasOfVaraus = DBManager.asiakkaat.filtered(
                        asiakas -> asiakas.asiakasID == varausOriginal.asiakasID
                ).getFirst();
                popup.createPopup(asiakasOfVaraus);
                popup.showPopup();
            } catch (NoSuchElementException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Virhe");
                alert.setHeaderText("Asiakasta ei löytynyt");
                alert.setContentText("Varausta vastaavaa asiakasta ei löytynyt tietokannasta.");
                alert.showAndWait();
            }
        });

        Button naytaMokkiButton = new Button("Näytä mökki");
        naytaMokkiButton.setOnAction(e -> {
            try {
                MokkiPopup popup = new MokkiPopup();
                Mokki mokkiOfVaraus = DBManager.mokkis.filtered(
                        mokki -> mokki.mokkiID == varausOriginal.mokkiID
                ).getFirst();
                popup.createPopup(mokkiOfVaraus);
                popup.showPopup();
            } catch (NoSuchElementException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Virhe");
                alert.setHeaderText("Mökkiä ei löytynyt");
                alert.setContentText("Varausta vastaavaa mökkiä ei löytynyt tietokannasta.");
                alert.showAndWait();
            }
        });


        grid.add(new Label("Varaus ID:"), 0, 1);
        grid.add(idField, 1, 1);
        grid.add(new Label("Asiakas ID:"), 0, 2);
        grid.add(asiakasIdField, 1, 2);
        grid.add(naytaAsiakasButton, 2, 2);
        grid.add(new Label("Mökki ID:"), 0, 3);
        grid.add(mokkiIdField, 1, 3);
        grid.add(naytaMokkiButton, 2, 3);
        grid.add(new Label("Alkupäivä:"), 0, 4);
        grid.add(alkupaivaField, 1, 4);
        grid.add(new Label("Loppupäivä:"), 0, 5);
        grid.add(loppupaivaField, 1, 5);

        this.centerPane.getChildren().add(grid);

        Button deleteButton = new Button("Poista");
        deleteButton.setOnAction(e -> {
            if (!confirmDelete){
                confirmDelete = true;
                deleteButton.setText("Vahvista");
            }
            else {
                try {
                    DBManager.deleteVaraus(varausOriginal.varausID);
                    this.closePopup();
                }
                catch (Exception exception) {
                    System.out.println("Error deleting: " + exception.getMessage());
                }
            }
        });



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
            } catch (Exception exception) {
                System.out.println("Error saving: " + exception.getMessage());
            }
            this.closePopup();


        });


        bottomRow.getChildren().addAll(deleteButton, cancelButton, saveButton);


    }
}