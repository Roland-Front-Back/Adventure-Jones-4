package object;

import entity.Projectile;
import main.GamePanel;

public class Flame extends Projectile{

	GamePanel gp;

	public Flame(GamePanel gp) {
		super(gp);
		this.gp = gp;

		name = "Poison";
		speed = 8;
		maxLife = 90;
		life = maxLife;
		attack = 2;
		useCost = 1;
		alive = false;

		getImage();
	}

	public void getImage() {
		up1 = setup("/projectiles/FlameU", gp.tileSize, gp.tileSize);
		up2 = setup("/projectiles/FlameU", gp.tileSize, gp.tileSize);
		up3 = setup("/projectiles/FlameU", gp.tileSize, gp.tileSize);
		up4 = setup("/projectiles/FlameU", gp.tileSize, gp.tileSize);

		down1 = setup("/projectiles/FlameD", gp.tileSize, gp.tileSize);
		down2 = setup("/projectiles/FlameD", gp.tileSize, gp.tileSize);
		down3 = setup("/projectiles/FlameD", gp.tileSize, gp.tileSize);
		down4 = setup("/projectiles/FlameD", gp.tileSize, gp.tileSize);

		left1 = setup("/projectiles/FlameL", gp.tileSize, gp.tileSize);
		left2 = setup("/projectiles/FlameL", gp.tileSize, gp.tileSize);
		left3 = setup("/projectiles/FlameL", gp.tileSize, gp.tileSize);
		left4 = setup("/projectiles/FlameL", gp.tileSize, gp.tileSize);

		right1 = setup("/projectiles/FlameR", gp.tileSize, gp.tileSize);
		right2 = setup("/projectiles/FlameR", gp.tileSize, gp.tileSize);
		right3 = setup("/projectiles/FlameR", gp.tileSize, gp.tileSize);
		right4 = setup("/projectiles/FlameR", gp.tileSize, gp.tileSize);

	}


}
