package presentation.uicomponents;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PopupControl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import presentation.BaseView;

public class PopUpMenuView extends PopupControl {

    public Pane backgroundColorPane;
    public ImageView backgroundColor;
    
	public Button continueButton;
    public Button menuButton;
    public Button settingsButton;
	
	public PopUpMenuView() {
		
		setId("pop-up-menu-panel");
		Popup menuPopUp = new Popup();
		
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
        
        mainButtonsLayout.getChildren().addAll(continueButton, menuButton, settingsButton);
        
        menuPopUp.getContent().add(mainButtonsLayout);
        
	}

}
