package application;

import java.util.HashMap;

import business.music.MP3Player;
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
	Pane playerView;
	Pane playlistView;
	MP3Player player;
	
	
	public static void main(String[] args) {
		launch();
	}
	
	public void initialize(){
		
	}
	
	@Override
    public void start(Stage primaryStage) throws Exception {
		initialize();
        this.primaryStage = primaryStage;

        Pane root = new Pane();

        Scene scene = new Scene(root, 630, 480);
        scene.getStylesheets().add(getClass().getResource("/assets/styles.CSS").toExternalForm());
        primaryStage.setTitle("MP3 Player");
        primaryStage.setScene(scene);

        currentView = new SimpleObjectProperty<PrimaryViewNames>();
//        switchView();
        

        primaryStage.setMinHeight(800);
        primaryStage.setMinWidth(600);
        primaryStage.setMaxHeight(1200);
        primaryStage.setMaxWidth(1000);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            player.stop();
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
