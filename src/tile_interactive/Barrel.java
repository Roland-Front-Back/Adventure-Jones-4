package tile_interactive;

import java.awt.Color;

import entity.Entity;
import main.GamePanel;

public class Barrel extends InteractiveTile{

	GamePanel gp;
	
	public Barrel(GamePanel gp, int col, int row) {
		super(gp,col,row);
		this.gp = gp;
		
		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;
		
		down1 = setup("/tile_interactive/Barrel1",gp.tileSize,gp.tileSize);
		destructible = true;
		life = 3;
		
	}
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		
		if(entity.weapon.type == type_whip) {
			isCorrectItem = true;
		}
		
		return isCorrectItem;
	}
	public void playerSE() {
		
	}

	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile = new B_Destroy(gp, worldX/gp.tileSize, worldY/gp.tileSize);
		return tile;
	}
	public Color getParticleColor() {
		Color color = new Color(65, 50,30);
		return color;
	}
	public int getParticleSize() {
		int size = 6; // 6 pixels
		return size;
	}
	public int getParticleSpeed() {
		int speed = 1;
		return speed;
	}
	public int getParticleMaxLife() {
		int maxLife = 20;
		return maxLife;
	}
}
