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

        // Update the position and dimensions based on the current size of the pane
        arlie.arlieBody.setTranslateX(getWidth() * 0.3 - arlie.arlieBody.getFitWidth());
        arlie.arlieBody.setTranslateY(getHeight() * 0.8 - arlie.arlieBody.getFitHeight());
    }
}
