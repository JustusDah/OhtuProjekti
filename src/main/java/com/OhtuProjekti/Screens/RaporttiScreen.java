package com.OhtuProjekti.Screens;

import com.OhtuProjekti.Classes.Asiakas;
import com.OhtuProjekti.Classes.Mokki;
import com.OhtuProjekti.DBManager;
import com.OhtuProjekti.Popups.*;
import com.OhtuProjekti.SceneManager;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RaporttiScreen extends SuperScreen {
    public RaporttiScreen(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public Pane getScreen(){
        return screen;
    }

    public void createScreen() {
        super.createScreenSuper("Raportit");

        VBox mainBox = new VBox();
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setSpacing(10);


        Button kokonaistuloButton = new Button("Toteutuneet kokonaistulot");
        kokonaistuloButton.setOnAction(e -> {
            KokonaistuloRaporttiPopup popup = new KokonaistuloRaporttiPopup();
            popup.createPopup();
            popup.showPopup();

        });

        Button varauksetButton = new Button("Varaukset");
        varauksetButton.setOnAction(e -> {
            sceneManager.showVarausScreen();
        });

        Text valitseMokki = new Text("Valitse mökki nähdäksesi mökkikohtainen raportti");

        rebuildVBox();
        mainBox.getChildren().addAll(kokonaistuloButton, valitseMokki, buttonBox);
        screen.setCenter(mainBox);


    }


    private Button createListButton(Mokki mokki) {
        Button button = new Button(mokki.nimi + ", osoite: " + mokki.osoite);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setPrefWidth(200);

        button.setOnAction(e -> {
            MokkiRaporttiPopup popup = new MokkiRaporttiPopup();
            popup.createPopup(mokki);
            popup.showPopup();
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
