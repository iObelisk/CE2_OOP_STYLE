import java.io.IOException;
import java.util.Scanner;


public class Main {

	private static final int EXIT_PROGRAM_CMD = -1;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		TextBuddy textbuddy = new TextBuddy();
		
		boolean isInit = false;
		

		do{
			
			//Initializing textbuddy with a filename input from user			
			isInit = textbuddy.init(sc.next());
			
		}while(isInit == false);
		
		//TextBuddy will continue asking for user input until 'exit' is called
		while(textbuddy.run(sc) != EXIT_PROGRAM_CMD);
		
		textbuddy.exit();
		sc.close();
		
		System.out.println("Bye Bye!");
		
		
		
	}

}
