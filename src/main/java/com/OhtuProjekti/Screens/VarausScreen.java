package com.OhtuProjekti.Screens;

import com.OhtuProjekti.Classes.Asiakas;
import com.OhtuProjekti.Classes.Varaus;
import com.OhtuProjekti.DBManager;
import com.OhtuProjekti.Popups.AsiakasPopup;
import com.OhtuProjekti.Popups.InsertVarausPopup;
import com.OhtuProjekti.Popups.VarausPopup;
import com.OhtuProjekti.SceneManager;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

public class VarausScreen extends SuperScreen {

    /** Constructor, sets this.sceneManager
     * @param sceneManager The common project SceneManager
     */
    public VarausScreen(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    /**
     * Creates the StackPane for drawing the screen
     */
    public void createScreen(){
        super.createScreenSuper("Varaukset");


        VBox contentBox = new VBox(10);
        contentBox.setPadding(new Insets(20));

        Text title = new Text("Valitse varaus nähdäksesi tai muokataksesi tietoja:");
        contentBox.getChildren().add(title);

        buttonBox = new VBox(5);
        buttonBox.setFillWidth(true);

        contentBox.getChildren().add(buttonBox);

        DBManager.getAllVaraukset();

        rebuildVBox();

        // Makes the UI list react to changes in the list in DBManager
        DBManager.varaukset.addListener((ListChangeListener<Varaus>) change -> {
            rebuildVBox();
        });



        screen.setCenter(contentBox);


        Button insertVarausButton = new Button("Lisää varaus");
        insertVarausButton.setOnAction(e -> {
            InsertVarausPopup insertVarausPopup = new InsertVarausPopup();
            insertVarausPopup.createPopup();
            insertVarausPopup.showPopup();

        });
        bottomPane.getChildren().add(insertVarausButton);






    }

    private Button createListButton(Varaus varaus) {
        Button button = new Button("VarausId: " + String.valueOf(varaus.varausID) +
            ", Alkupaiva: " + String.valueOf(varaus.alkupaiva)
        );
        button.setMaxWidth(Double.MAX_VALUE);
        button.setPrefWidth(200);

        button.setOnAction(e -> {
            VarausPopup varausPopup = new VarausPopup();
            varausPopup.createPopup(varaus);
            varausPopup.showPopup();
        });

        return button;
    }

    private void rebuildVBox() {
        buttonBox.getChildren().clear();
        for (Varaus v : DBManager.varaukset) {
            buttonBox.getChildren().add(createListButton(v));
        }
    }



}
