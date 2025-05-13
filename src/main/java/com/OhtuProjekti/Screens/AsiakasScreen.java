package com.OhtuProjekti.Screens;

import com.OhtuProjekti.Classes.Asiakas;
import com.OhtuProjekti.DBManager;
import com.OhtuProjekti.Popups.InsertAsiakasPopup;
import com.OhtuProjekti.Popups.AsiakasPopup;
import com.OhtuProjekti.SceneManager;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;

import java.util.List;

public class AsiakasScreen extends SuperScreen {

    public AsiakasScreen(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public Pane getScreen() {
        return screen;
    }

    public void createScreen() {
        super.createScreenSuper("Asiakkaat");

        VBox contentBox = new VBox(10);
        contentBox.setPadding(new Insets(20));

        Text title = new Text("Valitse asiakas nähdäksesi tiedot:");
        contentBox.getChildren().add(title);

        VBox buttonBox = new VBox(5);
        buttonBox.setFillWidth(true);

        Text detailsText = new Text();
        detailsText.setWrappingWidth(400);

        List<Asiakas> asiakkaat = DBManager.getAllAsiakkaat();
        for (Asiakas asiakas : asiakkaat) {
            Button button = new Button(asiakas.nimi);
            button.setMaxWidth(Double.MAX_VALUE);
            button.setPrefWidth(200);

            button.setOnAction(e -> {
                AsiakasPopupp asiakasPopup = new Asiakaspopup();
                asiakasPopup.createPopup(asiakas);
                asiakasPopup.showPopup();
                String details = "ID: " + asiakas.asiakasID + "\n" +
                        "Nimi: " + asiakasPopup.nimi + "\n" +
                        "Osoite: " + asiakasPopup.osoite + "\n" +
                        "Sähköposti: " + asiakasPopup.sahkoposti + "\n" +
                        "Puhelinnumero: " + asiakasPopup.puhnro + "\n";
                detailsText.setText(details);
            });

            buttonBox.getChildren().add(button);
        }

        contentBox.getChildren().addAll(buttonBox, detailsText);


        screen.setCenter(contentBox);


        Button insertAsiakasButton = new Button("Lisää asiakas");
        insertAsiakasButton.setOnAction(e -> {
            InsertAsiakasPopup insertAsiakasPopup = new InsertAsiakasPopup();
            insertAsiakasPopup.createPopup();
            insertAsiakasPopup.showPopup();

        });
        bottomPane.getChildren().add(insertAsiakasButton);

    }
}
