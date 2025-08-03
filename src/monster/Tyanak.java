package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

//public class Tyanak extends Entity{
//
//	public Tyanak(GamePanel gp) {
//		super(gp);
//		
//		name = "Tyanak";
//		speed = 2;
//		maxLife = 4;
//		life = maxLife;
//		
//		solidArea.x = 4;
//		solidArea.y = 10;
//		solidArea.width = 30;
//		solidArea.height = 20;
//		solidAreaDefaultX = solidArea.x;
//		solidAreaDefaultY = solidArea.y;
//		
//		getImage();
//	}
//	public void getImage() {
//		
//		up1 = setup("/monster/tyanak_left_1");
//		up2 = setup("/monster/tyanak_left_2");
//		up3 = setup("/monster/tyanak_left_3");
//		up4 = setup("/monster/tyanak_left_4");
//		up5 = setup("/monster/tyanak_left_5");
//		up6 = setup("/monster/tyanak_left_6");
//		up7 = setup("/monster/tyanak_left_7");
//		up8 = setup("/monster/tyanak_left_8");
//		
//		down1 = setup("/monster/tyanak_left_1");
//		down2 = setup("/monster/tyanak_left_2");
//		down3 = setup("/monster/tyanak_left_3");
//		down4 = setup("/monster/tyanak_left_4");
//		down5 = setup("/monster/tyanak_left_5");
//		down6 = setup("/monster/tyanak_left_6");
//		down7 = setup("/monster/tyanak_left_7");
//		down8 = setup("/monster/tyanak_left_8");
//		
//		right1 = setup("/monster/tyanak_right_1");
//		right2 = setup("/monster/tyanak_right_2");
//		right3 = setup("/monster/tyanak_right_3");
//		right4 = setup("/monster/tyanak_right_4");
//		right5 = setup("/monster/tyanak_right_5");
//		right6 = setup("/monster/tyanak_right_6");
//		right7 = setup("/monster/tyanak_right_7");
//		right8 = setup("/monster/tyanak_right_8");
//
//		left1 = setup("/monster/tyanak_left_1");
//		left2 = setup("/monster/tyanak_left_2");
//		left3 = setup("/monster/tyanak_left_3");
//		left4 = setup("/monster/tyanak_left_4");
//		left5 = setup("/monster/tyanak_left_5");
//		left6 = setup("/monster/tyanak_left_6");
//		left7 = setup("/monster/tyanak_left_7");
//		left8 = setup("/monster/tyanak_left_8");
//	}
//	public void setAction() {
//		
//actionLockCounter ++;
//		
//		if(actionLockCounter == 120) {
//			
//			Random random = new Random();
//			int i  = random.nextInt(100)+1; //pick up numnber 1 to 100
//			
//			if(i <= 25) {
//				direction = "up";
//			}
//			if(i > 25 && i  <= 50) {
//				direction = "down";
//			}
//			if(i > 50 &&  i <= 75) {
//				direction = "left";
//			}
//			if(i > 75 && i <= 100) {
//				direction = "right";
//			}
//			
//			actionLockCounter = 0;
//		}
//	}
//}
