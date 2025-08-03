package monster;

import entity.Entity;
import main.GamePanel;

public class Boss_1 extends Entity{

	GamePanel gp;

	public Boss_1(GamePanel gp) {
		super(gp);
		this.gp = gp;

		type = type_monster;
		name = "Death";
		speed = 3;
		maxLife = 10;
		life = maxLife;
		attack = 2;
		defense = 1;
		exp = 4;

		solidArea.x = 22;
		solidArea.y = 38;
		solidArea.width = 30;
		solidArea.height = 34;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		attackArea.width = 24;
		attackArea.height = 24;
		motion1_duration = 40;
		motion2_duration = 85;

		getImage();
		getattackmons();
	}
	public void damagereaction() {

		actionLockCounter = 0;
		onPath = true;
	}
	//	public void checkdrop() {
	//
	//		int i = new Random().nextInt(100)+1;
	//
	//		if(i< 50) {
	//			dropItem(new OBJ_heart(gp));
	//		}
	//		if(i >= 50 && i < 75) {
	//			dropItem(new energybar(gp));
	//		}
	//		if(i >= 75 && i < 100) {
	//			dropItem(new OBJ_gem(gp));
	//		}
	//	}
	public void getattackmons() {

		attackUp1 = setup("/monster/1_atk_1", gp.tileSize, gp.tileSize);
		attackUp2 = setup("/monster/1_atk_2", gp.tileSize, gp.tileSize);
		attackUp3 = setup("/monster/1_atk_3", gp.tileSize, gp.tileSize);
		//		attackUp4 = setup("/monster/1_atk_4", gp.tileSize, gp.tileSize);
		//		attackUp5 = setup("/monster/1_atk_5", gp.tileSize, gp.tileSize);
		//		attackUp6 = setup("/monster/1_atk_6", gp.tileSize, gp.tileSize);
		//		attackUp7 = setup("/monster/1_atk_7", gp.tileSize, gp.tileSize);
		//		attackUp8 = setup("/monster/1_atk_8", gp.tileSize, gp.tileSize);
		//		attackUp9 = setup("/monster/1_atk_9", gp.tileSize, gp.tileSize);
		//		attackUp10 = setup("/monster/1_atk_10", gp.tileSize, gp.tileSize);

		attackDown1 = setup("/monster/1_atk_1", gp.tileSize, gp.tileSize);
		attackDown2 = setup("/monster/1_atk_2", gp.tileSize, gp.tileSize);
		attackDown3 = setup("/monster/1_atk_3", gp.tileSize, gp.tileSize);
		//		attackDown4 = setup("/monster/1_atk_4", gp.tileSize, gp.tileSize);
		//		attackDown5 = setup("/monster/1_atk_5", gp.tileSize, gp.tileSize);
		//		attackDown6 = setup("/monster/1_atk_6", gp.tileSize, gp.tileSize);
		//		attackDown7 = setup("/monster/1_atk_7", gp.tileSize, gp.tileSize);
		//		attackDown8 = setup("/monster/1_atk_8", gp.tileSize, gp.tileSize);
		//		attackDown9 = setup("/monster/1_atk_9", gp.tileSize, gp.tileSize);
		//		attackDown10 = setup("/monster/1_atk_10", gp.tileSize, gp.tileSize);

		attackLeft1 = setup("/monster/1_atk_1", gp.tileSize, gp.tileSize);
		attackLeft2 = setup("/monster/1_atk_2", gp.tileSize, gp.tileSize);
		attackLeft3 = setup("/monster/1_atk_3", gp.tileSize, gp.tileSize);
		//		attackLeft4 = setup("/monster/1_atk_4", gp.tileSize, gp.tileSize);
		//		attackLeft5 = setup("/monster/1_atk_5", gp.tileSize, gp.tileSize);
		//		attackLeft6 = setup("/monster/1_atk_6", gp.tileSize, gp.tileSize);
		//		attackLeft7 = setup("/monster/1_atk_7", gp.tileSize, gp.tileSize);
		//		attackLeft8 = setup("/monster/1_atk_8", gp.tileSize, gp.tileSize);
		//		attackLeft9 = setup("/monster/1_atk_9", gp.tileSize, gp.tileSize);
		//		attackLeft10 = setup("/monster/1_atk_10", gp.tileSize, gp.tileSize);

		attackRight1 = setup("/monster/1_atk_1", gp.tileSize, gp.tileSize);
		attackRight2 = setup("/monster/1_atk_2", gp.tileSize, gp.tileSize);
		attackRight3 = setup("/monster/1_atk_3", gp.tileSize, gp.tileSize);
		//		attackRight4 = setup("/monster/1_atk_4", gp.tileSize, gp.tileSize);
		//		attackRight5 = setup("/monster/1_atk_5", gp.tileSize, gp.tileSize);
		//		attackRight6 = setup("/monster/1_atk_6", gp.tileSize, gp.tileSize);
		//		attackRight7 = setup("/monster/1_atk_7", gp.tileSize, gp.tileSize);
		//		attackRight8 = setup("/monster/1_atk_8", gp.tileSize, gp.tileSize);
		//		attackRight9 = setup("/monster/1_atk_9", gp.tileSize, gp.tileSize);
		//attackRight10 = setup("/monster/1_atk_10", gp.tileSize, gp.tileSize);

	}
	public void getImage() {

		up1 = setup("/monster/walk_1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/walk_2", gp.tileSize, gp.tileSize);
		up3 = setup("/monster/walk_3", gp.tileSize, gp.tileSize);
		//		up4 = setup("/monster/walk_4", gp.tileSize, gp.tileSize);
		//		up5 = setup("/monster/walk_5", gp.tileSize, gp.tileSize);
		//		up6 = setup("/monster/walk_6", gp.tileSize, gp.tileSize);
		//		up7 = setup("/monster/wallk_7", gp.tileSize, gp.tileSize);
		//		up8 = setup("/monster/walk_8", gp.tileSize, gp.tileSize);
		//		up9 = setup("/monster/walk_9", gp.tileSize, gp.tileSize);
		//		up10 = setup("/monster/walk_10", gp.tileSize, gp.tileSize);

		down1 = setup("/monster/walk_1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/walk_2", gp.tileSize, gp.tileSize);
		down3 = setup("/monster/walk_3", gp.tileSize, gp.tileSize);
		//		down4 = setup("/monster/walk_4", gp.tileSize, gp.tileSize);
		//		down5 = setup("/monster/walk_5", gp.tileSize, gp.tileSize);
		//		down6 = setup("/monster/walk_6", gp.tileSize, gp.tileSize);
		//		down7 = setup("/monster/wallk_7", gp.tileSize, gp.tileSize);
		//		down8 = setup("/monster/walk_8", gp.tileSize, gp.tileSize);
		//		down9 = setup("/monster/walk_9", gp.tileSize, gp.tileSize);
		//		down10 = setup("/monster/walk_10", gp.tileSize, gp.tileSize);

		left1 = setup("/monster/walk_1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/walk_2", gp.tileSize, gp.tileSize);
		left3 = setup("/monster/walk_3", gp.tileSize, gp.tileSize);
		//		left4 = setup("/monster/walk_4", gp.tileSize, gp.tileSize);
		//		left5 = setup("/monster/walk_5", gp.tileSize, gp.tileSize);
		//		left6 = setup("/monster/walk_6", gp.tileSize, gp.tileSize);
		//		left7 = setup("/monster/wallk_7", gp.tileSize, gp.tileSize);
		//		left8 = setup("/monster/walk_8", gp.tileSize, gp.tileSize);
		//		left9 = setup("/monster/walk_9", gp.tileSize, gp.tileSize);
		//		left10 = setup("/monster/walk_10", gp.tileSize, gp.tileSize);

		right1 = setup("/monster/walk_1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/walk_2", gp.tileSize, gp.tileSize);
		right3 = setup("/monster/walk_3", gp.tileSize, gp.tileSize);
		//		right4 = setup("/monster/walk_4", gp.tileSize, gp.tileSize);
		//		right5 = setup("/monster/walk_5", gp.tileSize, gp.tileSize);
		//		right6 = setup("/monster/walk_6", gp.tileSize, gp.tileSize);
		//		right7 = setup("/monster/wallk_7", gp.tileSize, gp.tileSize);
		//		right8 = setup("/monster/walk_8", gp.tileSize, gp.tileSize);
		//		right9 = setup("/monster/walk_9", gp.tileSize, gp.tileSize);
		//		right10 = setup("/monster/walk_10", gp.tileSize, gp.tileSize);
	}

	@Override
	public void setAction() {

		if(onPath == true) {

			stopChasingorNot(gp.player, 15, 100);

			searchPath(getGoalCol(gp.player),getGoalRow(gp.player));
		}
		else {

			startChasingorNot(gp.player, 5, 100);

			RandomDirection();
		}
		if(attacking == false) {
			AttackorNot(30, gp.tileSize*4, gp.tileSize);
		}
	}

}
