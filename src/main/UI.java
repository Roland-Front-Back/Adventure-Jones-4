package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import entity.Entity;
import object.Ammo;
import object.Health_Bar;
public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font KiddyP, WoodenL;
	BufferedImage health_1, health_2, ammoFull, ammoEmpty;
	public boolean messageOn = false;
	//	public String message = "";
	//	int messageCounter = 0;
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	public int slotCol = 0;
	public int slotRow = 0;
	int subState = 0;

	public UI (GamePanel gp) {
		this.gp = gp;

		try {
			InputStream is = getClass().getResourceAsStream("/font/Wooden Log.ttf");
			WoodenL = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/font/Kiddy Play.ttf");
			KiddyP = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		//CREATE HUD OBJECT
		Entity health = new Health_Bar(gp);
		health_1 = health.image;
		health_2 = health.image2;
		Entity Ammo = new Ammo(gp);
		ammoFull = Ammo.image;
		ammoEmpty = Ammo.image2;

	}
	public void addMessage(String text) {

		message.add(text);
		messageCounter.add(0);
	}
	public void Credits() {

		g2.drawImage(gp.BackgroundImage3, 0, 0, gp.screenWidth, gp.screenHeight, null);

		g2.setColor(Color.black);
		g2.setFont(g2.getFont().deriveFont(40F));

		int textx;
		int texty;

		String text = "Credits";
		textx = getXforCenteredText(text);
		texty = gp.tileSize + gp.tileSize;
		g2.drawString(text, textx, texty);

		g2.setFont(g2.getFont().deriveFont(20F));
		text = "Roland C. Amoguis";
		textx = gp.tileSize*6;
		texty += gp.tileSize;
		g2.drawString(text, textx, texty);

		text = "Sherilyn Cambangay";
		textx += gp.tileSize*5;
		texty = gp.tileSize*3;
		g2.drawString(text, textx, texty);


		g2.setFont(g2.getFont().deriveFont(15F));
		text = "Programmer";
		textx = (int)(gp.tileSize*6.5);
		texty += gp.tileSize - 40;
		g2.drawString(text, textx, texty);

		text = "Encoder/Writer";
		textx += (int)(gp.tileSize*5.2);
		texty = (gp.tileSize*4) - 40;
		g2.drawString(text, textx, texty);


		g2.setFont(g2.getFont().deriveFont(20F));
		text = "Raquel Llemit";
		textx = (int)(gp.tileSize*6.2);
		texty += gp.tileSize ;
		g2.drawString(text, textx, texty);

		g2.setFont(g2.getFont().deriveFont(15F));
		text = "Storline/Writer";
		textx = (int)(gp.tileSize*6.3);
		texty += gp.tileSize - 40;
		g2.drawString(text, textx, texty);


		g2.setFont(g2.getFont().deriveFont(20F));
		text = "Christian Dave Ayag";
		textx = (int)(gp.tileSize* 10.3)+43;
		texty = (gp.tileSize*4)+30;
		g2.drawString(text, textx, texty);

		g2.setFont(g2.getFont().deriveFont(15F));
		text = "Graphics/Design";
		textx = (int)(gp.tileSize*10.8)+48;
		texty = (gp.tileSize*5)- 10;
		g2.drawString(text, textx, texty);


		g2.setFont(g2.getFont().deriveFont(20F));
		text = "Donie Bajao";
		textx = (int)(gp.tileSize*6.2);
		texty += gp.tileSize ;
		g2.drawString(text, textx, texty);

		g2.setFont(g2.getFont().deriveFont(15F));
		text = "BGMusic/Design";
		textx = (int)(gp.tileSize*6.3);
		texty += gp.tileSize - 40;
		g2.drawString(text, textx, texty);


		g2.setFont(g2.getFont().deriveFont(20F));
		text = "Kathleen Jade Gulay";
		textx = (int)(gp.tileSize*10.8)+10;
		texty += gp.tileSize-86;
		g2.drawString(text, textx, texty);

		g2.setFont(g2.getFont().deriveFont(15F));
		text = "Soundfx/Design";
		textx = (int)(gp.tileSize*11.3)+15;
		texty += gp.tileSize -40;
		g2.drawString(text, textx, texty);

		g2.setFont(g2.getFont().deriveFont(25F));
		text = "Created by Group 4";
		textx = gp.tileSize*8+15;
		texty += gp.tileSize -10+25;
		g2.drawString(text, textx, texty);


		g2.drawString(text, textx, texty);
		g2.setFont(g2.getFont().deriveFont(32F));
		textx = (gp.tileSize*6) + gp.tileSize;
		texty = gp.tileSize + gp.tileSize*9;
		g2.drawString("Back", textx, texty);
		if(commandNum == 0) {
			g2.drawString("+", textx-25, texty);
		}
	}
	public void draw(Graphics2D g2) {

		this.g2 = g2;

		g2.setFont(WoodenL);
		g2.setColor(new Color(50,20,20));

		//TITLE STATE
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		//PLAY STATE
		if(gp.gameState == gp.playState) {
			drawPlayerLife();
			drawMessage();
		}
		//PAUSE STATE
		if(gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPauseScreen();
		}
		//DIALOGUE STATE
		if(gp.gameState == gp.dialogueState) {
			drawPlayerLife();
			drawDialogueScreen();
		}
		//CHARACTER STATE
		if(gp.gameState == gp.characterState) {
			drawCharacterScreen();
			drawInventory();
		}
		//OPTION STATE
		if(gp.gameState == gp.optionsState) {
			drawOptionScreen();
		}
		//GAMEOVER STATE
		if(gp.gameState == gp.gameOverState) {
			drawGameOverScreen();
		}
		if(gp.gameState == gp.gameOvertime) {
			gameOverTime();
		}
		if(gp.gameState == gp.finishState) {
			finishState();
		}
		if(gp.titleState == gp.ControlState) {
			OptionScreen();
		}
		if(gp.titleState == gp.CreditState) {
			Credits();
		}
	}
	public void drawCharacterScreen() {

		//CREATE FRAME
		final int frameX = gp.tileSize-6;
		final int frameY = gp.tileSize;
		final int frameWidth = gp.tileSize*5;
		final int frameHeight = gp.tileSize*10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);

		//TEXT
		g2.setColor(new Color(50,20,20));
		g2.setFont(g2.getFont().deriveFont(32F));

		int textX = frameX + 15;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 40;

		//NAmes
		g2.drawString("Level", textX, textY);textY += lineHeight;
		g2.drawString("Life", textX, textY);textY += lineHeight;
		g2.drawString("Ammo", textX, textY);textY += lineHeight;
		g2.drawString("Strength", textX, textY);textY += lineHeight;
		g2.drawString("Energy", textX, textY);textY += lineHeight;
		g2.drawString("Attack", textX, textY);textY += lineHeight;
		g2.drawString("Defense", textX, textY);textY += lineHeight;
		g2.drawString("Artifact", textX, textY);textY += lineHeight;
		g2.drawString("Exp", textX, textY);textY += lineHeight;
		g2.drawString("Next Level", textX, textY);textY += lineHeight + 30;
		g2.drawString("Weapon", textX, textY);textY += lineHeight + 25;
		g2.drawString("Armor", textX, textY);textY += lineHeight;

		//VALUES
		int tailX = (frameX + frameWidth) - 30;

		//Reset textY
		textY = frameY + gp.tileSize;
		String value;

		value = String.valueOf(gp.player.level);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.ammo + "/" + gp.player.maxAmmo);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.strength);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.energy);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.attack);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.defense);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.artifact);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.exp);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.nextLevelExp);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		g2.drawImage(gp.player.weapon.down1, tailX - gp.tileSize, textY - 10, null);
		textY += gp.tileSize;

		g2.drawImage(gp.player.armor.down1, tailX - gp.tileSize, textY - 10, null);
		textY += gp.tileSize;

	}
	public void drawDialogueScreen() {

		g2.setFont(KiddyP);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(new Color(50,20,20));
		//WINDOW
		int x = gp.tileSize*2;
		int y = gp.tileSize/2;
		int width = gp.screenWidth - (gp.tileSize*4);
		int height = gp.tileSize*3;
		drawSubWindow(x, y, width, height);

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,19F));
		x += gp.tileSize;
		y += gp.tileSize;


		for(String line : currentDialogue.split("\n")) {
			g2.drawString(line,  x,  y);
			y += 40;
		}
	}
	public void drawGameOverScreen() {

		g2.drawImage(gp.BackgroundImage2, 0, 0, gp.screenWidth, gp.screenHeight, null);


		int x;
		int y;
		String text;

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 110f));

		text = "Game Over";
		//Shadow
		g2.setColor(Color.black);
		x = getXforCenteredText(text);
		y = gp.tileSize*4;
		g2.drawString(text, x, y);
		//MAIN
		g2.setColor(Color.white);
		g2.drawString(text, x-4, y-4);

		//Retry
		g2.setFont(g2.getFont().deriveFont(50f));
		text = "Retry";
		x = getXforCenteredText(text);
		y += gp.tileSize*4;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString("+", x-40, y);
		}
		//Back to the title screen
		text = "Quit";
		x = getXforCenteredText(text);
		y += 55;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString("+", x-40, y);
		}
	}
	public  void drawInventory() {

		//FRAME
		int frameX = gp.tileSize*13;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize*6;
		int frameHeight = gp.tileSize*5;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);

		//SLOT
		final int slotXstart = frameX + 20;
		final int slotYstart = frameY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;
		int slotSize = gp.tileSize+6;

		//DRAW PLAYER'S ITEM
		for(int i = 0; i < gp.player.inventory.size(); i++) {

			//EQUIP CURSOR
			if(gp.player.inventory.get(i) == gp.player.weapon ||
					gp.player.inventory.get(i) == gp.player.armor) {
				g2.setColor(new Color(240,190,90));
				g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
			}

			g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);

			slotX += slotSize;

			if(i == 4 || i == 9 || i == 14) {
				slotX = slotXstart;
				slotY += slotSize;
			}
		}

		//CURSOR
		int cursorX = slotXstart + (slotSize * slotCol);
		int cursorY  = slotYstart + (slotSize * slotRow);
		int cursorWidth = gp.tileSize;
		int cursorHeight = gp.tileSize;

		//DRAW CURSOR
		g2.setColor(Color.darkGray);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

		//DESCRIPTION FRAME
		int dFrameX = frameX;
		int dFrameY = frameY + frameHeight;
		int dFrameWidth = frameWidth;
		int dFrameHeight = gp.tileSize*3;

		//Description Text
		int textX = dFrameX + 20;
		int textY = dFrameY + gp.tileSize;
		g2.setFont(g2.getFont().deriveFont(20f));

		int itemIndex = getItemIndexSlot();

		if(itemIndex < gp.player.inventory.size()) {

			drawSubWindow(dFrameX,dFrameY,dFrameWidth,dFrameHeight);

			for(String line: gp.player.inventory.get(itemIndex).description.split("\n")) {

				g2.drawString(gp.player.inventory.get(itemIndex).description, textX, textY);
				textY += 32;
			}
		}
	}
	public void drawMessage() {

		int messageX = gp.tileSize;
		int messageY = gp.tileSize*10;
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25F));;

		for(int i = 0; i < message.size(); ++i) {

			if(message.get(i) != null) {

				g2.setColor(Color.black);
				g2.drawString(message.get(i), messageX+2, messageY+2);
				g2.setColor(Color.white);
				g2.drawString(message.get(i), messageX, messageY);

				int counter = messageCounter.get(i) + 1; //messageCounter++
				messageCounter.set(i, counter); //set the counter of the array
				messageY += 50;

				if(messageCounter.get(i) > 180) {
					message.remove(i);
					messageCounter.remove(i);
				}
			}
		}

	}
	public void drawOptionScreen() {

		g2.setColor(Color.darkGray);
		g2.setFont(g2.getFont().deriveFont(30F));

		//SUB WINDOW
		int frameX = gp.tileSize*6;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize*8;
		int frameHeight = gp.tileSize*10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);

		switch(subState) {
		case 0: options_top(frameX, frameY); break;
		case 1: options_fullScreenNotification(frameX, frameY); break;
		case 2: options_control(frameX, frameY); break;
		case 3: options_endGameConfirmation(frameX, frameY); break;

		}
		gp.keyH.enterPressed = false;
	}
	public void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,40F));
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight/2;

		g2.drawString(text, x, y);
	}
	public void drawPlayerLife() {

		int x = gp.tileSize/3;
		int y = gp.tileSize/4;
		int i = 0;

		//DRAW MAX LIFE
		while(i < gp.player.maxLife/2) {
			g2.drawImage(health_2, x, y, null);
			i++;
			x += gp.tileSize;
		}
		//RESET
		x = gp.tileSize/3;
		y = gp.tileSize/4;
		i = 0;

		//DRAW CURRENT LIFE
		while(i < gp.player.life) {
			g2.drawImage(health_2, x, y, null);
			i++;
			if(i < gp.player.life) {
				g2.drawImage(health_1, x, y, null);
			}
			i++;
			x += gp.tileSize;
		}
		//Draw maxAmmo
		x = gp.tileSize/3;
		y = gp.tileSize/1;
		i = 0;
		while(i < gp.player.maxAmmo) {
			g2.drawImage(ammoEmpty, x, y, null);
			i++;
			x += gp.tileSize;
		}

		//draw ammo
		x = gp.tileSize/3;
		y = gp.tileSize/1;
		i = 0;
		while(i < gp.player.ammo) {
			g2.drawImage(ammoFull, x, y, null);
			i++;
			x += gp.tileSize;
		}
	}
	public void drawSubWindow(int x, int y,int width, int height) {

		Color c = new Color(205,127,50);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);

		c = new Color(0, 0, 0);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);

	}
	public void drawTitleScreen() {

		g2.drawImage(gp.BackgroundImage, 0, 0, gp.screenWidth, gp.screenHeight, null);

		//TITLE NAME
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,75F));
		String text = "Adventure Jones";
		int x = getXforCenteredText(text);
		int y = gp.tileSize*3;

		//shadow
		g2.setColor(Color.black);
		g2.drawString(text, x+5, y+5);

		//main color
		g2.setColor(Color.white);
		g2.drawString(text, x, y);

		//Jones Image
		x = gp.screenWidth/2 - (gp.tileSize*2)/2;
		y += gp.tileSize*1;
		g2.drawImage(gp.player.right1, x, y, gp.tileSize*2, gp.tileSize*2, null);

		//MENU
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30F));
		text = "Start Game";
		x = getXforCenteredText(text);
		y += gp.tileSize*3.5;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString("+", x-gp.tileSize, y);
		}

		text = "Option";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString("+", x-gp.tileSize, y);
		}

		text = "Credits";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 2) {
			g2.drawString("+", x-gp.tileSize, y);
		}

		text = "Quit";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 3) {
			g2.drawString("+", x-gp.tileSize, y);
		}


	}
	public void finishState() {

		g2.drawImage(gp.BackgroundImage2, 0, 0, gp.screenWidth, gp.screenHeight, null);

		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50f));

		text = "Congratulations you've found all the artifacts";
		//Shadow
		g2.setColor(Color.black);
		x = getXforCenteredText(text);
		y = gp.tileSize*3;
		g2.drawString(text, x, y);
		//MAIN
		g2.setColor(Color.white);
		g2.drawString(text, x-4, y-4);

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30f));
		text = "Quit";
		x = getXforCenteredText(text);
		y = gp.tileSize*10;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString("+", x-40, y);
		}
	}
	public void gameOverTime() {

		g2.drawImage(gp.BackgroundImage2, 0, 0, gp.screenWidth, gp.screenHeight, null);

		int x;
		int y;
		String text;

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 110f));

		text = "Game Over";
		//Shadow
		g2.setColor(Color.black);
		x = getXforCenteredText(text);
		y = gp.tileSize*4;
		g2.drawString(text, x, y);
		//MAIN
		g2.setColor(Color.white);
		g2.drawString(text, x-4, y-4);

		//Retry
		g2.setFont(g2.getFont().deriveFont(50f));
		text = "Retry";
		x = getXforCenteredText(text);
		y += gp.tileSize*4;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString("+", x-40, y);
		}
		//Back to the title screen
		text = "Quit";
		x = getXforCenteredText(text);
		y += 55;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString("+", x-40, y);
		}
	}
	public int getItemIndexSlot() {
		int itemIndex = slotCol + (slotRow*5);
		return itemIndex;
	}
	public int getXforAlignToRightText(String text, int tailX) {

		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
	}
	public int getXforCenteredText(String text) {

		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
	public void HowToPlayScreen(int frameX, int frameY) {

		int textX;
		int textY;

		String text = "Option";
		textX = getXforCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.setColor(new Color(0, 0, 0));
		g2.drawString(text, textX, textY);

		textX = frameX + gp.tileSize;
		textY += gp.tileSize*2;
		g2.setColor(new Color(0, 0, 0));
		g2.drawString("Full Screen", textX, textY);
		if(commandNum == 0) {
			g2.setColor(new Color(0, 0, 0));
			g2.drawString("+", textX-20, textY);
			if(gp.keyH.enterPressed == true) {
				if(gp.fullScreenOn == false) {
					gp.fullScreenOn = true;
				}
				else if(gp.fullScreenOn == true) {
					gp.fullScreenOn = false;
				}
				subState = 1;
			}
		}
		//music
		textY += gp.tileSize;
		g2.setColor(new Color(0, 0, 0));
		g2.drawString("Music", textX, textY);
		if(commandNum == 1) {
			g2.setColor(new Color(0, 0, 0));
			g2.drawString("+", textX-20, textY);
		}
		//se
		textY += gp.tileSize;
		g2.setColor(new Color(0, 0, 0));
		g2.drawString("SE", textX, textY);
		if(commandNum == 2) {
			g2.setColor(new Color(0, 0, 0));
			g2.drawString("+", textX-20, textY);
		}
		//control
		textY += gp.tileSize;
		g2.setColor(new Color(0, 0, 0));
		g2.drawString("Control", textX, textY);
		if(commandNum == 3) {
			g2.setColor(new Color(0, 0, 0));
			g2.drawString("+", textX-20, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 2;
				commandNum = 0;
			}
		}

		// back
		textY += gp.tileSize*2;
		g2.setColor(new Color(0, 0, 0));
		g2.drawString("Back", textX, textY);
		if(commandNum == 4) {
			g2.setColor(new Color(0, 0, 0));
			g2.drawString("+", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				gp.titleState = 0;
				commandNum = 0;
			}
		}


		// Full Screen
		textX = frameX + (int) (gp.tileSize*5.2);
		textY = frameY + gp.tileSize*2 + 41;
		g2.setStroke(new BasicStroke(3));
		g2.setColor(new Color(0, 0, 0));
		g2.drawRect(textX, textY, 24, 24);
		if(gp.fullScreenOn == true) {
			g2.setColor(new Color(255, 255, 255));
			g2.fillRect(textX, textY, 24, 24);
		}

		// Music
		textY += gp.tileSize;
		g2.setColor(new Color(0, 0, 0));
		g2.drawRect(textX, textY, 120, 24); // 130/5 = 26
		int volumewidth = 24 * gp.music.volumeScale;
		g2.fillRect(textX, textY, volumewidth, 24);
		// SE
		textY += gp.tileSize;
		g2.setColor(new Color(0, 0, 0));
		g2.drawRect(textX, textY, 120, 24);
		volumewidth = 24 * gp.se.volumeScale;
		g2.fillRect(textX, textY, volumewidth, 24);

		gp.config.saveConfig();
	}
	public void options_control(int frameX, int frameY) {

		int textX;
		int textY = 0;

		//TITLE
		String text = "Control";
		textX = getXforCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);

		textX = frameX + gp.tileSize;
		textY += gp.tileSize;
		g2.drawString("Move", textX, textY); textY+=gp.tileSize;
		g2.drawString("Confirm/Attack", textX, textY); textY+=gp.tileSize;
		g2.drawString("Shoot", textX, textY); textY+=gp.tileSize;
		g2.drawString("Character Screen", textX, textY); textY+=gp.tileSize;
		g2.drawString("Pause", textX, textY); textY+=gp.tileSize;
		g2.drawString("Options", textX, textY); textY+=gp.tileSize;

		textX = frameX + gp.tileSize*6;
		textY = frameY + gp.tileSize*2;
		g2.drawString("WASD", textX, textY); textY += gp.tileSize;
		g2.drawString("ENTER", textX, textY); textY += gp.tileSize;
		g2.drawString("U", textX, textY); textY += gp.tileSize;
		g2.drawString("C", textX, textY); textY += gp.tileSize;
		g2.drawString("P", textX, textY); textY += gp.tileSize;
		g2.drawString("ESC", textX, textY); textY += gp.tileSize;

		//BACK
		textX = frameX + gp.tileSize;
		textY = frameY + gp.tileSize*9;
		g2.drawString("Back", textX, textY);
		if(commandNum == 0) {
			g2.drawString("+", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 3;
			}
		}
	}
	public void options_endGameConfirmation(int frameX, int frameY) {

		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize;

		currentDialogue = "Return to the title screen";

		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY = 40;
		}

		//YES
		String text = "Yes";
		textX = getXforCenteredText(text);
		textY += gp.tileSize*3;
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			g2.drawString("+", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				gp.gameState = gp.titleState;
			}
		}
		//NO
		text = "No";
		textX = getXforCenteredText(text);
		textY += gp.tileSize;
		g2.drawString(text, textX, textY);
		if(commandNum == 1) {
			g2.drawString("+", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 4;
			}
		}

	}
	public void options_fullScreenNotification(int frameX, int frameY) {

		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize;

		currentDialogue = "\nThe change will take \neffect after restarting \nthe game.";

		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY+=40;
		}

		//BAck
		textY += gp.tileSize*6;
		g2.drawString("Back", textX, textY);
		if(commandNum == 0) {
			g2.drawString("+", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
			}
		}
	}
	public void options_top(int frameX, int frameY) {

		int textX;
		int textY;

		//TiITLE
		String text = "Options";
		textX = getXforCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);

		//FULL SCREEN ON/OFF
		textX = frameX + gp.tileSize;
		textY += gp.tileSize*2;
		g2.drawString("Full Screen", textX, textY);
		if(commandNum == 0) {
			g2.drawString("+", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				if(gp.fullScreenOn == false) {
					gp.fullScreenOn = true;
				}
				else if(gp.fullScreenOn == true) {
					gp.fullScreenOn = false;
				}
				subState = 1;
			}
		}
		//MUSIC
		textY += gp.tileSize;
		g2.drawString("Music", textX, textY);
		if(commandNum == 1) {
			g2.drawString("+", textX-25, textY);
		}
		//SE
		textY += gp.tileSize;
		g2.drawString("SE", textX, textY);
		if(commandNum == 2) {
			g2.drawString("+", textX-25, textY);
		}
		//CONTROL
		textY += gp.tileSize;
		g2.drawString("Controls", textX, textY);
		if(commandNum == 3) {
			g2.drawString("+", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 2;
				commandNum = 0;
			}
		}
		//EXIT GAME
		textY += gp.tileSize;
		g2.drawString("Exit Game", textX, textY);
		if(commandNum == 4) {
			g2.drawString("+", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 3;
				commandNum = 0;
			}
		}
		//BACK
		textY += gp.tileSize*2;
		g2.drawString("Back", textX, textY);
		if(commandNum == 5) {
			g2.drawString("+", textX, textY);
			if(gp.keyH.enterPressed == true) {
				gp.gameState = gp.playState;
				commandNum = 0;
			}
		}

		//EXIT GAME
		textY += gp.tileSize*2;
		g2.drawString("Back", textX, textY);
		if(commandNum == 5) {
			g2.drawString("+", textX-25, textY);
		}

		//FULL SCREEN CHECK BOX
		textX = frameX + (int)(gp.tileSize*4.5);
		textY = frameY + gp.tileSize*3 - 20;
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(textX, textY, 24, 24);
		if(gp.fullScreenOn == true) {
			g2.fillRect(textX, textY, 24, 24);
		}

		//MUSIC VOLUME
		textY += gp.tileSize;
		g2.drawRect(textX, textY, 120, 24);
		int volumeWidth = 24 * gp.music.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);

		//SE VOLUME
		textY += gp.tileSize;
		g2.drawRect(textX, textY, 120, 24);
		volumeWidth = 24 * gp.se.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);

		gp.config.saveConfig();
	}
	public void OptionScreen() {

		g2.drawImage(gp.BackgroundImage, 0, 0, gp.screenWidth, gp.screenHeight, null);

		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));

		final int frameX = gp.tileSize*6;
		final int frameY = gp.tileSize;
		final int framewidth = gp.tileSize*8;
		final int framehieght = gp.tileSize*10;
		drawSubWindow(frameX, frameY, framewidth, framehieght);

		switch(subState) {
		case 0: HowToPlayScreen(frameX, frameY); break;
		case 1: OptionTitleFullScreenNoti(frameX, frameY); break;
		case 2: OptionTitleControlPanel(frameX, frameY); break;
		}

		gp.keyH.enterPressed = false;
	}
	public void OptionTitleControlPanel(int frameX, int frameY) {

		int textx;
		int texty;

		String text = "Control";
		textx = getXforCenteredText(text);
		texty = frameY + gp.tileSize;
		g2.drawString(text, textx, texty);

		textx = frameX + gp.tileSize;
		texty += gp.tileSize;
		g2.drawString("Move", textx, texty); texty += gp.tileSize;
		g2.drawString("Confirm/Attack", textx, texty); texty += gp.tileSize;
		g2.drawString("Shoot/Cast", textx, texty); texty += gp.tileSize;
		g2.drawString("Status/Inventory", textx, texty); texty += gp.tileSize;
		g2.drawString("Pause", textx, texty); texty += gp.tileSize;
		g2.drawString("Options", textx, texty); texty += gp.tileSize;


		textx = frameX + gp.tileSize*6;
		texty = frameY + gp.tileSize*2;
		g2.drawString("WSAD", textx, texty); texty += gp.tileSize;
		g2.drawString("ENTER", textx, texty); texty += gp.tileSize;
		g2.drawString("U", textx, texty); texty += gp.tileSize;
		g2.drawString("C", textx, texty); texty += gp.tileSize;
		g2.drawString("P", textx, texty); texty += gp.tileSize;
		g2.drawString("ESC", textx, texty); texty += gp.tileSize;

		textx = frameX + gp.tileSize;
		texty = frameY + gp.tileSize*9;
		g2.drawString("Back", textx, texty);
		if(commandNum == 0) {
			g2.drawString("+", textx-25, texty);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 3;
			}
		}
	}
	public void OptionTitleFullScreenNoti(int frameX, int frameY) {

		int textx = frameX + (int) (gp.tileSize/1.5);
		int texty = frameY + gp.tileSize*3;

		currentDialogue = "The change will \ntake effect after restarting \nthe Game.";

		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textx, texty);
			texty += 40;
		}
		// Back
		texty = frameY + gp.tileSize*9;
		g2.drawString("Back", textx, texty);
		if(commandNum == 0) {
			g2.drawString("+", textx-25, texty);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
			}
		}
	}
}