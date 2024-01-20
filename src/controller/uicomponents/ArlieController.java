package controller.uicomponents;

import business.game.elements.Arlie;
import business.game.elements.Arlie.ArlieConditions;
import controller.BaseViewController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import presentation.InGameView;

public class ArlieController extends BaseViewController {

	private static final double GRAVITY = 1.0; 
	private static final double JUMP_INITIAL_VELOCITY = -25.0;
	//private double groundY;
	private double jumpVelocity;
	private double gravityModifier;
	private double groundY;
	private Timeline timeline;
    ImageView arlieBody;
    Arlie arlie;
    InGameView root;
    Scene scene;
    
   

    public ArlieController(InGameView root, Scene scene) {
        if (root != null) {
            this.root = root;
            arlie = root.arlie;
            arlieBody = root.arlie.arlieBody;
            
            
            root.setFocusTraversable(true);
            root.requestFocus();
            this.scene = scene;
            
        }
       
    }


	@SuppressWarnings("unchecked")
	@Override
    public void initialize() {
		
		groundY = this.scene.getHeight() * 0.7 - arlie.arlieBody.getFitHeight();
		
        scene.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // Recalculate groundY when scene height changes
                groundY = newValue.doubleValue() * 0.7 - arlie.arlieBody.getFitHeight();
            }
        });
        
		System.out.println(groundY);
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
		
		
        timeline = new Timeline(new KeyFrame(Duration.millis(16), event -> update()));
        timeline.setCycleCount(Animation.INDEFINITE);  
        timeline.play();
        

    }

    @Override
    public void update() {
    	// groundY = this.scene.getHeight() * 0.7 - arlie.arlieBody.getFitHeight();
    	updateJump();
    }
    
    //DOUBLE FLIP ARLIEEEE WOOOOOOOOOOHOOOOOOOOOO
//    private void updateJump() {
//        if (arlie.getConditionProperty() == ArlieConditions.JUMPING) {
//            double newY = arlieBody.getTranslateY() + jumpVelocity;
//            arlieBody.setTranslateY(newY);
//
//            // Calculate rotation based on jumpVelocity for a smooth rotation effect
//            double rotation = -(jumpVelocity / JUMP_INITIAL_VELOCITY) * 360;
//            arlieBody.setRotate(rotation);
//
//            jumpVelocity += GRAVITY;
//
//            // Check if Arlie has touched the ground
//            if (newY >= GROUND_LEVEL) {
//                arlie.setConditionProperty(ArlieConditions.RUNNING);
//                arlieBody.setTranslateY(GROUND_LEVEL);  // Ensure Arlie is exactly at the ground level
//                arlieBody.setRotate(0);  // Reset rotation
//                jumpVelocity = 0;  // Reset jump velocity for the next jump
//            }
//        }
//    }
    
    //Natural Jump Arlie
    private void updateJump() {
        if (arlie.getConditionProperty() == ArlieConditions.JUMPING) {
            double newY = arlieBody.getTranslateY() + jumpVelocity;
            arlieBody.setTranslateY(newY);

            // Calculate rotation based on an easing function
            double rotationSpeed = (1 - Math.exp(jumpVelocity / JUMP_INITIAL_VELOCITY)) * 2;
            double rotation = arlieBody.getRotate() + rotationSpeed;
            arlieBody.setRotate(rotation);

            jumpVelocity += (GRAVITY*gravityModifier);

            // Check if Arlie has touched the ground
            if (newY >= groundY) {
                arlie.setConditionProperty(ArlieConditions.RUNNING);
                arlieBody.setTranslateY(groundY);  // Ensure Arlie is exactly at the ground level
                arlieBody.setRotate(0);  // Reset rotation
                jumpVelocity = 0;  // Reset jump velocity for the next jump
            }
        }
    }

    private void handleKeyPress(KeyCode code) {
        switch (code) {
            case SPACE:
            	jump();
                break;
            case UP:
            	jump();
                break;
            case DOWN:
            	crouch();
                break;
            case CONTROL:
            	crouch();
                break;
            
        }
    }

    private void handleKeyRelease(KeyCode code) {
        switch (code) {
       		case SPACE:
        		jumpRelease();
            	break;
        	case UP:
        		jumpRelease();
            	break;
            case DOWN:
                crouchRelease();
                break;
            case CONTROL:
                crouchRelease();
                break;
           
        }
    }
    
    private void jumpRelease() {
    	if(gravityModifier == 1)
		gravityModifier = 2;
		
	}


	private void crouch() {
    	
    	if(arlie.getConditionProperty() == ArlieConditions.RUNNING) {
    		
    		arlie.setConditionProperty(ArlieConditions.CROUCHING);

    		//Shit doesn't work for some reason
    		
    		System.out.println(arlieBody.getTranslateY());
    		
    	} else if(arlie.getConditionProperty() == ArlieConditions.JUMPING) {
    		gravityModifier = 4;
    	}
    	
    }
    
    private void crouchRelease() {
    	
    	if(arlie.getConditionProperty() == ArlieConditions.CROUCHING) {
    		
    		arlie.setConditionProperty(ArlieConditions.RUNNING);

    		//Shit doesn't work for some reason
    		arlieBody.setTranslateY(arlieBody.getTranslateY() - 55);
    		
    	} else if(arlie.getConditionProperty() == ArlieConditions.JUMPING) {
    		gravityModifier = 2;
    	}
    }
    
    private void jump() {
    	gravityModifier = 1;
    	if(arlie.getConditionProperty() == ArlieConditions.RUNNING) {
    		arlie.setConditionProperty(ArlieConditions.JUMPING);
    		jumpVelocity = JUMP_INITIAL_VELOCITY;
    	}
    	
    	
    }
    
//    private void jumpRelease() {
//    	
//    	if(arlie.getConditionProperty() == ArlieConditions.JUMPING) {
//    		arlie.setConditionProperty(ArlieConditions.RUNNING);
//    		arlieBody.setTranslateY(arlieBody.getTranslateY() + 75);
//    	}
//    	
//    }
    
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
