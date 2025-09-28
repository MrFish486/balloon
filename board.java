public class board {
	int height;
	int width;
	int[][] board;
	public board (int width, int height) {
		this.board = new int[width][height];
		for (int x = 0; x < width; x ++) {
			for (int y = 0; y < height; y ++) {
				this.board[x][y] = 0;
			}
		}
		this.width  = width;
		this.height = height;
	}
	public board (String source) throws Exception {
		if (source.equals("")) {
			this.width  = 0;
			this.height = 0;
			this.board  = new int[0][0];
		} else {
			this.width  = source.split("\n")[0].length();
			this.height = source.split("\n").length;
			this.board = new int[width][height];
			for (int x = 0; x < this.width; x ++) {
				for (int y = 0; y < this.height; y ++) {
					char read = source.split("\n")[x].charAt(y);
					this.board[x][y] = this.translate(read);
				}
			}
		}
	}
	private static int translate (char in) {
		if (in == ' ') return 0;
		if (in == 'v' || in == '↓') return 1;
		if (in == '^' || in == '↑') return 2;
		if (in == '<' || in == '←') return 3;
		if (in == '>' || in == '→') return 4;
		if (in == 'T' || in == '┬') return 5;
		if (in == '!' || in == 'n') return 6;
		if (in == 'o' || in == 'O') return 7;
		if (in == 'x' || in == 'X') return 8;
		if (in == 'a' || in == 'A') return 9;
		if (in == 'D' || in == '⇣') return 10;
		if (in == 'U' || in == '⇡') return 11;
		if (in == 'L' || in == '⇠') return 12;
		if (in == 'R' || in == '⇢') return 13;
	}
}
