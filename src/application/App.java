package application;

import java.util.HashMap;

import business.game.elements.Arlie;
import business.music.MP3Player;
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
	private InGameView inGameView;
	
	
	public static void main(String[] args) {
		launch();
	}
	
	public void initialize(){
		inGameView = new InGameView();
		
	}
	
	@Override
    public void start(Stage primaryStage) throws Exception {
		initialize();
		
		
        this.primaryStage = primaryStage;

        Pane root = new Pane();
      

        Scene scene = new Scene(inGameView, 630, 480);
        primaryStage.setTitle("mf is gaming");
        primaryStage.setScene(scene);

        currentView = new SimpleObjectProperty<PrimaryViewNames>();
//        switchView();
        

        primaryStage.setMinHeight(800);
        primaryStage.setMinWidth(600);
        primaryStage.setMaxHeight(1200);
        primaryStage.setMaxWidth(1000);
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
	}
	
	public SimpleObjectProperty<PrimaryViewNames> currentViewProperty(){
		return currentView;
	}
	
	 
}
