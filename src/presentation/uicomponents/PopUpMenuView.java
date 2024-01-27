package presentation.uicomponents;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import presentation.BaseView;

public class PopUpMenuView extends BaseView {

    public Pane backgroundColorPane;
    public ImageView backgroundColor;
    
	public Button continueButton;
    public Button menuButton;
    public Button settingsButton;
	
	public PopUpMenuView() {
		
		// create containers for the ui-components
        VBox mainButtonsLayout = new VBox();

        // create ui-components

        backgroundColorPane = new Pane();
        continueButton = new Button();
        menuButton = new Button();
        settingsButton = new Button();
        
        // add style classes
        continueButton.getStyleClass().add("menu-button");
        menuButton.getStyleClass().add("menu-button");
        settingsButton.getStyleClass().add("menu-button");
        
        // add Id's
        continueButton.setId("new-journey-button");
        menuButton.setId("music-button");
        settingsButton.setId("settings-button");

        // set the box for the main button layout
        mainButtonsLayout.setSpacing(25);
        mainButtonsLayout.setScaleX(0.8);
        mainButtonsLayout.setScaleY(0.8);
        mainButtonsLayout.setAlignment(Pos.CENTER);
        
        
        Image backgroundColorImage = new Image(getClass().getResourceAsStream("/assets/images/pop-up-menu.png"));
        backgroundColor = new ImageView(backgroundColorImage);
        backgroundColorPane.getChildren().add(backgroundColor);
        
        mainButtonsLayout.getChildren().addAll(continueButton, menuButton, settingsButton);
        
        this.getChildren().addAll(backgroundColorPane, mainButtonsLayout);
		
	}

}
