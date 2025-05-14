package com.OhtuProjekti.Screens;

import com.OhtuProjekti.Classes.Asiakas;
import com.OhtuProjekti.Classes.Varaus;
import com.OhtuProjekti.DBManager;
import com.OhtuProjekti.Classes.Mokki;
import com.OhtuProjekti.Popups.InsertMokkiPopup;
import com.OhtuProjekti.Popups.MokkiPopup;
import com.OhtuProjekti.Popups.VarausPopup;
import com.OhtuProjekti.SceneManager;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
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

        Text title = new Text("Valitse mökki nähdäksesi tai muokataksesi tietoja:");
        contentBox.getChildren().add(title);

        buttonBox = new VBox(5);
        buttonBox.setFillWidth(true);

        DBManager.getAllMokit();

        rebuildVBox();

        // Makes the UI list react to changes in the list in DBManager
        DBManager.mokkis.addListener((ListChangeListener<Mokki>) change -> {
            rebuildVBox();
        });

        contentBox.getChildren().add(buttonBox);


        screen.setCenter(contentBox);


        Button insertMokkiButton = new Button("Lisää mökki");
        insertMokkiButton.setOnAction(e -> {
            InsertMokkiPopup insertMokkiPopup = new InsertMokkiPopup();
            insertMokkiPopup.createPopup();
            insertMokkiPopup.showPopup();

        });
        bottomPane.getChildren().add(insertMokkiButton);

    }

    private Button createListButton(Mokki mokki) {
        Button button = new Button(mokki.nimi + ", osoite: " + mokki.osoite);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setPrefWidth(200);

        button.setOnAction(e -> {
            MokkiPopup mokkiPopup = new MokkiPopup();
            mokkiPopup.createPopup(mokki);
            mokkiPopup.showPopup();
        });

        return button;
    }

    private void rebuildVBox() {
        buttonBox.getChildren().clear();
        for (Mokki m : DBManager.mokkis) {
            buttonBox.getChildren().add(createListButton(m));
        }
    }
}
