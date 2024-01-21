package presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import presentation.uicomponents.CurrentSongView;
import presentation.uicomponents.VolumeView;

public class MainMenuView extends BorderPane {
    public Button newJourneyButton;
    public Button musicButton;
    public Button settingsButton;
    public VolumeView volumeView;
    public CurrentSongView currentSongView;

    public MainMenuView() {
        VBox mainButtonsLayout = new VBox();
        mainButtonsLayout.setSpacing(25);

        newJourneyButton = new Button();
        musicButton = new Button();
        settingsButton = new Button();
        volumeView = new VolumeView();
        currentSongView = new CurrentSongView();

        newJourneyButton.getStyleClass().add("menu-button");
        musicButton.getStyleClass().add("menu-button");
        settingsButton.getStyleClass().add("menu-button");

        newJourneyButton.setId("new-journey-button");
        musicButton.setId("music-button");
        settingsButton.setId("settings-button");

        mainButtonsLayout.setScaleX(0.8);
        mainButtonsLayout.setScaleY(0.8);
        mainButtonsLayout.setAlignment(Pos.CENTER);
        setAlignment(mainButtonsLayout, Pos.CENTER);

        mainButtonsLayout.getChildren().addAll(newJourneyButton, musicButton, settingsButton);
        
        volumeView.setScaleX(0.4);
        volumeView.setScaleY(0.4);
        setAlignment(volumeView, Pos.BOTTOM_RIGHT);

        setCenter(mainButtonsLayout);
        	// This kept bugging me... Everytime I added the volumeView - which BTW fully works - it just fucks up the alignment of everything else
        //setBottom(volumeView);

        setId("main-menu-panel");
    }
}
