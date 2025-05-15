package com.OhtuProjekti.Popups;

import com.OhtuProjekti.Classes.Lasku;
import com.OhtuProjekti.Classes.Mokki;
import com.OhtuProjekti.Classes.Varaus;
import com.OhtuProjekti.DBManager;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.NoSuchElementException;

public class LaskuPopup extends SuperPopup{
    private Lasku laskuOriginal;

    private boolean confirmDelete = false;

    public void createPopup(Lasku laskuInput){
        laskuOriginal = laskuInput;

        super.createPopupSuper("Muokkaa laskua");

        GridPane grid = new GridPane();
        TextField idField = new TextField(String.valueOf(laskuOriginal.laskuID));
        idField.setEditable(false);
        TextField sumField = new TextField(String.valueOf(laskuOriginal.summa));
        TextField erapaivaField = new TextField(laskuOriginal.erapaiva);
        TextField varausIDField  = new TextField(String.valueOf(laskuOriginal.varausID));
        TextField maksettuField = new TextField(String.valueOf(laskuOriginal.maksettu));

        Button naytaVarausButton = new Button("Näytä varaus");
        naytaVarausButton.setOnAction(e -> {
            try {
                VarausPopup popup = new VarausPopup();
                Varaus varausOfVaraus = DBManager.varaukset.filtered(
                        varaus -> varaus.varausID == laskuOriginal.varausID
                ).getFirst();
                popup.createPopup(varausOfVaraus);
                popup.showPopup();
            } catch (NoSuchElementException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Virhe");
                alert.setHeaderText("Varausta ei löytynyt");
                alert.setContentText("Laskua vastaavaa varausta ei löytynyt tietokannasta.");
                alert.showAndWait();
            }
        });


        Button maksuButton = new Button("Merkitse maksetuksi");
        maksuButton.setOnAction(e -> {
           maksettuField.setText("1");
        });


        grid.add(new Label("Lasku ID:"), 0, 0);
        grid.add(idField, 1, 0);
        grid.add(new Label("Summa:"), 0, 1);
        grid.add(sumField, 1, 1);
        grid.add(new Label("Eräpäivä:"), 0, 2);
        grid.add(erapaivaField, 1, 2);
        grid.add(new Label("VarausID:"), 0, 3);
        grid.add(varausIDField, 1, 3);
        grid.add(naytaVarausButton, 2, 3);
        grid.add(new Label("Maksettu (0 = Ei, 1 = Kyllä):"), 0, 4);
        grid.add(maksettuField, 1, 4);
        grid.add(maksuButton, 2, 4);

        this.centerPane.getChildren().add(grid);


        Button cancelButton = new Button("Peruuta muutokset");
        cancelButton.setOnAction(e -> {
            try {
                sumField.setText(String.valueOf(laskuOriginal.summa));
                erapaivaField.setText(String.valueOf(laskuOriginal.erapaiva));
                varausIDField.setText(String.valueOf(laskuOriginal.varausID));
                maksettuField.setText(String.valueOf(laskuOriginal.maksettu));
            } catch (Exception _) {
            }
        });

        Button deleteButton = new Button("Poista");
        deleteButton.setOnAction(e -> {
            if (!confirmDelete){
                confirmDelete = true;
                deleteButton.setText("Vahvista");
            }
            else {
            try {
                DBManager.deleteLasku(laskuOriginal.laskuID);
                this.closePopup();
            }
            catch (Exception exception) {
                System.out.println("Error deleting: " + exception.getMessage());
            }
            }
        });




        Button saveButton = new Button("Tallenna muutokset");
        saveButton.setOnAction(e -> {
            try {
                double summa = Double.parseDouble(sumField.getText());
                String erapaiva = erapaivaField.getText();
                int varausid = Integer.parseInt(varausIDField.getText());
                int maksettu = Integer.parseInt(maksettuField.getText());

                Lasku lasku = new Lasku(laskuOriginal.laskuID, summa, erapaiva, varausid, maksettu);
                DBManager.updateLasku(lasku);
            } catch (Exception exception) {
                System.out.println("Error saving: " + exception.getMessage());
            }
            this.closePopup();
        });

        bottomRow.getChildren().addAll(deleteButton, cancelButton, saveButton);


    }
}