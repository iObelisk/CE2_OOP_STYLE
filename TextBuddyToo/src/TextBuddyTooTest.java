import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;



public class TextBuddyTooTest {


	@Test
	public void testExecuteCommand() throws IOException{
		
		//Test Cases - for textBuddy.init()
		assertEquals("Test - 'TextBuddy.init()'", false, testTextBuddyInit("<>?"));
		assertEquals("Test - 'TextBuddy.init()'", true, testTextBuddyInit("test"));
		
		
		//Test Cases for textBuddy.addContent()
		assertEquals("Test - 'TextBuddy.addContent()'",false,testTextBuddyAdd(null));		
		assertEquals("Test - 'TextBuddy.addContent()'",true,testTextBuddyAdd(""));
		assertEquals("Test - 'TextBuddy.addContent()'",true,testTextBuddyAdd("test"));

	}
	
	
	public boolean testTextBuddyInit(String filename){
		
		TextBuddy tb = new TextBuddy();
		
		return tb.init(filename);
	}
	
	public boolean testTextBuddyAdd(String userInput){
		
		TextBuddy tb = new TextBuddy();
		return tb.addContent(userInput);
	}
	
	
	
}
