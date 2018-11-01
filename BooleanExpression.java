package ex01;

import java.util.Stack;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public interface BooleanExpression 
{
	public static BooleanExpression parseExpression(String ausdruck) 
	{
		Stack<BooleanExpression> mystack=new Stack<BooleanExpression>();
		int n=ausdruck.length();
		for(int i=0;i<n;i++) {
			char a=ausdruck.charAt(i);
			if(a==' ')
				continue;
			else if(a=='&') {
				if(mystack.size()<=1)
					throw new IllegalArgumentException("Wrong input");
				BooleanExpression operand1=mystack.pop();
				BooleanExpression operand2=mystack.pop();
				mystack.push(new And(operand2,operand1));
			}
			else if(a=='|') {
				if(mystack.size()<=1)
					throw new IllegalArgumentException("Wrong input");
				BooleanExpression operand1=mystack.pop();
				BooleanExpression operand2=mystack.pop();
				mystack.push(new Or(operand2,operand1));
			}
			else if(a=='!') {
				if(mystack.isEmpty())
					throw new IllegalArgumentException("Wrong input");
				BooleanExpression operand=mystack.pop();
				mystack.push(new Not(operand));
			}
			else {
				mystack.push(new Var(Character.toString(a)));
			}
		}
		if(mystack.size()!=1)
			throw new IllegalArgumentException("Wrong input");
		return mystack.pop();
	}
	
	public String toMyString();
	
	default public String toPostfixString() {
		return toMyString();
	}
	
	public boolean evaluate(Map<String, Boolean>map);
	
	default public List<BooleanExpression>disjunctiveTerms(){
		List<BooleanExpression> myList=new LinkedList<BooleanExpression>();
		myList.add(parseExpression(toMyString()));
		return myList;
	}
	
	public BooleanExpression toDnf();
	
} 
