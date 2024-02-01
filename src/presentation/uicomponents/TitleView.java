package presentation.uicomponents;

import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import mp3player.scene.layout.ImageViewPane;
import presentation.uicomponents.VolumeView.VolumeConditions;

public class TitleView extends HBox {

	public ImageView title;

	public TitleView() {

		Image titleImage = new Image(getClass().getResourceAsStream("/assets/images/title-normal.png"));

		title = new ImageView(titleImage);
		title.getStyleClass().add("title-icon");
		title.setId("title-label");
		setAlignment(Pos.CENTER);

		getChildren().addAll(title);
	}

}
