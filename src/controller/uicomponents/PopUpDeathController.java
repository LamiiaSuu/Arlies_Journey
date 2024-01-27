package controller.uicomponents;

import application.App;
import controller.BaseViewController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import presentation.PrimaryViewNames;
import presentation.uicomponents.PopUpMenuView;

public class PopUpDeathController extends BaseViewController {

	private App app;
	PopUpMenuView root;
    private Button continueButton;
    private Button menuButton;
    private Button settingsButton;
    
	public PopUpDeathController(App app, Scene scene) {
        root = new PopUpMenuView();
        continueButton = root.continueButton;
        menuButton = root.menuButton;
        settingsButton = root.settingsButton;
        this.app = app;

        initialize();
    }
	
	@Override
    public void initialize() {
    	
        //continueButton.setOnAction(event -> app.switchView(PrimaryViewNames.IN_GAME_VIEW));
        menuButton.setOnAction(event -> app.switchView(PrimaryViewNames.MAIN_MENU_VIEW));
        //settingsButton.setOnAction(event -> app.switchView(PrimaryViewNames.SETTINGS_VIEW));
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
