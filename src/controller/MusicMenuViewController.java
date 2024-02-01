package controller;

import java.io.File;

import application.App;
import business.music.MP3Player;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javazoom.jl.player.Player;
import presentation.MusicMenuView;
import presentation.PrimaryViewNames;

public class MusicMenuViewController extends BaseViewController {

	public Button backButton;
	MusicMenuView root;
	App app;
	MP3Player player;
	MainMenuViewController mainMenuViewController;
	private RotateTransition rotateAnimation;
	private ScaleTransition jumpAnimation;

	public MusicMenuViewController(App app, Scene scene, MP3Player player,
			MainMenuViewController mainMenuViewController) {
		this.root = new MusicMenuView(this);
		this.app = app;
		this.player = player;
		this.mainMenuViewController = mainMenuViewController;
		backButton = root.backButton;

		initialize();
	}

	@Override
	public void initialize() {
		backButton.setOnMouseEntered(event -> {
			jumpAnimation(backButton);
//    		rotateAnimation(backButton);
		});
		backButton.setOnMouseExited(event -> stopBackAnimation(backButton));

		// It actually lags without stopping the animation xD
		backButton.setOnAction(event -> stopBackAnimation(backButton));
		backButton.setOnAction(event -> app.switchView(PrimaryViewNames.MAIN_MENU_VIEW));

		createSongButtons();
	}

	private void createSongButtons() {

		String musicFolderPath = "src/assets/songs/";

		File musicFolder = new File(musicFolderPath);
		File[] files = musicFolder.listFiles();
		System.out.println(files.length);
		if (files != null) {
			for (File file : files) {

				Button songButton = new Button(file.getName().replaceAll(".mp3", ""));
				songButton.getStyleClass().add("menu-button");
				songButton.setId("empty-button");

				songButton.setOnAction(event -> {
					playSong(file);
					mainMenuViewController.hideContinue();
					app.switchView(PrimaryViewNames.MAIN_MENU_VIEW);
				});

				root.songButtons.add(songButton);
			}
		}
		root.addSongButtons();
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

	public void playSong(File file) {
		player.selectSong(file.getName());
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	public Pane getRoot() {
		return root;
	}

}
