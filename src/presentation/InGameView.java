package presentation;

import business.game.elements.Arlie;
import business.game.elements.Floor;
import javafx.scene.layout.Pane;

public class InGameView extends Pane {

    private Arlie arlie;
    private Floor floor;

    public InGameView() {
        initialize();
    }

    private void initialize() {
        // Create Arlie with a size of 50x50
        arlie = new Arlie(50, 50);
        

        getChildren().add(arlie);
    }

    @Override
    protected void layoutChildren() {
        // Create Floor at the bottom quarter of the window
        double floorY = getHeight() * 0.75; // Adjust the percentage as needed
        arlie.setLayoutY(floorY - arlie.getHeight());
        if (floor == null) {
            floor = new Floor(0, floorY, getWidth(), floorY);
            getChildren().add(floor);
        } else {
            floor.setStartY(floorY);
            floor.setEndY(floorY);
        }

        super.layoutChildren();
    }

    // You can add additional methods or properties specific to the in-game view
}