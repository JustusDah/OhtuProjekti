package com.OhtuProjekti.Screens;

import com.OhtuProjekti.SceneManager;
import com.OhtuProjekti.Utils;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import static com.OhtuProjekti.Utils.*;


/**
 * Class for handling asiakkaat
 */
public class AsiakasScreen extends SuperScreen {


    /** Constructor, sets this.sceneManager
     * @param sceneManager The common project SceneManager
     */
    public AsiakasScreen(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    /**
     * Creates the StackPane for drawing the screen
     */
    public void createScreen(){
        super.createScreenSuper("Asiakkaat");



    }





}
