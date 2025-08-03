package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.Ammo2;
import object.Flame;
import object.Medkit;

public class Oinker extends Entity{

	GamePanel gp;

	public Oinker(GamePanel gp) {
		super(gp);

		this.gp = gp;

		type = type_monster;
		name = "Oinker";
		speed = 2;
		maxLife = 5;
		life = maxLife;
		attack = 2;
		defense = 1;
		exp = 2;
		projectile = new Flame(gp);

		solidArea.x = 4;
		solidArea.y = 10;
		solidArea.width = 30;
		solidArea.height = 20;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		getImage();
	}
	@Override
	public void checkDrop() {

		//Cast a die
		int i = new Random().nextInt(100)+1;

		//set the monster drop
		if(i < 50) {
			dropItem(new Ammo2(gp));
		}
		if(i >= 50 && i < 75) {
			dropItem(new Medkit(gp));
		}
	}
	@Override
	public void damageReaction() {

		actionLockCounter = 0;
		onPath = true;
		//direction = gp.player.direction;
	}
	public void getImage() {

		up1 = setup("/monster/PIGL1",gp.tileSize,gp.tileSize);
		up2 = setup("/monster/PIGL2",gp.tileSize,gp.tileSize);
		up3 = setup("/monster/PIGL3",gp.tileSize,gp.tileSize);
		up4 = setup("/monster/PIGL4",gp.tileSize,gp.tileSize);


		down1 = setup("/monster/PIGL1",gp.tileSize,gp.tileSize);
		down2 = setup("/monster/PIGL2",gp.tileSize,gp.tileSize);
		down3 = setup("/monster/PIGL3",gp.tileSize,gp.tileSize);
		down4 = setup("/monster/PIGL4",gp.tileSize,gp.tileSize);


		right1 = setup("/monster/PIGR1",gp.tileSize,gp.tileSize);
		right2 = setup("/monster/PIGR2",gp.tileSize,gp.tileSize);
		right3 = setup("/monster/PIGR3",gp.tileSize,gp.tileSize);
		right4 = setup("/monster/PIGR4",gp.tileSize,gp.tileSize);


		left1 = setup("/monster/PIGL1",gp.tileSize,gp.tileSize);
		left2 = setup("/monster/PIGL2",gp.tileSize,gp.tileSize);
		left3 = setup("/monster/PIGL3",gp.tileSize,gp.tileSize);
		left4 = setup("/monster/PIGL4",gp.tileSize,gp.tileSize);

	}
	@Override
	public void setAction() {

		if(onPath == true) {

			stopChasingorNot(gp.player, 15, 100);

			searchPath(getGoalCol(gp.player),getGoalRow(gp.player));

			ShotorNot(100, 30);
		}
		else {

			startChasingorNot(gp.player, 5, 100);

			RandomDirection();
		}

	}
}
