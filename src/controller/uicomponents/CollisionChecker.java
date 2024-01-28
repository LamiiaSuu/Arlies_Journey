package controller.uicomponents;

import javafx.scene.image.ImageView;

public class CollisionChecker {

    private static final double hitboxRadiusFactor = 0.75;

    public static boolean checkCollision(ImageView image1, ImageView image2, String obstacleType) {
        double radius1 = image1.getBoundsInParent().getHeight() * hitboxRadiusFactor / 2.0;
        double centerX1 = image1.getBoundsInParent().getMinX() + image1.getBoundsInParent().getWidth() / 2.0;
        double centerY1 = image1.getBoundsInParent().getMinY() + image1.getBoundsInParent().getHeight() / 2.0;

        
        //Can now change hitboxes/circles per case
        if(obstacleType.equalsIgnoreCase("tree")) {
            double radius2 = image2.getBoundsInParent().getHeight() * (hitboxRadiusFactor * 0.75) / 2.0;
            double centerX2 = image2.getBoundsInParent().getMinX() + image2.getBoundsInParent().getWidth() / 2.0;
            double centerY2 = image2.getBoundsInParent().getMinY() + image2.getBoundsInParent().getHeight() / 2.0;
            
            return circlesIntersect(centerX1, centerY1, radius1, centerX2, centerY2, radius2);
        }
        else {
                double radius2 = image2.getBoundsInParent().getHeight() * (hitboxRadiusFactor * 0.75) / 2.0;
                double centerX2 = image2.getBoundsInParent().getMinX() + image2.getBoundsInParent().getWidth() / 2.0;
                double centerY2 = image2.getBoundsInParent().getMinY() + image2.getBoundsInParent().getHeight() / 2.0;
                
                return circlesIntersect(centerX1, centerY1, radius1, centerX2, centerY2, radius2);
        }
        


       
    }
    
    

    private static boolean circlesIntersect(double centerX1, double centerY1, double radius1,
                                            double centerX2, double centerY2, double radius2) {
        double deltaX = centerX2 - centerX1;
        double deltaY = centerY2 - centerY1;
        double distanceSquared = deltaX * deltaX + deltaY * deltaY;
        double sumRadius = radius1 + radius2;
        double sumRadiusSquared = sumRadius * sumRadius;

        return distanceSquared <= sumRadiusSquared;
    }
    
//    private static boolean ovalsIntersect(double centerX1, double centerY1, double radius1,
//            double centerX2, double centerY2, double radius2) {
//    	double deltaX = centerX2 - centerX1;
//		double deltaY = centerY2 - centerY1;
//		double distanceSquared = deltaX * deltaX + deltaY * deltaY;
//		double sumRadius = radius1 + radius2;
//		double sumRadiusSquared = sumRadius * sumRadius;
//		
//		return distanceSquared <= sumRadiusSquared;
//    }
}
