package presentation;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import presentation.uicomponents.*;

public class SettingsMenuView extends BaseView {
    public Button placeHolder;
    public Button placeHolder1;
    public Button placeHolder2;
    public Button backButton;
    public VolumeView volumeView;
    public CurrentSongView currentSongView;
    public TitleView titleView;

    public SettingsMenuView() {
    	
    	// create containers for the ui-components
        HBox mainButtonsLayout = new HBox();

        // create ui-components
        placeHolder = new Button();
        placeHolder1 = new Button();
        placeHolder2 = new Button();
        backButton = new Button();
        volumeView = new VolumeView();
        currentSongView = new CurrentSongView();
        titleView = new TitleView();
        
        // add style classes
        placeHolder.getStyleClass().add("menu-button");
        placeHolder1.getStyleClass().add("menu-button");
        placeHolder2.getStyleClass().add("menu-button");
        backButton.getStyleClass().add("menu-button");
        
        // add Id's
        placeHolder.setId("settings-button");
        placeHolder1.setId("settings-button");
        placeHolder2.setId("settings-button");
        backButton.setId("back-button");

        // set the box for the main button layout
        mainButtonsLayout.setSpacing(25);
        mainButtonsLayout.setScaleX(0.8);
        mainButtonsLayout.setScaleY(0.8);
        mainButtonsLayout.setAlignment(Pos.CENTER);
       
        // set the volume view
        volumeView.setScaleX(0.4);
        volumeView.setScaleY(0.4);
        setHalignment(volumeView, HPos.RIGHT);
        
        backButton.setScaleX(0.6);
        backButton.setScaleY(0.6);
        setHalignment(backButton, HPos.LEFT);
        
        // set the title view
        titleView.setScaleX(0.5);
        titleView.setScaleY(0.5);
        setHalignment(titleView, HPos.CENTER);
        
        // add all to gridPane
//        add(titleView, 0, 0, 3, 1);
        add(mainButtonsLayout, 0, 1);
        add(volumeView, 2, 2);
        add(backButton, 0, 2);

        mainButtonsLayout.getChildren().addAll(placeHolder, placeHolder1, placeHolder2);

        setId("main-menu-panel");
    }
  
}
