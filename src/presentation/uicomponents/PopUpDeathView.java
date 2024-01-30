package presentation.uicomponents;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

public class PopUpDeathView extends Popup {

	public Button newJourneyButton;
    public Button menuButton;
    public Button settingsButton;
    public HBox deathTextBox;
    public Pane popUp;
	
	public PopUpDeathView() {

		Pane popUp = new Pane();
		popUp.setScaleX(0.8);
		popUp.setScaleY(0.8);
	    popUp.setId("pop-up-death-panel");
	    
	    deathTextBox = new HBox();
	    ImageView deathImage = new ImageView(new Image(getClass().getResourceAsStream("/assets/images/death-text.png")));
	    deathTextBox.getChildren().addAll(deathImage);
	    deathTextBox.setScaleX(0.6);
	    deathTextBox.setScaleY(0.6);
	    deathTextBox.setAlignment(Pos.CENTER);
		
		// create containers for the ui-components
        VBox mainButtonsLayout = new VBox();

        // create ui-components

        newJourneyButton = new Button();
        menuButton = new Button();
        settingsButton = new Button();
        
        // add style classes
        newJourneyButton.getStyleClass().add("menu-button");
        menuButton.getStyleClass().add("menu-button");
        settingsButton.getStyleClass().add("menu-button");
        
        // add Id's
        newJourneyButton.setId("new-journey-button");
        menuButton.setId("music-button");
        settingsButton.setId("settings-button");

        // set the box for the main button layout
        mainButtonsLayout.setSpacing(25);
        mainButtonsLayout.setScaleX(0.8);
        mainButtonsLayout.setScaleY(0.8);
        mainButtonsLayout.setAlignment(Pos.BASELINE_CENTER);
        mainButtonsLayout.getChildren().addAll(newJourneyButton, menuButton, settingsButton);
        
        VBox components = new VBox();
        components.getChildren().addAll(deathTextBox, mainButtonsLayout);
        
        popUp.getChildren().addAll(components);
        
        getContent().add(popUp);
        
        this.hide();
		
	}
}
