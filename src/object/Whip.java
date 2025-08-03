package object;

import entity.Entity;
import main.GamePanel;

public class Whip extends Entity{

	public Whip(GamePanel gp) {
		super(gp);

		type = type_whip;
		name = "Whip";
		down1 = setup("/objects/whip", gp.tileSize, gp.tileSize);
		attackValue = 3;
		attackArea.width = 36;
		attackArea.height = 36;
		description = "" + name + " A bullwhip";
	}
}
