package business.game.elements;

import application.App;
import business.game.elements.Arlie.ArlieConditions;
import business.music.MP3Player;
import controller.BaseViewController;
import controller.InGameViewController;
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
import javafx.scene.effect.*;

public class ArlieController extends BaseViewController {

	private static final double BASE_GRAVITY = 0.75;
	private static final double JUMP_INITIAL_VELOCITY = -22;
	private static final double ARLIE_RUNNING_ANIMATION_INTENSITY = 7;
	private static final double ARLIE_RUNNING_ANIMATION_FREQUENCY_MILLIS = 200;
	private static final boolean SMALL_CROUCH = false;
	private double adjustedVelocity;
	private double adjustedGravity;
	private double jumpVelocity;
	private double gravityModifier;
	public double groundY;
	private boolean crouchPressed;
	private boolean doubleJumpable;
	private boolean doubleJumped;
	private long lastUpdateTime = System.currentTimeMillis();

	InGameViewController inGameViewController;
	MP3Player player;
	ColorAdjust colorAdjust;
	ColorAdjust godColorAdjust;
	Bloom bloom;
	Glow glow;
	Glow godGlow;
	Blend blend;
	Blend godBlend;

	private SimpleBooleanProperty confusedLandedProperty;

	private RotateTransition rotateAnimation;

	ImageView arlieBody;
	Arlie arlie;
	Pane root;
	Scene scene;
	App app;

