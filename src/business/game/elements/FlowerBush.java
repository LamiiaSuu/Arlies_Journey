package business.game.elements;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FlowerBush extends ImageView {

    public FlowerBush(int flowerBushType, double fitHeight) {
        
    	
        Image treeImage = new Image("/assets/obstacles/flower-bush"+Integer.toString(flowerBushType)+".png");
        this.setImage(treeImage);
        this.setPreserveRatio(true);
        this.setFitHeight(fitHeight);  
    }
}