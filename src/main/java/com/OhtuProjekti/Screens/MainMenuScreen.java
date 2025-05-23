package com.OhtuProjekti.Screens;
import com.OhtuProjekti.DBManager;
import com.OhtuProjekti.SceneManager;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


/**
 * Class for handling main menu
 */
public class MainMenuScreen extends SuperScreen {

    /** Returns the Pane screen where the main menu is drawn
     * @return MainMenuScreen.screen
     */
    @Override
    public Pane getScreen() {
        return screen;
    }

    /** Constructor creates new MainMenuScreen object with given SceneManager
     * @param sceneManager the common project Scenemanager
     */
    public MainMenuScreen(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }


    /**
     * Initializes the app and creates the Pane layout for drawing the main menu
     */
    public void createScreen(){
        super.createScreenSuperNoBack("Mökkikodit varausjärjestelmä");

        DBManager.getAllData();

        VBox menuBox = new VBox();
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setSpacing(10);


        Button mokkiButton = new Button("Mökit");
        mokkiButton.setOnAction(e -> {
            sceneManager.showMokkiScreen();
        });

        Button varauksetButton = new Button("Varaukset");
        varauksetButton.setOnAction(e -> {
            sceneManager.showVarausScreen();
        });

        Button asiakasButton = new Button("Asiakkaat");
        asiakasButton.setOnAction(e -> {
            sceneManager.showAsiakasScreen();
        });

        Button laskuButton = new Button("Laskut");
        laskuButton.setOnAction(e -> {
            sceneManager.showLaskuScreen();
        });

        Button raporttiButton = new Button("Raportit");
        raporttiButton.setOnAction(e -> {
            sceneManager.showRaporttiScreen();
        });

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> {
            System.exit(0);
        });

        menuBox.getChildren().addAll(mokkiButton, varauksetButton, asiakasButton, laskuButton, raporttiButton, exitButton);
        screen.setCenter(menuBox);

    }

}
