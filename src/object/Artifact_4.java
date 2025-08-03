package object;

import entity.Entity;
import main.GamePanel;

public class Artifact_4 extends Entity{

	GamePanel gp;

	public Artifact_4(GamePanel gp) {
		super(gp);
		this.gp = gp;

		type = type_dropitem;
		name = "Helmet";
		value = 1;
		down1 = setup("/objects/Helmet",gp.tileSize,gp.tileSize);
		description = "An ancient shield of Marcus Aurelius";

	}

	@Override
	public void use(Entity entity) {

		//	gp.playerSE();
		gp.ui.addMessage("Helmet +" + value);
		gp.player.artifact += value;

	}

}
