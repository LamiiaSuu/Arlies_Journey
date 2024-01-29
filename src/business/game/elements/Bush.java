package business.game.elements;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bush extends ImageView {

    public Bush(int bushType, double fitHeight) {
        
    	
        Image treeImage = new Image("/assets/obstacles/bush"+Integer.toString(bushType)+".png");
        this.setImage(treeImage);
        this.setPreserveRatio(true);
        this.setFitHeight(fitHeight);  
    }
}