package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {

	GamePanel gp;
	public BufferedImage up1, up2, up3, up4, up5, up6,up7, up8, up9, up10, down1, down2, down3, down4, down5, down6, down7, down8, down9, down10,
	left1, left2, left3, left4, left5, left6, left7, left8, left9, left10, right1, right2, right3, right4, right5, right6, right7, right8, right9, right10;
	public BufferedImage attackUp1, attackUp2, attackUp3, attackUp4, attackUp5, attackUp6, attackUp7, attackUp8, attackUp9, attackUp10,  attackDown1, attackDown2,
	attackDown3, attackDown4, attackDown5, attackDown6, attackDown7, attackDown8, attackDown9, attackDown10,
	attackLeft1, attackLeft2, attackLeft3, attackLeft4, attackLeft5, attackLeft6, attackLeft7, attackLeft8, attackLeft9, attackLeft10, attackRight1, attackRight2,
	attackRight3, attackRight4, attackRight5, attackRight6, attackRight7, attackRight8, attackRight9, attackRight10;
	public BufferedImage image, image2, image3, image4, image5;
	public Rectangle solidArea = new Rectangle(36,36, 64, 64);
	public Rectangle attackArea = new Rectangle(0, 0, 36, 40);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collision = false;
	String dialogues[] = new String[50];

	//STATE
	public int worldX, worldY;
	public String direction = "down";
	public int spriteNum = 1;
	int dialogueIndex = 0;
	public boolean collisionOn = false;
	public boolean invincible = false;
	public boolean attacking = false;
	public boolean alive = true;
	public boolean dying = false;
	boolean hpBarOn = false;
	public boolean onPath = false;


	//COUNTER
	public int spriteCounter = 0;
	public int actionLockCounter = 0;
	public int invincibleCounter =0;
	public int shotAvailableCounter = 0;
	int dyingCounter = 0;
	int hpBarCounter = 0;

	//CHARACTER ATTRIBUTES
	public String name;
	public int speed;
	public int maxLife;
	public int life;
	public int level;
	public int maxAmmo;
	public int ammo;
	public int strength;
	public int energy;
	public int attack;
	public int defense;
	public int artifact;
	public int exp;
	public int nextLevelExp;
	public int motion1_duration;
	public int motion2_duration;
	public Entity weapon;
	public Entity armor;
	public Entity pistol;
	public Projectile projectile;



	//ITEM ATTRIBUTES
	public int value;
	public int attackValue;
	public int defenseValue;
	public String description = "";
	public int useCost;


	//TYpe
	public int type; // 0 = player 1 npv 2 monster
	public final int type_player = 0;
	public final int type_npc = 1;
	public final int type_monster = 2;
	public final int type_whip = 3;
	public final int type_armor = 4;
	public final int type_artifact = 9;
	public final int type_consumable = 5;
	public final int type_pistol = 6;
	public final int type_bow = 7;
	public final int type_dropitem = 8;

	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	public void attacking() {

		spriteCounter++;

		if(spriteCounter <= motion1_duration) {
			spriteNum = 1;
		}
		if(spriteCounter > motion1_duration && spriteCounter <= motion2_duration) {
			spriteNum = 2;

			int currentworldX = worldX;
			int currentworldY = worldY;
			int solidareawidth = solidArea.width;
			int solidareaheight = solidArea.height;

			switch(direction) {
			case "up": worldY -= attackArea.height; break;
			case "down": worldY += attackArea.height; break;
			case "left": worldX -= attackArea.width; break;
			case "right": worldX += attackArea.width; break;
			}
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;

			if(type == type_monster) {
				if(gp.cChecker.checkPlayer(this) == true) {
					damagePlayer(attack);
				}
			}
			else {

				int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
				gp.player.damagedMonster(monsterIndex, attack);

				int tileIndex = gp.cChecker.checkEntity(this, gp.iTile);
				gp.player.damageInteractiveTile(tileIndex);

				int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
				gp.player.damagedProjectile(projectileIndex);
			}

			worldX = currentworldX;
			worldY = currentworldY;
			solidArea.width = solidareawidth;
			solidArea.height = solidareaheight;
		}
		if(spriteCounter > motion2_duration) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}
	public void AttackorNot(int rate, int straight, int horizontal) {

		boolean targetrange = false;
		int xdis = getXdistance(gp.player);
		int ydis = getYdistance(gp.player);

		switch(direction) {
		case "up":
			if(gp.player.worldY < worldY && ydis < straight && xdis < horizontal) {
				targetrange = true;
			}
			break;
		case "down":
			if(gp.player.worldY > worldY && ydis < straight && xdis < horizontal) {
				targetrange = true;
			}
			break;
		case "left":
			if(gp.player.worldX < worldX && xdis < straight && ydis < horizontal) {
				targetrange = true;
			}
			break;
		case "right":
			if(gp.player.worldX > worldX && xdis < straight && ydis < horizontal) {
				targetrange = true;
			}
			break;
		}

		if(targetrange == true) {
			int i = new Random().nextInt(rate);
			if(i == 0) {
				attacking = true;
				spriteNum = 3;
				spriteCounter = 0;
				shotAvailableCounter = 0;
			}
		}
	}
	public void changeAlpha(Graphics2D g2, float alphaValue ){

		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));

	}
	public void checkDrop() {}
	public void collisionCheck() {

		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		gp.cChecker.checkEntity(this, gp.iTile);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);


		if(this.type == type_monster && contactPlayer == true) {

			damagePlayer(attack);
		}
	}
	public void damagePlayer(int attack) {

		if(gp.player.invincible == false) {
			//we can give damge

			gp.playSE(4);

			int damage = attack - gp.player.defense;
			if(damage < 0) {
				damage = 0;
			}
			gp.player.life -= damage;
			gp.player.invincible = true;
		}
	}
	public void damageReaction() {

	}
	public void draw(Graphics2D g2) {

		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;

		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
				worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
				worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

			int temsscreenX = screenX;
			int temscreenY = screenY;

			switch(direction) {
			case "up":

				if(spriteNum == 1) {image = up1;}
				if(spriteNum == 2) {image = up2;}
				if(spriteNum == 3) {image = up3;}
				if(spriteNum == 4) {image = up4;}

			case "down":

				if(spriteNum == 1) {image = down1;}
				if(spriteNum == 2) {image = down2;}
				if(spriteNum == 3) {image = down3;}
				if(spriteNum == 4) {image = down4;}
				break;
			case "left":
				if(spriteNum == 1) {image = left1;}
				if(spriteNum == 2) {image = left2;}
				if(spriteNum == 3) {image = left3;}
				if(spriteNum == 4) {image = left4;}
				break;
			case "right":
				if(spriteNum == 1) {image = right1;}
				if(spriteNum == 2) {image = right2;}
				if(spriteNum == 3) {image = right3;}
				if(spriteNum == 4) {image = right4;}
				break;
			}

			//Monster HP Bar
			if(type == 2 && hpBarOn == true) {

				double oneScale = (double)gp.tileSize/maxLife;
				double hpBarValue = oneScale*life;

				g2.setColor(new Color(35,35,35));
				g2.fillRect(screenX-1, screenY-16, gp.tileSize+2, 12);

				g2.setColor(new Color(255, 0, 30));
				g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);

				hpBarCounter++;

				if(hpBarCounter > 600) {
					hpBarCounter = 0;
					hpBarOn = false;
				}
			}

			if(invincible == true) {
				hpBarOn = true;
				hpBarCounter = 0;
				changeAlpha(g2,0.4f);
			}
			if(dying == true) {
				dyingAnimation(g2);
			}
			g2.drawImage(image, screenX, screenY, null);
			changeAlpha(g2,1f);

		}

	}
	public void dropItem(Entity droppedItem) {

		for(int i = 0; i < gp.obj[1].length; ++i) {
			if(gp.obj[gp.currentmap][i] == null) {
				gp.obj[gp.currentmap][i] = droppedItem;
				gp.obj[gp.currentmap][i].worldX = worldX; // the dead monster worldX
				gp.obj[gp.currentmap][i].worldY = worldY;
				break;
			}
		}
	}
	public void dyingAnimation(Graphics2D g2) {

		dyingCounter++;

		int i = 5;

		if(dyingCounter <= i) {changeAlpha(g2, 0f);}
		if(dyingCounter > i && dyingCounter <= i*2) {changeAlpha(g2, 1f);}
		if(dyingCounter > i*2 && dyingCounter <= i*3) {changeAlpha(g2, 0f);}
		if(dyingCounter > i*3 && dyingCounter <= i*4) {changeAlpha(g2, 1f);}
		if(dyingCounter > i*4 && dyingCounter <= i*5) {changeAlpha(g2, 0f);}
		if(dyingCounter > i*5 && dyingCounter <= i*6) {changeAlpha(g2, 1f);}
		if(dyingCounter > i*6 && dyingCounter <= i*7) {changeAlpha(g2, 0f);}
		if(dyingCounter > i*7 && dyingCounter <= i*8) {changeAlpha(g2, 1f);}
		if(dyingCounter > i*8) {
			alive = false;
		}
	}
	public void generateParticle(Entity generator, Entity target) {

		Color color = generator.getParticleColor();
		int size = generator.getParticleSize();
		int speed = generator.getParticleSpeed();
		int maxLife = generator.getParticleMaxLife();

		Particle p1 = new Particle(gp, generator, color, size, speed, maxLife, -2, -1);
		Particle p2 = new Particle(gp, generator, color, size, speed, maxLife, 2, -1);
		Particle p3 = new Particle(gp, generator, color, size, speed, maxLife, -2, 1);
		Particle p4 = new Particle(gp, generator, color, size, speed, maxLife, 2, 1);

		gp.particleList.add(p1);
		gp.particleList.add(p2);
		gp.particleList.add(p3);
		gp.particleList.add(p4);

	}
	public int getGoalCol(Entity target) {
		int goalcol = (target.worldX + target.solidArea.x)/gp.tileSize;
		return goalcol;
	}
	public int getGoalRow(Entity target) {
		int goalrow = (target.worldY + target.solidArea.y)/gp.tileSize;
		return goalrow;
	}
	public Color getParticleColor() {
		Color color = null;
		return color;
	}
	public int getParticleMaxLife() {
		int maxLife = 0;
		return maxLife;
	}
	public int getParticleSize() {
		int size = 0; // 6 pixels
		return size;
	}
	public int getParticleSpeed() {
		int speed = 0;
		return speed;
	}
	public int gettiledistance(Entity target) {

		int tiledistance = (getXdistance(target) + getYdistance(target))/gp.tileSize;
		return tiledistance;
	}
	public int getXdistance(Entity target) {
		int xdistance = Math.abs(worldX - target.worldX);
		return xdistance;
	}
	public int getYdistance(Entity target) {

		int ydistance = Math.abs(worldY - target.worldY);
		return ydistance;
	}
	public void RandomDirection() {

		actionLockCounter ++;

		if(actionLockCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(100)+1;

			if(i <= 25) {direction = "up";}
			if(i > 25 && i <= 50) {direction = "down";}
			if(i > 50 && i <= 75) {direction = "left";}
			if(i > 75 && i <= 100) {direction = "right";}
			actionLockCounter = 0;
		}
	}
	public void searchPath(int goalcol, int goalrow) {

		int startcol = (worldX + solidArea.x)/gp.tileSize;
		int startrow = (worldY + solidArea.y)/gp.tileSize;

		gp.pathP.setfindpath(startcol, startrow, goalcol, goalrow,this);

		if(gp.pathP.search() == true) {

			int nextX = gp.pathP.pathList.get(0).col * gp.tileSize;
			int nextY = gp.pathP.pathList.get(0).row * gp.tileSize;

			int leftx = worldX + solidArea.x;
			int rightx = worldX + solidArea.x + solidArea.width;
			int topy = worldY + solidArea.y;
			int bottony = worldY + solidArea.y + solidArea.height;

			if(topy > nextY && leftx >= nextX && rightx < nextX + gp.tileSize) {
				direction = "up";
			}
			else if(topy < nextY && leftx >= nextX && rightx < nextX + gp.tileSize) {
				direction = "down";
			}
			else if(topy >= nextY && bottony < nextY + gp.tileSize) {

				if(leftx > nextX) {
					direction = "left";
				}
				if(leftx < nextX) {
					direction = "right";
				}
			}
			else if(topy > nextY && leftx > nextX) {

				direction = "up";
				collisionCheck();
				if(collisionOn == true) {
					direction = "left";
				}
			}
			else if(topy > nextY && leftx < nextX) {

				direction = "up";
				collisionCheck();
				if(collisionOn == true) {
					direction = "right";
				}
			}
			else if(topy < nextY && leftx > nextX) {

				direction = "down";
				collisionCheck();
				if(collisionOn == true) {
					direction = "left";
				}
			}
			else if(topy < nextY && leftx < nextX) {

				direction = "down";
				collisionCheck();
				if(collisionOn == true) {
					direction = "right";
				}
			}
			//			int nextcol = gp.pathP.pathList.get(0).col;
			//			int nextrow = gp.pathP.pathList.get(0).row;
			//			if(nextcol == goalcol && nextrow == goalrow) {
			//				onpath = false;
			//			}
		}
	}
	public void setAction() {}
	public BufferedImage setup(String imagePath, int width, int  height) {

		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;

		try {

			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, width, height);


		}catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	public void ShotorNot(int rate, int shotinterval) {

		int i = new Random().nextInt(rate);
		if(i == 0 && projectile.alive == false && shotAvailableCounter == shotinterval) {

			projectile.set(worldX, worldY, direction, true, this);

			for(int ii = 0; ii <gp.projectile[1].length; ii++) {
				if(gp.projectile[gp.currentmap][ii] == null) {
					gp.projectile[gp.currentmap][ii] = projectile;
					break;
				}
			}
			shotAvailableCounter = 0;
		}
	}
	public void speak() {

		if(dialogues[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;

		switch(gp.player.direction) {
		case "up":
			direction = "down";
			break;
		case "down":
			direction = "up";
			break;
		case "left":
			direction = "right";
			break;
		case "right":
			direction = "left";
			break;
		}
	}
	public void startChasingorNot(Entity target, int distance, int rate) {

		if(gettiledistance(target) < distance) {
			int i = new Random().nextInt(rate);
			if(i == 0) {
				onPath = true;
			}
		}
	}
	public void stopChasingorNot(Entity target, int distance, int rate) {

		if(gettiledistance(target) > distance) {
			int i = new Random().nextInt(rate);
			if(i == 0) {
				onPath = false;
			}
		}
	}
	public void update() {

		if(attacking == true) {
			attacking();
		}
		else {

			//use for all npc
			setAction();

			collisionCheck();

			//IF collision false,  player can move
			if(collisionOn == false) {

				switch(direction) {
				case "up":worldY -= speed; break;
				case "down":worldY += speed; break;
				case "left":worldX -= speed; break;
				case "right":worldX += speed; break;
				}
			}

			spriteCounter++;
			if(spriteCounter > 9) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 3;
				}
				else if(spriteNum == 3) {
					spriteNum = 4;
				}
				else if(spriteNum == 4) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}


		if(invincible == true) {
			invincibleCounter++;
			if(invincibleCounter > 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		if(shotAvailableCounter < 30) {
			shotAvailableCounter++;
		}
	}
	public void use(Entity entity) {}
}
