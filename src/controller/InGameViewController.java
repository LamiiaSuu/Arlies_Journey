package controller;

import application.App;
import business.game.elements.ArlieController;
import business.game.elements.BackgroundScroll;
import business.game.elements.FloorScroller;
import business.music.MP3Player;
import controller.uicomponents.HitBoxManager;
import controller.uicomponents.ObstacleGenerator;
import controller.uicomponents.PopUpDeathViewController;
import controller.uicomponents.PopUpMenuViewController;
import controller.uicomponents.ScoreBoardController;
import controller.uicomponents.Difficulty;
import controller.uicomponents.GameMode;
import controller.uicomponents.HealthBarController;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import presentation.InGameView;
import presentation.PrimaryViewNames;
import presentation.uicomponents.PopUpMenuView;

public class InGameViewController extends BaseViewController {

	private static final Duration COLLISION_INVULNERABILITY_INTERVAL = Duration.seconds(1.5);
	private static final int MAX_HEALTH = 3;
	private static final boolean CHANGE_GAMEMODE_INGAME = false;

	private boolean gamePaused = false;
	private boolean gameOver = false;
	public boolean godMode = false;
	private boolean initializationPaused = false;
	private AnimationTimer gameLoop;
	private double groundY;
	private long lastCollisionTime = 0;
	private long currentTime;
	private double gameLoopOffSet = 1;
	private double gameLoopCounter = 0;

	private Difficulty difficulty = Difficulty.MEDIUM;
	private GameMode gameMode = GameMode.JOURNEY_MODE;

	private int fps;

	MP3Player player;
	InGameView root;
	ArlieController arlieController;
	HealthBarController healthBarController;
	ScoreBoardController scoreBoardController;
	PopUpMenuViewController popUpMenuController;
	PopUpDeathViewController popUpDeathController;
	MainMenuViewController mainMenuViewController;
	ObstacleGenerator obstacleGen;
	BackgroundScroll backgroundScroll;
	FloorScroller floorScroller;
	GraphicsContext hitBoxGC;
	App app;
	Scene scene;

	public InGameViewController(App app, Scene scene, MP3Player player, MainMenuViewController mainMenuViewController) {

		root = new InGameView(app, scene, MAX_HEALTH);
		this.app = app;
		this.scene = scene;
		this.player = player;
		this.hitBoxGC = root.getHitBoxGraphicsContext();
		this.mainMenuViewController = mainMenuViewController;
		this.fps = 60;

		floorScroller = new FloorScroller(root.getGroundPane());
		arlieController = new ArlieController(app, root.getArliePane(), root.arlie, scene, player, this);
		obstacleGen = new ObstacleGenerator(root.getObstaclePane(), root.getHitBoxGraphicsContext(), scene,
				arlieController, this);
		backgroundScroll = new BackgroundScroll(root.getBackgroundPane());
		healthBarController = new HealthBarController(root.getHealthBar());
		scoreBoardController = new ScoreBoardController(root.getScoreBar());
		popUpMenuController = new PopUpMenuViewController(app, scene);
		popUpDeathController = new PopUpDeathViewController(app, scene);

		initialize();

	}

