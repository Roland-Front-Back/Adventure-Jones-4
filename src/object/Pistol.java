package object;

import entity.Entity;
import main.GamePanel;

public class Pistol extends Entity{

	GamePanel gp;

	public Pistol(GamePanel gp) {
		super(gp);

		type = type_pistol;
		name = "Pistol";
		down1 = setup("/objects/Gun2", gp.tileSize, gp.tileSize);
		attackValue = 1;
		description = "An old pistol.";


	}

}
