package controller;

import application.App;
import business.music.MP3Player;
import controller.uicomponents.Difficulty;
import controller.uicomponents.VolumeViewController;
import javafx.animation.RotateTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import presentation.PrimaryViewNames;
import presentation.SettingsMenuView;
import presentation.uicomponents.CurrentSongView;
import presentation.uicomponents.VolumeView;
import presentation.uicomponents.VolumeView.VolumeConditions;
import javafx.animation.ScaleTransition;

public class SettingsMenuViewController extends BaseViewController {
    private App app;
    SettingsMenuView root;
    VolumeViewController volumeViewController;
    MainMenuViewController mainMenuViewController;
    public Button FPSButton;
    public Button placeHolder1;
    public Button difficultyButton;
    public Button backButton;
    public Button volumeButton;
    public VolumeView volumeView;
    public CurrentSongView currentSongView;
    private InGameViewController inGameViewController;
    private ImageView arliesJourneyImageView;
    private MP3Player player;
    
    private RotateTransition rotateAnimation;
    private ScaleTransition jumpAnimation;

    public SettingsMenuViewController(App app, Scene scene, MP3Player player, InGameViewController inGameViewController, MainMenuViewController mainMenuViewController) {
        root = new SettingsMenuView();
        arliesJourneyImageView = root.titleView.title;
        backButton = root.backButton;
        FPSButton = root.FPSButton;
        placeHolder1 = root.placeHolder1;
        difficultyButton = root.difficultyButton;
        volumeButton = root.volumeView.volumeButton;
        this.inGameViewController = inGameViewController;
        this.mainMenuViewController = mainMenuViewController;
        this.app = app;
        this.player = player;

//        Am not using it atm... code is all in here
//        volumeViewController = new VolumeViewController();

        initialize();
    }

    @Override
    public void initialize() {
    	
    	backButton.setOnMouseEntered(event -> {
    		jumpAnimation(backButton);
//    		rotateAnimation(backButton);
    	});
    	backButton.setOnMouseExited(event -> stopBackAnimation(backButton));

        //It actually lags without stopping the animation xD
    	backButton.setOnAction(event -> stopBackAnimation(backButton));
        backButton.setOnAction(event -> app.switchView(PrimaryViewNames.MAIN_MENU_VIEW));
        
        FPSButton.setOnAction(event -> {

        	if(inGameViewController.getFPS() == 60) {
        		FPSButton.setId("fps-144-button");
        		inGameViewController.setFPS(144);
        		mainMenuViewController.hideContinue();
        	}else if(inGameViewController.getFPS() == 144){
        		
        		FPSButton.setId("fps-540-button");
        		inGameViewController.setFPS(540);
        		mainMenuViewController.hideContinue();
        	}else {
        		FPSButton.setId("fps-60-button");
        		inGameViewController.setFPS(60);
        		mainMenuViewController.hideContinue();
        	}
        	System.out.println(inGameViewController.getFPS());
        });
        
        difficultyButton.setOnAction(event -> {
        	
        	mainMenuViewController.hideContinue();
        	
        	if(inGameViewController.getDifficulty() == Difficulty.EASY) {
        		inGameViewController.setDifficulty(Difficulty.MEDIUM);
        	}else if(inGameViewController.getDifficulty() == Difficulty.MEDIUM){
        		inGameViewController.setDifficulty(Difficulty.HARD);

        	}else if (inGameViewController.getDifficulty() == Difficulty.HARD){
        		inGameViewController.setDifficulty(Difficulty.EASY);
        	}
        	
        	System.out.println(inGameViewController.getDifficulty().toString());
        });
        
//      musicButton.setOnMouseEntered(event -> jumpAnimation(musicButton));
//      musicButton.setOnMouseExited(event -> stopAnimation(musicButton));
//
//      //It actually lags without stopping the animation xD
//      musicButton.setOnAction(event -> stopAnimation(musicButton));
//      musicButton.setOnAction(event -> app.switchView(PrimaryViewNames.MUSIC_VIEW));
        
//        settingsButton.setOnMouseEntered(event -> jumpAnimation(settingsButton));
//        settingsButton.setOnMouseExited(event -> stopAnimation(settingsButton));
//
//        //It actually lags without stopping the animation xD
//        settingsButton.setOnAction(event -> stopAnimation(settingsButton));
//        settingsButton.setOnAction(event -> app.switchView(PrimaryViewNames.SETTINGS_VIEW));
        
        
        volumeButton.setOnAction(event -> cycleVolume());

        
        
    }


	public void jumpAnimation(Button button) {
    	

        jumpAnimation = new ScaleTransition(Duration.seconds(0.1), button);
        jumpAnimation.setFromX(0.6);
        jumpAnimation.setFromY(0.6);
        jumpAnimation.setToX(0.7);
        jumpAnimation.setToY(0.7);


        jumpAnimation.setCycleCount(1);


        jumpAnimation.setAutoReverse(true);


        jumpAnimation.play();
    }
	
	public void rotateAnimation(Button button) {
    	

        rotateAnimation = new RotateTransition(Duration.seconds(0.5), button);
        rotateAnimation.setFromAngle(-2); 
        rotateAnimation.setToAngle(2);   
        rotateAnimation.setCycleCount(100); 

        rotateAnimation.setAutoReverse(true);

        rotateAnimation.play();
    }
    
    public void stopAnimation(Button button) {
    	button.setScaleX(1);
    	button.setScaleY(1);
    	button.setRotate(0);
//    	rotateAnimation.stop();
    }
    
    public void stopBackAnimation(Button button) {
    	button.setScaleX(0.6);
    	button.setScaleY(0.6);
    	button.setRotate(0);
//    	rotateAnimation.stop();
    }
    
    private void cycleVolume() {

    	switch(root.volumeView.getVolumeConditionProperty()){
    		
    	case MUTED:
    		root.volumeView.setVolumeConditionProperty(VolumeConditions.LOW);
    		volumeButton.setId("volume-button-low");
    		player.setVolumeLow();
    		break;
    		
    	case LOW:
    		root.volumeView.setVolumeConditionProperty(VolumeConditions.MEDIUM);
    		volumeButton.setId("volume-button-medium");
    		player.setVolumeMedium();
    		break;
    		
    	case MEDIUM:
    		root.volumeView.setVolumeConditionProperty(VolumeConditions.HIGH);
    		volumeButton.setId("volume-button-high");
    		player.setVolumeHigh();
    		break;
    		
    	case HIGH:
    		root.volumeView.setVolumeConditionProperty(VolumeConditions.MUTED);
    		volumeButton.setId("volume-button-mute");
    		player.setVolumeMuted();
    		break;
    		
    	}
	}

    @Override
    public void update() {
        // TODO Auto-generated method stub
    }

    public Pane getRoot() {
        return root;
    }
}
