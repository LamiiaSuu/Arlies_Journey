package controller;

import application.App;
import controller.uicomponents.ArlieController;
import javafx.scene.layout.Pane;
import presentation.InGameView;

public class InGameViewController extends BaseViewController {
	
	InGameView root;
	ArlieController arlieController;
	
	
	public InGameViewController(App app) {
		root = new InGameView();
		
		arlieController = new ArlieController(root);
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
