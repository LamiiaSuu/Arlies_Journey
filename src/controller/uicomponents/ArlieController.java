package controller.uicomponents;

import business.game.elements.Arlie;
import business.game.elements.Arlie.ArlieConditions;
import controller.BaseViewController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import presentation.InGameView;

public class ArlieController extends BaseViewController {

    ImageView arlieBody;
    Arlie arlie;
    InGameView root;
    Scene scene;

    public ArlieController(InGameView root, Scene scene) {
        if (root != null) {
            this.root = root;
            arlie = root.arlie;
            arlieBody = root.arlie.arlieBody;

            // Set focus on the root to enable key events
            root.setFocusTraversable(true);
            root.requestFocus();
            this.scene = scene;
        }
    }


	@SuppressWarnings("unchecked")
	@Override
    public void initialize() {
        // Add event handlers for key presses and releases
        scene.setOnKeyPressed(event -> {
            handleKeyPress(event.getCode());
        });
        scene.setOnKeyReleased(event -> {
            handleKeyRelease(event.getCode());
        });
        
		arlie.ConditionProperty().addListener(new ChangeListener<>() {


			@Override
			public void changed(ObservableValue<? extends Object> arg0, Object arg1, Object arg2) {
				if(arg1 != arg2) {
					loadArlieImage();
				}
				
			}
		});
    }

    @Override
    public void update() {
        // TODO: Update code, if needed
    }

    private void handleKeyPress(KeyCode code) {
        switch (code) {
            case SPACE:
            	jump();
                break;
            case DOWN:
            	crouch();
                break;
            // Add more cases for other actions if needed
        }
    }

    private void handleKeyRelease(KeyCode code) {
        switch (code) {
        	case SPACE:
        		jumpRelease();
            	break;
            case DOWN:
                crouchRelease();
                break;
            // Add more cases for other actions if needed
        }
    }
    
    private void crouch() {
    	
    	if(arlie.getConditionProperty() == ArlieConditions.RUNNING) {
    		
    		arlie.setConditionProperty(ArlieConditions.CROUCHING);

    		//Shit doesn't work for some reason
    		arlieBody.setTranslateY(arlieBody.getTranslateY() + 55);
    		
    	}
    	
    }
    
    private void crouchRelease() {
    	
    	if(arlie.getConditionProperty() == ArlieConditions.CROUCHING) {
    		
    		arlie.setConditionProperty(ArlieConditions.RUNNING);

    		//Shit doesn't work for some reason
    		arlieBody.setTranslateY(arlieBody.getTranslateY() - 55);
    		
    	}
    }
    
    private void jump() {
    	
    	if(arlie.getConditionProperty() == ArlieConditions.RUNNING) {
    		arlie.setConditionProperty(ArlieConditions.JUMPING);
    		arlieBody.setTranslateY(arlieBody.getTranslateY() - 75);
    	}
    	
    	
    }
    
    private void jumpRelease() {
    	
    	if(arlie.getConditionProperty() == ArlieConditions.JUMPING) {
    		arlie.setConditionProperty(ArlieConditions.RUNNING);
    		arlieBody.setTranslateY(arlieBody.getTranslateY() + 75);
    	}
    	
    }
    
    private void loadArlieImage() {
        System.out.println("SWITCHING IMAGE TO: " + arlie.getConditionProperty());
        switch (arlie.getConditionProperty()) {
            case RUNNING:
                Image runningImage = new Image(getClass().getResourceAsStream("/assets/arlie-running.png"));
                arlieBody.setImage(runningImage); 
                break;
            case CROUCHING:
                Image crouchingImage = new Image(getClass().getResourceAsStream("/assets/arlie-crouched.png"));
                arlieBody.setImage(crouchingImage);
                break;
            case JUMPING:

                break;
            case CONFUSED:
                Image confusedImage = new Image(getClass().getResourceAsStream("/assets/arlie-confused.png"));
                arlieBody.setImage(confusedImage);
                break;
        }
    }

}
