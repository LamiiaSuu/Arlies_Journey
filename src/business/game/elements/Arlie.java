package business.game.elements;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Arlie {
    public SimpleObjectProperty<ArlieConditions> condition;
    public ImageView arlieBody;

    public Arlie() {
        condition = new SimpleObjectProperty<>();
        


            // Load an image (replace "path/to/your/image.png" with the actual path to your image file)
            Image arlieImage = new Image(getClass().getResourceAsStream("/assets/Arlie_Transparent.png"));

            // Create an ImageView with the loaded image
            arlieBody = new ImageView(arlieImage);
            arlieBody.setFitWidth(150);  // Set the desired width
            arlieBody.setFitHeight(150); // Set the desired height


    }

    public ImageView getArlieImageView() {
        return arlieBody;
    }
}
