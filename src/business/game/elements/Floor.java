package business.game.elements;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Floor extends Line {

    public Floor(double startX, double startY, double endX, double endY) {
        super(startX, startY, endX, endY);
        setStroke(Color.BLACK);
        // You can customize other properties of the line here
    }

    // Add any additional methods or properties for Floor as needed
}
