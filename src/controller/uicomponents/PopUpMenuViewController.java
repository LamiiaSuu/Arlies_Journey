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
		
		continueButton.setOnAction((event) -> {
			root.hide();
        });
        
        
        menuButton.setOnAction((event) -> {
        	app.switchView(PrimaryViewNames.MAIN_MENU_VIEW);
        	root.hide();
        });
        
        settingsButton.setOnAction((event) -> {
        	app.switchView(PrimaryViewNames.SETTINGS_VIEW);
        	root.hide();
        });
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
	
	public PopUpMenuView getPopupRoot() {
		return root;
	}
}
