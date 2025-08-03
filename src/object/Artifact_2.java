package object;

import entity.Entity;
import main.GamePanel;

public class Artifact_2 extends Entity{

	GamePanel gp;

	public Artifact_2(GamePanel gp) {
		super(gp);
		this.gp = gp;

		type = type_dropitem;
		name = "Shield";
		value = 1;
		down1 = setup("/objects/Shield",gp.tileSize,gp.tileSize);
		description = "An ancient shield of Marcus Aurelius";

	}

	@Override
	public void use(Entity entity) {

		//	gp.playerSE();
		gp.ui.addMessage("Shield +" + value);
		gp.player.artifact += value;

	}

}
