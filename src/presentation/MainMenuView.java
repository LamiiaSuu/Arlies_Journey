package presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import presentation.uicomponents.*;

public class MainMenuView extends VBox {
	
	public Button newJourneyButton;
	public Button musicButton;
	public Button settingsButton;
	public VolumeView volumeView;
	public CurrentSongView currentSongView;
	
	public MainMenuView() {
		
		

		
		VBox layout = new VBox();
		layout.setSpacing(25);

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
        
        layout.setScaleX(0.8);
        layout.setScaleY(0.8);
        layout.setAlignment(Pos.CENTER);
        

        layout.getChildren().addAll(newJourneyButton, musicButton, settingsButton);
	
        setAlignment(Pos.CENTER);
        
        getChildren().addAll(layout);
        
        setId("main-menu-panel");
        
	}

}
