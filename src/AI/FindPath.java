package AI;

public class FindPath {

	FindPath parent;
	public int col;
	public int row;
	int gcost;
	int hcost;
	int fcost;
	boolean solid;
	boolean open;
	boolean checked;

	public FindPath(int col, int row) {
		this.col = col;
		this.row = row;

	}
}
