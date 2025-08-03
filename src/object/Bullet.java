package object;

import java.awt.Color;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class Bullet extends Projectile{

	GamePanel gp;

	public Bullet(GamePanel gp) {
		super(gp);
		this.gp = gp;

		name = "Bullet";
		speed = 3;
		maxLife = 300;
		life = maxLife;
		attack = 3;
		useCost = 1;
		alive = false;
		getImage();
	}

	public void getImage() {
		up1 = setup("/projectiles/BulletU", gp.tileSize, gp.tileSize);
		up2 = setup("/projectiles/BulletU", gp.tileSize, gp.tileSize);
		up3 = setup("/projectiles/BulletU", gp.tileSize, gp.tileSize);
		up4 = setup("/projectiles/BulletU", gp.tileSize, gp.tileSize);

		down1 = setup("/projectiles/BulletD", gp.tileSize, gp.tileSize);
		down2 = setup("/projectiles/BulletD", gp.tileSize, gp.tileSize);
		down3 = setup("/projectiles/BulletD", gp.tileSize, gp.tileSize);
		down4 = setup("/projectiles/BulletD", gp.tileSize, gp.tileSize);

		left1 = setup("/projectiles/BulletL", gp.tileSize, gp.tileSize);
		left2 = setup("/projectiles/BulletL", gp.tileSize, gp.tileSize);
		left3 = setup("/projectiles/BulletL", gp.tileSize, gp.tileSize);
		left4 = setup("/projectiles/BulletL", gp.tileSize, gp.tileSize);

		right1 = setup("/projectiles/BulletR", gp.tileSize, gp.tileSize);
		right2 = setup("/projectiles/BulletR", gp.tileSize, gp.tileSize);
		right3 = setup("/projectiles/BulletR", gp.tileSize, gp.tileSize);
		right4 = setup("/projectiles/BulletR", gp.tileSize, gp.tileSize);

	}
	@Override
	public Color getParticleColor() {
		Color color = new Color(240, 50,0);
		return color;
	}
	@Override
	public int getParticleMaxLife() {
		int maxLife = 20;
		return maxLife;
	}
	@Override
	public int getParticleSize() {
		int size = 8; // 6 pixels
		return size;
	}
	@Override
	public int getParticleSpeed() {
		int speed = 1;
		return speed;
	}
	@Override
	public boolean haveRessource(Entity user) {

		boolean haveResource = false;
		if(user.ammo >= useCost) {
			haveResource = true;
		}
		return haveResource;
	}
	@Override
	public void subtractResource(Entity user) {
		user.ammo -= useCost;
	}
}
