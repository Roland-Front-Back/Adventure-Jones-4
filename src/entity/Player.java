package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GamePanel;
import main.KeyHandler;
import object.Armor;
import object.Arrow;
import object.Bullet;
import object.Whip;

public class Player extends Entity{

	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	int standCounter = 0;
	public boolean attackCanceled = false;
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 20;

	public Player(GamePanel gp, KeyHandler keyH) {

		super(gp);

		this.keyH = keyH;

		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);

		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;

		//	attackArea.width = 36;
		//	attackArea.height = 36;

		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
		setItems();
	}
	@Override
	public void attacking() {

		spriteCounter++;

		if(spriteCounter <= 5) {
			spriteNum =1;
		}
		if(spriteCounter > 5 && spriteCounter <= 25) {
			spriteNum = 2;

			//Save the current worldX, worldY, solidARea
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;

			//Adjust player's worldX/Y for the attackArea
			switch(direction) {
			case "up": worldY -= attackArea.height; break;
			case "down": worldY += attackArea.height; break;
			case "left": worldX -= attackArea.width; break;
			case "right": worldX += attackArea.width; break;
			}
			//attackArea become solidArea
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			//Check o=monster collision with the updated worldx, worldy, solidarea
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			damagedMonster(monsterIndex, attack);

			int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
			damageInteractiveTile(iTileIndex);

			//After checking collision, restore the original data
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;

		}
		if(spriteCounter > 25) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}
	public void checkLevelUp() {

		if(exp >= nextLevelExp) {

			level++;
			nextLevelExp = nextLevelExp*2;
			strength++;
			energy++;
			attack = getAttack();
			defense = getDefense();

			gp.playSE(5);
			gp.gameState = gp.dialogueState;
			gp.ui.currentDialogue = "You are level " + level + " now!\n"
					+ "You feel stronger";
		}
	}
	public void contactMonster(int i) {

		if(i != 999) {

			if(invincible == false && gp.monster[gp.currentmap][i].dying == false) {
				gp.playSE(4);

				int damage = gp.monster[gp.currentmap][i].attack - defense;
				if(damage < 0) {
					damage = 0;
				}
				life -= damage;
				invincible = true;
			}
		}
	}
	public void damagedMonster(int i, int attack) {

		if(i != 999) {


			if(gp.monster[gp.currentmap][i].invincible == false) {

				gp.playSE(3);

				int damage = attack - gp.monster[gp.currentmap][i].defense;
				if(damage < 0) {
					damage = 0;
				}
				gp.monster[gp.currentmap][i].life -= damage;
				gp.ui.addMessage(damage + "damage");

				gp.monster[gp.currentmap][i].invincible = true;
				gp.monster[gp.currentmap][i].damageReaction();

				if(gp.monster[gp.currentmap][i].life <= 0) {
					gp.monster[gp.currentmap][i].dying = true;
					gp.ui.addMessage("killed the " + gp.monster[gp.currentmap][i].name + "!");
					gp.ui.addMessage("Exp " + gp.monster[gp.currentmap][i].exp);
					exp += gp.monster[gp.currentmap][i].exp;
					checkLevelUp();

				}
			}
		}

	}
	public void damagedProjectile(int i) {

		if(i != 999) {
			Entity projectile = gp.projectile[gp.currentmap][i];
			projectile.alive = false;
			generateParticle(projectile,projectile);
		}
	}
	public void damageInteractiveTile(int i) {

		if(i != 999 && gp.iTile[gp.currentmap][i].destructible == true
				&& gp.iTile[gp.currentmap][i].isCorrectItem(this) == true && gp.iTile[gp.currentmap][i].invincible == false) {

			gp.iTile[gp.currentmap][i].playerSE();
			gp.iTile[gp.currentmap][i].life--;
			gp.iTile[gp.currentmap][i].invincible = true;

			//Genet=rate particles
			generateParticle(gp.iTile[gp.currentmap][i],gp.iTile[gp.currentmap][i]);

			if(gp.iTile[gp.currentmap][i].life == 0) {
				gp.iTile[gp.currentmap][i] = gp.iTile[gp.currentmap][i].getDestroyedForm();
			}
		}
	}
	@Override
	public void draw(Graphics2D g2) {

		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;

		switch(direction) {
		case "up":
			if(attacking == false) {
				if(spriteNum == 1) {image = up1;}
				if(spriteNum == 2) {image = up2;}
				if(spriteNum == 3) {image = up3;}
				if(spriteNum == 4) {image = up4;}
			}
			else if(attacking == true) {
				tempScreenY = screenY - gp.tileSize/1;
				//if(spriteNum == 1) {image = attackUp1;}
				if(spriteNum == 2) {image = attackUp2;}
				//if(spriteNum == 3) {image = up3;}
				//if(spriteNum == 4) {image = up4;}
			}
			break;
		case "down":
			if(attacking == false) {
				if(spriteNum == 1) {image = down1;}
				if(spriteNum == 2) {image = down2;}
				if(spriteNum == 3) {image = down3;}
				if(spriteNum == 4) {image = down4;}
			}
			if(attacking == true) {
				tempScreenY = screenY - gp.tileSize/5;
				//if(spriteNum == 1) {image = attackDown1;}
				if(spriteNum == 2) {image = attackDown2;}
				//	if(spriteNum == 3) {image = down3;}
				//if(spriteNum == 4) {image = down4;}
			}
			break;
		case "left":
			if(attacking == false) {
				if(spriteNum == 1) {image = left1;}
				if(spriteNum == 2) {image = left2;}
				if(spriteNum == 3) {image = left3;}
				if(spriteNum == 4) {image = left4;}
			}
			if(attacking == true) {
				tempScreenX = screenX - gp.tileSize;
				if(spriteNum == 1) {image = attackLeft1;}
				if(spriteNum == 2) {image = attackLeft2;}
				//if(spriteNum == 3) {image = left3;}
				//if(spriteNum == 4) {image = left4;}
			}
			break;
		case "right":
			if(attacking == false) {
				if(spriteNum == 1) {image = right1;}
				if(spriteNum == 2) {image = right2;}
				if(spriteNum == 3) {image = right3;}
				if(spriteNum == 4) {image = right4;}
			}
			if(attacking == true) {
				if(spriteNum == 1) {image = attackRight1;}
				if(spriteNum == 2) {image = attackRight2;}
				//if(spriteNum == 3) {image = right3;}
				//if(spriteNum == 4) {image = right4;}
				break;
			}

		}
		if(invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		}
		g2.drawImage(image, tempScreenX, tempScreenY, null);

		// Reset alpha to fully opaque
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

	}
	public int getAttack() {
		attackArea = weapon.attackArea;
		motion1_duration = weapon.motion1_duration;
		motion2_duration = weapon.motion2_duration;
		return attack = strength  * weapon.attackValue;

	}
	public int getDefense() {
		return defense = energy * armor.defenseValue;
	}
	public void getPlayerAttackImage() {

		//attackUp1 = setup("/player/rup_whip_1",gp.tileSize,gp.tileSize*2);
		attackUp2 = setup("/player/whip_2.2 (1)",gp.tileSize,gp.tileSize*2);
		//attackUp3 = setup("/player/whip_3",gp.tileSize,gp.tileSize*2);
		//attackUp4 = setup("/player/whip_3",gp.tileSize,gp.tileSize*2);

		//attackDown1 = setup("/player/whip_2",gp.tileSize,gp.tileSize*2);
		attackDown2 = setup("/player/whip_3.2 (1)",gp.tileSize,gp.tileSize*2);
		//attackDown3 = setup("/player/up_whip_2",gp.tileSize,gp.tileSize*2);
		//attackDown4 = setup("/player/up_Whip_2",gp.tileSize,gp.tileSize*2);

		attackLeft1 = setup("/player/left_whip_1",gp.tileSize*2,gp.tileSize);
		attackLeft2 = setup("/player/left_whip_2",gp.tileSize*2,gp.tileSize);
		//attackLeft3 = setup("/player/left_whip_2",gp.tileSize*2,gp.tileSize);
		//attackLeft4 = setup("/player/left_Whip_2",gp.tileSize*2,gp.tileSize);

		attackRight1 = setup("/player/right_whip_1",gp.tileSize*2,gp.tileSize);
		attackRight2 = setup("/player/right_Whip_2",gp.tileSize*2,gp.tileSize);
		//attackRight3 = setup("/player/right_whip_2",gp.tileSize*2,gp.tileSize);
		//	attackRight4 = setup("/player/right_whip_2",gp.tileSize*2,gp.tileSize);
	}
	public void getPlayerImage() {

		if(weapon.type == type_whip) {

			up1 = setup("/player/jones up",gp.tileSize,gp.tileSize);
			up2 = setup("/player/jones up1",gp.tileSize,gp.tileSize);
			up3 = setup("/player/jones up2",gp.tileSize,gp.tileSize);
			up4 = setup("/player/jones up3",gp.tileSize,gp.tileSize);

			down1 = setup("/player/jones down",gp.tileSize,gp.tileSize);
			down2 = setup("/player/jones down1",gp.tileSize,gp.tileSize);
			down3 = setup("/player/jones down2",gp.tileSize,gp.tileSize);
			down4 = setup("/player/jones down3",gp.tileSize,gp.tileSize);

			left1 = setup("/player/jones left",gp.tileSize,gp.tileSize);
			left2 = setup("/player/jones left1",gp.tileSize,gp.tileSize);
			left3 = setup("/player/jones left2",gp.tileSize,gp.tileSize);
			left4 = setup("/player/jones left3",gp.tileSize,gp.tileSize);

			right1 = setup("/player/jones right",gp.tileSize,gp.tileSize);
			right2 = setup("/player/jones right1",gp.tileSize,gp.tileSize);
			right3 = setup("/player/jones right2",gp.tileSize,gp.tileSize);
			right4 = setup("/player/jones right3",gp.tileSize,gp.tileSize);
		}

		if(weapon.type == type_pistol) {

			up1 = setup("/player/jones gun up 1",gp.tileSize,gp.tileSize);
			down1 = setup("/player/jones gun down 1",gp.tileSize,gp.tileSize);
			left1 = setup("/player/jones gun left 1",gp.tileSize,gp.tileSize);
			right1 = setup("/player/jones gun right 1",gp.tileSize,gp.tileSize);

		}


	}
	public void interactNPC(int i) {

		if(gp.keyH.enterPressed == true) {
			if(i != 999) {
				attackCanceled = true;
				gp.gameState = gp.dialogueState;
				gp.npc[gp.currentmap][i].speak();
			}
		}
	}
	public void pickUpObject(int i) {

		if(i != 999) {

			//PICK ONLY ITEMS
			if(gp.obj[gp.currentmap][i].type == type_dropitem) {

				gp.obj[gp.currentmap][i].use(this);
				gp.obj[gp.currentmap][i] = null;
				gp.playSE(5);
			}

			//INVENTORY ITEMS
			else {

				String text;

				if(inventory.size() != maxInventorySize) {

					inventory.add(gp.obj[gp.currentmap][i]);
					gp.playSE(5);
					text = "Got a " + gp.obj[gp.currentmap][i].name + "!";
				}
				else {
					text = "Invetory is Full!";
				}
				gp.ui.addMessage(text);
				gp.obj[gp.currentmap][i] = null;
			}
		}
	}
	public void restoreLifeAndAmmo() {

		life = maxLife;
		ammo = maxAmmo;
		invincible = false;
	}
	public void selectItem() {

		int itemIndex = gp.ui.getItemIndexSlot();

		if(itemIndex < inventory.size()) {

			Entity selectedItem = inventory.get(itemIndex);

			if(selectedItem.type == type_whip || selectedItem.type == type_armor) {

				weapon = selectedItem;
				attack = getAttack();
				getPlayerAttackImage();
			}
			if(selectedItem.type == type_bow) {
				weapon = selectedItem;
				projectile = new Arrow(gp);
			}
			if(selectedItem.type == type_pistol) {
				weapon = selectedItem;
				projectile = new Bullet(gp);
			}
			if(selectedItem.type == type_armor) {

				armor = selectedItem;
				defense = getDefense();
			}
			if(selectedItem.type == type_consumable) {

				selectedItem.use(this);
				inventory.remove(itemIndex);
			}
		}
	}
	public void setDefaultPosition() {

		worldX = gp.tileSize * 25;
		worldY = gp.tileSize * 42;
		direction = "down";

	}
	public void setDefaultValues() {

		worldX = gp.tileSize * 25;
		worldY = gp.tileSize * 42;
		speed = 6;
		direction = "down";

		//PLAYER STATUS
		level = 1;
		maxLife = 10;
		life = maxLife;
		maxAmmo = 5;
		ammo = maxAmmo;
		strength = 1; // the more strength he has, the more damage he gives
		energy = 1;
		artifact = 0;
		exp = 0;
		nextLevelExp = 5;
		weapon = new Whip(gp);
		armor = new Armor(gp);
		projectile = new Bullet(gp);
		attack = getAttack(); // the total attack value is decided by strength and weapon
		defense = getDefense();
	}
	public void setItems() {

		inventory.clear();
		inventory.add(weapon);
		inventory.add(armor);
		//inventory.add(shield)
	}
	@Override
	public void update() {

		if(attacking == true) {
			attacking();
		}
		else if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true ||
				keyH.rightPressed == true || keyH.enterPressed == true) {

			if(keyH.upPressed == true) {
				direction = "up";
			}
			else if(keyH.downPressed == true) {
				direction = "down";
			}
			else if(keyH.leftPressed == true) {
				direction = "left";
			}
			else if(keyH.rightPressed == true) {
				direction = "right";

			}

			//Check Tile Collision
			collisionOn = false;
			gp.cChecker.checkTile(this);

			//CHECK OBJECT COLLISION
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);

			//CHECK NPC COLLISION
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);

			//CHECK MONSTER COLLISION
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);

			//Check Interaction tile collision
			int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);

			//CHECK EVENT
			//	gp.eHandler.checkEvent();

			//IF collision false,  player can move
			if(collisionOn == false && keyH.enterPressed == false) {

				switch(direction) {
				case "up":worldY = worldY - speed;break;
				case "down":worldY = worldY + speed;break;
				case "left":worldX = worldX - speed;break;
				case "right":worldX = worldX + speed; break;
				}
			}

			if(keyH.enterPressed == true && attackCanceled == false) {
				gp.playSE(1);
				attacking = true;
				spriteCounter = 0;
			}

			attackCanceled = false;
			gp.keyH.enterPressed = false;

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
		else {
			standCounter++;
			if(standCounter == 20) {
				spriteNum = 1;
				standCounter =0;
			}
		}
		if(weapon.type == type_pistol || weapon.type == type_bow) {

			if(gp.keyH.shotKeyPressed == true && projectile.alive == false
					&& shotAvailableCounter == 30 && projectile.haveRessource(this) == true) {

				//SET DEFALT COORDINATES, DIRECTION AND USER
				projectile.set(worldX, worldY, direction, true, this);

				//SUBTRACT THE COST (AMMO, ARROWS)
				projectile.subtractResource(this);

				//ADD IT TO THE LIST
				for(int i = 0; i < gp.projectile[1].length; ++i) {
					if(gp.projectile[gp.currentmap][i] == null) {
						gp.projectile[gp.currentmap][i] = projectile;
					}
				}

				shotAvailableCounter = 0;

			}
		}


		//GAWAS RANI SIYA BORDS
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
		if(life > maxLife) {
			life = maxLife;
		}
		if(ammo > maxAmmo) {
			ammo = maxAmmo;
		}
		if(life <= 0) {
			gp.gameState = gp.gameOverState;
			//gp.playSE();
		}
	}
}

