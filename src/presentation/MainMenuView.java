package presentation;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import presentation.uicomponents.*;

public class MainMenuView extends BaseView {
	
	public Button continueButton;
	public Button newJourneyButton;
	public Button musicButton;
	public Button settingsButton;
	public VolumeView volumeView;
	public CurrentSongView currentSongView;
	
	public MainMenuView() {
		
		
		// Nodes for BorderPane
//				super();
		
		VBox layout = new VBox();
		
		// Instantiate UI Components
		continueButton = new Button("Continue");
        newJourneyButton = new Button("New Journey");
        musicButton = new Button("Music");
        settingsButton = new Button("Settings");
        volumeView = new VolumeView();
        currentSongView = new CurrentSongView();
        
        
		
		// Ad UI Components to Nodes
//		center.getChildren().addAll(continueButton, newJourneyButton, musicButton, settingsButton);
//		bottom.getChildren().addAll(currentSongView, volumeView);

        layout.getChildren().addAll(continueButton, newJourneyButton, musicButton, settingsButton, currentSongView, volumeView);
	
        setCenter(layout);
	}

}
