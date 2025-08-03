package tile_interactive;

import entity.Entity;
import main.GamePanel;

public class Bush extends InteractiveTile{

	GamePanel gp;

	public Bush(GamePanel gp, int col, int row) {
		super(gp,col,row);
		this.gp = gp;

		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;

		down1 = setup("/tile_interactive/bush4",gp.tileSize,gp.tileSize);
		destructible = true;
		life = 3;

	}
	@Override
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;

		if(entity.weapon.type == type_whip) {
			isCorrectItem = false;
		}

		return isCorrectItem;
	}
}