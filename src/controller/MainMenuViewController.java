package controller;

import application.App;
import business.music.MP3Player;
import controller.uicomponents.VolumeViewController;
import javafx.animation.RotateTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import presentation.MainMenuView;
import presentation.PrimaryViewNames;
import presentation.uicomponents.CurrentSongView;
import presentation.uicomponents.VolumeView;
import presentation.uicomponents.VolumeView.VolumeConditions;
import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;

public class MainMenuViewController extends BaseViewController {
	private App app;
	MainMenuView root;
	VolumeViewController volumeViewController;
	private Button newJourneyButton;
//    private Button volumeButton;
	private Button musicButton;
	private Button continueButton;
	private Button settingsButton;
	private ImageView arliesJourneyImageView;
	private MP3Player player;

	private RotateTransition rotateAnimation;
	private ScaleTransition jumpAnimation;

	public MainMenuViewController(App app, Scene scene, MP3Player player) {
		root = new MainMenuView();
		arliesJourneyImageView = root.titleView.title;
		musicButton = root.musicButton;
		newJourneyButton = root.newJourneyButton;
		settingsButton = root.settingsButton;
		continueButton = root.continueButton;
//        volumeButton = root.volumeView.volumeButton;
		this.app = app;
		this.player = player;

//        Am not using it atm... code is all in here
//        volumeViewController = new VolumeViewController();

		initialize();
	}

	@Override
	public void initialize() {
		
		continueButton.setOnMouseEntered(event -> jumpAnimation(continueButton));
		continueButton.setOnMouseExited(event -> stopAnimation(continueButton));
		
		continueButton.setOnAction(event -> stopAnimation(continueButton));
		continueButton.setOnAction(event -> {
			app.switchView(PrimaryViewNames.IN_GAME_VIEW);
		});

		newJourneyButton.setOnMouseEntered(event -> jumpAnimation(newJourneyButton));
		newJourneyButton.setOnMouseExited(event -> stopAnimation(newJourneyButton));

		// It actually lags without stopping the animation xD
		newJourneyButton.setOnAction(event -> {
			stopAnimation(newJourneyButton);
		});
		newJourneyButton.setOnAction(event -> {
			if(app.getInGameViewController() != null)
			app.getInGameViewController().resetGame();
			app.switchView(PrimaryViewNames.IN_GAME_VIEW);
		});

		musicButton.setOnMouseEntered(event -> {
			musicButton.setId("music-button-anim");
			jumpAnimation(musicButton);
			
		});
		musicButton.setOnMouseExited(event -> {
			musicButton.setId("music-button");
			stopAnimation(musicButton);
		});

		// It actually lags without stopping the animation xD
		musicButton.setOnAction(event -> {
			musicButton.setId("music-button");
			stopAnimation(musicButton);
		});
		musicButton.setOnAction(event -> app.switchView(PrimaryViewNames.MUSIC_VIEW));

		settingsButton.setOnMouseEntered(event -> {
			settingsButton.setId("settings-button-anim");
			jumpAnimation(settingsButton);
		});
		settingsButton.setOnMouseExited(event -> {
			settingsButton.setId("settings-button");
			stopAnimation(settingsButton);
		});

		// It actually lags without stopping the animation xD
		settingsButton.setOnAction(event -> {
			settingsButton.setId("settings-button");
			stopAnimation(settingsButton);
		});
		settingsButton.setOnAction(event -> app.switchView(PrimaryViewNames.SETTINGS_VIEW));

		arliesJourneyImageView.setOnMouseClicked(event -> {

			arliesJourneyImageView.setId("title-label-clicked");
			arliesJourneyImageView
					.setImage(new Image(getClass().getResourceAsStream("/assets/images/title-special.png")));

		});

//        volumeButton.setOnAction(event -> cycleVolume());

	}
	
	public void showContinue() {
		continueButton.setVisible(true);
//		root.mainButtonsLayout.setAlignment(Pos.BASELINE_CENTER);
		root.mainButtonsLayout.setScaleX(0.65);
        root.mainButtonsLayout.setScaleY(0.65);
        root.mainButtonsLayout.setSpacing(18);
	}
	
	public void hideContinue() {
		continueButton.setVisible(false);
//		root.mainButtonsLayout.setAlignment(Pos.BASELINE_CENTER);
		root.mainButtonsLayout.setScaleX(0.8);
        root.mainButtonsLayout.setScaleY(0.8);
        root.mainButtonsLayout.setSpacing(25);
	}

	public void jumpAnimation(Button button) {

		jumpAnimation = new ScaleTransition(Duration.seconds(0.05), button);
		jumpAnimation.setFromX(1.0);
		jumpAnimation.setFromY(1.0);
		jumpAnimation.setToX(1.1);
		jumpAnimation.setToY(1.1);

		jumpAnimation.setCycleCount(1);

		jumpAnimation.setAutoReverse(true);

		jumpAnimation.play();

		rotateAnimation = new RotateTransition(Duration.seconds(0.5), button);
		rotateAnimation.setFromAngle(-2);
		rotateAnimation.setToAngle(2);
		rotateAnimation.setCycleCount(100);

		rotateAnimation.setAutoReverse(true);

		rotateAnimation.play();
	}

	public void stopAnimation(Button button) {
		button.setScaleX(1.0);
		button.setScaleY(1.0);
		button.setRotate(0);
		rotateAnimation.stop();
	}

//    private void cycleVolume() {
//
//    	switch(root.volumeView.getVolumeConditionProperty()){
//    		
//    	case MUTED:
//    		root.volumeView.setVolumeConditionProperty(VolumeConditions.LOW);
//    		volumeButton.setId("volume-button-low");
//    		player.setVolumeLow();
//    		break;
//    		
//    	case LOW:
//    		root.volumeView.setVolumeConditionProperty(VolumeConditions.MEDIUM);
//    		volumeButton.setId("volume-button-medium");
//    		player.setVolumeMedium();
//    		break;
//    		
//    	case MEDIUM:
//    		root.volumeView.setVolumeConditionProperty(VolumeConditions.HIGH);
//    		volumeButton.setId("volume-button-high");
//    		player.setVolumeHigh();
//    		break;
//    		
//    	case HIGH:
//    		root.volumeView.setVolumeConditionProperty(VolumeConditions.MUTED);
//    		volumeButton.setId("volume-button-mute");
//    		player.setVolumeMuted();
//    		break;
//    		
//    	}
//	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	public Pane getRoot() {
		return root;
	}
}
