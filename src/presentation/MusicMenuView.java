package presentation;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.ScrollPane;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import controller.MusicMenuViewController;

public class MusicMenuView extends BaseView {
	
	public Button backButton;
	public VBox songButtonsVBox;
	public ScrollPane scrollPane;
	private MusicMenuViewController musicController;
	
	public List<Button> songButtons;
	
	public MusicMenuView(MusicMenuViewController musicController) {
		
		this.musicController = musicController;
		
        songButtons = new ArrayList<>();
        backButton = new Button();
        
        backButton.getStyleClass().add("menu-button");
        backButton.setId("back-button");
        
        backButton.setScaleX(0.6);
        backButton.setScaleY(0.6);
        
        
        setHalignment(backButton, HPos.LEFT);

        
//        createSongButtons();

        
        songButtonsVBox = new VBox(20);
        
        songButtonsVBox.setAlignment(Pos.CENTER);
        
        setHalignment(songButtonsVBox, HPos.CENTER);
        setValignment(songButtonsVBox, VPos.CENTER);


        scrollPane = new ScrollPane(songButtonsVBox);
        scrollPane.setFitToWidth(true);
        
        scrollPane.getStyleClass().add("scroll-pane");
        scrollPane.setId("song-scroll-pane");
        
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        
        scrollPane.setMaxHeight(500);
        scrollPane.setMinHeight(500);
        scrollPane.setMaxWidth(500);
        scrollPane.setMinWidth(400);
        
        setHalignment(scrollPane, HPos.CENTER);
        setValignment(scrollPane, VPos.CENTER);


        setId("main-menu-panel");
        
        add(scrollPane, 1, 1);
        
        add(backButton, 0, 2);

    }
	
    public void addSongButtons() {

    	songButtonsVBox.getChildren().addAll(songButtons);


    }
}
