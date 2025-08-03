package object;

import entity.Entity;
import main.GamePanel;

public class Health_Bar extends Entity{
	
	
	public Health_Bar (GamePanel gp) {
		super(gp);
		
		name = "Health";
		image = setup("/objects/full hat",gp.tileSize,gp.tileSize);
		image2 = setup("/objects/empty hat",gp.tileSize,gp.tileSize);

		
	}
}
