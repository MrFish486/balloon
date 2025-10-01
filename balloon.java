import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;

public class balloon {
	public static void main (String args[]) throws Exception {
		String help = "balloon run <file> - run a file.\nballoon -f  <file> - format a file\nballoon -v         - print version and exit";
		String version = "0.1";
		if (args.length == 0) {
			System.out.println(help);
			return;
		} else if (args[0].equals("-v") && args.length == 1) {
			System.out.println(version);
			return;
		} else if (args[0].equals("run") && args.length == 2) {
			balloon.cursor();
			balloon.clear();
			board main = new board(balloon.readFile(new File(args[1])));
			while (true) {
				main.render();
				main.tick();
				Thread.sleep(100);
			}
		} else if (args[0].equals("-f") && args.length == 2) {
			String formatted = board.format(balloon.readFile(new File(args[1])));
			balloon.writeFile(args[1], formatted);
			System.out.println(String.format("Formatted <%s>", args[1]));
			return;
		} else {
			System.out.println(help);
			return;
		}
	}
	
	private static String readFile (File input) throws Exception {
		String data = "";
		Scanner reader = new Scanner(input);
		while (reader.hasNextLine()) {
			data += reader.nextLine() + "\n";
		}
		return data;
	}
	private static void writeFile (String file, String content) throws Exception {
		PrintWriter handle = new PrintWriter(file, "UTF-8");
		handle.print(content);
		handle.close();
	}
	private static void clear () {
		System.out.print("\033[1;1H\033[2J");
	}
	private static void cursor () {
		System.out.print("\033[?25l");
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run () {
				System.out.print("\033[?25h");
			}
		}, "shutdownhook"));
	}
}
