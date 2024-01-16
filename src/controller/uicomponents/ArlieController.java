package controller.uicomponents;

import business.game.elements.Arlie;
import controller.BaseViewController;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import presentation.InGameView;

public class ArlieController extends BaseViewController {

    Rectangle arlieBody;
    InGameView root;
    Scene scene;

    public ArlieController(InGameView root, Scene scene) {
        if (root != null) {
            this.root = root;
            arlieBody = root.arlie.getArlieRectangle();

            // Set focus on the root to enable key events
            root.setFocusTraversable(true);
            root.requestFocus();
            this.scene = scene;
        }
    }

    @Override
    public void initialize() {
        // Add event handlers for key presses and releases
        scene.setOnKeyPressed(event -> {
            System.out.println("Key Pressed: " + event.getCode());
            handleKeyPress(event.getCode());
        });
        scene.setOnKeyReleased(event -> {
            System.out.println("Key Released: " + event.getCode());
            handleKeyRelease(event.getCode());
        });
    }

    @Override
    public void update() {
        // TODO: Update code, if needed
    }

    private void handleKeyPress(KeyCode code) {
        switch (code) {
            case SPACE:
                arlieBody.setTranslateY(arlieBody.getTranslateY() - 50);
                break;
            case DOWN:
                arlieBody.setHeight(arlieBody.getHeight() / 2);
                break;
            // Add more cases for other actions if needed
        }
    }

    private void handleKeyRelease(KeyCode code) {
        switch (code) {
        case SPACE:
            arlieBody.setTranslateY(arlieBody.getTranslateY() + 50);
            break;
            case DOWN:
                arlieBody.setHeight(arlieBody.getHeight() * 2);
                break;
            // Add more cases for other actions if needed
        }
    }
    
    private void duck() {
    	
    }
    
    
}
