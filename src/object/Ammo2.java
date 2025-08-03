package object;

import entity.Entity;
import main.GamePanel;

public class Ammo2 extends Entity{
	
	GamePanel gp;
	
	public Ammo2(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_dropitem;
		name = "Ammo";
		value = 1;
		down1 = setup("/objects/Ammo",gp.tileSize,gp.tileSize);
	}

	public void use(Entity entity) {
		
	//	gp.playerSE();
		gp.ui.addMessage("Ammo +" + value);
		gp.player.ammo += value;
	}
}
