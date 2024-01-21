package presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import presentation.uicomponents.CurrentSongView;
import presentation.uicomponents.VolumeView;

public class MainMenuView extends GridPane {
    public Button newJourneyButton;
    public Button musicButton;
    public Button settingsButton;
    public VolumeView volumeView;
    public CurrentSongView currentSongView;

    public MainMenuView() {
    	
    	setGrid();
    	
        VBox mainButtonsLayout = new VBox();
        mainButtonsLayout.setSpacing(25);

        newJourneyButton = new Button();
        musicButton = new Button();
        settingsButton = new Button();
        volumeView = new VolumeView();
        currentSongView = new CurrentSongView();

        newJourneyButton.getStyleClass().add("menu-button");
        musicButton.getStyleClass().add("menu-button");
        settingsButton.getStyleClass().add("menu-button");

        newJourneyButton.setId("new-journey-button");
        musicButton.setId("music-button");
        settingsButton.setId("settings-button");

        mainButtonsLayout.setScaleX(0.8);
        mainButtonsLayout.setScaleY(0.8);
        mainButtonsLayout.setAlignment(Pos.CENTER);
        
        volumeView.setScaleX(0.4);
        volumeView.setScaleY(0.4);
        
        volumeView.setAlignment(Pos.BOTTOM_RIGHT);
        
        add(mainButtonsLayout, 1, 1);
        add(volumeView, 2, 2);

        mainButtonsLayout.getChildren().addAll(newJourneyButton, musicButton, settingsButton);

        setId("main-menu-panel");
    }
    
    
    public void setGrid() {
    	// set column sizes
    	ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(33.3);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(33.3);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(33.3);
        getColumnConstraints().addAll(column1, column2, column3);
        
        // set row sizes
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(33.3);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(33.3);
        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(33.3);
        getRowConstraints().addAll(row1, row2, row3);
    }
}
