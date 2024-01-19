package controller;

import application.App;
import controller.uicomponents.ArlieController;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import presentation.InGameView;

public class InGameViewController extends BaseViewController {
	
	InGameView root;
	ArlieController arlieController;
	
	
	public InGameViewController(App app, Scene scene) {
		
		root = new InGameView();
		
		arlieController = new ArlieController(root, scene);
		
		
		initialize();
		
	}

	@Override
	public void initialize() {
		
		arlieController.initialize();
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
    public Pane getRoot() {
        return root;
    }
}
