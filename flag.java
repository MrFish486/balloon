public class flag {
	int x;
	int y;
	boolean active;
	public flag (int x, int y, boolean active) {
		this.x = x;
		this.y = y;
		this.active = active;
	}
	public boolean test (int x, int y) {
		return this.active ? x == this.x && y == this.y : false;
	}
}
