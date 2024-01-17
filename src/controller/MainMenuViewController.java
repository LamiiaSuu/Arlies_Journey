package controller;

import application.App;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import presentation.MainMenuView;
import presentation.PrimaryViewNames;

public class MainMenuViewController extends BaseViewController {
    private App app;  // Correcting the variable name to lowercase
    MainMenuView root;
    private Button newJourneyButton;

    public MainMenuViewController(App app, Scene scene) {
        root = new MainMenuView();
        newJourneyButton = root.newJourneyButton;
        this.app = app;  // Corrected variable name to lowercase
        
        initialize();
        }

    @Override
    public void initialize() {
        newJourneyButton.setOnAction(event -> app.switchView(PrimaryViewNames.IN_GAME_VIEW));
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
    }

    public Pane getRoot() {
        return root;
    }
}
