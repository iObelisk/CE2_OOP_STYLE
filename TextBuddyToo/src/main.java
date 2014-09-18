import java.util.Scanner;


public class main {

	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		TextBuddy textbuddy = new TextBuddy();
		
		boolean isInit = false;
		
		do{
			
			isInit = textbuddy.init(sc.next());
			
		}while(isInit == false);
		
		System.out.println("I'm outta here");
		
		
		while(textbuddy.run(sc) != -1);
		textbuddy.exit();
		sc.close();
		
		
		
	}

}
