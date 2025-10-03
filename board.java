import java.util.ArrayList;

public class board {
	int height;
	int width;
	cell[][] board;
	flag activeflag = new flag(0, 0, false);
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
			this.height = source.split("\n").length;
			this.width  = source.split("\n")[0].length();
			for (int i = 0; i < height; i ++) { // Seek height
				if (source.split("\n")[i].length() > this.width) this.width = source.split("\n")[i].length();
			}
			//System.out.println(String.format("h=%d, w=%d", height, width));
			this.board = new cell[width][height];
			for (int y = 0; y < this.height; y ++) {
				for (int x = 0; x < this.width; x ++) {
					//System.out.println(String.format("x=%d, y=%d", x, y));
					char read = ' ';
					try {
						read = source.split("\n")[y].charAt(x);
					} catch (Exception e) {}
					this.board[x][y] = new cell(this.translate(read), false);
				}
			}
		}
	}
	public board (board source) throws Exception {
		this(source.serialize());
	}
	public String toString () {
		String re = "";
		for (int y = 0; y < this.height; y ++) {
			for (int x = 0; x < this.width; x ++) {
				if (this.board[x][y].powered) re += "\033[31m";
				//if (this.board[x][y].powered) System.out.println(String.format("POW, x=%d, y=%d", x, y));
				re += this.reverseTranslate(this.board[x][y].type);
				if (this.board[x][y].powered) re += "\033[0m";
			}
			if (y != this.width) re += '\n';
		}
		return re;
	}
	public String serialize () {
		String re = "";
		for (int y = 0; y < this.height; y ++) {
			for (int x = 0; x < this.width; x ++) {
				re += this.reverseTranslate(this.board[x][y].type);
			}
			if (y != this.height) re += '\n';
		}
		return re;
	}
	public void tick (int iterations) throws Exception {
		board new_board = new board(this);
		ArrayList <Thread> scheduledtasks = new ArrayList <Thread> ();
		for (int x = 0; x < this.width; x ++) {
			for (int y = 0; y < this.height; y ++) {
				int type = this.board[x][y].type;
				if (type == 14) {
					new_board.set(x - 1, y, true);
					new_board.set(x + 1, y, true);
					new_board.set(x, y - 1, true);
					new_board.set(x, y + 1, true);
					new_board.set(x, y, true);
				} else if (type == 6) {
					new_board.set(x, y + 1, !this.is(x, y - 1));
					new_board.set(x, y, !this.is(x, y - 1));
				} else if (type == 7) {
					new_board.set(x, y + 1, this.is(x - 1, y) || this.is(x + 1, y));
					new_board.set(x, y, this.is(x - 1, y) || this.is(x + 1, y));
				} else if (type == 8) {
					new_board.set(x, y + 1, this.is(x - 1, y) ^ this.is(x + 1, y));
					new_board.set(x, y, this.is(x - 1, y) ^ this.is(x + 1, y));
				} else if (type == 9) {
					boolean tpow = this.is(x - 1, y) && this.is(x + 1, y);
					new_board.set(x, y + 1, tpow);
					new_board.set(x, y, tpow);
					scheduledtasks.add(new Thread(new setter(this, x, y, false)));
				} else if (type == 15) {
					new_board.set(x - 1, y, true);
					new_board.set(x + 1, y, true);
					new_board.set(x, y - 1, true);
					new_board.set(x, y + 1, true);
					new_board.set(x, y, true);
					new_board.setType(x, y, 0);
				} else if (type == 16) {
					boolean tpow = !(this.is(x - 1, y) && this.is(x + 1, y));
					new_board.set(x, y + 1, tpow);
					new_board.set(x, y, tpow);
				} else if (type == 17) {
					if (this.board[x][y].powered) {
						scheduledtasks.add(new Thread(new setter(this, x, y, false)));
					//	new_board.set(x, y, false);
					}
				}
				if (this.board[x][y].powered) {
					if (type == 1) {
						new_board.set(x, y + 1, true);
					} else if (type == 2) {
						new_board.set(x, y - 1, true);
					} else if (type == 3) {
						new_board.set(x - 1, y, true);
					} else if (type == 4) {
						new_board.set(x + 1, y, true);
					} else if (type == 5) {
						new_board.set(x - 1, y, true);
						new_board.set(x + 1, y, true);
						new_board.set(x, y - 1, true);
						new_board.set(x, y + 1, true);
					} else if (type == 10) {
						new_board.set(x, y + 2, true);
					} else if (type == 11) {
						new_board.set(x, y - 2, true);
					} else if (type == 12) {
						new_board.set(x - 2, y, true);
					} else if (type == 13) {
						new_board.set(x + 2, y, true);
					} else if (type == 18) {
						scheduledtasks.add(new Thread(new Runnable() {
							public void run () {
								System.out.println(String.format("\nExited in %d ticks.", iterations));
								System.exit(0);
							}
						}, "enderthread"));
					} else if (type == 19) {
						scheduledtasks.add(new Thread(new Runnable() {
							public void run () {
								System.out.println(String.format("\nExited in %d ticks.", iterations));
								System.exit(1);
							}
						}, "enderthread"));
					}
/*				} else {
					if (type == 1) {
						new_board.set(x, y + 1, false);
					} else if (type == 2) {
						new_board.set(x, y - 1, false);
					} else if (type == 3) {
						new_board.set(x - 1, y, false);
					} else if (type == 4) {
						new_board.set(x + 1, y, false);
					} else if (type == 5) {
						new_board.set(x - 1, y, false);
						new_board.set(x + 1, y, false);
						new_board.set(x, y - 1, false);
						new_board.set(x, y + 1, false);
					} else if (type == 10) {
						new_board.set(x, y + 2, false);
					} else if (type == 11) {
						new_board.set(x, y - 2, false);
					} else if (type == 12) {
						new_board.set(x - 2, y, false);
					} else if (type == 13) {
						new_board.set(x + 2, y, false);
					}*/
				}
			}
		}
		for (int i = 0; i < scheduledtasks.size(); i ++) {
			scheduledtasks.get(i).start();
			scheduledtasks.get(i).join();
		}
		this.board = new_board.board;
	}
	public boolean set (int x, int y, boolean value) {
		if (x < 0 || x >= this.width || y < 0 || y >= this.height) return false;
		this.board[x][y].powered = value;
		return true;
	}
	public boolean setType (int x, int y, int value) {
		if (x < 0 || x >= this.width || y < 0 || y >= this.height) return false;
		this.board[x][y].type = value;
		return true;
	}
	public int get (int x, int y) {
		if (x < 0 || x >= this.width || y < 0 || y >= this.height) return -1;
		return this.board[x][y].type;
	}
	public boolean is (int x, int y) {
		if (x < 0 || x >= this.width || y < 0 || y >= this.height) return false;
		return this.board[x][y].powered;
	}
	private static int translate (char in) {
		if (in == ' ') return 0;
		if (in == 'v' || in == '↓') return 1;
		if (in == '^' || in == '↑') return 2;
		if (in == '<' || in == '←') return 3;
		if (in == '>' || in == '→') return 4;
		if (in == '+') return 5;
		if (in == '!' || in == 'n') return 6;
		if (in == 'o' || in == 'O') return 7;
		if (in == 'x' || in == 'X') return 8;
		if (in == 'a' || in == 'A') return 9;
		if (in == 'D' || in == '▼') return 10;
		if (in == 'U' || in == '▲') return 11;
		if (in == 'L' || in == '◄') return 12;
		if (in == 'R' || in == '►') return 13;
		if (in == 'e' || in == '♦') return 14;
		if (in == 'p' || in == '♣') return 15;
		if (in == '@') return 16;
		if (in == '=' || in == '●') return 17;
		if (in == '0') return 18;
		if (in == '1') return 19;
		if (in == '/') return 20;
		return 0;
	}
	private static char reverseTranslate (int in) {
		if (in == 0) return ' ';
		if (in == 1) return '↓';
		if (in == 2) return '↑';
		if (in == 3) return '←';
		if (in == 4) return '→';
		if (in == 5) return '+';
		if (in == 6) return 'n';
		if (in == 7) return 'O';
		if (in == 8) return 'X';
		if (in == 9) return 'A';
		if (in == 10) return '▼';
		if (in == 11) return '▲';
		if (in == 12) return '◄';
		if (in == 13) return '►';
		if (in == 14) return '♦';
		if (in == 15) return '♣';
		if (in == 16) return '@';
		if (in == 17) return '●';
		if (in == 18) return '0';
		if (in == 19) return '1';
		if (in == 20) return '/';
		return ' ';
	}
	private static void put (int x, int y, String s) {
		System.out.print(String.format("\033[%d;%df%s", y, x, s));
	}
	public void render () {
		for (int y = 0; y < this.height; y ++) {
			for (int x = 0; x < this.width; x ++) {
				String pow = "";
				if (this.board[x][y].powered) pow += "\033[31m";
				if (this.activeflag.test(x, y) && this.board[x][y].powered) pow += "\033[32m";
				if (this.activeflag.test(x, y) && !this.board[x][y].powered) pow += "\033[33m";
				pow += this.reverseTranslate(this.board[x][y].type);
				if (this.board[x][y].powered) pow += "\033[0m";
				this.put(x + 1, y + 1, pow);
			}
		}
	}
	private void flag (int x, int y) {
		this.activeflag = new flag(x, y, true);
	}
	public static String format (String src) throws Exception {
		return new board(src).toString();
	}
}
