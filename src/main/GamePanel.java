package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import AI.PathPosition;
import entity.Entity;
import entity.Player;
import tile.TileManager;
import tile_interactive.InteractiveTile;

public class GamePanel extends JPanel implements Runnable{


	//public final int maxMap = 0;
	//Screen Settings
	final int originalTileSize = 32; //32 by 32
	final int scale = 2;

	public final int tileSize = originalTileSize * scale; // 48 tile
	public final int maxScreenCol = 20;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 960 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

	//WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int maxmap = 10;
	public int currentmap = 0;


	//FOR FULLSCREEN
	int screenWidth2 = screenWidth;
	int screenHeight2 = screenHeight;
	BufferedImage tempScreen;
	Graphics2D g2;
	public boolean fullScreenOn = false;

	// FPS
	int FPS = 60;

	//SYSTEM
	public TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	Sound music = new Sound();
	Sound  se = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	//public EventHandler eHandler = new EventHandler(this);
	Config config = new Config(this);
	public PathPosition pathP = new PathPosition(this);
	Thread gameThread;

	//ENTITY AND OBJECT
	public Player player = new Player(this,keyH);
	public Entity obj[][] = new Entity [maxmap][20];
	public Entity npc[][] = new Entity[maxmap][10];
	public Entity monster[][] = new Entity[maxmap][20];
	public InteractiveTile iTile[][] = new InteractiveTile[maxmap][50];
	public Entity projectile[][] = new Entity [maxmap][20];
	//public ArrayList<Entity> projectileList = new ArrayList<>();
	public ArrayList<Entity> particleList = new ArrayList<>();
	ArrayList<Entity> entityList = new ArrayList<>();

	//GAME STATE
	public int gameState;
	public int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int characterState = 4;
	public final int optionsState = 5;
	public final int gameOverState = 6;
	public final int finishState = 7;
	public final int gameOvertime = 8;
	public final int ControlState = 9;
	public final int CreditState = 10;

	BufferedImage BackgroundImage, BackgroundImage2, BackgroundImage3;

	public int TimeLeft = 300;
	public long  TimerStart;
	public String FormattedTime = "01:00";



