package presentation;

import javafx.scene.control.Button;
import presentation.uicomponents.CurrentSongView;
import presentation.uicomponents.VolumeView;

public class JourneySelectionView extends BaseView {
	
	public Button backButton;
	public VolumeView volumeView;
	public CurrentSongView currentSongView;

	public JourneySelectionView() {
		// Nodes for BorderPane
		super();

		// Instantiate UI Components
		backButton = new Button();
		volumeView = new VolumeView();
		currentSongView = new CurrentSongView();

		// Ad UI Components to Nodes
		top.getChildren().addAll(backButton);
		bottom.getChildren().addAll(currentSongView, volumeView);
	}

}
