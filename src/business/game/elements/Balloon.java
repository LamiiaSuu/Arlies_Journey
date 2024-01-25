package business.game.elements;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Balloon extends ImageView {

	
	
    public Balloon(int balloonType, double fitHeight) {
        
    	
        Image balloonImage = new Image("/assets/obstacles/balloon"+Integer.toString(balloonType)+".png");
        this.setImage(balloonImage);
        this.setPreserveRatio(true);
        this.setFitHeight(fitHeight);  
    }
}