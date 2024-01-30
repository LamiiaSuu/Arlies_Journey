package controller.uicomponents;

import application.App;
import controller.BaseViewController;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import presentation.uicomponents.PopUpMenuView;
import presentation.PrimaryViewNames;

public class PopUpMenuViewController extends BaseViewController {
	
	private App app;
	private PopUpMenuView root;
    private Button continueButton;
    private Button menuButton;
    private Button settingsButton;
    
	public PopUpMenuViewController(App app, Scene scene) {
        root = new PopUpMenuView();
        continueButton = root.continueButton;
        menuButton = root.menuButton;
        settingsButton = root.settingsButton;
        this.app = app;

        initialize();
    }
	
	@Override
    public void initialize() {
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
	
	public PopUpMenuView getPopupRoot() {
		return root;
	}
	
	public Button getButton(String button) {
		switch (button) {
		default:
			return continueButton;
		case "menuButton":
			return menuButton;
		case "settingsButton":
			return settingsButton;
		}
	}
}
