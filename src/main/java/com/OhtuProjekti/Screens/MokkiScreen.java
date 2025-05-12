package com.OhtuProjekti.Screens;

import com.OhtuProjekti.DBManager;
import com.OhtuProjekti.Classes.Mokki;
import com.OhtuProjekti.Popups.InsertMokkiPopup;
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


        screen.setCenter(contentBox);


        Button insertMokkiButton = new Button("Lisää mökki");
        insertMokkiButton.setOnAction(e -> {
            InsertMokkiPopup insertMokkiPopup = new InsertMokkiPopup();
            insertMokkiPopup.createPopup();
            insertMokkiPopup.showPopup();

        });
        bottomPane.getChildren().add(insertMokkiButton);

    }
}
