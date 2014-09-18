import java.io.IOException;
import java.util.List;
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
	private static final String MSG_CONTENT_ADDED = "added to \"%s\" - \"%s\"";
	private static final String MSG_EMPTY_CONTENT = "Invalid input after specified command";
	private static final String MSG_CONTENT_DELETED = "deleted from \"%s\": \"%s\"";
	private static final String MSG_CONTENT_CLEARED = "Content cleared";
	private static final String MSG_EMPTY_FILE = "File \"%s\" is empty";
	private static final String MSG_INDEX_OUTOFSCOPE = "Index specified out of scope";
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
			
			if (dataStore.createNewFile(filename)){
				
				printMsg(true,MSG_TEXTBUDDY_INITIATED,filename);
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
	 * @throws IOException 
	 * 
	 */
	boolean exit() throws IOException{
		
		dataStore.exit();
		return true;
	}
	
	
	
	/**
	 * TextBuddy()'s "run" is to start the whole programme running.
	 * @throws IOException - depending on the function call
	 * 
	 */
	public int run(Scanner userInput) throws IOException{
		
		String userCommand = null;
		
		do {
			System.out.print("Command:");
			
			userCommand = userInput.next();
			
			switch (userCommand){
				
				case CMD_ADD:
					addContent(userInput.nextLine());
					break;
	
				case CMD_DISPLAY:
					displayContents();
					break;
					
				case CMD_DELETE:
					deleteContent(userInput.nextInt());
					break;
	
				case CMD_CLEAR:
					clearContents();
					break;
					
				case CMD_CHECK:
					break;
					
				case CMD_SORT:
					break;		
					
				default:					
					
					if(!userCommand.equals(CMD_EXIT)){
						
						printMsg(true,MSG_NO_CMD,userCommand);	
					}
					break;
			}
			
		} while (!userCommand.equals(CMD_EXIT));
		
		//Closes any object stream before exiting to prevent leaks
		exit();
		return CMD_EXIT_KEY;
		
	}
	
	
	
	/**
	 * 'add' command called by user. Data 'add' is written into the buffer.
	 * 
	 * @throws IOException - if unable to write to fileWriter
	 */
	public boolean addContent(String data){	
		
		if (data != null){
			boolean isAdded = dataStore.addContent(data);
			
			printMsg(true,MSG_CONTENT_ADDED,dataStore.getFileName(),data.trim());
			return isAdded;
		}
		
		printMsg(true,MSG_EMPTY_CONTENT);
		return false;
	}
	
	
	
	
	/**
	 * Display Contents that have been 'add'ed by the user from 1,2... .
	 * 
	 * @throws IOException - if file can't be written to for saving purpose.
	 * @throws IOException - if contents can't be extracted from file. 
	 */
	public boolean displayContents() throws IOException{
		
		dataStore.writeToFile();
		
		List<String> datas = dataStore.getFileContents();
		
		//Display current data(s) in file.
		if(datas.isEmpty()){
			
			printMsg(true,MSG_EMPTY_FILE,dataStore.getFileName());
			
			return false;
			
		}else{
			
			for (String data:datas){
				
				System.out.println(datas.indexOf(data)+1 + ": " + data);
			}
			
			return true;
		}
	}

	
	
	
	/**
	 * Deletes the user's selected content.
	 * 
	 * @throws IOException - if file can't be written to for saving purpose.
	 */
	public void deleteContent(int id) throws IOException{
		
		// To ensure that user 'add'(s) are written to file first.
		dataStore.writeToFile(); 
		
		List<String> datas = dataStore.getFileContents();
		
		//Checks if input index out of scope
		if ((id - 1) >= datas.size() || (id - 1) < 0){
			
			printMsg(true,MSG_INDEX_OUTOFSCOPE);
		}
		else if (!datas.isEmpty()){
			
			//Removing user selected data via specified id.
			String deletedContent = datas.get(id-1);
			datas.remove(id-1);
		
			//Write updated data (with the deleted item gone).
			dataStore.modifyAllContents(datas);
			printMsg(true,MSG_CONTENT_DELETED,dataStore.getFileName(),deletedContent);
			
		}
		else {
			printMsg(true,MSG_EMPTY_FILE,dataStore.getFileName());
		}
	}

	
	
	
	/**
	 * Clears all contents in the current file.
	 * 
	 *
	 */
	public String clearContents(){
		
		dataStore.clearAllContents();
		return MSG_CONTENT_CLEARED;
	}
	
	
	/**
	 * Check if word queried is within the 'add'ed contents
     *
	 * @param checkString - the string that the user input to query about
	 * @param datas - the list of contents already 'add'ed to check upon 
	 * 				  for the queried string
	 * @throws IOException 
	 */	
	public boolean searchWord(String checkString){
		
		if (checkString == null || checkString.isEmpty()){
			
			return false;
		}

		return true;
	}
	
	
	
	
	/**
	 * Returns the intended Message, upon entering wrong command or any other purposes
	 * Also may used for debugging purposes.
	 * 
	 * @param isPrintRequired - if user specifies 'true' a system.out will be called
	 * @param format - takes in a format of how the string should appear.
	 * @param args - any number of arguments that would be used by the format
	 */	
	private String printMsg(boolean isPrintRequired,String format, Object... args )
	{
		String msg = String.format(format, args);
		
		//Calls system.out if printing of message to user is required.
		if (isPrintRequired){

			System.out.println(msg);	
		}
		
		return msg;
	}
}
