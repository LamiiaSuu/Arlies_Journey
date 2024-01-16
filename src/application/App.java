package application;

import java.util.HashMap;

import business.game.elements.Arlie;
import business.music.MP3Player;
import controller.InGameViewController;
import controller.JourneySelectionViewController;
import controller.MainMenuViewController;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.stage.Stage;
import presentation.InGameView;
import presentation.PrimaryViewNames;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class App extends Application {

	private Stage primaryStage;
	private HashMap<PrimaryViewNames, Pane> primaryViews;
	private SimpleObjectProperty<PrimaryViewNames> currentView;
	Pane inGameView;
	Pane journeySelectionView;
	Pane mainMenuView;
	
	public static void main(String[] args) {
		launch();
	}
	
	public void initialize(){
		primaryViews = new HashMap<>();
		
		InGameViewController inGameViewController = new InGameViewController(this);
		inGameView = inGameViewController.getRoot();
		primaryViews.put(PrimaryViewNames.IN_GAME_VIEW, inGameView);

		JourneySelectionViewController journeySelectionViewController = new JourneySelectionViewController(this);
		journeySelectionView = journeySelectionViewController.getRoot();
		primaryViews.put(PrimaryViewNames.JOURNEY_SELECTION_VIEW, journeySelectionView);
		
		MainMenuViewController mainMenuViewController = new MainMenuViewController(this);
		mainMenuView = mainMenuViewController.getRoot();
		primaryViews.put(PrimaryViewNames.MAIN_MENU_VIEW, mainMenuView);
	}
	
	@Override
    public void start(Stage primaryStage) throws Exception {
		initialize();
		
		
        this.primaryStage = primaryStage;

        Pane root = new Pane();
      

        Scene scene = new Scene(root, 630, 480);
        primaryStage.setTitle("Arlie's Journey");
        primaryStage.setScene(scene);
        currentView = new SimpleObjectProperty<PrimaryViewNames>();
        switchView(PrimaryViewNames.IN_GAME_VIEW);
        

        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(600);
        primaryStage.setMaxHeight(600);
        primaryStage.setMaxWidth(800);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
           
            primaryStage.close();
        });

    }

	public void switchView(PrimaryViewNames viewName) {
		Scene currentScene = primaryStage.getScene();
		Pane nextView = primaryViews.get(viewName);
		if (nextView != null) {
			currentScene.setRoot(nextView);
			currentView.set(viewName);
		}
		System.out.println(nextView);
		System.out.println(currentView);
	}
	
	public SimpleObjectProperty<PrimaryViewNames> currentViewProperty(){
		return currentView;
	}
	
	 
}
