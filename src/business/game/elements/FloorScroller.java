package business.game.elements;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class FloorScroller {
    private static final int IMAGE_WIDTH = 1920;
    
    private ImageView[] imageViewFloor = new ImageView[2];
    
    
    
    private double y = 0;
    private Pane root;
    private AnimationTimer timer;

    public FloorScroller(Pane root) {
        this.root = root;
        start();
    }

    public void start() {
    	
        Image[] imagesFloor = new Image[2];
        imagesFloor[0] = new Image(getClass().getResourceAsStream("/assets/images/ground-dirt-mirrored.png"));
        imagesFloor[1] = new Image(getClass().getResourceAsStream("/assets/images/ground-dirt-mirrored.png"));
        
        

        
   
       

        for (int i = 0; i < imageViewFloor.length; i++) {
        	imageViewFloor[i] = new ImageView(imagesFloor[i]);
        	imageViewFloor[i].setX(IMAGE_WIDTH * i);
        	
        	
//        	imageViewFloor[i].setPreserveRatio(true);
//        	imageViewFloor[i].setFitWidth(1920);
//        	imageViewFloor[i].setScaleX(0.66);
//        	imageViewFloor[i].setScaleY(0.66);
        	imageViewFloor[i].setY(475); // Müssen so komisch pixel genau gesetzt werden bc the image is not 1920x1080
//        	imageViewFloor[i].setX(-326);
        	
        	
//            if(i >= 2) {
//            	imageViewFloor[i].setY(imageViewFloor[i].getY()+72);
//            }
            root.getChildren().add(imageViewFloor[i]);
            
            
        }
        
        
        
        
       


        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            	 y -= 5;
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
    
}