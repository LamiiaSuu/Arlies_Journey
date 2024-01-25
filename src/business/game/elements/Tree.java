package business.game.elements;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tree extends ImageView {

    public Tree(int treeType, double fitWidth) {
        
    	
        Image treeImage = new Image("/assets/obstacles/tree"+Integer.toString(treeType)+".png");
        this.setImage(treeImage);
        this.setPreserveRatio(true);
        this.setFitWidth(fitWidth);  
    }
}