	public GamePanel() {

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void drawTimer(Graphics2D g2) {
		if(gameState == gameOverState || gameState == dialogueState || gameState == optionsState
				|| titleState == ControlState || gameState == characterState || gameState == ControlState || titleState == CreditState) {
			return;
		}
		g2.setFont(g2.getFont().deriveFont(40F));
		g2.setColor(Color.white);

		if(gameState == finishState) {
			g2.drawString("Your Time is:\n " +  FormattedTime, (int) (tileSize * 6.8), tileSize*5);
		}else {
			g2.drawString("Time: " + FormattedTime,  (int) (tileSize * 15.5), tileSize);
		}
	}
	public void drawToScreen() {

		Graphics g = getGraphics();
		g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
		g.dispose();

	}
	public void drawToTempScreen() {

		//DEBUG
		long drawStart = 0;
		if(keyH.showDebugText == true) {
			drawStart = System.nanoTime();
		}
		// TITLE SCREEN
		if(gameState == titleState) {
			ui.draw(g2);
		}
		//OTHERS
		else {

			//TILE
			tileM.draw(g2);

			//INTERACTIVE TILE
			for(int i = 0; i < iTile[1].length; ++i) {
				if(iTile[currentmap][i] != null) {
					iTile[currentmap][i].draw(g2);
				}
			}

			//ADD ENTITIES TO THE LIST
			entityList.add(player);

			for(int i = 0; i < npc[1].length; ++i) {
				if(npc[currentmap][i] != null) {
					entityList.add(npc[currentmap][i]);
				}
			}
			for(int i = 0; i < obj[1].length; ++i) {
				if(obj[currentmap][i] != null) {
					entityList.add(obj[currentmap][i]);
				}
			}
			for(int i = 0; i < monster[1].length; ++i) {
				if(monster[currentmap][i] != null) {
					entityList.add(monster[currentmap][i]);
				}
			}
			for(int i = 0; i < projectile[1].length; ++i) {
				if(projectile[currentmap][i] != null) {
					entityList.add(projectile[currentmap][i]);
				}
			}
			for(int i = 0; i < particleList.size(); ++i) {
				if(particleList.get(i) != null) {
					entityList.add(particleList.get(i));
				}
			}
			//SORT
			Collections.sort(entityList, new Comparator<Entity>() {

				@Override
				public int compare(Entity e1, Entity e2) {

					int result = Integer.compare(e1.worldY, e2.worldY);
					return result;
				}

			});

			//DRAW ENTITIES
			for(int i = 0;  i < entityList.size(); ++i) {
				entityList.get(i).draw(g2);
			}
			// EMPTY ENTITY LIST
			for(int i = 0;  i < entityList.size(); ++i) {
				entityList.remove(i);
			}

			//UI
			ui.draw(g2);

			drawTimer(g2);

		}

		//DEBUG
		if(keyH.showDebugText == true) {
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;

			g2.setFont(new Font("Arial",Font.PLAIN,20));
			g2.setColor(Color.white);
			int x = 10;
			int y = 400;
			int lineHeight = 20;

			g2.drawString("WorldX"+ player.worldX, x, y); y += lineHeight;
			g2.drawString("WorldY"+ player.worldY, x, y); y += lineHeight;
			g2.drawString("Col"+ (player.worldX + player.solidArea.x)/tileSize, x, y); y += lineHeight;
			g2.drawString("Row"+ (player.worldY + player.solidArea.y)/tileSize, x, y); y += lineHeight;
			g2.drawString("Draw Time: " + passed, x, y);
		}
	}
	public void playMusic(int i) {

		music.setFile(i);
		music.play();
		music.loop();
	}
	public  void  playSE(int i) {

		se.setFile(i);
		se.play();
	}
	public void replayTime() {

		player.setDefaultValues();
		player.setDefaultPosition();
		player.restoreLifeAndAmmo();
		player.setItems();
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		aSetter.setInteractiveTile();

	}

	public void resetTimer() {
		TimerStart = System.nanoTime();
		TimeLeft = 300;
		updateFormattedTime();
	}
	public void restart() {

		player.setDefaultValues();
		player.setDefaultPosition();
		player.restoreLifeAndAmmo();
		player.setItems();
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		aSetter.setInteractiveTile();
		resetTimer();

	}
	public void retry() {

		player.setDefaultPosition();
		player.restoreLifeAndAmmo();
		aSetter.setNPC();
		aSetter.setMonster();
		resetTimer();
	}
	@Override
	public void run() {

		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;

		while(gameThread != null) {

			currentTime = System.nanoTime();

			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;

			if(delta >= 1) {
				update();
				drawToTempScreen(); //DRAW EVERYTHIN TP THE BUFFERED IMAGE
				drawToScreen(); // DRAW THE BUFFERED IMAGE TO THE SCREEN
				delta--;
				drawCount++;
			}

		}
	}
	public void setFullScreen() {

		//GET LOCAL SCREEN DEVICE
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		gd.setFullScreenWindow(Main.window);

		//GET FULL SCREEN WIDTH AND HEIGHT
		screenWidth2 = Main.window.getWidth();
		screenHeight2 = Main.window.getHeight();

	}
	public void setupGame() {

		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		aSetter.setInteractiveTile();
		//playMusic(0);
		gameState = titleState;

		tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
		g2 = (Graphics2D)tempScreen.getGraphics();

		if(fullScreenOn == true) {
			setFullScreen();
		}
		try {
			BackgroundImage = ImageIO.read(getClass().getResource("/BG/BG1.jpg"));
			BackgroundImage2 = ImageIO.read(getClass().getResource("/BG/GameOverBG.jpg"));
			BackgroundImage3 = ImageIO.read(getClass().getResource("/BG/Credit.jpg"));

		}catch(IOException e) {
			e.printStackTrace();
		}
		TimerStart = System.nanoTime();
		updateFormattedTime();
	}
	public void startGameThread() {

		gameThread = new Thread(this);
		gameThread.start();
	}
	public void stopMusic() {

		music.stop();
	}
	public void update() {

		if(gameState == playState) {
			//PLAYER
			player.update();

			//NPC
			for(int i  = 0; i < npc[1].length; ++i) {
				if(npc[currentmap][i] != null) {
					npc[currentmap][i].update();
				}
			}
			for(int  i = 0; i < monster[1].length; ++i)  {
				if(monster[currentmap][i] != null) {
					if(monster[currentmap][i].alive == true && monster[currentmap][i].dying == false) {
						monster[currentmap][i].update();
					}
					if(monster[currentmap][i].alive == false) {
						monster[currentmap][i].checkDrop();
						monster[currentmap][i] = null;
					}
				}
			}
			for(int  i = 0; i < projectile[1].length; ++i)  {
				if(projectile[currentmap][i] != null) {
					if(projectile[currentmap][i].alive == true) {
						projectile[currentmap][i].update();
					}
					if(projectile[currentmap][i].alive == false) {
						projectile[currentmap][i] = null;
					}
				}
			}
			for(int  i = 0; i < particleList.size(); ++i)  {
				if(particleList.get(i) != null) {
					if(particleList.get(i).alive == true) {
						particleList.get(i).update();
					}
					if(particleList.get(i).alive == false) {
						particleList.remove(i);
					}
				}
			}
			for(int i = 0; i < iTile[1].length; ++i) {
				if(iTile[currentmap][i] != null) {
					iTile[currentmap][i].update();
				}
			}
			updateTimer();
		}
		if(gameState == pauseState) {
			//nothing
		}


	}
	public void updateFormattedTime() {
		int minutes = TimeLeft / 60;
		int seconds = TimeLeft % 60;
		FormattedTime = String.format("%02d:%02d", minutes, seconds);
	}
	public void updateTimer() {
		long elapsedTime = System.nanoTime() - TimerStart;
		int secondsPassed = (int) (elapsedTime / 1000000000L);
		TimeLeft = Math.max(0, 300 - secondsPassed);
		updateFormattedTime();

		if(TimeLeft == 0) {
			gameState = gameOvertime;
			playMusic(0);
		}
	}
}
