package com.OhtuProjekti;

import com.OhtuProjekti.Screens.*;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Handles changing the scenes or screens of the app
 */
public class SceneManager {

    /**
     * The common project Stage, where various scenes are set
     */
    private final Stage stage;


    /** Constructor for SceneManager with given Stage
     * @param stage JavaFX stage
     */
    public SceneManager(Stage stage) {
        this.stage = stage;
    }


    /** Returns the current scene set on the common project stage
     * @return SceneManager.stage.getScene()
     */
    public Scene getScene() {
        return stage.getScene();
    }

    /** Creates a Scene from the given Pane newRoot, and sets the scene on SceneManager.stage
     * @param newRoot Pane to construct Ccene to set on SceneManager.stage
     */
    public void setScene(Pane newRoot) {
        Scene scene = new Scene(newRoot);
        stage.setScene(scene);
    }


    /**
     * Shows main menu and plays main menu music
     */
    public void showMainMenu() {
        MainMenuScreen mainMenuScreen = new MainMenuScreen(this);
        mainMenuScreen.createScreen();
        setScene(mainMenuScreen.getScreen());
        stage.setTitle("Main Menu");
    }

    /**
     * Shows mökki screen
     */
    public void showMokkiScreen() {
        MokkiScreen mokkiScreen = new MokkiScreen(this);
        mokkiScreen.createScreen();
        setScene(mokkiScreen.getScreen());

    }

    public void showAsiakasScreen(){
        AsiakasScreen asiakasScreen = new AsiakasScreen(this);
        asiakasScreen.createScreen();
        setScene(asiakasScreen.getScreen());
    }

    public void showTestScreen() {
        TestScreen testScreen = new TestScreen(this);
        testScreen.createScreen();
        setScene(testScreen.getScreen());
    }

}
