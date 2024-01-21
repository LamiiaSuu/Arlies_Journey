package presentation.uicomponents;

import business.game.elements.Arlie.ArlieConditions;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class VolumeView extends VBox{
		
	public Button volumeButton;
	
    private SimpleObjectProperty<VolumeConditions> volumeConditionProperty;
	
	public enum VolumeConditions {
	    	MUTED, LOW, MEDIUM, HIGH;
	    }
	
	public VolumeView() {
		
		volumeConditionProperty = new SimpleObjectProperty<VolumeConditions>(VolumeConditions.MEDIUM);
		volumeButton = new Button();
		volumeButton.getStyleClass().add("menu-icon");
		volumeButton.setId("volume-button-medium");
		
		setAlignment(Pos.BOTTOM_RIGHT);
	
		getChildren().addAll(volumeButton);
	}
	
	
	public VolumeConditions getVolumeConditionProperty(){
		
		return volumeConditionProperty.get();
	}
	
	public void setVolumeConditionProperty(VolumeConditions volumeCondition){
		
		volumeConditionProperty.set(volumeCondition);
	}

}
