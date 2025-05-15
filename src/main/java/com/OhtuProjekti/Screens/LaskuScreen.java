package com.OhtuProjekti.Screens;

import com.OhtuProjekti.Classes.Lasku;
import com.OhtuProjekti.Classes.Varaus;
import com.OhtuProjekti.DBManager;
import com.OhtuProjekti.Popups.InsertLaskuPopup;
import com.OhtuProjekti.Popups.LaskuPopup;
import com.OhtuProjekti.Popups.VarausPopup;
import com.OhtuProjekti.SceneManager;
import javafx.collections.ListChangeListener;
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

        buttonBox = new VBox(5);
        buttonBox.setFillWidth(true);

        DBManager.getAllLaskut();

        rebuildVBox();

        // Makes the UI list react to changes in the list in DBManager
        DBManager.laskut.addListener((ListChangeListener<Lasku>) change -> {
            rebuildVBox();
        });

        contentBox.getChildren().addAll(buttonBox);


        screen.setCenter(contentBox);


        Button insertLaskuButton = new Button("Lisää lasku");
        insertLaskuButton.setOnAction(e -> {
            InsertLaskuPopup insertLaskuPopup = new InsertLaskuPopup();
            insertLaskuPopup.createPopup();
            insertLaskuPopup.showPopup();

        });
        bottomPane.getChildren().add(insertLaskuButton);

    }

    private Button createListButton(Lasku lasku) {
        String maksettuString = "Kyllä";
        if (lasku.maksettu == 0) maksettuString = "Ei";
        Button button = new Button("Lasku ID: " + lasku.laskuID +
                ", Eräpäivä: " + lasku.erapaiva+
                ", Maksettu: " + maksettuString
        );
        button.setMaxWidth(Double.MAX_VALUE);
        button.setPrefWidth(200);

        button.setOnAction(e -> {
            LaskuPopup laskuPopup = new LaskuPopup();
            laskuPopup.createPopup(lasku);
            laskuPopup.showPopup();
        });

        return button;
    }

    private void rebuildVBox() {
        buttonBox.getChildren().clear();
        for (Lasku l : DBManager.laskut) {
            buttonBox.getChildren().add(createListButton(l));
        }
    }
}
