package business.game.elements;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Zeppelin extends ImageView {

	public Zeppelin(double fitHeight) {

		Image treeImage = new Image("/assets/obstacles/zeppelin-makes-eye-angry.png");
		this.setImage(treeImage);
		this.setPreserveRatio(true);
		this.setFitHeight(fitHeight);
	}
}