	@Override
	public void initialize() {

		groundY = scene.getHeight() * 0.6;

		setFPS(fps);
		setDifficulty(difficulty);

		setGround();

		healthBarController.initialize(MAX_HEALTH);

		arlieController.initialize();

		app.currentViewProperty().addListener(new ChangeListener<PrimaryViewNames>() {
			@Override
			public void changed(ObservableValue<? extends PrimaryViewNames> observable, PrimaryViewNames oldValue,
					PrimaryViewNames newValue) {
				if (newValue != PrimaryViewNames.IN_GAME_VIEW) {
					pauseGame();
					System.out.println("PAUSE");
				} else if (newValue == PrimaryViewNames.IN_GAME_VIEW) {
					resumeGame();
					System.out.println("RESuME");
				}
			}
		});

		gameLoop = new AnimationTimer() {
			@Override
			public void handle(long now) {

				if (gameLoopCounter == gameLoopOffSet) {
					gameLoopCounter = 0;
					update();
				}

				else {
					gameLoopCounter++;
				}
			}
		};

		scene.setOnKeyPressed(event -> {
			if (app.currentViewProperty().get().equals(PrimaryViewNames.IN_GAME_VIEW))
				handleKeyPress(event.getCode());
		});

		scene.setOnKeyReleased(event -> {
			if (app.currentViewProperty().get().equals(PrimaryViewNames.IN_GAME_VIEW) && !gamePaused && !gameOver)
				handleKeyRelease(event.getCode());
		});

		scene.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				groundY = newValue.doubleValue() * 0.6;
				setGround();
			}
		});

		healthBarController.getHealthProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number oldValue, Number newValue) {

				if (oldValue != newValue) {
					healthBarController.setHitPoints((int) newValue);
					if ((int) newValue == 0)
						gameOver();
				}

			}
		});

		arlieController.getConfusedLandedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue == true) {
					pauseGame();
					arlieController.confusedCircling();
					mainMenuViewController.hideContinue();
					popUpDeathController.getPopupRoot().show(app.getStage());

				}
			}
		});

		popUpDeathController.getPopupRoot().setOnHidden(event -> {
			if (app.currentViewProperty().get().equals(PrimaryViewNames.IN_GAME_VIEW) && gameOver)
				app.switchView(PrimaryViewNames.MAIN_MENU_VIEW);
		});

		popUpMenuController.getPopupRoot().setOnHidden(event -> {
			if (app.currentViewProperty().get().equals(PrimaryViewNames.IN_GAME_VIEW))
				resumeGame();
		});

		popUpDeathController.getButton("newJourney").setOnAction(event -> {
			if (gameOver) {
				resetGame();
				popUpDeathController.getPopupRoot().hide();

			}
		});

		popUpDeathController.getButton("menuButton").setOnAction(event -> {
			if (gameOver) {
				app.switchView(PrimaryViewNames.MAIN_MENU_VIEW);
				popUpDeathController.getPopupRoot().hide();
			}
		});

		popUpDeathController.getButton("settingsButton").setOnAction(event -> {
			if (gameOver) {
				app.switchView(PrimaryViewNames.SETTINGS_VIEW);
				popUpDeathController.getPopupRoot().hide();
			}
		});

		popUpMenuController.getButton("continueButton").setOnAction(event -> {
			if (gamePaused && !gameOver) {
				popUpMenuController.getPopupRoot().hide();
				resumeGame();
			}
		});

		popUpMenuController.getButton("menuButton").setOnAction(event -> {
			if (gamePaused && !gameOver) {
				app.switchView(PrimaryViewNames.MAIN_MENU_VIEW);
				popUpMenuController.getPopupRoot().hide();
				pauseGame();
			}
		});

		popUpMenuController.getButton("settingsButton").setOnAction(event -> {
			if (gamePaused && !gameOver) {
				app.switchView(PrimaryViewNames.SETTINGS_VIEW);
				popUpMenuController.getPopupRoot().hide();
				pauseGame();
			}
		});

	}

	public GameMode getGameMode() {
		return gameMode;
	}

	public void setGameMode(GameMode gameMode) {
		this.gameMode = gameMode;
		toggleGodMode();
	}

	private void handleKeyPress(KeyCode code) {
		switch (code) {

		case SPACE:
			if (!gamePaused && !gameOver)
				arlieController.jump();
			break;
		case UP:
			if (!gamePaused && !gameOver)
				arlieController.jump();
			break;
		case W:
			if (!gamePaused && !gameOver)
				arlieController.jump();
			break;
		case DOWN:
			if (!gamePaused && !gameOver)
				arlieController.crouch();
			break;
		case CONTROL:
			if (!gamePaused && !gameOver)
				arlieController.crouch();
			break;
		case S:
			if (!gamePaused && !gameOver)
				arlieController.crouch();
			break;
		case H:
			if (!gamePaused && !gameOver)
				toggleHitBoxView();
			break;

		case O:
			resetGame();
			break;

		case G:
			if (CHANGE_GAMEMODE_INGAME) {
				if (gameMode != GameMode.GOD_MODE)
					toggleGodMode();
			}

			break;

		case ESCAPE:
//            	app.switchView(PrimaryViewNames.MAIN_MENU_VIEW);
			if (!gamePaused && !gameOver && !popUpMenuController.getPopupRoot().isShowing()) {
				pauseGame();
				popUpMenuController.getPopupRoot().show(this.app.getStage());
			}
			break;

		}
	}

	public void setFPS(int fps) {
		this.fps = fps;
		floorScroller.setFPS(fps);
		obstacleGen.setFPS(fps);
		arlieController.setFPS(fps);
		switch (fps) {
		case 60:
			gameLoopOffSet = 0;
			break;
		case 144:
			gameLoopOffSet = 1;
			break;
		case 540:
			gameLoopOffSet = 7;
			break;
		}
	}

	public int getFPS() {
		return fps;
	}

	public void setDifficulty(Difficulty difficulty) {

		player.setDifficulty(difficulty);
		this.difficulty = difficulty;

	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void toggleGodMode() {
		if (godMode) {
			if (this.gameMode != GameMode.GOD_MODE) {
				godMode = false;
				arlieController.clearInvulnerableEffect();
			}
		} else {

			arlieController.setGodModeEffect();
			godMode = true;

		}

		healthBarController.toggleGodMode(godMode);

	}

	private void handleKeyRelease(KeyCode code) {
		switch (code) {
		case SPACE:
			if (!gamePaused && !gameOver)
				arlieController.jumpRelease();
			break;
		case UP:
			if (!gamePaused && !gameOver)
				arlieController.jumpRelease();
			break;
		case W:
			if (!gamePaused && !gameOver)
				arlieController.jumpRelease();
			break;
		case DOWN:
			if (!gamePaused && !gameOver)
				arlieController.crouchRelease();
			break;
		case CONTROL:
			if (!gamePaused && !gameOver)
				arlieController.crouchRelease();
			break;
		case S:
			if (!gamePaused && !gameOver)
				arlieController.crouchRelease();
			break;
		}
	}

	public void pauseGame() {
		if (!gamePaused) {
			obstacleGen.stopTimer();
			gameLoop.stop();
			backgroundScroll.stopTimer();
			floorScroller.stopTimer();
			gamePaused = true;
			if (initializationPaused) {
				player.pause();
				mainMenuViewController.showContinue();
			} else {
				initializationPaused = true;
			}
		}
	}

	// Resume the game
	public void resumeGame() {
		if (gamePaused && !gameOver) {
			gameLoop.start();
			obstacleGen.startTimer();
			backgroundScroll.startTimer();
			floorScroller.startTimer();
			gamePaused = false;
			player.pause();
		}
	}

	public void resetGame() {
		obstacleGen.reset();
		backgroundScroll.reset();
		floorScroller.reset();
		healthBarController.setHitPoints(MAX_HEALTH);
		if (gameMode == GameMode.GOD_MODE)
			healthBarController.toggleGodMode(godMode);
		player.seek(0);
		player.resume();
		scoreBoardController.setScore(0);
		arlieController.reset();
		gameLoop.start();
		gamePaused = false;
		gameOver = false;
		if (gameMode != GameMode.GOD_MODE)
			godMode = false;
		root.arlie.arlieBody.setTranslateY(groundY);
		if (gameMode != GameMode.GOD_MODE)
			arlieController.clearInvulnerableEffect();
	}

	public void toggleHitBoxView() {
		obstacleGen.toggleHitBoxVisibility();
	}

	@Override
	public void update() {
		arlieController.update();
		updateInvulnerableEffect();
		scoreBoardController.update();
		HitBoxManager.clearCanvas(hitBoxGC);
		player.analyze();
		if (player.isBeat()) {
			obstacleGen.update(this);
		}

	}

	public void generatedObstacle() {
		scoreBoardController.scoreUp(1);
	}

	public void gameOver() {

		gameOver = true;
		arlieController.gameOver();
		player.playDeathSound();

	}

	public void arlieCollided() {

		currentTime = System.currentTimeMillis();

		if (currentTime - lastCollisionTime >= COLLISION_INVULNERABILITY_INTERVAL.toMillis()) {

			if (!godMode)
				healthBarController.damage();

			lastCollisionTime = currentTime;

			player.playCollidedSound();

		}
		arlieController.setInvulnerableEffect();
	}

	public void updateInvulnerableEffect() {

		currentTime = System.currentTimeMillis();

		if (currentTime - lastCollisionTime >= COLLISION_INVULNERABILITY_INTERVAL.toMillis()) {

			arlieController.clearInvulnerableEffect();

		}
	}

	public void setGround() {
		arlieController.setGround(groundY);
		obstacleGen.setGround(groundY);
	}

	public Pane getRoot() {
		return root;
	}
}
