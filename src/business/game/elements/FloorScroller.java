package business.game.elements;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Random;

public class FloorScroller {
    private static final int IMAGE_WIDTH = 1920;
	//FPS_60 = 16, FPS_144 = 7, FPS_240 = 4
    private static final int FPS = 7;

    private ImageView[] imageViewFloor = new ImageView[50];

    private Random random;
    private double y = 0;
    private Pane root;
    private Timeline timeline;
    private Image[] imagesFloor;

    public FloorScroller(Pane root) {
        this.root = root;
        start();
    }

    public void start() {
        generateNewSeed();

        Duration duration = Duration.millis(FPS/2); 
        KeyFrame keyFrame = new KeyFrame(duration, event -> {
            y -= 2.5;
            for (int i = 0; i < imageViewFloor.length; i++) {
                double newX = y + IMAGE_WIDTH * i;

                while (newX < -IMAGE_WIDTH) {
                    newX += IMAGE_WIDTH * imageViewFloor.length;
                }
                imageViewFloor[i].setX(newX);
            }
        });

        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void stopTimer() {
        timeline.pause();
    }

    public void startTimer() {
        timeline.play();
    }

    public void reset() {
        timeline.pause();

        root.getChildren().removeAll(imageViewFloor);

        generateNewSeed();

        y = 0;

        timeline.play();
    }

    public void generateNewSeed() {
        imagesFloor = null;

        random = new Random();
        int seed = 0;

        imagesFloor = new Image[imageViewFloor.length];
        Image dirt = new Image(getClass().getResourceAsStream("/assets/images/ground-dirt-mirrored-fixed.png"));
        Image dirtTelephone = new Image(getClass().getResourceAsStream("/assets/images/ground-dirt-mirrored-2-fixed.png"));
        Image dirtNeighbour = new Image(getClass().getResourceAsStream("/assets/images/ground-dirt-mirrored-3-fixed.png"));

        for (int x = 0; x < imagesFloor.length; x++) {
            seed = random.nextInt(100) + 1;
            if (seed > 98) {
                imagesFloor[x] = dirtNeighbour;
                System.out.println("Totoro!");
            } else if (seed > 90) {
                imagesFloor[x] = dirtTelephone;
                System.out.println("Telephone Booth");
            } else {
                imagesFloor[x] = dirt;
                System.out.println("Dirt.");
            }
        }

        for (int i = 0; i < imageViewFloor.length; i++) {
            imageViewFloor[i] = new ImageView(imagesFloor[i]);
            imageViewFloor[i].setX(IMAGE_WIDTH * i);

            imageViewFloor[i].setPreserveRatio(true);
            imageViewFloor[i].setFitWidth(1920);
            imageViewFloor[i].setY(-1080 * 0.33);
            root.getChildren().add(imageViewFloor[i]);
        }
    }
}
