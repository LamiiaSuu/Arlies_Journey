package business.game.elements;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class BackgroundScroll {
    private static final int IMAGE_WIDTH = 1920; // Assuming all images have the same width
    private ImageView[] imageViews = new ImageView[4];
    private int currentIndex = 0;
    private double x = 0;

    public BackgroundScroll() {
        // Constructor with no parameters
    }

    public void start(Pane rootPane) {
        // Load images
        Image[] images = new Image[4];
        images[0] = new Image(getClass().getResourceAsStream("/assets/images/mountains-1.png"));
        images[1] = new Image(getClass().getResourceAsStream("/assets/images/mountains-1-variant.png"));
        images[2] = new Image(getClass().getResourceAsStream("/assets/images/mountains-2.png"));
        images[3] = new Image(getClass().getResourceAsStream("/assets/images/mountains-2-variant.png"));

        // Create image views and add them to the root pane
        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i] = new ImageView(images[i]);
            imageViews[i].setX(IMAGE_WIDTH * i);
            rootPane.getChildren().add(imageViews[i]);
        }

        // Animation timer to scroll the background
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                x -= 0.5; // Adjust scrolling speed here
                if (x <= -IMAGE_WIDTH * (imageViews.length - 1)) { // Check if the last image has reached the end
                    x = 0; // Reset the x position to restart scrolling
                }

                for (int i = 0; i < imageViews.length; i++) {
                    imageViews[i].setX(x + IMAGE_WIDTH * i);
                }
            }
        };
        timer.start();
    }
}
