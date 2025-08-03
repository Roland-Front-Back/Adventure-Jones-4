package object;

import entity.Entity;
import main.GamePanel;

public class Ammo extends Entity{

	GamePanel gp;
	
	public Ammo(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = "Ammo";
		image = setup("/objects/Ammo_full", gp.tileSize, gp.tileSize);
		image2 = setup("/objects/Ammo_Empty", gp.tileSize, gp.tileSize);

	}
//	public void use(Entity entity) {
//		
//		//	gp.playerSE();
//			gp.ui.addMessage("Ammo +" + value);
//			entity.ammo += value;
//		}
}
