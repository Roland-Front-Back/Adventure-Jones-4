package entity;

import java.util.Random;

import main.GamePanel;


public class Oldman_NPC extends Entity{
	
	public Oldman_NPC(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 0;
		
		getImage();
		setDialogue();
	}
	public void getImage() {
		

		up1 = setup("/npc/Quiboloy stop",gp.tileSize,gp.tileSize);
		up2 = setup("/npc/Quiboloy stop",gp.tileSize,gp.tileSize);
		up3 = setup("/npc/Quiboloy stop",gp.tileSize,gp.tileSize);
		up4 = setup("/npc/Quiboloy stop",gp.tileSize,gp.tileSize);

		left1 = setup("/npc/Quiboloy stop",gp.tileSize,gp.tileSize);
		left2 = setup("/npc/Quiboloy stop",gp.tileSize,gp.tileSize);
		left3 = setup("/npc/Quiboloy stop",gp.tileSize,gp.tileSize);
		left4 = setup("/npc/Quiboloy stop",gp.tileSize,gp.tileSize);
		
		right1 = setup("/npc/Quiboloy stop",gp.tileSize,gp.tileSize);
		right2 = setup("/npc/Quiboloy stop",gp.tileSize,gp.tileSize);
		right3 = setup("/npc/Quiboloy stop",gp.tileSize,gp.tileSize);
		right4 = setup("/npc/Quiboloy stop",gp.tileSize,gp.tileSize);
		
		down1 = setup("/npc/Quiboloy stop",gp.tileSize,gp.tileSize);
		down2 = setup("/npc/Quiboloy stop",gp.tileSize,gp.tileSize);
		down3 = setup("/npc/Quiboloy stop",gp.tileSize,gp.tileSize);
		down4 = setup("/npc/Quiboloy stop",gp.tileSize,gp.tileSize);
		
	}
	public void setDialogue() {
		
		dialogues[0] = "Hello Mr.Jones!";
		dialogues[1] = "How are you? Im glad you came.. I need your help about something important";
		dialogues[2] = "Since we all know that you are the expert";
		dialogues[3] = "I want you to find the four ancient artifact";

	}
	public void setAction () {
		
		actionLockCounter ++;
		
		if(actionLockCounter == 120) {
			
			Random random = new Random();
			int i  = random.nextInt(100)+1; //pick up numnber 1 to 100
			
			if(i <= 25) {
				direction = "up";
			}
			if(i > 25 && i  <= 50) {
				direction = "down";
			}
			if(i > 50 &&  i <= 75) {
				direction = "left";
			}
			if(i > 75 && i <= 100) {
				direction = "right";
			}
			
			actionLockCounter = 0;
		}
		
	}
	public void speak() {
		
		//DO THIS CHARACTER SPECIFC TASK
		super.speak();
	}
}
