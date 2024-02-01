package business.game.elements;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.util.Random;

public class FloorScroller {
	private static final int IMAGE_WIDTH = 1920;

	private ImageView[] imageViewFloor = new ImageView[50];

	private Random random;
	private double y = 0;
	private Pane root;
	private AnimationTimer timer;
	private Image[] imagesFloor;
	private int fps;

	public FloorScroller(Pane root) {
		this.root = root;
		start();
	}

	public void setFPS(int fps) {
		this.fps = fps;
	}

	public void start() {

		generateNewSeed();

		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (fps == 60) {
					y -= 12;
				} else if (fps == 144) {
					y -= 5;
				} else {
					y -= 1.33;
				}
				for (int i = 0; i < imageViewFloor.length; i++) {
					double newX = y + IMAGE_WIDTH * i;

					while (newX < -IMAGE_WIDTH) {

						newX += IMAGE_WIDTH * imageViewFloor.length;
					}
					imageViewFloor[i].setX(newX);
				}

			}
		};
		timer.start();

	}

	public void stopTimer() {
		timer.stop();
	}

	public void startTimer() {
		timer.start();
	}

	public void reset() {

		timer.stop();

		root.getChildren().removeAll(imageViewFloor);

		generateNewSeed();

		y = 0;

		timer.start();
	}

	public void generateNewSeed() {

		imagesFloor = null;

		random = new Random();
		int seed = 0;

		imagesFloor = new Image[imageViewFloor.length];
		Image dirt = new Image(getClass().getResourceAsStream("/assets/images/ground-dirt-mirrored-fixed.png"));
		Image dirtTelephone = new Image(
				getClass().getResourceAsStream("/assets/images/ground-dirt-mirrored-2-fixed.png"));
		Image dirtNeighbour = new Image(
				getClass().getResourceAsStream("/assets/images/ground-dirt-mirrored-3-fixed.png"));

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
			imageViewFloor[i].setY(-1080 * 0.33); // Müssen so komisch pixel genau gesetzt werden bc the image is not
													// 1920x1080
			root.getChildren().add(imageViewFloor[i]);

		}

	}
}