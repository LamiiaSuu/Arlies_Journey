package controller.uicomponents;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HitBoxManager {

	private static final double hitboxRadiusFactor = 0.75;

	public static boolean checkCollision(ImageView image1, ImageView image2, String obstacleType, GraphicsContext gc,
			boolean hitBoxVisible) {

		double radius1 = image1.getBoundsInParent().getHeight() * hitboxRadiusFactor / 2.25;
		double centerX1 = image1.getBoundsInParent().getMinX() + image1.getBoundsInParent().getWidth() / 2.0;
		double centerY1 = image1.getBoundsInParent().getMinY() + image1.getBoundsInParent().getHeight() / 2.0;

		if (hitBoxVisible) {
			drawCircleArlie(centerX1, centerY1, radius1, gc);
		}

		// Can now change hitboxes/circles per case
		if (obstacleType.equalsIgnoreCase("tree")) {
			double radius2 = image2.getBoundsInParent().getHeight() * (hitboxRadiusFactor * 0.75) / 2.0;
			double centerX2 = image2.getBoundsInParent().getMinX() + image2.getBoundsInParent().getWidth() / 2.0;
			double centerY2 = image2.getBoundsInParent().getMinY() + image2.getBoundsInParent().getHeight() / 2.0;

			if (hitBoxVisible) {
				drawCircleObstacles(centerX2, centerY2, radius2, gc, obstacleType);
			}

			return circlesIntersect(centerX1, centerY1, radius1, centerX2, centerY2, radius2);
		}

		else if (obstacleType.equalsIgnoreCase("fruit-tree")) {
			double radius2 = image2.getBoundsInParent().getHeight() * (hitboxRadiusFactor * 0.60) / 2.0;
			double centerX2 = image2.getBoundsInParent().getMinX() + image2.getBoundsInParent().getWidth() / 2.0;
			double centerY2 = image2.getBoundsInParent().getMinY() + image2.getBoundsInParent().getHeight() / 2.0;

			if (hitBoxVisible) {
				drawCircleObstacles(centerX2, centerY2, radius2, gc, obstacleType);
			}

			return circlesIntersect(centerX1, centerY1, radius1, centerX2, centerY2, radius2);
		}

		else if (obstacleType.equalsIgnoreCase("bush")) {
			double radius2 = image2.getBoundsInParent().getHeight() * (hitboxRadiusFactor * 1) / 2.0;
			double centerX2 = image2.getBoundsInParent().getMinX() + image2.getBoundsInParent().getWidth() / 2.0;
			double centerY2 = image2.getBoundsInParent().getMinY() + image2.getBoundsInParent().getHeight() / 2.0;

			if (hitBoxVisible) {
				drawCircleObstacles(centerX2, centerY2, radius2, gc, obstacleType);
			}

			return circlesIntersect(centerX1, centerY1, radius1, centerX2, centerY2, radius2);
		}

		else if (obstacleType.equalsIgnoreCase("flower-bush")) {
			double radius2 = image2.getBoundsInParent().getHeight() * (hitboxRadiusFactor * 1) / 2.0;
			double centerX2 = image2.getBoundsInParent().getMinX() + image2.getBoundsInParent().getWidth() / 2.0;
			double centerY2 = image2.getBoundsInParent().getMinY() + image2.getBoundsInParent().getHeight() / 2.0;

			if (hitBoxVisible) {
				drawCircleObstacles(centerX2, centerY2, radius2, gc, obstacleType);
			}

			return circlesIntersect(centerX1, centerY1, radius1, centerX2, centerY2, radius2);
		}

		else if (obstacleType.equalsIgnoreCase("zeppelin")) {
			double radius2 = image2.getBoundsInParent().getHeight() * (hitboxRadiusFactor * 0.75) / 2.0;
			double centerX2 = image2.getBoundsInParent().getMinX() + image2.getBoundsInParent().getWidth() / 2.0;
			double centerY2 = image2.getBoundsInParent().getMinY() + image2.getBoundsInParent().getHeight() / 2.0;

			if (hitBoxVisible) {
				drawCircleObstacles(centerX2, centerY2, radius2, gc, obstacleType);
			}

			return circlesIntersect(centerX1, centerY1, radius1, centerX2, centerY2, radius2);
		}

		else {
			double radius2 = image2.getBoundsInParent().getHeight() * (hitboxRadiusFactor * 0.85) / 2.0;
			double centerX2 = image2.getBoundsInParent().getMinX() + image2.getBoundsInParent().getWidth() / 3.5;
			double centerY2 = image2.getBoundsInParent().getMinY() + image2.getBoundsInParent().getHeight() / 2.25;

			if (hitBoxVisible) {
				drawCircleObstacles(centerX2, centerY2, radius2, gc, obstacleType);
			}

			return circlesIntersect(centerX1, centerY1, radius1, centerX2, centerY2, radius2);
		}
	}

	private static boolean circlesIntersect(double centerX1, double centerY1, double radius1, double centerX2,
			double centerY2, double radius2) {
		double deltaX = centerX2 - centerX1;
		double deltaY = centerY2 - centerY1;
		double distanceSquared = deltaX * deltaX + deltaY * deltaY;
		double sumRadius = radius1 + radius2;
		double sumRadiusSquared = sumRadius * sumRadius;

		return distanceSquared <= sumRadiusSquared;
	}

	private static void drawCircleArlie(double centerX, double centerY, double radius, GraphicsContext gc) {
//    	gc.clearRect(centerX-100, centerY-100, centerX+100, centerY+100);

		gc.setFill(Color.TRANSPARENT);
		gc.setStroke(Color.RED);
		gc.setLineWidth(3);
		gc.strokeOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
		gc.setLineWidth(1.5);
		gc.setFont(new Font(Font.getDefault().getName(), 30));
		gc.strokeText("Arlie", centerX - 10, centerY - radius - 20);
	}

	private static void drawCircleObstacles(double centerX, double centerY, double radius, GraphicsContext gc,
			String obstacleType) {
//    	gc.clearRect(centerX-radius*1.25, centerY-radius*2 - 40, radius*3+40, radius*3+60);

		gc.setFill(Color.TRANSPARENT);
		gc.setStroke(Color.RED);
		gc.setLineWidth(2);
		gc.strokeOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
		gc.setLineWidth(1);
		gc.setFont(new Font(Font.getDefault().getName(), 30));
		gc.strokeText(obstacleType, centerX - 10, centerY - radius - 20);
	}

	public static void clearCanvas(GraphicsContext gc) {
		gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

	}

}
