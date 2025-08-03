package main;

import monster.Death;
import monster.Fly;
import monster.Mecha;
import monster.Oinker;
import object.Ammo2;
import object.Artifact_1;
import object.Artifact_2;
import object.Artifact_3;
import object.Artifact_4;
import object.Bow;
import object.Medkit;
import object.Pistol;
//import monster.Tyanak;
import tile_interactive.Barrel;

public class AssetSetter {

	GamePanel gp;

	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	public void setInteractiveTile() {

		int num = 0;

		int i = 0;
		gp.iTile[num][i] = new Barrel(gp,26,44);i++;
		gp.iTile[num][i] = new Barrel(gp,26,45);i++;
		gp.iTile[num][i] = new Barrel(gp,26,42);i++;

	}
	public void setMonster() {

		int num = 0;
		int i = 0;

		gp.monster[num][i] = new Death(gp);
		gp.monster[num][i].worldX = gp.tileSize*40;
		gp.monster[num][i].worldY = gp.tileSize*28;
		i++;

		gp.monster[num][i] = new Death(gp);
		gp.monster[num][i].worldX = gp.tileSize*40;
		gp.monster[num][i].worldY = gp.tileSize*27;
		i++;

		gp.monster[num][i] = new Death(gp);
		gp.monster[num][i].worldX = gp.tileSize*40;
		gp.monster[num][i].worldY = gp.tileSize*25;
		i++;

		//		gp.monster[num][i] = new Death(gp);
		//		gp.monster[num][i].worldX = gp.tileSize*40;
		//		gp.monster[num][i].worldY = gp.tileSize*23;
		//		i++;

		//		gp.monster[num][i] = new Death(gp);
		//		gp.monster[num][i].worldX = gp.tileSize*40;
		//		gp.monster[num][i].worldY = gp.tileSize*21;
		//		i++;

		gp.monster[num][i] = new Fly(gp);
		gp.monster[num][i].worldX = gp.tileSize*10;
		gp.monster[num][i].worldY = gp.tileSize*25;
		i++;

		gp.monster[num][i] = new Fly(gp);
		gp.monster[num][i].worldX = gp.tileSize*9;
		gp.monster[num][i].worldY = gp.tileSize*24;
		i++;

		gp.monster[num][i] = new Fly(gp);
		gp.monster[num][i].worldX = gp.tileSize*9;
		gp.monster[num][i].worldY = gp.tileSize*26;
		i++;

		//		gp.monster[num][i] = new Fly(gp);
		//		gp.monster[num][i].worldX = gp.tileSize*9;
		//		gp.monster[num][i].worldY = gp.tileSize*23;
		//		i++;

		//		gp.monster[num][i] = new Fly(gp);
		//		gp.monster[num][i].worldX = gp.tileSize*9;
		//		gp.monster[num][i].worldY = gp.tileSize*27;
		//		i++;

		gp.monster[num][i] = new Oinker(gp);
		gp.monster[num][i].worldX = gp.tileSize*40;
		gp.monster[num][i].worldY = gp.tileSize*8;
		i++;

		gp.monster[num][i] = new Oinker(gp);
		gp.monster[num][i].worldX = gp.tileSize*42;
		gp.monster[num][i].worldY = gp.tileSize*7;
		i++;

		gp.monster[num][i] = new Oinker(gp);
		gp.monster[num][i].worldX = gp.tileSize*41;
		gp.monster[num][i].worldY = gp.tileSize*8;
		i++;

		//		gp.monster[num][i] = new Oinker(gp);
		//		gp.monster[num][i].worldX = gp.tileSize*40;
		//		gp.monster[num][i].worldY = gp.tileSize*9;
		//		i++;

		//		gp.monster[num][i] = new Oinker(gp);
		//		gp.monster[num][i].worldX = gp.tileSize*42;
		//		gp.monster[num][i].worldY = gp.tileSize*8;
		//		i++;


		gp.monster[num][i] = new Mecha(gp);
		gp.monster[num][i].worldX = gp.tileSize*7;
		gp.monster[num][i].worldY = gp.tileSize*8;
		i++;

		gp.monster[num][i] = new Mecha(gp);
		gp.monster[num][i].worldX = gp.tileSize*4;
		gp.monster[num][i].worldY = gp.tileSize*8;
		i++;

		gp.monster[num][i] = new Mecha(gp);
		gp.monster[num][i].worldX = gp.tileSize*11;
		gp.monster[num][i].worldY = gp.tileSize*5;
		i++;

		//		gp.monster[num][i] = new Mecha(gp);
		//		gp.monster[num][i].worldX = gp.tileSize*12;
		//		gp.monster[num][i].worldY = gp.tileSize*10;
		//		i++;
		//
		//		gp.monster[num][i] = new Mecha(gp);
		//		gp.monster[num][i].worldX = gp.tileSize*6;
		//		gp.monster[num][i].worldY = gp.tileSize*5;
		//		i++;

	}
	public void setNPC() {

		//		gp.npc[0] = new Oldman_NPC(gp);
		//		gp.npc[0].worldX = gp.tileSize*19;
		//		gp.npc[0].worldY = gp.tileSize*41;

	}
	public void setObject() {
		int num = 0;
		int i = 0;
		gp.obj[num][i] = new Bow(gp);
		gp.obj[num][i].worldX = gp.tileSize*3;
		gp.obj[num][i].worldY = gp.tileSize*43;
		i++;
		//
		gp.obj[num][i] = new Pistol(gp);
		gp.obj[num][i].worldX = gp.tileSize*3;
		gp.obj[num][i].worldY = gp.tileSize*44;
		i++;



		gp.obj[num][i] = new Artifact_4(gp);
		gp.obj[num][i].worldX = gp.tileSize*20;
		gp.obj[num][i].worldY = gp.tileSize*10;
		i++;

		gp.obj[num][i] = new Artifact_3(gp);
		gp.obj[num][i].worldX = gp.tileSize*7;
		gp.obj[num][i].worldY = gp.tileSize*7;
		i++;

		gp.obj[num][i] = new Artifact_2(gp);
		gp.obj[num][i].worldX = gp.tileSize*40;
		gp.obj[num][i].worldY = gp.tileSize*8;
		i++;

		gp.obj[num][i] = new Artifact_1(gp);
		gp.obj[num][i].worldX = gp.tileSize*40;
		gp.obj[num][i].worldY = gp.tileSize*28;
		i++;



		gp.obj[num][i] = new Ammo2(gp);
		gp.obj[num][i].worldX = gp.tileSize*8;
		gp.obj[num][i].worldY = gp.tileSize*45;
		i++;

		gp.obj[num][i] = new Ammo2(gp);
		gp.obj[num][i].worldX = gp.tileSize*6;
		gp.obj[num][i].worldY = gp.tileSize*45;
		i++;

		gp.obj[num][i] = new Ammo2(gp);
		gp.obj[num][i].worldX = gp.tileSize*4;
		gp.obj[num][i].worldY = gp.tileSize*45;
		i++;

		gp.obj[num][i] = new Medkit(gp);
		gp.obj[num][i].worldX = gp.tileSize*8;
		gp.obj[num][i].worldY = gp.tileSize*42;
		i++;

		gp.obj[num][i] = new Medkit(gp);
		gp.obj[num][i].worldX = gp.tileSize*6;
		gp.obj[num][i].worldY = gp.tileSize*42;
		i++;

		gp.obj[num][i] = new Medkit(gp);
		gp.obj[num][i].worldX = gp.tileSize*4;
		gp.obj[num][i].worldY = gp.tileSize*42;
		i++;



	}
}
