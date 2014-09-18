import java.io.IOException;
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
	private static final String MSG_CONTENT_ADDED = "Content added";
	private static final String MSG_CONTENT_DELETE = "Content deleted";
	private static final String MSG_CONTENT_CLEARED = "Content cleared";
	private static final String MSG_CONTENT_DISPLAY = "Content displayed";
	private static final String MSG_PROMPT_CMD = "Command: ";
	private static final String MSG_TEXTBUDDY_INITIATED = "Welcome To TextBuddy! Your file \"%s\" is ready for use.";
	
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
	 * TextBuddy()'s initiator, where it request for user to input the 
	 * file's name, will prompt user if the given name is invalid.
	 * 
	 * @param filename - name of the file to be created i.e testfile.txt
	 * 
	 */
	public boolean init(String filename){
				
		try {
			
			if(dataStore.createNewFile(filename)){
				
				returnMsg(true,MSG_TEXTBUDDY_INITIATED,filename);
				return true;
			}
		
		} catch (IOException e) {
			
			e.printStackTrace();
			return false;
		}
		
		return false;
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
	 * TextBuddy()'s "run" is to start the whole programme running.
	 * 
	 */
	public int run(Scanner userInput){
		
		String userCommand = "";
		
		do{
			returnMsg(true,MSG_PROMPT_CMD);
			
			userCommand = userInput.next();
			
			switch(userCommand)
			{
				case CMD_ADD:
					addContent(userInput.nextLine());
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
						returnMsg(true,MSG_NO_CMD,userCommand);	
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
	public static String addContent(String data){	
		
		return MSG_CONTENT_ADDED;
	}
	
	
	
	
	/**
	 * Display Contents that have been 'add'ed by the user from 1,2... .
	 * 
	 * @throws IOException - if file can't be written to for saving purpose.
	 */
	public static String displayContents(){
		
		return MSG_CONTENT_DISPLAY;
	}

	
	
	
	/**
	 * Deletes the user's selected content.
	 * 
	 * @throws IOException - if file can't be written to for saving purpose.
	 */
	public static String deleteContent(int id){
		
		return MSG_CONTENT_DELETE;
	}

	
	
	
	/**
	 * Clears all contents in the current file.
	 * 
	 *
	 */
	public static String clearContents(){
		
		return MSG_CONTENT_CLEARED;
	}
	
	
	
	/**
	 * Prints the intended Message to user upon entering wrong command.
	 * Also may used for debugging purposes.
	 * 
	 * @param format - takes in a format of how the string should appear.
	 * @param args - any number of arguments that would be used by the format
	 */	
	private String returnMsg(boolean isPrintRequired,String format, Object... args )
	{
		String msg = String.format(format, args);
		
		//If direct system.out of message to user is required
		if(isPrintRequired){

			System.out.println(msg);	
		}
		
		return msg;
	}
}
