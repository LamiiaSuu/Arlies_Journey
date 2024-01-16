package business.game.elements;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Arlie {

    public Rectangle arlieBody;

    public Arlie() {
        arlieBody = new Rectangle(50, 50);
        arlieBody.setFill(Color.GREENYELLOW);  // You can customize the color here
    }

    public Rectangle getArlieRectangle() {
        return arlieBody;
    }

    // Add any additional methods or properties for Arlie as needed
}
