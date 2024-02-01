package controller;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import presentation.BaseView;

public abstract class BaseViewController {

	BaseView root; // Correct type

	public abstract void initialize();

	public abstract void update();

	public Pane getRoot() {
		return root;
	}
}
