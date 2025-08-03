package object;

import entity.Entity;
import main.GamePanel;

public class Bow extends Entity{

	GamePanel gp;

	public Bow(GamePanel gp) {
		super(gp);

		type = type_bow;
		name = "Bow";
		down1 = setup("/objects/BownArrow", gp.tileSize, gp.tileSize);
		attackValue = 1;
		description = "A noraml bow but handy.";

	}

}
