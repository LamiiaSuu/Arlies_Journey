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

public class MainMenuView extends BaseView {
    public Button newJourneyButton;
    public Button musicButton;
    public Button settingsButton;
    public Button continueButton;
    public VBox mainButtonsLayout;
//    public VolumeView volumeView;
    public CurrentSongView currentSongView;
    public TitleView titleView;

    public MainMenuView() {
    	
    	// create containers for the ui-components
        mainButtonsLayout = new VBox();

        // create ui-components
        newJourneyButton = new Button();
        musicButton = new Button();
        settingsButton = new Button();
        continueButton = new Button();
//        volumeView = new VolumeView();
        currentSongView = new CurrentSongView();
        titleView = new TitleView();
        
        // add style classes
        newJourneyButton.getStyleClass().add("menu-button");
        musicButton.getStyleClass().add("menu-button");
        settingsButton.getStyleClass().add("menu-button");
        continueButton.getStyleClass().add("menu-button");
        
        // add Id's
        newJourneyButton.setId("new-journey-button");
        musicButton.setId("music-button");
        settingsButton.setId("settings-button");
        continueButton.setId("continue-button");
        
        continueButton.setVisible(false);
        
        // set the box for the main button layout
        mainButtonsLayout.setSpacing(25);
        mainButtonsLayout.setScaleX(0.8);
        mainButtonsLayout.setScaleY(0.8);
        mainButtonsLayout.setAlignment(Pos.CENTER);
       
        // set the volume view
//        volumeView.setScaleX(0.4);
//        volumeView.setScaleY(0.4);
//        setHalignment(volumeView, HPos.RIGHT);
        
        // set the title view
        titleView.setScaleX(0.5);
        titleView.setScaleY(0.5);
        setHalignment(titleView, HPos.CENTER);
        
        // add all to gridPane
        add(titleView, 0, 0, 3, 1);
        add(mainButtonsLayout, 1, 1);
//        add(volumeView, 2, 2);

        mainButtonsLayout.getChildren().addAll(continueButton, newJourneyButton, musicButton, settingsButton);

        setId("main-menu-panel");
    }
  
}
