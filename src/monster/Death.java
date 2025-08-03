package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.Ammo2;
import object.Flame;
import object.Medkit;

public class Death extends Entity{

	GamePanel gp;

	public Death(GamePanel gp) {
		super(gp);

		this.gp = gp;

		type = type_monster;
		name = "Death";
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

		up1 = setup("/monster/deathL",gp.tileSize,gp.tileSize);
		up2 = setup("/monster/deathL2",gp.tileSize,gp.tileSize);
		up3 = setup("/monster/deathL3",gp.tileSize,gp.tileSize);
		up4 = setup("/monster/deathL4",gp.tileSize,gp.tileSize);


		down1 = setup("/monster/deathL",gp.tileSize,gp.tileSize);
		down2 = setup("/monster/deathL2",gp.tileSize,gp.tileSize);
		down3 = setup("/monster/deathL3",gp.tileSize,gp.tileSize);
		down4 = setup("/monster/deathL4",gp.tileSize,gp.tileSize);


		right1 = setup("/monster/deathR1",gp.tileSize,gp.tileSize);
		right2 = setup("/monster/deathR2",gp.tileSize,gp.tileSize);
		right3 = setup("/monster/deathR3",gp.tileSize,gp.tileSize);
		right4 = setup("/monster/deathR4",gp.tileSize,gp.tileSize);


		left1 = setup("/monster/deathL",gp.tileSize,gp.tileSize);
		left2 = setup("/monster/deathL2",gp.tileSize,gp.tileSize);
		left3 = setup("/monster/deathL3",gp.tileSize,gp.tileSize);
		left4 = setup("/monster/deathL4",gp.tileSize,gp.tileSize);

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
