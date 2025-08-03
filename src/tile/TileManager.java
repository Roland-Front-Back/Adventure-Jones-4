package tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][][];
	public BufferedImage scaledImage;
	boolean drawpath = true;

	public TileManager(GamePanel gp) {
		this.gp = gp;

		tile = new Tile[80];
		mapTileNum = new int[gp.maxmap][gp.maxWorldCol][gp.maxWorldRow];

		getTileImage();
		loadMap("/maps/final.txt",0);
	}
	public void draw(Graphics g2) {

		int worldCol = 0;
		int worldRow = 0;

		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

			int tileNum = mapTileNum[gp.currentmap][worldCol][worldRow];

			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;

			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
					worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
					worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
					worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}
			worldCol++;

			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}

	public void getTileImage() {

		//PLACEHOLDER
		setup(0, "newwater", false);
		setup(1, "newwater", false);
		setup(2, "newwater", false);
		setup(3, "newwater", false);
		setup(4, "newwater", false);
		setup(5, "newwater", false);
		setup(6, "newwater", false);
		setup(7, "newwater", false);
		setup(8, "newwater", false);
		setup(9, "newwater", false);
		//
		//			//TILESET
		setup(10, "newgrass1", false);
		setup(11, "tile8", true);
		setup(12, "topleftcorner", true);
		setup(13, "tile6", true);
		setup(14, "downmid", true);
		setup(15, "tile7", true);
		setup(16, "tile11", true);
		setup(17, "topright", true);
		setup(18, "upmid", true);
		setup(19, "newsand", true);
		setup(20, "lake14", true);
		setup(21, "tile12", true);
		setup(22, "toprightcorner", true);
		setup(23, "tile9", true);
		setup(24, "newtree1", true);
		setup(25, "tile10", true);
		setup(26, "log1", true);
		setup(27, "rip", true);
		setup(28, "water", true);
		setup(29, "tile4", true);
		setup(30, "tile3", true);
		setup(31, "tile2", true);
		setup(32, "tile1", true);
		setup(33, "tile.8", true);
		setup(34, "tile.7", true);
		setup(35, "tile.6", true);
		setup(36, "tile.5", true);
		setup(37, "tile.10", true);
		setup(38, "tile.9", true);
		setup(39, "tile.11", true);
		setup(40, "tile.13", true);
		setup(41, "tile.14", true);
		setup(42, "tile.15", true);
		setup(43, "tile.12", true);
		setup(44, "boss1stone", true);
		setup(45, "boss2stone", true);
		setup(46, "bosstile.3", false);
		setup(47, "stonepath.1", false);
		setup(48, "grave.1", true);
		setup(49, "bosstile2", false);
		setup(50, "boss3stone", true);
		setup(51, "bosstile3", false);
		setup(52, "newgrass2", false);
		setup(53, "tab", false);

	}
	public void loadMap(String filePath, int map) {

		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;

			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {

				String line = br.readLine();

				while(col < gp.maxWorldCol) {

					String numbers[] = line.split(" ");

					int num = Integer.parseInt(numbers[col]);

					mapTileNum[map][col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		}catch(Exception e) {

		}

	}
	public void setup(int index, String imageName, boolean collision) {

		UtilityTool uTool = new UtilityTool();

		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName +".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;

		}catch(IOException e) {
			e.printStackTrace();
		}

	}

}

