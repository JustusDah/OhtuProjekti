package com.OhtuProjekti.Popups;

import com.OhtuProjekti.Classes.Mokki;
import com.OhtuProjekti.DBManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class InsertMokkiPopup extends SuperPopup{
    public void createPopup(){
        super.createPopupSuper("Lisää mökki");

        GridPane grid = new GridPane();
        //TextField idField = new TextField();
        TextField nameField = new TextField();
        TextField addressField = new TextField();
        TextField equipmentField = new TextField();
        TextField priceField = new TextField();
        TextField capacityField = new TextField();

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



        Button insertButton = new Button("Tallenna mökki");
        insertButton.setOnAction(e -> {
            try {
                String nimi = nameField.getText();
                String osoite = addressField.getText();
                String varustelu = equipmentField.getText();
                double hinta = Double.parseDouble(priceField.getText());
                int kapasiteetti = Integer.parseInt(capacityField.getText());

                Mokki mokki = new Mokki( nimi, osoite, varustelu, hinta, kapasiteetti);
                DBManager.insertMokki(mokki);
            } catch (Exception _) {
            }
            this.closePopup();
        });

        bottomRow.getChildren().add(insertButton);


    }
}
