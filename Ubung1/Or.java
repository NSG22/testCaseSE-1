package ex01;

import java.util.ListIterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Or implements BooleanExpression
{
	BooleanExpression operandLeft,operandRight;
	
	public Or(BooleanExpression _operandLeft,BooleanExpression _operandRight) {
		this.operandLeft=_operandLeft;
		this.operandRight=_operandRight;
	}
	
	public BooleanExpression getLeftOp()
	{
		return operandLeft;
	}
	public BooleanExpression getRightOp()
	{
		return operandRight;
	}
	
	public String toMyString() {
		StringBuilder myString=new StringBuilder();
		myString.append(operandLeft.toMyString());
		myString.append(' ');
		myString.append(operandRight.toMyString());
		myString.append(" |");
		return new String(myString) ;

	}
	
	@Override
	public String toString() {
		StringBuilder myString=new StringBuilder();	
		myString.append('(');
		myString.append(operandLeft.toString());
		myString.append("|");
		myString.append(operandRight.toString());
		myString.append(')');
		return new String(myString) ;
	}
	
	public boolean evaluate(Map<String, Boolean>map) {
		boolean a=operandLeft.evaluate(map);
		boolean b=operandRight.evaluate(map);
		return (a||b);
	}
	
	@Override
	public List<BooleanExpression>disjunctiveTerms(){
		List<BooleanExpression> myList=new LinkedList<BooleanExpression>();
		myList.addAll(this.operandLeft.disjunctiveTerms());
		myList.addAll(this.operandRight.disjunctiveTerms());
		return myList;
	}

	public BooleanExpression toDnf() {
		return new Or(this.operandLeft.toDnf(),this.operandRight.toDnf());
	}

}
