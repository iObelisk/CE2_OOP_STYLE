import java.io.IOException;
import java.util.Scanner;


public class Main {

	/*Declarations of Constants for "Magic Numbers" */
	private static final int EXIT_PROGRAM_CMD = -1;
	
	
	
	public static void main(String[] args) throws IOException {
		
		runTextBuddy();
		
	}
	
	
	
	private static void runTextBuddy() throws IOException{
		
		Scanner sc = new Scanner(System.in);
		TextBuddy textbuddy = new TextBuddy();
		
		boolean isInit = false;
		
		do{
	
			//Initializing TextBuddy Object with a filename input from user			
			isInit = textbuddy.init(sc.next());
			
		}while(isInit == false);
		
		//TextBuddy will continue asking for user input until 'exit' is called
		while(textbuddy.run(sc) != EXIT_PROGRAM_CMD);

		//Closes the scanner to prevent leaks
		sc.close();
		
		System.out.println("Bye Bye!");
		
	}

}
