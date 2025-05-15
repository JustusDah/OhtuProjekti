package com.OhtuProjekti.Popups;

import com.OhtuProjekti.Classes.Asiakas;
import com.OhtuProjekti.DBManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AsiakasPopup extends SuperPopup{
    private Asiakas asiakasOriginal;


    private boolean confirmDelete = false;

    public void createPopup(Asiakas asiakasInput){
        asiakasOriginal = asiakasInput;

        super.createPopupSuper("Muokkaa asiakasta");

        GridPane grid = new GridPane();
        TextField idField = new TextField(String.valueOf(asiakasOriginal.asiakasID));
        idField.setEditable(false);
        TextField nameField = new TextField(asiakasOriginal.nimi);
        TextField addressField = new TextField(asiakasOriginal.osoite);
        TextField phoneField  = new TextField(asiakasOriginal.puhnro);
        TextField emailField = new TextField( String.valueOf(asiakasOriginal.sahkoposti));


        grid.add(new Label("Asiakas ID:"), 0, 0);
        grid.add(idField, 1, 0);
        grid.add(new Label("Nimi:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Osoite:"), 0, 2);
        grid.add(addressField, 1, 2);
        grid.add(new Label("Puhelinnumero:"), 0, 3);
        grid.add(phoneField, 1, 3);
        grid.add(new Label("Sähköposti:"), 0, 4);
        grid.add(emailField, 1, 4);

        this.centerPane.getChildren().add(grid);


        Button deleteButton = new Button("Poista");
        deleteButton.setOnAction(e -> {
            if (!confirmDelete){
                confirmDelete = true;
                deleteButton.setText("Oikeasti poista?");
            }
            else {
                try {
                    DBManager.deleteAsiakas(asiakasOriginal.asiakasID);
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
                nameField.setText(String.valueOf(asiakasOriginal.nimi));
                addressField.setText(String.valueOf(asiakasOriginal.osoite));
                phoneField.setText(String.valueOf(asiakasOriginal.puhnro));
                emailField.setText(String.valueOf(asiakasOriginal.sahkoposti));
            } catch (Exception _) {
            }
        });


        Button saveButton = new Button("Tallenna muutokset");
        saveButton.setOnAction(e -> {
            try {
                String nimi = nameField.getText();
                String osoite = addressField.getText();
                String puhnro = phoneField.getText();
                String email = emailField.getText();

                Asiakas asiakas = new Asiakas(asiakasOriginal.asiakasID, nimi, osoite, puhnro, email);
                DBManager.updateAsiakas(asiakas);
            } catch (Exception exception) {
                System.out.println("Error saving: " + exception.getMessage());
            }
            this.closePopup();
        });

        bottomRow.getChildren().addAll(deleteButton, cancelButton, saveButton);


    }
}