package controller;

import application.App;
import javafx.scene.layout.Pane;
import presentation.BaseView;
import presentation.InGameView;
import presentation.JourneySelectionView;

public class JourneySelectionViewController extends BaseViewController {
	
	JourneySelectionView root;
	
	public JourneySelectionViewController(App app) {
		root = new JourneySelectionView();
		
		
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
