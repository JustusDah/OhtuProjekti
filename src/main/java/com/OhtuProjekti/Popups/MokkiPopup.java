package com.OhtuProjekti.Popups;

import com.OhtuProjekti.Classes.Mokki;
import com.OhtuProjekti.DBManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class MokkiPopup extends SuperPopup{
    private Mokki mokkiOriginal;

    public void createPopup(Mokki mokkiInput){
        mokkiOriginal = mokkiInput;

        super.createPopupSuper("Muokkaa mökkiä");

        GridPane grid = new GridPane();
        TextField idField = new TextField(String.valueOf(mokkiOriginal.mokkiID));
        idField.setEditable(false);
        TextField nameField = new TextField(mokkiOriginal.nimi);
        TextField addressField = new TextField(mokkiOriginal.osoite);
        TextField equipmentField = new TextField(mokkiOriginal.varustelu);
        TextField priceField = new TextField( String.valueOf(mokkiOriginal.hintaPerYo));
        TextField capacityField = new TextField(String.valueOf(mokkiOriginal.kapasiteetti));


        grid.add(new Label("Mökki ID:"), 0, 0);
        grid.add(idField, 1, 0);
        grid.add(new Label("Nimi:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Osoite:"), 0, 2);
        grid.add(addressField, 1, 2);
        grid.add(new Label("Varustelu:"), 0, 3);
        grid.add(equipmentField, 1, 3);
        grid.add(new Label("Hinta per yö:"), 0, 4);
        grid.add(priceField, 1, 4);
        grid.add(new Label("Kapasiteetti:"), 0, 5);
        grid.add(capacityField, 1, 5);

        this.centerPane.getChildren().add(grid);

        Button cancelButton = new Button("Peruuta muutokset");
        cancelButton.setOnAction(e -> {
            try {
                nameField.setText(mokkiOriginal.nimi);
                addressField.setText(mokkiOriginal.osoite);
                equipmentField.setText(mokkiOriginal.varustelu);
                capacityField.setText(String.valueOf(mokkiOriginal.kapasiteetti));
                priceField.setText(String.valueOf(mokkiOriginal.hintaPerYo));
            } catch (Exception _) {
            }
        });



        Button saveButton = new Button("Tallenna muutokset");
        saveButton.setOnAction(e -> {
            try {
                String nimi = nameField.getText();
                String osoite = addressField.getText();
                String varustelu = equipmentField.getText();
                double hinta = Double.parseDouble(priceField.getText());
                int kapasiteetti = Integer.parseInt(capacityField.getText());

                Mokki mokki = new Mokki(mokkiOriginal.mokkiID, nimi, osoite, varustelu, hinta, kapasiteetti);
                DBManager.updateMokki(mokki);
            } catch (Exception _) {
            }
            this.closePopup();
        });

        bottomRow.getChildren().addAll(cancelButton, saveButton);


    }
}