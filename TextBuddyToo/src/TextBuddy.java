import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class TextBuddy {
	
	/*Declarations of Constants for input Commands */
	private static final String CMD_ADD = "add";
	private static final String CMD_DISPLAY = "display";
	private static final String CMD_DELETE = "delete";
	private static final String CMD_CLEAR = "clear";
	private static final String CMD_EXIT = "exit";
	private static final String CMD_SEARCH = "search";
	private static final String CMD_SORT = "sort";
	private static final int CMD_EXIT_KEY = -1;
	

	/*Declarations of Constants for "Magic Strings" */
	private static final String MSG_NO_CMD = "No such command \"%s\" " ;
	private static final String MSG_CONTENT_ADDED = "added to \"%s\" - \"%s\"";
	private static final String MSG_EMPTY_CONTENT = "Invalid input|data|list of contens - Empty content";
	private static final String MSG_CONTENT_DELETED = "deleted from \"%s\": \"%s\"";
	private static final String MSG_CONTENT_CLEARED = "All content(s) cleared from \"%s\"";
	private static final String MSG_CONTENT_SORTED = "All content(s) from \"%s\" sorted ";
	private static final String MSG_EMPTY_FILE = "File \"%s\" is empty";
	private static final String MSG_INDEX_OUTOFSCOPE = "Index specified out of scope";
	private static final String MSG_TEXTBUDDY_INITIATED = "Welcome To TextBuddy! Your file \"%s\" is ready for use.";
	private static final String MSG_WORD_FOUND = " Searched word - \"%s\" is found";
	private static final String MSG_WORD_NOT_FOUND = " Searched word - \"%s\" is NOT found";
	
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
				
				printMsg(MSG_TEXTBUDDY_INITIATED,filename);
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
					dataStore.writeToFile();
					displayContents();
					break;
					
				case CMD_DELETE:
					dataStore.writeToFile();
					deleteContent(userInput.nextInt());
					break;
	
				case CMD_CLEAR:
					clearContents();
					break;
					
				case CMD_SEARCH:
					dataStore.writeToFile();
					searchWord(userInput.next(),dataStore.getFileContents());
					break;
					
				case CMD_SORT:
					dataStore.writeToFile();
					sortContents(dataStore.getFileContents());
					break;		
					
				default:					
					
					if(!userCommand.equals(CMD_EXIT)){
						
						printMsg(MSG_NO_CMD,userCommand);	
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
			
			printMsg(MSG_CONTENT_ADDED,dataStore.getFileName(),data.trim());
			return isAdded;
		}
		
		printMsg(MSG_EMPTY_CONTENT);
		return false;
	}
	
	
	
	
	/**
	 * Display Contents that have been 'add'ed by the user from 1,2... .
	 * 
	 * @throws IOException - if file can't be written to for saving purpose.
	 * @throws IOException - if contents can't be extracted from file. 
	 */
	public boolean displayContents() throws IOException{
		
		List<String> datas = dataStore.getFileContents();
		
		//Display current data(s) in file.
		if(datas.isEmpty()){
			
			printMsg(MSG_EMPTY_FILE,dataStore.getFileName());
			
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
			
			printMsg(MSG_INDEX_OUTOFSCOPE);
		}
		else if (!datas.isEmpty()){
			
			//Removing user selected data via specified id.
			String deletedContent = datas.get(id-1);
			datas.remove(id-1);
		
			//Write updated data (with the deleted item gone).
			dataStore.modifyAllContents(datas);
			printMsg(MSG_CONTENT_DELETED,dataStore.getFileName(),deletedContent);
			
		}
		else {
			printMsg(MSG_EMPTY_FILE,dataStore.getFileName());
		}
	}

	
	
	
	/**
	 * Clears all contents in the current file.
	 * 
	 *
	 */
	public String clearContents(){
		
		dataStore.clearAllContents();
		printMsg(MSG_CONTENT_CLEARED,dataStore.getFileName());
		return MSG_CONTENT_CLEARED;
	}
	
	
	/**
	 * Check if word queried is within the 'add'ed contents
     *
	 * @param queryWord - the string that the user input to query about
	 * @param datas - the list of contents already 'add'ed to check upon 
	 * 				  for the queried string
	 * @throws IOException 
	 */	
	public boolean searchWord(String queryWord, List<String> datas) throws IOException{
		
		if (queryWord == null || queryWord.isEmpty() || datas == null || datas.isEmpty()){
			
			printMsg(MSG_EMPTY_CONTENT);
			return false;
		}
		
		for (String data:datas){
			
			if (data.contains(queryWord)){
				
				printMsg(MSG_WORD_FOUND, queryWord);
				return true;
			}
		}
		
		printMsg(MSG_WORD_NOT_FOUND, queryWord);
		return false;
	}
	
	
	
	/**
	 * Sorts the display list that user has 'add'ed previously - alphabetically
	 * and saves it to the storage file indicated at the start of the program
	 * 
	 * @param originalList - takes in the list to be sorted
	 * @throws IOException 
	 */	
	public List<String> sortContents(List<String> originalList) throws IOException{
	
		List<String> newList = originalList;
		
		if(originalList == null || originalList.isEmpty())
		{
			printMsg(MSG_EMPTY_CONTENT);
			
		}else{
		
			Collections.sort(newList);
			dataStore.modifyAllContents(newList);
			printMsg(MSG_CONTENT_SORTED, dataStore.getFileName());
		}
		
		return newList;
		
	}
	
	
	
	/**
	 * Returns the intended Message, upon entering wrong command or any other purposes
	 * Also may used for debugging purposes.
	 * 
	 * @param format - takes in a format of how the string should appear.
	 * @param args - any number of arguments that would be used by the format
	 */	
	private String printMsg(String format, Object... args ){
		
		String msg = String.format(format, args);
		System.out.println(msg);	
		
		return msg;
	}
}
