package com.OhtuProjekti;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Startpoint of the application.
 *
 * Instructions to start clean:
 * 1) delete mokki_db.sqlite from project root
 * 2) run SQL.main() to generate an empty database
 * 3) (optional) run GenerateExampleData.main() to populate the database with example data
 */
public class MainApp extends Application {

    /** The main launch method of the app
     * @param args Arguments not in use
     */
    public static void main(String[] args) {
        launch(args);
    }

    /** Starts the app. First shows main menu.
     * @param stage Root stage
     */
    @Override
    public void start(Stage stage) {

        stage.setResizable(false);
        stage.setMaxHeight(Utils.SCREEN_HEIGHT);
        stage.setMaxWidth(Utils.SCREEN_WIDTH);
        stage.setMinHeight(Utils.SCREEN_HEIGHT);
        stage.setMinWidth(Utils.SCREEN_WIDTH);
        SceneManager sceneManager = new SceneManager(stage);

        sceneManager.showMainMenu();

        stage.setTitle("Mökkikodit varausjärjestelmä");
        stage.show();


    }
}
