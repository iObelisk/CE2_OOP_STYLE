import java.util.Scanner;


public class main {

	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		TextBuddy textbuddy = new TextBuddy();
		
		while(textbuddy.run(sc) != -1);
		textbuddy.exit();
		sc.close();
		
		
	}

}
