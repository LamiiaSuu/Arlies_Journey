//package business.game.elements;
//
//import javafx.animation.AnimationTimer;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.Pane;
//
//public class FloorScroller {
//    private static final int IMAGE_WIDTH = 1920;
//    
//    private ImageView[] imageViews = new ImageView[2];
//    private double x = 0;
//    private Pane root;
//    private AnimationTimer timer;
//
//    public FloorScroller(Pane root) {
//        this.root = root;
//        start();
//    }
//
//    public void start() {
//
//        Image[] images = new Image[2];
//        images[0] = new Image(getClass().getResourceAsStream("/assets/images/ground-dirt-mirrored.png"));
//        images[1] = new Image(getClass().getResourceAsStream("/assets/images/ground-dirt-mirrored.png"));
//        
//
//        for (int i = 0; i < imageViews.length; i++) {
//            imageViews[i] = new ImageView(images[i]);
//            imageViews[i].setX(IMAGE_WIDTH * i);
//            if(i >= 2) {
//            	imageViews[i].setY(imageViews[i].getY()+72);
//            }
//            root.getChildren().add(imageViews[i]);
//        }
//
//
//        timer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                x -= 3; // Adjust the scrolling speed as needed
//
//                // Move images smoothly without resetting
//                for (int i = 0; i < imageViews.length; i++) {
//                    double newX = x + IMAGE_WIDTH * i;
//                    // Check if the image has scrolled off the screen multiple times
//                    while (newX < -IMAGE_WIDTH) {
//                        // Adjust the position to ensure seamless looping
//                        newX += IMAGE_WIDTH * imageViews.length;
//                    }
//                    imageViews[i].setX(newX);
//                }
//            }
//
//
//        };
//        timer.start();
//        
//    }
//    
//    public void stopTimer() {
//    	timer.stop();
//    }
//    
//    public void startTimer() {
//    	timer.start();
//    }
//    
//}