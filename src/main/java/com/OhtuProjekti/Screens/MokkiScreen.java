package com.OhtuProjekti.Screens;

import com.OhtuProjekti.DBManager;
import com.OhtuProjekti.Classes.Mokki;
import com.OhtuProjekti.SceneManager;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;

import java.util.List;

public class MokkiScreen extends SuperScreen {

    public MokkiScreen(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public Pane getScreen() {
        return screen;
    }

    public void createScreen() {
        super.createScreenSuper("Mökit");

        VBox contentBox = new VBox(10);
        contentBox.setPadding(new Insets(20));

        Text title = new Text("Valitse mökki nähdäksesi tiedot:");
        contentBox.getChildren().add(title);

        VBox buttonBox = new VBox(5);
        buttonBox.setFillWidth(true);

        Text detailsText = new Text();
        detailsText.setWrappingWidth(400);

        List<Mokki> mokit = DBManager.getAllMokit();
        for (Mokki mokki : mokit) {
            Button button = new Button(mokki.nimi);
            button.setMaxWidth(Double.MAX_VALUE);
            button.setPrefWidth(200);

            button.setOnAction(e -> {
                String details = "ID: " + mokki.mokkiID + "\n" +
                        "Nimi: " + mokki.nimi + "\n" +
                        "Osoite: " + mokki.osoite + "\n" +
                        "Varustelu: " + mokki.varustelu + "\n" +
                        "Hinta per yö: " + mokki.hintaPerYo + " €\n" +
                        "Kapasiteetti: " + mokki.kapasiteetti + " hlö";
                detailsText.setText(details);
            });

            buttonBox.getChildren().add(button);
        }

        contentBox.getChildren().addAll(buttonBox, detailsText);

        GridPane grid = new GridPane();
        TextField idField = new TextField();
        TextField nameField = new TextField();
        TextField addressField = new TextField();
        TextField equipmentField = new TextField();
        TextField priceField = new TextField();
        TextField capacityField = new TextField();
        Button insertButton = new Button("Insert Mökki");

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
        grid.add(insertButton, 1, 6);

        insertButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String nimi = nameField.getText();
                String osoite = addressField.getText();
                String varustelu = equipmentField.getText();
                double hinta = Double.parseDouble(priceField.getText());
                int kapasiteetti = Integer.parseInt(capacityField.getText());

                Mokki mokki = new Mokki(id, nimi, osoite, varustelu, hinta, kapasiteetti);
                DBManager.insertMokki(mokki);
            } catch (Exception _) {
            }
        });

        contentBox.getChildren().add(grid);

        screen.setCenter(contentBox);
    }
}
