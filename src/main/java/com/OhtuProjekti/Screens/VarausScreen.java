package com.OhtuProjekti.Screens;

import com.OhtuProjekti.Classes.Varaus;
import com.OhtuProjekti.DBManager;
import com.OhtuProjekti.Popups.InsertVarausPopup;
import com.OhtuProjekti.Popups.VarausPopup;
import com.OhtuProjekti.SceneManager;
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

        VBox buttonBox = new VBox(5);
        buttonBox.setFillWidth(true);

        List<Varaus> varausList = DBManager.getAllVaraukset();
        for (Varaus varaus : varausList) {
            Button button = new Button("VarausId: " + String.valueOf(varaus.varausID));
            button.setMaxWidth(Double.MAX_VALUE);
            button.setPrefWidth(200);

            button.setOnAction(e -> {
                VarausPopup varausPopup = new VarausPopup();
                varausPopup.createPopup(varaus);
                varausPopup.showPopup();
            });

            buttonBox.getChildren().add(button);
        }

        contentBox.getChildren().add(buttonBox);


        screen.setCenter(contentBox);


        Button insertVarausButton = new Button("Lisää mökki");
        insertVarausButton.setOnAction(e -> {
            InsertVarausPopup insertVarausPopup = new InsertVarausPopup();
            insertVarausPopup.createPopup();
            insertVarausPopup.showPopup();

        });
        bottomPane.getChildren().add(insertVarausButton);






    }


}
