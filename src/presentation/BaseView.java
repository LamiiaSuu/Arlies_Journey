package presentation;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class BaseView extends BorderPane {
	
	protected VBox center, left, right;
	protected HBox top, bottom;
	
	public BaseView() {
		center = new VBox();
		left = new VBox();
		right = new VBox();
		
		top = new HBox();
		bottom = new HBox();
		
	}

}
