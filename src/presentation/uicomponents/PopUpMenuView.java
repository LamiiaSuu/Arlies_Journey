package presentation.uicomponents;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

public class PopUpMenuView extends Popup {

	public Button continueButton;
    public Button menuButton;
    public Button settingsButton;
    public Pane popUp;
	
	public PopUpMenuView() {
		
	    popUp = new Pane();
	    popUp.setId("pop-up-menu-panel");
		
		// create containers for the ui-components
        VBox mainButtonsLayout = new VBox();

        // create ui-components
        continueButton = new Button();
        menuButton = new Button();
        settingsButton = new Button();
        
        // add style classes
        continueButton.getStyleClass().add("menu-button");
        menuButton.getStyleClass().add("menu-button");
        settingsButton.getStyleClass().add("menu-button");
        
        // add Id's
        continueButton.setId("continue-button");
        menuButton.setId("menu-button");
        settingsButton.setId("settings-button");

        // set the box for the main button layout
        mainButtonsLayout.setSpacing(25);
        mainButtonsLayout.setScaleX(0.8);
        mainButtonsLayout.setScaleY(0.8);
        mainButtonsLayout.setAlignment(Pos.CENTER);
        
        mainButtonsLayout.getChildren().addAll(continueButton, menuButton, settingsButton);
        
        popUp.getChildren().addAll(mainButtonsLayout);
        
        getContent().add(popUp);
        
        this.hide();
	}

}
