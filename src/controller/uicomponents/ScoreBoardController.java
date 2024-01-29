package controller.uicomponents;

import application.App;
import controller.BaseViewController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import presentation.PrimaryViewNames;
import presentation.uicomponents.PopUpMenuView;
import presentation.uicomponents.ScoreBar;

public class ScoreBoardController extends BaseViewController {

    private Label scoreBoard;
	
	int score;
	double initialTranslateX;
	int scoreDigits;
    
	public ScoreBoardController(ScoreBar scoreBar) {
		
		this.scoreBoard = scoreBar.scoreBoard;
		
		

        initialize();
    }
	
	@Override
    public void initialize() {
    	
		scoreBoard.setText("0");
		initialTranslateX = scoreBoard.getTranslateX();
		
    }

	@Override
	public void update() {
		
		scoreDigits = 0;
		for(int x = 0; x < Math.floor(Math.log10(score)); x++) {
			scoreDigits++;
		}
		scoreBoard.setTranslateX(initialTranslateX-(20*scoreDigits));
		
		scoreBoard.setText(Integer.toString(score));
	}
	
	public void scoreUp(int scoreToAdd)  {
		score += scoreToAdd;
	}
	
	public void scoreDown(int scoreToSubtract) {
		score -= scoreToSubtract;
	}
	
	public void setScore(int scoreToSet) {
		score = scoreToSet;
	}

}
