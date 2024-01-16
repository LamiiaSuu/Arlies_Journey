package controller.uicomponents;

import business.game.elements.Arlie;
import controller.BaseViewController;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import presentation.InGameView;

public class ArlieController extends BaseViewController {

    Rectangle arlieBody;
    InGameView root;

    public ArlieController(InGameView root) {
        if(root != null) {
            this.root = root;
            arlieBody = root.arlie.getArlieRectangle();

            // Set focus on the root to enable key events
            root.setFocusTraversable(true);
            root.requestFocus();
        }
    }

    @Override
    public void initialize() {
        // Add event handlers for key presses and releases
        root.setOnKeyPressed(event -> handleKeyPress(event.getCode()));
        root.setOnKeyReleased(event -> handleKeyRelease(event.getCode()));
    }

    @Override
    public void update() {
        // TODO: Update code, if needed
    }

    private void handleKeyPress(KeyCode code) {
        System.out.println("Key Pressed: " + code);
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
        System.out.println("Key Released: " + code);
        switch (code) {
            case DOWN:
                arlieBody.setHeight(arlieBody.getHeight() * 2);
                break;
            // Add more cases for other actions if needed
        }
    }
}
