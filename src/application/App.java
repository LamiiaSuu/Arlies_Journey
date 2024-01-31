package application;

import java.util.HashMap;

import business.music.MP3Player;
import controller.InGameViewController;
import controller.MainMenuViewController;
import controller.SettingsMenuViewController;
import controller.MusicMenuViewController;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.stage.Stage;
import presentation.PrimaryViewNames;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class App extends Application {

	private Stage primaryStage;
	private HashMap<PrimaryViewNames, Pane> primaryViews;
	private SimpleObjectProperty<PrimaryViewNames> currentView;
	InGameViewController inGameViewController;
	Pane inGameView;
	Pane journeySelectionView;
	Pane mainMenuView;
	Pane musicMenuView;
	Pane settingsMenuView;
	MP3Player player;
	
	public static void main(String[] args) {
		launch();
	}
	
	public void initialize(Scene scene){
		primaryViews = new HashMap<>();
		
		player = new MP3Player();
		player.start();
		
		MainMenuViewController mainMenuViewController = new MainMenuViewController(this, scene, player);
		mainMenuView = mainMenuViewController.getRoot();
		primaryViews.put(PrimaryViewNames.MAIN_MENU_VIEW, mainMenuView);
		
		inGameViewController = new InGameViewController(this, scene, player, mainMenuViewController);
		inGameView = inGameViewController.getRoot();
		primaryViews.put(PrimaryViewNames.IN_GAME_VIEW, inGameView);
		
		SettingsMenuViewController settingsMenuViewController = new SettingsMenuViewController(this, scene, player, inGameViewController, mainMenuViewController);
		settingsMenuView = settingsMenuViewController.getRoot();
		primaryViews.put(PrimaryViewNames.SETTINGS_VIEW, settingsMenuView);
		
		MusicMenuViewController musicMenuViewController = new MusicMenuViewController(this, scene, player, mainMenuViewController);
		musicMenuView = musicMenuViewController.getRoot();
		primaryViews.put(PrimaryViewNames.MUSIC_VIEW, musicMenuView);
	}
	
	
	
	@Override
    public void start(Stage primaryStage) throws Exception {
		
		
		
        this.primaryStage = primaryStage;
        
        primaryStage.setMinHeight(720);
        primaryStage.setMinWidth(1280);
        primaryStage.setMaxHeight(720);
        primaryStage.setMaxWidth(1280);
        
        
        Pane root = new Pane();
        currentView = new SimpleObjectProperty<PrimaryViewNames>();

        Scene scene = new Scene(root,  1280, 720);
        scene.getStylesheets().add(getClass().getResource("/assets/styles.CSS").toExternalForm());
        initialize(scene);

        primaryStage.setTitle("Arlie's Journey");
        primaryStage.setScene(scene);
        
        switchView(PrimaryViewNames.MAIN_MENU_VIEW);
        
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
        	
        	inGameViewController.pauseGame();
        	player.stop();
        	primaryStage.close();
        });
    }

	public void switchView(PrimaryViewNames viewName) {
		Scene currentScene = primaryStage.getScene();
		Pane nextView = primaryViews.get(viewName);
		System.out.println(nextView);
		if (nextView != null) {
			currentScene.setRoot(nextView);
			currentView.set(viewName);
		}
		System.out.println(currentView);
	}
	
	
	public SimpleObjectProperty<PrimaryViewNames> currentViewProperty(){
		return currentView;
	}
	
	public double getWidth() {
		return primaryStage.getMaxWidth();
	}
	
	public double getHeight() {
		
		return primaryStage.getMaxHeight();
	}
	
	public Stage getStage() {
		return primaryStage;
	}
	
	public InGameViewController getInGameViewController() {
		return inGameViewController;
	}
	 
}
