package ex01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

class myTestCase {
	static enum Input {
		Case1("a b & c d & | "),
		Case2("a      !      b          & b              c | c      d | & &       "),
		Case3("a         b    | b         c | c          d | &      &        !");
		
		private String myString;
		Input(String x){
			this.myString=x;
		}
		public String getString() {
			return myString;
		}
	};
	static String[] outputPostfix = {"a b & c d & |" , "a ! b & b c | c d | & &" , "a b | b c | c d | & & !"};

	
	static boolean[] evaluate = {false,false,true};

	static String[] DNFpostFix = {"a b & c d & |" , "a ! b & b c & & a ! b & b d & & | a ! b & c c & & | a ! b & c d & & |" ,
			 "a ! b ! & b ! c ! & c ! d ! & | |" };
		
	static Map<String,Boolean> testMap;
	static List<BooleanExpression>testList;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		testMap=new HashMap<String,Boolean>();
		testMap.put("a", true);
		testMap.put("b", false);
		testMap.put("c",false);
		testMap.put("d", true);
		testList=new LinkedList<BooleanExpression>();
		for(Input myCase : Input.values())
			testList.add(BooleanExpression.parseExpression(myCase.getString()));
	}

	@Test
	void testoutPutpostfix() {
		ListIterator<BooleanExpression> ite1=testList.listIterator();
		int i=0;
		while(ite1.hasNext() && i<myTestCase.outputPostfix.length) {
			assertEquals(myTestCase.outputPostfix[i], ite1.next().toPostfixString(),
					"toPostFix Test "+(i+1)+" fail\n");
			i++;
		}
	}
	
	@Test
	void testEvaluate() {
		ListIterator<BooleanExpression> ite1=testList.listIterator();
		int i=0;
		while(ite1.hasNext() && i<myTestCase.evaluate.length) {
			assertEquals(myTestCase.evaluate[i], ite1.next().evaluate(testMap),
					"evaluate Test "+(i+1)+" fail\n");
			i++;
		}
	}
	
	@Test
	void testToDnfPostfix() {
		ListIterator<BooleanExpression> ite1=testList.listIterator();
		int i=0;
		while(ite1.hasNext() && i<myTestCase.DNFpostFix.length) {
			assertEquals(myTestCase.DNFpostFix[i], ite1.next().toDnf().toPostfixString(),
					"toDNF postfix Test "+(i+1)+" fail\n");
			i++;
		}
	}
}
