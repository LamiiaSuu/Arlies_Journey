package controller.uicomponents;

import application.App;
import business.game.elements.Arlie;
import business.game.elements.Arlie.ArlieConditions;
import controller.BaseViewController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import presentation.InGameView;


public class ArlieController extends BaseViewController {

	private static final double GRAVITY = 1.0; 
	private static final double JUMP_INITIAL_VELOCITY = -25.0;
	private static final double ARLIE_RUNNING_ANIMATION_INTENSITY = 7;
	private static final double ARLIE_RUNNING_ANIMATION_FREQUENCY_MILIS = 200;
	private double jumpVelocity;
	private double gravityModifier;
	private double groundY;
	private boolean doubleJumpable;
	private boolean doubleJumped;
	private long lastUpdateTime = System.currentTimeMillis();

    ImageView arlieBody;
    Arlie arlie;
    InGameView root;
    Scene scene;
    App app;
    
   

    public ArlieController(App app, InGameView root, Scene scene) {
        if (root != null) {
            this.root = root;
            this.app = app;
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

                groundY = newValue.doubleValue() * 0.7 - arlie.arlieBody.getFitHeight();
            }
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
        if (System.currentTimeMillis() - lastUpdateTime >= ARLIE_RUNNING_ANIMATION_FREQUENCY_MILIS) {
            // Reset the timer
            lastUpdateTime = System.currentTimeMillis();

            // Execute the if and else cases
            if (arlie.getConditionProperty() == ArlieConditions.RUNNING && arlieBody.getRotate() == 0) {
                arlieBody.setRotate(ARLIE_RUNNING_ANIMATION_INTENSITY);
            } else if (arlie.getConditionProperty() == ArlieConditions.RUNNING && arlieBody.getRotate() != 0){
                arlieBody.setRotate(0);
            }
        }
    	updateJump();
    }
    
    //DOUBLE FLIP ARLIEEEE WOOOOOOOOOOHOOOOOOOOOO
//    private void updateJump() {
//        if (arlie.getConditionProperty() == ArlieConditions.JUMPING) {
//            double newY = arlieBody.getTranslateY() + jumpVelocity;
//            arlieBody.setTranslateY(newY);
//
//            double rotation = -(jumpVelocity / JUMP_INITIAL_VELOCITY) * 360;
//            arlieBody.setRotate(rotation);
//
//            jumpVelocity += GRAVITY;
//
//            if (newY >= GROUND_LEVEL) {
//                arlie.setConditionProperty(ArlieConditions.RUNNING);
//                arlieBody.setTranslateY(GROUND_LEVEL); 
//                arlieBody.setRotate(0);  
//                jumpVelocity = 0;  
//            }
//        }
//    }
    
    //Natural Jump Arlie
    private void updateJump() {
        if (arlie.getConditionProperty() == ArlieConditions.JUMPING) {
            double newY = arlieBody.getTranslateY() + jumpVelocity;
            arlieBody.setTranslateY(newY);

            double rotationSpeed = (1 - Math.exp(jumpVelocity / JUMP_INITIAL_VELOCITY)) * 2;
            double rotation = arlieBody.getRotate() + rotationSpeed;

            if (doubleJumped) {
        		gravityModifier = 1;
            	
//                double totalRotation = 250.0; 
//                double rotationPerTick = totalRotation / (JUMP_INITIAL_VELOCITY / (GRAVITY));
//                rotation -= rotationPerTick;
                
              //Instead of this one could use jumpVelocity to calculate how many ticks until Arlie reaches the ground. The ticks could be equally, or through a Math.abs(Math.exp) to get a smooth roll with Arlie always landing on his "feet"
            	rotationSpeed = ((Math.cos(jumpVelocity / JUMP_INITIAL_VELOCITY)) * 12);
            	rotation = arlieBody.getRotate() + rotationSpeed;
            }

            arlieBody.setRotate(rotation);

            jumpVelocity += (GRAVITY * gravityModifier);

            if (newY >= groundY) {
                arlie.setConditionProperty(ArlieConditions.RUNNING);
                arlieBody.setTranslateY(groundY);
                arlieBody.setRotate(0);
                jumpVelocity = 0;
                doubleJumped = false;
            }
        }
    }



    
    public void jumpRelease() {
    	if(gravityModifier == 1 && !doubleJumped)
		gravityModifier = 2;
    	doubleJumpable = true;
	}


    public void crouch() {
    	
    	if(arlie.getConditionProperty() == ArlieConditions.RUNNING) {
    		
    		arlie.setConditionProperty(ArlieConditions.CROUCHING);

    		//Shit doesn't work for some reason
    		
    		System.out.println(arlieBody.getTranslateY());
    		
    	} else if(arlie.getConditionProperty() == ArlieConditions.JUMPING) {
    		gravityModifier = 4;
    	}
    	
    }
    
	public void crouchRelease() {
    	
    	if(arlie.getConditionProperty() == ArlieConditions.CROUCHING) {
    		
    		arlie.setConditionProperty(ArlieConditions.RUNNING);

    		//Shit doesn't work for some reason
    		arlieBody.setTranslateY(arlieBody.getTranslateY() - 55);
    		
    	} else if(arlie.getConditionProperty() == ArlieConditions.JUMPING && doubleJumped) {
    		gravityModifier = 2;
    	}
    }
    
    public void jump() {
    	gravityModifier = 1;
    	if(arlie.getConditionProperty() == ArlieConditions.RUNNING) {
    		doubleJumpable = false;
    		arlie.setConditionProperty(ArlieConditions.JUMPING);
    		jumpVelocity = JUMP_INITIAL_VELOCITY;
    	
    	//Double Jump!
    	}else if (arlie.getConditionProperty() == ArlieConditions.JUMPING && doubleJumpable && !doubleJumped) {
    		jumpVelocity = 0.5*JUMP_INITIAL_VELOCITY;
    		doubleJumpable = false;
    		doubleJumped = true;

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
