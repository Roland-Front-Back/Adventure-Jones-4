package object;

import entity.Entity;
import main.GamePanel;

public class Bush extends Entity{

	GamePanel gp;

	public Bush(GamePanel gp) {
		super(gp);
		this.gp = gp;

		name = "Bush";
		down1 = setup("/objects/bush4",gp.tileSize,gp.tileSize);


	}

}
