/**
 * Main.java
 * 
 * Do note that the code have been refactored to OOP style thus there's
 * more then one .java src file.
 * 
 * Project CE2 - TextBuddy++
 *  
 * TextBuddy ++ allows users to store messages into a file they request with the 
 * provided filename by them, user can add, delete, clear and exit in the program.
 *  
 * This program is written with the below assumptions:
 * 1. Users only uses the commands provided.
 * 2. Although simple out of scope error handling has been coded, 
 * 	  it is still assumed that users uses this program as shown below, not any old, how.
 * 3. Existing Files will be overwritten.
 * 
 * Available Commands for users are: add, display, delete, clear, search, sort & exit
 * 
 * Below is the format in which user should follow,
 * or else users would be prompt with the wrong inputs.
 * 
 * 		TextBuddy.txt
		Welcome To TextBuddy! Your file "TextBuddy.txt" is ready for use.
		Command: add i love dao suan
		added to "TextBuddy.txt" - "i love dao suan"
		Command: add i love mee goreng
		added to "TextBuddy.txt" - "i love mee goreng"
		Command: add i love mee soto
		added to "TextBuddy.txt" - "i love mee soto"
		Command: clear
		All content(s) cleared from "TextBuddy.txt"
		Command: display
		File "TextBuddy.txt" is empty
		Command: delete 0
		Index specified out of scope
		Command: add i love dao suan
		added to "TextBuddy.txt" - "i love dao suan"
		Command: add i love mee goreng
		added to "TextBuddy.txt" - "i love mee goreng"
		Command: add i love mee soto
		added to "TextBuddy.txt" - "i love mee soto"
		Command: display
		1: i love dao suan
		2: i love mee goreng
		3: i love mee soto
		Command: search dao
 		Searched word - "dao" is found
 		Command: delete 7
		Index specified out of scope
		Command: delete 3
		deleted from "TextBuddy.txt": "i love mee soto"
		Command: display
		1: i love dao suan
		2: i love mee goreng
		Command: add i love mee soto
		Command: sort
		All content(s) from "TextBuddy.txt" sorted 
		Command: display
		1: i love dao suan
		2: i love mee goreng
		3: i love mee soto
		Command: add i love agar agar
		added to "TextBuddy.txt" - "i love agar agar"
		Command: display
		1: i love dao suan
		2: i love mee goreng
		3: i love mee soto
		4: i love agar agar
		Command: sort
		All content(s) from "TextBuddy.txt" sorted 
		Command: display
		1: i love agar agar
		2: i love dao suan
		3: i love mee goreng
		4: i love mee soto
		Command: exit
		Bye Bye!
 
 *
 * @author Bay Chuan Wei Candiie
 */


import java.io.IOException;

import java.util.Scanner;


public class Main {

	/*Declarations of Constants for "Magic Numbers" */
	private static final int EXIT_PROGRAM_CMD = -1;
	
	
	
	/**
	 * Main function of the entire project.
	 * 
	 * @throws IOException - refer to textbuddy.java
	 */
	public static void main(String[] args) throws IOException {
		
		runTextBuddy();
		
	}
	
	
	/**
	 * Logic call to run TextBuddy - includes initialization.
	 * 
	 * @throws IOException - refer to textbuddy.java
	 */
	private static void runTextBuddy() throws IOException{
		
		Scanner sc = new Scanner(System.in);
		TextBuddy textbuddy = new TextBuddy();
		
		boolean isInit = false;
		
		do {
	
			//Initializing TextBuddy Object with a filename input from user			
			isInit = textbuddy.init(sc.next());
			
		} while (isInit == false);
		
		//TextBuddy will continue asking for user input until 'exit' is called
		while (textbuddy.run(sc) != EXIT_PROGRAM_CMD);

		//Closes the scanner to prevent leaks
		sc.close();
		
		System.out.println("Bye Bye!");
		
	}

}
