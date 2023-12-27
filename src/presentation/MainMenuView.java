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
		super();
		
		// Instantiate UI Components
		continueButton = new Button();
		newJourneyButton = new Button();
		musicButton = new Button();
		settingsButton = new Button();
		volumeView = new VolumeView();
		currentSongView = new CurrentSongView();
		
		// Ad UI Components to Nodes
		center.getChildren().addAll(continueButton, newJourneyButton, musicButton, settingsButton);
		bottom.getChildren().addAll(currentSongView, volumeView);
		
	}

}
