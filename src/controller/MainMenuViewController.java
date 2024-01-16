package controller;

import application.App;
import javafx.scene.layout.Pane;
import presentation.MainMenuView;

public class MainMenuViewController extends BaseViewController {
		
	MainMenuView root;
	
	public MainMenuViewController(App app) {
		root = new MainMenuView();
		
		
	}
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
    public Pane getRoot() {
        return root;
    }
}
