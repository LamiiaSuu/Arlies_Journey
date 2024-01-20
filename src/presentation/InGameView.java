package presentation;

import business.game.elements.Arlie;
import javafx.scene.layout.Pane;

public class InGameView extends Pane {

    public Arlie arlie;

    public InGameView() {
        arlie = new Arlie();

        getChildren().add(arlie.arlieBody);

        layoutChildren();
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();

        arlie.arlieBody.setTranslateX(getWidth() * 0.25 - arlie.arlieBody.getFitWidth());
        arlie.arlieBody.setTranslateY(getHeight() * 0.7 - arlie.arlieBody.getFitHeight());
        
    }
}
