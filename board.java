public class board {
	int height;
	int width;
	cell[][] board;
	public board (int width, int height) {
		this.board = new cell[width][height];
		for (int x = 0; x < width; x ++) {
			for (int y = 0; y < height; y ++) {
				this.board[x][y] = new cell(0, false);
			}
		}
		this.width  = width;
		this.height = height;
	}
	public board (String source) throws Exception {
		if (source.equals("")) {
			this.width  = 0;
			this.height = 0;
			this.board  = new cell[0][0];
		} else {
			this.width  = source.split("\n")[0].length();
			this.height = source.split("\n").length;
			this.board = new cell[width][height];
			for (int x = 0; x < this.width; x ++) {
				for (int y = 0; y < this.height; y ++) {
					char read = source.split("\n")[x].charAt(y);
					this.board[x][y] = new cell(this.translate(read), false);
				}
			}
		}
	}
	public String toString () {
		String re = "";
		for (int x = 0; x < this.width; x ++) {
			for (int y = 0; y < this.height; y ++) {
				re += this.reverseTranslate(this.board[x][y].type);
			}
			if (x != this.width) re += '\n';
		}
		return re;
	}
	public void tick () {
		int[][] new_board = this.board.clone();
		for (int x = 0; x < this.width; x ++) {
			for (int y = 0; y < this.width; y ++) {
				if (this.board[x][y].powered) {
					int type = this.board[x][y].type;
					if (type == 1) {
						
					}
				}
			}
		}
	}
	public boolean set (int x, int y, int value) {
		if (x < 0 || x >= this.width || y < 0 || y >= this.height) return false;
		this.board[x][y] = value;
		return true;
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
		if (in == 'e' || in == '⬡') return 14;
		return 0;
	}
	private static char reverseTranslate (int in) {
		if (in == 0) return ' ';
		if (in == 1) return '↓';
		if (in == 2) return '↑';
		if (in == 3) return '←';
		if (in == 4) return '→';
		if (in == 5) return '┬';
		if (in == 6) return 'n';
		if (in == 7) return 'O';
		if (in == 8) return 'X';
		if (in == 9) return 'A';
		if (in == 10) return '⇣';
		if (in == 11) return '⇡';
		if (in == 12) return '⇠';
		if (in == 13) return '⇢';
		if (in == 14) return '⬡';
		return 0;
	}
}
