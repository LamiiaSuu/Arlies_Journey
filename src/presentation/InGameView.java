package presentation;

import application.App;
import business.game.elements.Arlie;
import business.game.elements.FloorScroller;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import presentation.uicomponents.HealthBar;
import presentation.uicomponents.PopUpDeathView;
import presentation.uicomponents.PopUpMenuView;
import presentation.uicomponents.ScoreBar;

public class InGameView extends BaseView {

	public Arlie arlie;
	public HealthBar healthBar;
	public ScoreBar scoreBar;
	public PopUpMenuView popUpMenu;
	public PopUpDeathView popUpDeath;

	public Pane arliePane;
	public Pane obstaclePane;
	public Pane backgroundPane;
	public Pane backgroundColorPane;
	public Pane groundPane;
	public Pane healthPane;
	public Pane hitBoxPane;
	public Pane scorePane;

	public Canvas canvas;
	public GraphicsContext gc;

	public ImageView backgroundColor;
	public ImageView ground;

	public InGameView(App app, Scene scene, int maxHealth) {
		arlie = new Arlie();
		healthBar = new HealthBar(maxHealth);
		scoreBar = new ScoreBar(app.getWidth(), app.getHeight());
		popUpMenu = new PopUpMenuView();
		popUpDeath = new PopUpDeathView();

		arliePane = new Pane();
		obstaclePane = new Pane();
		healthPane = new Pane();
		backgroundPane = new Pane();
		backgroundColorPane = new Pane();
		groundPane = new Pane();
		hitBoxPane = new Pane();
		scorePane = new Pane();

		canvas = new Canvas(1280, 720);
		gc = canvas.getGraphicsContext2D();

		backgroundPane.setScaleY(0.75);

//        Image groundImage = new Image(getClass().getResourceAsStream("/assets/images/ground-dirt-mirrored.png"));
//        ground = new ImageView(groundImage);
//        ground.setPreserveRatio(true);
//        ground.setFitWidth(1920);
//        ground.setScaleX(0.66);
//        ground.setScaleY(0.66);
//        ground.setY(435); // Müssen so komisch pixel genau gesetzt werden bc the image is not 1920x1080
//        ground.setX(-326);
//        groundPane.getChildren().add(ground);

		Image backgroundColorImage = new Image(getClass().getResourceAsStream("/assets/images/sky-blue.png"));
		backgroundColor = new ImageView(backgroundColorImage);

		backgroundColorPane.getChildren().add(backgroundColor);
		arliePane.getChildren().add(arlie.arlieBody);
		scorePane.getChildren().add(scoreBar);
		hitBoxPane.getChildren().add(canvas);
		healthPane.getChildren().add(healthBar);

		getChildren().addAll(backgroundColorPane, backgroundPane, groundPane, obstaclePane, arliePane, hitBoxPane,
				healthPane, scorePane);

		setManaged(false);

		arlie.arlieBody.setTranslateX(scene.getWidth() * 0.15);
		arlie.arlieBody.setTranslateY(scene.getHeight() * 0.565);

		setId("in-game-view");
	}

	public Pane getArliePane() {
		return arliePane;
	}

	public Pane getObstaclePane() {
		return obstaclePane;
	}

	public Pane getBackgroundPane() {
		return backgroundPane;
	}

	public HealthBar getHealthBar() {
		return healthBar;
	}

	public ScoreBar getScoreBar() {
		return scoreBar;
	}

	public Pane getGroundPane() {
		return groundPane;
	}

	public GraphicsContext getHitBoxGraphicsContext() {
		return gc;
	}

}
