package controller.uicomponents;

import application.App;
import controller.BaseViewController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import presentation.PrimaryViewNames;
import presentation.uicomponents.PopUpDeathView;
import presentation.uicomponents.PopUpMenuView;

public class PopUpDeathViewController extends BaseViewController {

	private App app;
	Scene scene;
	private PopUpDeathView root;
    private Button newJourneyButton;
    private Button menuButton;
    private Button settingsButton;
    
	public PopUpDeathViewController(App app, Scene scene) {
        root = new PopUpDeathView();
        newJourneyButton = root.newJourneyButton;
        menuButton = root.menuButton;
        settingsButton = root.settingsButton;
        this.app = app;

        initialize();
    }
	
	@Override
    public void initialize() {
    	
        newJourneyButton.setOnAction(event -> app.switchView(PrimaryViewNames.IN_GAME_VIEW));
        menuButton.setOnAction(event -> app.switchView(PrimaryViewNames.MAIN_MENU_VIEW));
        settingsButton.setOnAction(event -> app.switchView(PrimaryViewNames.SETTINGS_VIEW));
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
	
	public PopUpDeathView getPopupRoot() {
		return root;
	}

}
