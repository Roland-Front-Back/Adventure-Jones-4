package object;

import entity.Entity;
import main.GamePanel;

public class Artifact_1 extends Entity{

	GamePanel gp;

	public Artifact_1(GamePanel gp) {
		super(gp);
		this.gp = gp;

		type = type_dropitem;
		name = "Book";
		value = 1;
		down1 = setup("/objects/Book",gp.tileSize,gp.tileSize);
		description = "An ancient book of Lazariuss";

	}

	@Override
	public void use(Entity entity) {

		//	gp.playerSE();
		gp.ui.addMessage("Book +" + value);
		gp.player.artifact += value;
		if(value == 1) {
			gp.gameState = gp.finishState;
		}
	}

}
