import java.io.File;
import java.util.Scanner;

public class balloon {
	public static void main (String args[]) throws Exception {
		//board foo = new board(new File("foo.txt"));
		board foo = new board(balloon.readFile(new File("foo.txt")));
		System.out.println(foo.toString());
		Thread.sleep(100);
	}
	public static String readFile (File input) throws Exception {
		String data = "";
		Scanner reader = new Scanner(input);
		while (reader.hasNextLine()) {
			data += reader.nextLine() + "\n";
		}
		return data;
	}
}
