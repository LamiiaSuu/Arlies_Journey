package business.game.elements;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Arlie {
	
    public enum ArlieConditions {
    	RUNNING, JUMPING, CROUCHING, CONFUSED;
    }
	
    private SimpleObjectProperty<ArlieConditions> conditionProperty;
    public ImageView arlieBody;

    public Arlie() {
        conditionProperty = new SimpleObjectProperty<ArlieConditions>(ArlieConditions.RUNNING);

            Image arlieImage = new Image(getClass().getResourceAsStream("/assets/images/arlie-running.png"));

            arlieBody = new ImageView(arlieImage);
            arlieBody.setPreserveRatio(true);
            arlieBody.setFitWidth(125);  

    }
    
    public SimpleObjectProperty ConditionProperty() {
    	
		return conditionProperty;
    	
    }
    
    public void setConditionProperty(ArlieConditions condition) {
    	conditionProperty.set(condition);
    	
    }
    
    public ArlieConditions getConditionProperty() {
    	return conditionProperty.get();
    	
    }
    
    

}
