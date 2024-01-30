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
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	public PopUpDeathView getPopupRoot() {
		return root;
	}

	public Button getButton(String button) {
		switch (button) {
		default:
			return newJourneyButton;
		case "menuButton":
			return menuButton;
		case "settingsButton":
			return settingsButton;
		}
	}

}
