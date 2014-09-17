import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;



public class TextBuddyTooTest {


	@Test
	public void testExecuteCommand() throws IOException{
		
		assertEquals("Test - 'TextBuddy.addContent()'","Content added",TextBuddy.addContent("haha"));
		assertEquals("Test - 'TextBuddy.clearContents()'","Content cleared",TextBuddy.clearContents());		
		assertEquals("Test - 'TextBuddy.displayContents()'","Content displayed",TextBuddy.displayContents());
		assertEquals("Test - 'TextBuddy.deleteContent()'","Content deleted",TextBuddy.deleteContent(1));		
	}
	
	
}
