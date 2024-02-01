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
    public Button FPSButton;
    public Button gameModeButton;
    public Button difficultyButton;
    public Button backButton;
    public Button optimizedFor;
    public Button difficulty;
    public Button gameMode;
    public VolumeView volumeView;
    public CurrentSongView currentSongView;
    public TitleView titleView;


    public SettingsMenuView() {
    	
    	// create containers for the ui-components
        HBox mainButtonsLayout = new HBox();
        HBox mainLabelsLayout = new HBox();

        // create ui-components
        FPSButton = new Button();
        gameModeButton = new Button();
        difficultyButton = new Button();
        backButton = new Button();
        optimizedFor = new Button();
        difficulty = new Button();
        gameMode = new Button();
        volumeView = new VolumeView();
        currentSongView = new CurrentSongView();
        titleView = new TitleView();
        
        // add style classes
        FPSButton.getStyleClass().add("menu-button");
        gameModeButton.getStyleClass().add("menu-button");
        difficultyButton.getStyleClass().add("menu-button");
        backButton.getStyleClass().add("menu-button");
        optimizedFor.getStyleClass().add("menu-button");
        difficulty.getStyleClass().add("menu-button");
        gameMode.getStyleClass().add("menu-button");
        
        // add Id's
        FPSButton.setId("fps-60-button");
        gameModeButton.setId("journey-button");
        difficultyButton.setId("medium-button");
        backButton.setId("back-button");
        optimizedFor.setId("optimized-for");
        difficulty.setId("difficulty");
        gameMode.setId("game-mode");
        
        // set the box for the main button layout
        mainButtonsLayout.setSpacing(25);
        mainButtonsLayout.setScaleX(0.8);
        mainButtonsLayout.setScaleY(0.8);
        mainButtonsLayout.setAlignment(Pos.CENTER);
        mainLabelsLayout.setSpacing(25);
        mainLabelsLayout.setScaleX(0.8);
        mainLabelsLayout.setScaleY(0.8);
        mainLabelsLayout.setAlignment(Pos.CENTER);
        mainLabelsLayout.setTranslateY(-115);
//        optimizedFor.setScaleX(0.8);
//        optimizedFor.setScaleY(0.8);
//        optimizedFor.setTranslateY(120);
//        optimizedFor.setTranslateX(79);
//        difficulty.setScaleX(0.8);
//        difficulty.setScaleY(0.8);
//        difficulty.setTranslateY(120);
//        difficulty.setTranslateX(282);
       
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
        add(mainLabelsLayout, 0 , 1);
        add(mainButtonsLayout, 0, 1);
        add(volumeView, 2, 2);
        add(backButton, 0, 2);

        mainLabelsLayout.getChildren().addAll(optimizedFor, gameMode, difficulty);
        mainButtonsLayout.getChildren().addAll(FPSButton, gameModeButton, difficultyButton);

        setId("main-menu-panel");
    }
  
}
