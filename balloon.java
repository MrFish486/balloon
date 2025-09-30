import java.io.File;
import java.util.Scanner;

public class balloon {
	public static void main (String args[]) throws Exception {
		//board main = new board(new File("main.txt"));
		System.out.print("\033[?25l");
		board main = new board(balloon.readFile(new File(args[0])));
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run () {
				System.out.print("\033[?25h");
			}
		}, "shutdownhook"));
		while (true) {
			//System.out.println(main.toString());
			balloon.clear();
			main.render();
			main.tick();
			Thread.sleep(100);
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
	private static void clear () {
		System.out.print("\033[1;1H\033[2J");
	}
}
