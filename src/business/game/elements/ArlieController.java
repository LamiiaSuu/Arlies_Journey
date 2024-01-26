package business.game.elements;

import application.App;
import business.game.elements.Arlie.ArlieConditions;
import controller.BaseViewController;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.scene.image.Image;


public class ArlieController extends BaseViewController {

	private static final double GRAVITY = 1.0; 
	private static final double JUMP_INITIAL_VELOCITY = -25.0;
	private static final double ARLIE_RUNNING_ANIMATION_INTENSITY = 7;
	private static final double ARLIE_RUNNING_ANIMATION_FREQUENCY_MILLIS = 200;
	private static final boolean SMALL_CROUCH = false;
	private double jumpVelocity;
	private double gravityModifier;
	public double groundY;
	private boolean crouchPressed;
	private boolean doubleJumpable;
	private boolean doubleJumped;
	private long lastUpdateTime = System.currentTimeMillis();
	
    private SimpleBooleanProperty confusedLandedProperty;
    
    private RotateTransition rotateAnimation;

    ImageView arlieBody;
    Arlie arlie;
    Pane root;
    Scene scene;
    App app;
    
   

    public ArlieController(App app, Pane root, Arlie arlie, Scene scene) {
        if (root != null) {
            this.root = root;
            this.app = app;
            this.arlie = arlie;
            this.arlieBody = arlie.arlieBody;
            
            
            root.setFocusTraversable(true);
            root.requestFocus();
            this.scene = scene;
            
        }
       
    }


	@SuppressWarnings("unchecked")
	@Override
    public void initialize() {
		
		confusedLandedProperty = new SimpleBooleanProperty(false);
        
//        Skin change maybe?
//        arlie.arlieBody.getOnMouseClicked();
        
        
        
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
        if (System.currentTimeMillis() - lastUpdateTime >= ARLIE_RUNNING_ANIMATION_FREQUENCY_MILLIS) {
        	

            lastUpdateTime = System.currentTimeMillis();


            if (arlie.getConditionProperty() == ArlieConditions.RUNNING && arlieBody.getRotate() == 0) {
            	
                arlieBody.setRotate(ARLIE_RUNNING_ANIMATION_INTENSITY);
                
            } else if (arlie.getConditionProperty() == ArlieConditions.RUNNING && arlieBody.getRotate() != 0){
            	
                arlieBody.setRotate(0);
                
            }
            
            if (arlie.getConditionProperty() == ArlieConditions.CROUCHING && arlieBody.getRotate() == 2) {
            	
                arlieBody.setRotate(ARLIE_RUNNING_ANIMATION_INTENSITY/1.75);
                
            } else if (arlie.getConditionProperty() == ArlieConditions.CROUCHING && arlieBody.getRotate() != 2){
            	
                arlieBody.setRotate(2);
                
            }
        }
        
    	updateJump();
    	updateCrouch();
    	
    }
    
    public void gameOver() {
        arlie.setConditionProperty(ArlieConditions.CONFUSED);
        jump();
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
        if (arlie.getConditionProperty() == ArlieConditions.JUMPING || arlie.getConditionProperty() == ArlieConditions.CONFUSED ) {
        	
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
            	if(arlie.getConditionProperty() != ArlieConditions.CONFUSED) {
            		if(crouchPressed) {
                		arlie.setConditionProperty(ArlieConditions.CROUCHING);
                	}else {
                		arlie.setConditionProperty(ArlieConditions.RUNNING);
                	}
            	}else {
            		confusedLandedProperty.set(true);
            	}
            	
            	
            	
                arlieBody.setTranslateY(groundY);
                arlieBody.setRotate(2);
                jumpVelocity = 0;
                doubleJumped = false;
                
                
            }
        }
        
    }
    
    private void updateCrouch() {

    	if (arlie.getConditionProperty() == ArlieConditions.CROUCHING) {
    		if(!SMALL_CROUCH)
        	arlieBody.setTranslateY(groundY + 30);
    		else {
    			arlieBody.setTranslateY(groundY + 50);
    		}
        }
    }



    
    public void jumpRelease() {
    	if(gravityModifier == 1 && !doubleJumped)
		gravityModifier = 2;
    	doubleJumpable = true;
	}


    public void crouch() {
    	crouchPressed = true;
    	
    	if(arlie.getConditionProperty() == ArlieConditions.RUNNING) {
    		
    		arlie.setConditionProperty(ArlieConditions.CROUCHING);
    		arlieBody.setRotate(2);
    		
    		
    	} else if(arlie.getConditionProperty() == ArlieConditions.JUMPING) {
    		
    		gravityModifier = 4;
    		
    	}
    	
    }
    
	public void crouchRelease() {
    	crouchPressed = false;
		
		
    	if(arlie.getConditionProperty() == ArlieConditions.CROUCHING) {
    		
    		arlie.setConditionProperty(ArlieConditions.RUNNING);
    		if(!SMALL_CROUCH)
            	arlieBody.setTranslateY(groundY);
        		else {
        			arlieBody.setTranslateY(groundY);
        		}
    		
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
    	}else if (arlie.getConditionProperty() == ArlieConditions.JUMPING && doubleJumpable && !doubleJumped || arlie.getConditionProperty() == ArlieConditions.CONFUSED) {
    		jumpVelocity = 0.5*JUMP_INITIAL_VELOCITY;
    		doubleJumpable = false;
    		doubleJumped = true;

    	}
    	
    	
    }

    
    private void loadArlieImage() {
        System.out.println("SWITCHING IMAGE TO: " + arlie.getConditionProperty());
        switch (arlie.getConditionProperty()) {
            case RUNNING:
                Image runningImage = new Image(getClass().getResourceAsStream("/assets/images/arlie-running.png"));
                arlieBody.setImage(runningImage); 
                
                break;
            case CROUCHING:
            	Image crouchingImage;
            	
            	if(SMALL_CROUCH) {
            		crouchingImage = new Image(getClass().getResourceAsStream("/assets/images/arlie-crouched-smaller.png"));
            	}
                
            	else {
            		crouchingImage = new Image(getClass().getResourceAsStream("/assets/images/arlie-crouched.png"));
            	}
            	
                arlieBody.setImage(crouchingImage);
                arlieBody.setTranslateY(arlieBody.getTranslateY());
                break;
            case JUMPING:

                break;
            case CONFUSED:
                Image confusedImage = new Image(getClass().getResourceAsStream("/assets/images/arlie-confused.png"));
                arlieBody.setImage(confusedImage);
                break;
        }
    }
    
    public void confusedCircling() {
        	

            rotateAnimation = new RotateTransition(Duration.seconds(1.0), arlieBody);
            rotateAnimation.setFromAngle(-3); 
            rotateAnimation.setToAngle(6);   
            rotateAnimation.setCycleCount(100); 

            rotateAnimation.setAutoReverse(true);

            rotateAnimation.play();
        
    }
    
    public void setGround(double groundY) {
    	this.groundY = groundY;
    }


	public ImageView getArlieBody() {
		return arlieBody;
	}
    
    public SimpleBooleanProperty getConfusedLandedProperty() {
    	return confusedLandedProperty;
    	
    }

}
