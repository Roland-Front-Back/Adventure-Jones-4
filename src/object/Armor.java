package object;

import entity.Entity;
import main.GamePanel;

public class Armor extends Entity{

	public Armor(GamePanel gp) {
		super(gp);

		name = "Vest";
		down1 = setup("/objects/Vest", gp.tileSize, gp.tileSize);
		description = "" + name + "A bulletproof vest";

	}

}
