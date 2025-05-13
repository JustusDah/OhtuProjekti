package com.OhtuProjekti.Popups;

import com.OhtuProjekti.Classes.Asiakas;
import com.OhtuProjekti.Classes.Mokki;
import com.OhtuProjekti.DBManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class InsertAsiakasPopup extends SuperPopup{
    public void createPopup(){
        super.createPopupSuper("Lisää asiakas");

        GridPane grid = new GridPane();
        //TextField idField = new TextField();
        TextField nameField = new TextField();
        TextField addressField = new TextField();
        TextField sahkopostiField = new TextField();
        TextField puhnroField = new TextField();


        grid.add(new Label("Asiakas ID:"), 0, 0);
        //grid.add(idField, 1, 0);
        grid.add(new Label("Nimi:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Osoite:"), 0, 2);
        grid.add(addressField, 1, 2);
        grid.add(new Label("Sähköposti:"), 0, 3);
        grid.add(sahkopostiField, 1, 3);
        grid.add(new Label("Puhnro:"), 0, 4);
        grid.add(puhnroField, 1, 4);

        this.centerPane.getChildren().add(grid);



        Button insertButton = new Button("Tallenna asiakas");
        insertButton.setOnAction(e -> {
            try {
                //int id = Integer.parseInt(idField.getText());
                String nimi = nameField.getText();
                String osoite = addressField.getText();
                String sahkoposti = sahkopostiField.getText();
                String puhnro = puhnroField.getText();

                Asiakas asiakas = new Asiakas(nimi, osoite, sahkoposti, puhnro);
                DBManager.insertAsiakas(asiakas);
            } catch (Exception _) {
            }
            this.closePopup();
        });

        bottomRow.getChildren().add(insertButton);


    }
}
