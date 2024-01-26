package business.game.elements;

import business.game.elements.Arlie.ArlieConditions;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class HealthBar extends HBox{
	
	ImageView[] hearts;

	
	int maxHealth;
	
    public HealthBar(int maxHealth) {
    		hearts = new ImageView[maxHealth];
    	
    		this.maxHealth = maxHealth;
    	
            Image healthImage = new Image(getClass().getResourceAsStream("/assets/images/heart-full.png"));

            for(int i = 0; i < hearts.length; i++) {
            	hearts[i] = new ImageView(healthImage);
            	hearts[i].setPreserveRatio(true);
            	hearts[i].setFitWidth(75);  
            }

            
            setTranslateX(25);
            setTranslateY(25);
            
            setSpacing(10);
            
            getChildren().addAll(hearts);


    }
    
}
