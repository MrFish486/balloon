public class setter implements Runnable {
	private final board executor;
	private final int x;
	private final int y;
	private final boolean value;
	public setter (board executor, int x, int y, boolean value) {
		this.executor = executor;
		this.x = x;
		this.y = y;
		this.value = value;
	}
	public void run () {
		this.executor.set(this.x, this.y, this.value);
	}
}
