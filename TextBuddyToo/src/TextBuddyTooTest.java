import org.junit.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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
		
		
		//TDD - Test Cases for TextBuddy.searchWord()
		assertEquals("Test - 'TextBuddy.searchWord()'",false,testTextBuddySearchWord("",null));
		assertEquals("Test - 'TextBuddy.searchWord()'",false,testTextBuddySearchWord(null,null));
		
		List<String> dataList = new LinkedList<String>();
		dataList.add("test1");
		
		assertEquals("Test - 'TextBuddy.searchWord()'",true,testTextBuddySearchWord("test1",dataList));
		assertEquals("Test - 'TextBuddy.searchWord()'",false,testTextBuddySearchWord("test2",dataList));
		
		
		//TDD - Test Cases for TextBuddy.sort()
		assertEquals("Test - 'TextBuddy.sortContents()'","[]",testTextBuddySort(dataList));
	}
	
	
	public boolean testTextBuddyInit(String filename){
		
		TextBuddy tb = new TextBuddy();
		
		return tb.init(filename);
	}
	
	public boolean testTextBuddyAdd(String userInput){
		
		TextBuddy tb = new TextBuddy();
		return tb.addContent(userInput);
	}
	
	public boolean testTextBuddySearchWord(String queryWord, List<String> datas) throws IOException{
		
		TextBuddy tb = new TextBuddy();
		
		return tb.searchWord(queryWord,datas);
	}
	
	public String testTextBuddySort(List<String> datas) throws IOException{
		
		TextBuddy tb = new TextBuddy();
		
		return tb.sortContents(datas).toString();
	}	
	
}
