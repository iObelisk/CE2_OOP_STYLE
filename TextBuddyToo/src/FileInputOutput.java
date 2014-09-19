import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;

import java.util.List;

public class FileInputOutput {
	
	/*Declarations of Constants for "Magic Strings" */
	private static final String MSG_INVALID_FILENAME = "Not a valid filename, please input a valid filename";
	private static final String MSG_EXIT_PROGRAME_FILEIO = "FileInputOutput - 'exit()' called! buffer closed";
	private static final String MSG_EMPTY_FILE = "%s is empty";	
	private static final String MSG_FILE_DELETED = "Deleted from \"%s\" - \"%s\" ";
	
	/*Declarations of Entities */
	private static File file;
	private static BufferedWriter fileWriter;

	/*Declarations of Variables */	
	private static String filename;

	
	/**
	 * Constructor of the 'FileInputOutput'
	 */
	FileInputOutput(){
		
		filename = "";
		file = null;
	}
	
	
	
	/**
	 * "Destructor" of the 'FileInputOutput'- When exiting the program, 
	 * closes the BufferedWriter before exiting to prevent leaks.
	 * 
	 * @throws IOException - if unable to close Scanner/BufferedWriter
	 */
	public String exit() throws IOException{
		
		fileWriter.close();		
		return MSG_EXIT_PROGRAME_FILEIO;
	}
	
	
	
	/**
	 * Request for user's desired filename for the file to be written to.
	 */
	private void requestFileName(String tempName){
		
		filename = tempName;
	}
	
	
	
	/**
	 * Get the filename from when initialized return to the call
	 * 
	 */
	public String getFileName(){
		
		return filename;
	}
	
	
	/**
	 * Checks for valid filename that user has input.
	 * 
	 * @return true = valid | false = invalid filename.
	 * @throws if invalid filename, output a msg to alert user.
	 */
	private boolean checkValidFileName(String tempName){
		
		requestFileName(tempName);
		file = new File(filename);
		
		try {
			
			file.getCanonicalPath();
			return true;
		} 
		catch (IOException e) {
			
			returnMsg(true,MSG_INVALID_FILENAME);
			return false;
		}
	}
	
	
	
	/**
	 * Creates a new BuffereWriter to write the future data inputs.
	 *
	 * @throws IOException - if file can't be created (perhaps its opened etc.)
	 */
	public boolean createNewFile(String tempName) throws IOException{
		
		if (checkValidFileName(tempName)){
			
			fileWriter = new BufferedWriter(new FileWriter(file));
			return true;
		}
		
		return false;
	}
	
	
	
	/**
	 * Flush the content(s) in the BufferWriter into the actual file.
	 * 
	 * @throws IOException 
	 */
	public boolean writeToFile(){
		
		try {
			
			fileWriter.flush();
			return true;
			
		} catch (IOException e) {
			
			return false;
		}
		
	}

	
	
	/**
	 * Get data(s) from file and keep into the List<String>
	 * 
	 * @return the list of data(s)
	 * @throws IOException 
	 */
	public List<String> getFileContents() throws IOException{
		
		//Stores current data(s) in List to assist removal/retrival of object.
		List<String> datas = Files.readAllLines(Paths.get(filename), 
				Charset.forName("UTF-8"));
		
		return datas;
	}
	
	
	
	/**
	 * 'add' command called by user. Data 'add' is written into the buffer.
	 * 
	 * @throws IOException - if unable to write to fileWriter
	 */
	public boolean addContent(String data){	
		
		try {
					
			fileWriter.write(data.trim());
			fileWriter.newLine();
			return true;
			
		} catch (IOException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	
	

	/**
	 * Deletes the user's selected content.
	 * 
	 * @throws IOException - if file can't be written to for saving purpose.
	 */
	public String deleteContent(int id) throws IOException{
		
		String deletedContent = "";
		
		if (!isEmptyFile()){
			//Retrieving & Removing user selected data via specified id.
			List<String> datas = getFileContents();	
			
			deletedContent = datas.get(id-1);
			datas.remove(id-1);
		
			//Write updated data (with the deleted item gone).
			modifyAllContents(datas);
		}
		else {
			return returnMsg(false,MSG_EMPTY_FILE,filename);
		}
		
		return returnMsg(false,MSG_FILE_DELETED,filename,deletedContent);
	}
	
	
	
	
	/**
	 * Clear All Contents from file before re-writing the new data(s) into file.
	 * 
	 * @throws IOException 
	 */
	public void modifyAllContents(List<String> datas) throws IOException{
		
		//Clear data from file to prevent duplicates.
		clearAllContents();
		
		//Write new data into the buffer.
		for (String data:datas){
			
			fileWriter.write(data.trim());
			fileWriter.newLine();
		}
		
		writeToFile();
	}
	
	
	
	/**
	 * Clears all contents in the current file.
	 * 
	 * @throws IOException - if file can't be close/created.
	 */
	public boolean clearAllContents(){
		
		// To ensure that user 'add'(s) are written to file first.
		writeToFile(); 
		
		try {

			fileWriter.close();
			createNewFile(filename);
			return true;
			
		} catch (IOException e) {
			
			e.printStackTrace();		
			return false;
		}
	}
	
	
	
	/**
	 * Checks if file is empty
	 */
	private boolean isEmptyFile() throws IOException{
		
		return getFileContents().isEmpty();
	}
	
	
	
	/**
	 * Returns the intended Message, upon entering wrong command or any other purposes
	 * Also may used for debugging purposes.
	 * 
	 * @param isPrintRequired - if user specifies 'true' a system.out will be called
	 * @param format - takes in a format of how the string should appear.
	 * @param args - any number of arguments that would be used by the format
	 */	
	private String returnMsg(boolean isPrintRequired,String format, Object... args ){
		
		String msg = String.format(format, args);
		
		//If direct system.out of message to user is required
		if(isPrintRequired){

			System.out.println(msg);	
		}
		
		return msg;
	}
}
