package AI;

import java.util.ArrayList;

import entity.Entity;
import main.GamePanel;

public class PathPosition {

	GamePanel gp;
	FindPath[][] path;
	ArrayList<FindPath> openList = new ArrayList<>();
	public ArrayList<FindPath> pathList= new ArrayList<>();
	FindPath startpath, goalpath, currentpath;
	boolean goalreach = false;
	int step = 0;

	public PathPosition(GamePanel gp) {
		this.gp = gp;
		findpathposition();
	}
	public void filepathreset() {

		int col = 0;
		int row = 0;

		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {

			path[col][row].open = false;
			path[col][row].checked = false;
			path[col][row].solid = false;

			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
		openList.clear();
		pathList.clear();
		goalreach = false;
		step = 0;
	}
	public void findpathposition() {

		path = new FindPath[gp.maxWorldCol][gp.maxWorldRow];

		int col = 0;
		int row = 0;

		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
			path[col][row] = new FindPath(col,row);

			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}
	public void getcost(FindPath path) {

		int xdistance = Math.abs(path.col - startpath.col);
		int ydistance = Math.abs(path.row - startpath.row);
		path.gcost = xdistance + ydistance;

		xdistance = Math.abs(path.col - goalpath.col);
		ydistance = Math.abs(path.row - goalpath.row);
		path.hcost = xdistance + ydistance;

		path.fcost = path.gcost + path.hcost;
	}
	public void openpath(FindPath path) {

		if(path.open == false && path.checked == false && path.solid == false) {

			path.open = true;
			path.parent = currentpath;
			openList.add(path);
		}
	}
	public boolean search() {

		while(goalreach == false && step < 500) {

			int col = currentpath.col;
			int row = currentpath.row;

			currentpath.checked = true;
			openList.remove(currentpath);

			if(row -1 >= 0) {
				openpath(path[col][row-1]);
			}
			if(col -1 >= 0) {
				openpath(path[col-1][row]);
			}
			if(row + 1 < gp.maxWorldRow) {
				openpath(path[col][row+1]);
			}
			if(col + 1 < gp.maxWorldRow) {
				openpath(path[col+1][row]);
			}

			int pathIndex = 0;
			int pathfcost = 999;

			for(int i = 0; i < openList.size(); i++) {

				if(openList.get(i).fcost < pathfcost) {
					pathIndex = i;
					pathfcost = openList.get(i).fcost;
				}
				else if(openList.get(i).fcost == pathfcost) {
					if(openList.get(i).gcost < openList.get(pathIndex).gcost) {
						pathIndex = i;
					}
				}
			}
			if(openList.size() == 0) {
				break;
			}
			currentpath = openList.get(pathIndex);

			if(currentpath == goalpath) {
				goalreach = true;
				trackpath();
			}
			step++;
		}
		return goalreach;
	}
	public void setfindpath(int startcol, int startrow, int goalcol, int goalrow, Entity entity) {

		filepathreset();

		startpath = path[startcol][startrow];
		currentpath = startpath;
		goalpath = path[goalcol][goalrow];
		openList.add(currentpath);

		int col = 0;
		int row = 0;

		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {

			int tileNum = gp.tileM.mapTileNum[gp.currentmap][col][row];
			if(gp.tileM.tile[tileNum].collision == true) {
				path[col][row].solid = true;
			}
			for(int i = 0; i < gp.iTile[1].length; i++) {
				if(gp.iTile[gp.currentmap][i] != null && gp.iTile[gp.currentmap][i].destructible == true) {
					int itcol = gp.iTile[gp.currentmap][i].worldX/gp.tileSize;
					int itrow = gp.iTile[gp.currentmap][i].worldY/gp.tileSize;
					path[itcol][itrow].solid = true;

				}
			}
			getcost(path[col][row]);

			col++;
			if(col == gp.maxScreenCol) {
				col = 0;
				row++;
			}
		}
	}
	public void trackpath() {

		FindPath current = goalpath;

		while(current != startpath) {

			pathList.add(0,current);
			current = current.parent;
		}
	}

}
