package business.game.elements;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FruitTree extends ImageView {

	public FruitTree(int treeType, double fitHeight) {

		Image treeImage = new Image("/assets/obstacles/fruit-tree" + Integer.toString(treeType) + ".png");
		this.setImage(treeImage);
		this.setPreserveRatio(true);
		this.setFitHeight(fitHeight);
	}
}