package presentation;

import business.game.elements.Arlie;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class InGameView extends BaseView {

    public Arlie arlie;
    public Pane arliePane;
    public Pane obstaclePane;

    public InGameView(Scene scene) {
        arlie = new Arlie();
        
        arliePane = new Pane();
        obstaclePane = new Pane();
        
        arliePane.getChildren().add(arlie.arlieBody);
 
        getChildren().addAll(arliePane, obstaclePane);

        setManaged(false);
        
        arlie.arlieBody.setTranslateX(scene.getWidth() * 0.15);
        arlie.arlieBody.setTranslateY(scene.getHeight() * 0.565);
    }


    
    public Pane getArliePane() {
    	return arliePane;
    }
    
    public Pane getObstaclePane() {
    	return obstaclePane;
    }
}
