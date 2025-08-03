package main;

import entity.Entity;

public class CollisionChecker {

	GamePanel gp;

	public CollisionChecker(GamePanel gp) {

		this.gp = gp;
	}

	//NPC & MONSTER COLLISION
	public int checkEntity(Entity entity, Entity[][] target) {

		int index = 999;

		for(int i = 0; i < target[1].length; ++i) {

			if(target[gp.currentmap][i] != null) {

				//Get entity's soliad area position
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				//Get the object's solid area position
				target[gp.currentmap][i].solidArea.x = target[gp.currentmap][i].worldX + target[gp.currentmap][i].solidArea.x;
				target[gp.currentmap][i].solidArea.y = target[gp.currentmap][i].worldY + target[gp.currentmap][i].solidArea.y;

				switch(entity.direction) {
				case "up": entity.solidArea.y -= entity.speed;
				break;
				case "down": entity.solidArea.y += entity.speed;
				break;
				case "left": entity.solidArea.x -= entity.speed;
				break;
				case "right": entity.solidArea.x += entity.speed;
				break;
				}
				if(entity.solidArea.intersects(target[gp.currentmap][i].solidArea)) {
					if(target[gp.currentmap][i] != entity) {
						entity.collisionOn = true;
						index = i;
					}
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				target[gp.currentmap][i].solidArea.x = target[gp.currentmap][i].solidAreaDefaultX;
				target[gp.currentmap][i].solidArea.y = target[gp.currentmap][i].solidAreaDefaultY;
			}
		}
		return index;
	}
	public int checkObject(Entity entity, boolean player) {

		int index = 999;

		for(int i = 0; i < gp.obj[1].length; ++i) {
			if(gp.obj[gp.currentmap][i] != null) {

				//Get entity's solid area position
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;

				//Get the object's solid area position
				gp.obj[gp.currentmap][i].solidArea.x = gp.obj[gp.currentmap][i].worldX + gp.obj[gp.currentmap][i].solidArea.x;
				gp.obj[gp.currentmap][i].solidArea.y = gp.obj[gp.currentmap][i].worldY + gp.obj[gp.currentmap][i].solidArea.y;

				switch(entity.direction) {
				case "up": entity.solidArea.y -= entity.speed; break;
				case "down": entity.solidArea.y += entity.speed; break;
				case "left": entity.solidArea.x -= entity.speed; break;
				case "right":entity.solidArea.x += entity.speed; break;

				}
				if(entity.solidArea.intersects(gp.obj[gp.currentmap][i].solidArea)) {
					if(gp.obj[gp.currentmap][i].collision == true) {
						entity.collisionOn = true;
					}
					if(player == true) {
						index = i;
					}
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[gp.currentmap][i].solidArea.x = gp.obj[gp.currentmap][i].solidAreaDefaultX;
				gp.obj[gp.currentmap][i].solidArea.y = gp.obj[gp.currentmap][i].solidAreaDefaultY;
			}
		}
		return index;
	}
	public boolean checkPlayer(Entity entity) {

		boolean contactPlayer = false;

		//Get entity's solid area position
		entity.solidArea.x = entity.worldX + entity.solidArea.x;
		entity.solidArea.y = entity.worldY + entity.solidArea.y;
		//Get the object's solid area position
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

		switch(entity.direction) {
		case "up":entity.solidArea.y -= entity.speed; break;
		case "down": entity.solidArea.y += entity.speed;break;
		case "left":entity.solidArea.x -= entity.speed; break;
		case "right": entity.solidArea.x += entity.speed; break;
		}
		if(entity.solidArea.intersects(gp.player.solidArea)) {
			entity.collisionOn = true;
			contactPlayer = true;
		}
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;

		return contactPlayer;
	}

	public void checkTile(Entity entity) {

		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;

		int tileNum1, tileNum2;


		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.currentmap][entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentmap][entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.currentmap][entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentmap][entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.currentmap][entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentmap][entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.currentmap][entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentmap][entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;


		}

	}
}
