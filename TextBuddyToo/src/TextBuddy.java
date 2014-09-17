import java.util.Scanner;


public class TextBuddy {
	
	/*Declarations of Constants for input Commands */
	private static final String CMD_ADD = "add";
	private static final String CMD_DISPLAY = "display";
	private static final String CMD_DELETE = "delete";
	private static final String CMD_CLEAR = "clear";
	private static final String CMD_EXIT = "exit";
	private static final String CMD_CHECK = "check";
	private static final String CMD_SORT = "sort";
	private static final int CMD_EXIT_KEY = -1;
	

	/*Declarations of Constants for "Magic Strings" */
	private static final String MSG_NO_CMD = "No such command \"%s\" " ;
	
	
	/*Objects*/
	FileInputOutput dataStore;
	
	
	
	/**
	 * TextBuddy()'s Constructor
	 * 
	 */
	TextBuddy(){
		
		dataStore = new FileInputOutput();
	}

	
	
	/**
	 * TextBuddy()'s initiator
	 * 
	 */
	void TextBuddyInit(String filename){
		
		//dataStore.createNewFile(filename);
	}
	
	
	
	/**
	 * TextBuddy()'s "destructor" ensures that open objects are closed if 
	 * required to close or delete to prevent leak.
	 * 
	 */
	public boolean exit(){
		
		
		return true;
	}
	
	
	
	/**
	 * TextBuddy()'s "destructor" ensures that open objects are closed if 
	 * required to close or delete to prevent leak.
	 * 
	 */
	private boolean firstRun(String filename){

		System.out.print("Please input your filename: ");
		
		return false;
	}
	
	
	
	/**
	 * TextBuddy()'s "run" is to start the whole programme running.
	 * 
	 */
	public int run(Scanner userInput){
		
		String userCommand = "";
		
		do{
			userCommand = userInput.next();
			
			switch(userCommand)
			{
				case CMD_ADD:
					//addContent(userInput.nextLine());
					break;
	
				case CMD_DISPLAY:
					//displayContents();
					break;
					
				case CMD_DELETE:
					//deleteContent(userInput.nextInt());
					break;
	
				case CMD_CLEAR:
					//clearContents(true);
					break;
				
				default:					
					if(!userCommand.equals(CMD_EXIT))
					{
						printMsg(MSG_NO_CMD,userCommand);	
					}
					break;
			}
			
		}while(!userCommand.equals(CMD_EXIT));
		
		return CMD_EXIT_KEY;
		
	}
	
	
	
	/**
	 * 'add' command called by user. Data 'add' is written into the buffer.
	 * 
	 * @throws IOException - if unable to write to fileWriter
	 */
	private static void addContent(String data){	
	
	}
	
	
	
	
	/**
	 * Display Contents that have been 'add'ed by the user from 1,2... .
	 * 
	 * @throws IOException - if file can't be written to for saving purpose.
	 */
	public static void displayContents(){
		
		
	}

	
	
	
	/**
	 * Deletes the user's selected content.
	 * 
	 * @throws IOException - if file can't be written to for saving purpose.
	 */
	public static void deleteContent(int id){
		
	}

	
	
	
	/**
	 * Clears all contents in the current file.
	 * 
	 *
	 */
	public static void clearContents(){
		
	}
	
	
	
	/**
	 * Prints the intended Message to user upon entering wrong command.
	 * Also may used for debugging purposes.
	 * 
	 * @param format - takes in a format of how the string should appear.
	 * @param args - any number of arguments that would be used by the format
	 */	
	private static void printMsg(String format, Object... args )
	{
		String msg = String.format(format, args);
		System.out.println(msg);
	}
}
