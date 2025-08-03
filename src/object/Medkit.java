package object;

import entity.Entity;
import main.GamePanel;

public class Medkit extends Entity{

	GamePanel gp;

	public Medkit(GamePanel gp) {
		super(gp);
		this.gp = gp;

		type = type_consumable;
		name = "Medkit";
		value = 5;
		down1 = setup("/objects/MedKit", gp.tileSize,gp.tileSize);
		description = "Restore player's Life by" + value + ".";
	}
	@Override
	public void use(Entity entity) {
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "Heals your life" + value + ".";
		entity.life += value;
		//sgp.playerSE(value);
	}

}
