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
		
		
		//List(s) to be used for sort/search testcases below
		List<String> dataList = new LinkedList<String>();
		dataList.add("test1");
		
		
		//TDD - Test Cases for TextBuddy.searchWord()
		assertEquals("Test - 'TextBuddy.searchWord()'",false,testTextBuddySearchWord("",null));
		assertEquals("Test - 'TextBuddy.searchWord()'",false,testTextBuddySearchWord(null,null));
		assertEquals("Test - 'TextBuddy.searchWord()'",true,testTextBuddySearchWord("test1",dataList));
		assertEquals("Test - 'TextBuddy.searchWord()'",false,testTextBuddySearchWord("test0",dataList));
		
		
		//TDD - Test Cases for TextBuddy.sort()
		dataList.clear();
		assertEquals("Test - 'TextBuddy.sortContents()'","[]",testTextBuddySort(dataList));		
		
		dataList.add("test1");
		dataList.add("test3");
		dataList.add("test2");
		assertEquals("Test - 'TextBuddy.sortContents()'","[test1, test2, test3]",testTextBuddySort(dataList));
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
		tb.init("newTestFile.txt");
		
		return tb.sortContents(datas).toString();
	}	
	
}
