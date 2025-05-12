package com.OhtuProjekti.Popups;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static com.OhtuProjekti.Utils.*;

abstract public class SuperPopup {
    /**
     * The popup window
     */
    Stage stage;

    /**
     * The whole popup content
     */
    BorderPane popupPane;

    /**
     * Pane in the center
     */
    StackPane centerPane;

    /**
     * Row of buttons in the bottom
     */
    HBox bottomButtonRow;


    public void createPopupSuper(String titleText){
        popupPane = new BorderPane();

        bottomButtonRow = new HBox();
        bottomButtonRow.setSpacing(10);
        bottomButtonRow.setPadding(new Insets(10, 10, 10, 10));

        centerPane = new StackPane();
        Text labelText = new Text(titleText);
        labelText.setScaleX(3);
        labelText.setScaleY(3);
        labelText.setStroke(TITLE_TEXT_COLOR);

        //menuText.setTranslateY(-SCREEN_HEIGHT/2+50);
        StackPane labelTextPane = new StackPane();
        labelTextPane.setMinHeight(100);
        labelTextPane.setAlignment(Pos.CENTER);
        labelTextPane.getChildren().add(labelText);

        popupPane.setTop(labelTextPane);
        popupPane.setCenter(centerPane);
        popupPane.setBottom(bottomButtonRow);





    }

    public void showPopup(){
        stage = new Stage();
        stage.setScene(new Scene(popupPane, POPUP_WIDTH, POPUP_HEIGHT));
        stage.show();

    }

    public void closePopup(){
        stage.close();


    }



}
