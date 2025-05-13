package com.OhtuProjekti.Screens;

import com.OhtuProjekti.Classes.Lasku;
import com.OhtuProjekti.DBManager;
import com.OhtuProjekti.Popups.InsertLaskuPopup;
import com.OhtuProjekti.Popups.LaskuPopup;
import com.OhtuProjekti.SceneManager;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;

import java.util.List;

public class LaskuScreen extends SuperScreen {

    public LaskuScreen(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public Pane getScreen() {
        return screen;
    }

    public void createScreen() {
        super.createScreenSuper("Laskut");

        VBox contentBox = new VBox(10);
        contentBox.setPadding(new Insets(20));

        Text title = new Text("Valitse lasku nähdäksesi tai muokataksesi tietoja:");
        contentBox.getChildren().add(title);

        VBox buttonBox = new VBox(5);
        buttonBox.setFillWidth(true);

        Text detailsText = new Text();
        detailsText.setWrappingWidth(400);

        List<Lasku> laskut = DBManager.getAllLaskut();
        for (Lasku lasku : laskut) {
            Button button = new Button("Lasku ID: " + lasku.laskuID);
            button.setMaxWidth(Double.MAX_VALUE);
            button.setPrefWidth(200);

            button.setOnAction(e -> {
                LaskuPopup laskuPopup = new LaskuPopup();
                laskuPopup.createPopup(lasku);
                laskuPopup.showPopup();
            });

            buttonBox.getChildren().add(button);
        }

        contentBox.getChildren().addAll(buttonBox, detailsText);


        screen.setCenter(contentBox);


        Button insertLaskuButton = new Button("Lisää lasku");
        insertLaskuButton.setOnAction(e -> {
            InsertLaskuPopup insertLaskuPopup = new InsertLaskuPopup();
            insertLaskuPopup.createPopup();
            insertLaskuPopup.showPopup();

        });
        bottomPane.getChildren().add(insertLaskuButton);

    }
}
