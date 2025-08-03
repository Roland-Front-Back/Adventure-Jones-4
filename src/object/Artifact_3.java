package object;

import entity.Entity;
import main.GamePanel;

public class Artifact_3 extends Entity{

	GamePanel gp;

	public Artifact_3(GamePanel gp) {
		super(gp);
		this.gp = gp;

		type = type_dropitem;
		name = "Skull";
		value = 1;
		down1 = setup("/objects/Skull",gp.tileSize,gp.tileSize);
		description = "An ancient skull from long lost civillization";

	}

	@Override
	public void use(Entity entity) {

		//	gp.playerSE();
		gp.ui.addMessage("Skull +" + value);
		gp.player.artifact += value;

	}

}
