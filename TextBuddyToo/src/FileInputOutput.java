import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class FileInputOutput {
	
	
	private static final String MSG_INVALID_FILENAME = "Not a valid filename, please input a valid filename";
	
	private static String filename;
	
	private static File file;
	
	
	/**
	 * Constructor of the 'FileInputOutput'
	 */
	FileInputOutput(){
		
		filename = "";
	}
	
	
	
	/**
	 * Checks for valid filename that user has input.
	 * 
	 * @return true = valid | false = invalid filename.
	 * @throws if invalid filename, output a msg to alert user.
	 */
	private boolean checkValidFileName(String tempName)
	{
		file = new File(tempName);
		
		try 
		{
			file.getCanonicalPath();
			return true;
		} 
		catch (IOException e) 
		{
			printMsg(MSG_INVALID_FILENAME);
			return false;
		}
	}
	
	
	
	/**
	 * Creates a new BuffereWriter to write the future data inputs.
	 *
	 * @throws IOException - if file can't be created (perhaps its opened etc.)
	 */
	public boolean createNewFile(){
		
		return true;
	}
	
	
	
	/**
	 * Flush the content(s) in the BufferWriter into the actual file.
	 * 
	 * @throws IOException 
	 */
	public boolean writeToFile(){
		
		return true;
	}

	
	
	/**
	 * Get data(s) from file and keep into the List<String>
	 * 
	 * @return the list of data(s)
	 * @throws IOException 
	 */
	private static List<String> getFileContents() throws IOException{
		
		//Stores current data(s) in file into a List to assist removal/retrival of object.
		List<String> datas = Files.readAllLines(Paths.get(filename), 
				Charset.forName("UTF-8"));
		
		return datas;
	}
	
	
	
	/**
	 * Clear Contents from file before re-writing the data(s) into file.
	 * 
	 * @throws IOException 
	 */
	private static void modifyContents(List<String> datas) throws IOException{
	
	}
	
	
	
	/**
	 * Clears all contents in the current file.
	 * 
	 * @throws IOException - if file can't be close/created.
	 */
	public static void clearContents() throws IOException{
		
	}
	
	
	
	/**
	 * Checks if file is empty
	 */
	private static boolean isEmptyFile(List<String> datas) throws IOException{
		
		return true;
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
