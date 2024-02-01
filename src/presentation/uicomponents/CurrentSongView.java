package presentation.uicomponents;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CurrentSongView extends VBox {
	public Label titleLabel;
	public Label artistLabel;

	public CurrentSongView() {
		titleLabel = new Label();
		artistLabel = new Label();

		titleLabel.setAlignment(Pos.CENTER);
		artistLabel.setAlignment(Pos.CENTER);

		getChildren().addAll(titleLabel, artistLabel);

	}

}