	public ArlieController(App app, Pane root, Arlie arlie, Scene scene, MP3Player player,
			InGameViewController inGameViewController) {
		if (root != null) {
			this.root = root;
			this.app = app;
			this.arlie = arlie;
			this.arlieBody = arlie.arlieBody;
			this.player = player;
			this.inGameViewController = inGameViewController;

			root.setFocusTraversable(true);
			root.requestFocus();
			this.scene = scene;

		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize() {

		colorAdjust = new ColorAdjust();

		godColorAdjust = new ColorAdjust();

		blend = new Blend();

		godBlend = new Blend();

		glow = new Glow();

		godGlow = new Glow();

		godGlow.setLevel(0.55);
		godColorAdjust.setHue(-0.9);
		godColorAdjust.setBrightness(-0.1);
		godBlend.setBottomInput(godGlow);
		godBlend.setTopInput(godColorAdjust);

		colorAdjust.setHue(-0.48);
		glow.setLevel(0.5);
		blend.setBottomInput(glow);
		blend.setTopInput(colorAdjust);

		confusedLandedProperty = new SimpleBooleanProperty(false);

//        Skin change maybe?
//        arlie.arlieBody.getOnMouseClicked();

		arlie.ConditionProperty().addListener(new ChangeListener<>() {

			@Override
			public void changed(ObservableValue<? extends Object> arg0, Object arg1, Object arg2) {

				if (arg1 != arg2) {

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

			} else if (arlie.getConditionProperty() == ArlieConditions.RUNNING && arlieBody.getRotate() != 0) {

				arlieBody.setRotate(0);

			}

			if (arlie.getConditionProperty() == ArlieConditions.CROUCHING && arlieBody.getRotate() == 2) {

				arlieBody.setRotate(ARLIE_RUNNING_ANIMATION_INTENSITY / 1.75);

			} else if (arlie.getConditionProperty() == ArlieConditions.CROUCHING && arlieBody.getRotate() != 2) {

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

	public void setFPS(int fps) {
		if (fps == 60) {
			adjustedVelocity = JUMP_INITIAL_VELOCITY * 1.1;
			adjustedGravity = BASE_GRAVITY * 1.2;
		} else {
			adjustedGravity = BASE_GRAVITY;
			adjustedVelocity = JUMP_INITIAL_VELOCITY;
		}
	}

	// DOUBLE FLIP ARLIEEEE WOOOOOOOOOOHOOOOOOOOOO
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

	// Natural Jump Arlie
	private void updateJump() {
		if (arlie.getConditionProperty() == ArlieConditions.JUMPING
				|| arlie.getConditionProperty() == ArlieConditions.CONFUSED) {

			double newY = arlieBody.getTranslateY() + jumpVelocity;
			arlieBody.setTranslateY(newY);

			double rotationSpeed = (1 - Math.exp(jumpVelocity / adjustedVelocity)) * 2;
			double rotation = arlieBody.getRotate() + rotationSpeed;

			if (doubleJumped) {
				gravityModifier = 1;

//                double totalRotation = 250.0; 
//                double rotationPerTick = totalRotation / (JUMP_INITIAL_VELOCITY / (GRAVITY));
//                rotation -= rotationPerTick;

				// Instead of this one could use jumpVelocity to calculate how many ticks until
				// Arlie reaches the ground. The ticks could be equally, or through a
				// Math.abs(Math.exp) to get a smooth roll with Arlie always landing on his
				// "feet"
				rotationSpeed = ((Math.cos(jumpVelocity / adjustedVelocity)) * 10);
				rotation = arlieBody.getRotate() + rotationSpeed;
			}

			arlieBody.setRotate(rotation);

			jumpVelocity += (adjustedGravity * gravityModifier);

			if (newY >= groundY) {
				if (arlie.getConditionProperty() != ArlieConditions.CONFUSED) {
					if (crouchPressed) {
						arlie.setConditionProperty(ArlieConditions.CROUCHING);
					} else {
						arlie.setConditionProperty(ArlieConditions.RUNNING);
					}
				} else {
					confusedLandedProperty.set(true);
				}

				arlieBody.setTranslateY(groundY);
//                player.playLandSound();
				arlieBody.setRotate(2);
				jumpVelocity = 0;
				doubleJumped = false;

			}
		}

	}

	private void updateCrouch() {

		if (arlie.getConditionProperty() == ArlieConditions.CROUCHING) {
			if (!SMALL_CROUCH)
				arlieBody.setTranslateY(groundY + 30);
			else {
				arlieBody.setTranslateY(groundY + 50);
			}
		}
	}

	public void jumpRelease() {
		if (gravityModifier == 1 && !doubleJumped)
			gravityModifier = 2;
		doubleJumpable = true;
	}

	public void crouch() {
		crouchPressed = true;

		if (arlie.getConditionProperty() == ArlieConditions.RUNNING) {

			arlie.setConditionProperty(ArlieConditions.CROUCHING);
			arlieBody.setRotate(2);

		} else if (arlie.getConditionProperty() == ArlieConditions.JUMPING) {

			gravityModifier = 4;

		}

	}

	public void crouchRelease() {
		crouchPressed = false;

		if (arlie.getConditionProperty() == ArlieConditions.CROUCHING) {

			arlie.setConditionProperty(ArlieConditions.RUNNING);
			if (!SMALL_CROUCH)
				arlieBody.setTranslateY(groundY);
			else {
				arlieBody.setTranslateY(groundY);
			}

		} else if (arlie.getConditionProperty() == ArlieConditions.JUMPING && doubleJumped) {

			gravityModifier = 2;
		}
	}

	public void jump() {
		gravityModifier = 1;
		if (arlie.getConditionProperty() == ArlieConditions.RUNNING) {
			doubleJumpable = false;
			arlie.setConditionProperty(ArlieConditions.JUMPING);
			player.playJumpSound();
			jumpVelocity = adjustedVelocity;

			// Double Jump!
		} else if (arlie.getConditionProperty() == ArlieConditions.JUMPING && doubleJumpable && !doubleJumped
				|| arlie.getConditionProperty() == ArlieConditions.CONFUSED) {
			jumpVelocity = 0.5 * adjustedVelocity;
			doubleJumpable = false;
			doubleJumped = true;

		}

	}

	public void setInvulnerableEffect() {
//    	arlieBody.setEffect(bloom);
//    	arlieBody.setEffect(colorAdjust);
		if (!inGameViewController.godMode && arlieBody.getEffect() == null) {
			arlieBody.setEffect(blend);
		}

	}

	public void setGodModeEffect() {
		arlieBody.setEffect(godBlend);
	}

	public void clearInvulnerableEffect() {
		if (arlieBody.getEffect() != null && !inGameViewController.godMode) {
			arlieBody.setEffect(null);
		}

	}

	private void loadArlieImage() {
		switch (arlie.getConditionProperty()) {
		case RUNNING:
			Image runningImage = new Image(getClass().getResourceAsStream("/assets/images/arlie-running.png"));
			arlieBody.setImage(runningImage);

			break;
		case CROUCHING:
			Image crouchingImage;

			if (SMALL_CROUCH) {
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

		if (arlieBody.getEffect() != null) {
			arlieBody.setEffect(null);
		}

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

	public void reset() {
		if (rotateAnimation != null) {
			rotateAnimation.stop();
			rotateAnimation = null;
		}
		arlieBody.setRotate(0);
		arlie.setConditionProperty(ArlieConditions.RUNNING);
		confusedLandedProperty.set(false);
		crouchPressed = false;
		doubleJumped = false;
	}

	public ImageView getArlieBody() {
		return arlieBody;
	}

	public SimpleBooleanProperty getConfusedLandedProperty() {
		return confusedLandedProperty;

	}

}
