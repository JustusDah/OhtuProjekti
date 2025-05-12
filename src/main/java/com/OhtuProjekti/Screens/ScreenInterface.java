package com.OhtuProjekti.Screens;

import javafx.scene.layout.Pane;

/**
 * Interface for the various screens in the app
 */
interface ScreenInterface {

    /**
     * @return Pane where the screen is drawn
     */
    Pane getScreen();

    /**
     * Creates Pane
     */
    void createScreen();
}
