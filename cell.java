public class cell {
	int     type;
	boolean powered;
	public cell (int type, boolean powered) {
		this.type    = type;
		this.powered = powered;
	}
	public String toString () {
		return String.format("TYPE:%d, POWERED:%b", this.type, this.powered);
	}
}
