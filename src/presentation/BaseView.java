package presentation;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public abstract class BaseView extends GridPane {
	
	public BaseView() {
		// set column sizes
    	ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(33.3);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(33.3);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(33.3);
        getColumnConstraints().addAll(column1, column2, column3);
        
        // set row sizes
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(33.3);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(33.3);
        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(33.3);
        getRowConstraints().addAll(row1, row2, row3);
	}

}
