package com.OhtuProjekti.Screens;

import com.OhtuProjekti.Classes.Asiakas;
import com.OhtuProjekti.DBManager;
import com.OhtuProjekti.Popups.InsertAsiakasPopup;
import com.OhtuProjekti.Popups.AsiakasPopup;
import com.OhtuProjekti.SceneManager;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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

        Text title = new Text("Valitse asiakas nähdäksesi tai muokataksesi tietoja:");
        contentBox.getChildren().add(title);

        buttonBox = new VBox(5);
        buttonBox.setFillWidth(true);

        DBManager.getAllAsiakkaat();

        rebuildVBox();

        // Makes the UI list react to changes in the list in DBManager
        DBManager.asiakkaat.addListener((ListChangeListener<Asiakas>) change -> {
            rebuildVBox();
        });


        contentBox.getChildren().addAll(buttonBox);


        screen.setCenter(contentBox);


        Button insertAsiakasButton = new Button("Lisää asiakas");
        insertAsiakasButton.setOnAction(e -> {
            InsertAsiakasPopup insertAsiakasPopup = new InsertAsiakasPopup();
            insertAsiakasPopup.createPopup();
            insertAsiakasPopup.showPopup();

        });
        bottomPane.getChildren().add(insertAsiakasButton);

    }

    private Button createAsiakasButton(Asiakas asiakas) {
        Button button = new Button(asiakas.nimi);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setPrefWidth(200);

        button.setOnAction(e -> {
            AsiakasPopup asiakasPopup = new AsiakasPopup();
            asiakasPopup.createPopup(asiakas);
            asiakasPopup.showPopup();
        });

        return button;
    }

    private void rebuildVBox() {
        buttonBox.getChildren().clear();
        for (Asiakas a : DBManager.asiakkaat) {
            buttonBox.getChildren().add(createAsiakasButton(a));
        }
    }


}


