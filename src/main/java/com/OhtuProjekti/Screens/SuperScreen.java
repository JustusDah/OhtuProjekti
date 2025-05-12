package com.OhtuProjekti.Screens;

import com.OhtuProjekti.SceneManager;
import com.OhtuProjekti.Utils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import static com.OhtuProjekti.Utils.*;

abstract public class SuperScreen {


    /**
     * SceneManager, should be the common project SceneManager
     */
    SceneManager sceneManager;


    /**
     * Screen for drawing the game
     */
    public BorderPane screen;

    /**
     * VBox at the left of the screen
     */
    public VBox leftPane;

    /**
     * HBox at the bottom of the screen, should contain buttons
     */
    public HBox bottomPane;
    
    public VBox rightPane;


    /** Returns the Pane where the game is drawn
     * @return returns this.screen
     */
    public Pane getScreen(){
        return screen;
    }

    public void createScreenSuperNoBack(String titleText){
        BorderPane layout = new BorderPane();
        layout.setStyle(BACKGROUNDCOLOR);

        layout.setMinSize(Utils.SCREEN_WIDTH, Utils.SCREEN_HEIGHT);
        layout.setMaxSize(Utils.SCREEN_WIDTH, Utils.SCREEN_HEIGHT);
        screen = layout;

        bottomPane = new HBox();
        leftPane = new VBox();
        bottomPane.setSpacing(10);
        bottomPane.setPadding(new Insets(10, 10, 10, 10));
        bottomPane.setAlignment(Pos.CENTER);
        leftPane.setSpacing(10);
        leftPane.setPadding(new Insets(10, 10, 10, 10));
        leftPane.setAlignment(Pos.CENTER);

        Text labelText = new Text(titleText);
        labelText.setScaleX(3);
        labelText.setScaleY(3);
        labelText.setStroke(TITLE_TEXT_COLOR);

        //menuText.setTranslateY(-SCREEN_HEIGHT/2+50);
        StackPane labelTextPane = new StackPane();
        labelTextPane.setMinHeight(100);
        labelTextPane.setAlignment(Pos.CENTER);
        labelTextPane.getChildren().add(labelText);

        screen.setTop(labelTextPane);
        screen.setBottom(bottomPane);
        screen.setLeft(leftPane);
    }


    /**
     * Creates the StackPane for drawing the screen
     */
    public void createScreenSuper(
            String titleText
    ){
        createScreenSuperNoBack(titleText);
        addBackButton();
    }

    public void addBackButton(){
        Button backButton = new Button();
        backButton.setText("Menu");
        backButton.setOnAction(
                e -> {
                    sceneManager.showMainMenu();
                });
        leftPane.getChildren().add(backButton);
        backButton.setAlignment(Pos.CENTER);

        //screen.setBottom(backButton);


    }




}
