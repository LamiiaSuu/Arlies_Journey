package business.game.elements;

import business.game.elements.Arlie.ArlieConditions;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HealthBarController {

	ImageView[] hearts;
	
    private SimpleIntegerProperty hitPointsProperty;
	
	public HealthBarController(HealthBar healthBar) {
		
		this.hearts = healthBar.hearts;
		
	}
	
	public void damage() {
		if(hitPointsProperty.get() > 0)
		this.hitPointsProperty.set(hitPointsProperty.get()-1);
		
	}
	
	public void heal() {
		if (hitPointsProperty.get() < 3)
		this.hitPointsProperty.set(hitPointsProperty.get()+1);

	}
	
	public void setHitPoints(int hitPoints) {
		
		this.hitPointsProperty.set(hitPoints);
		
        for(int i = 0; i < hearts.length; i++) {
        	if(i<hitPoints) {
            	hearts[i].setImage(new Image(getClass().getResourceAsStream("/assets/images/heart-full.png"))); 
        	}

        	else {
        		hearts[i].setImage(new Image(getClass().getResourceAsStream("/assets/images/heart-empty.png")));
        	}
        }
		
	}

	
	public void initialize(int maxHealth) {
		hitPointsProperty = new SimpleIntegerProperty(maxHealth);
	}
	
    public SimpleIntegerProperty getHealthProperty() {
    	return hitPointsProperty;
    	
    }
    
    
	
	
}
