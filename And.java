package ex01;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class And implements BooleanExpression
{
	BooleanExpression operandLeft,operandRight;
	
	public And(BooleanExpression _operandLeft,BooleanExpression _operandRight) {
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
		myString.append(" &");
		return new String(myString) ;

	}
	
	@Override
	public String toString() {
		StringBuilder myString=new StringBuilder();
		myString.append('(');
		myString.append(operandLeft.toString());
		myString.append("&");
		myString.append(operandRight.toString());
		myString.append(')');
		return new String(myString) ;
	}

	public boolean evaluate(Map<String, Boolean>map) {
		boolean a=operandLeft.evaluate(map);
		boolean b=operandRight.evaluate(map);
		return (a&&b);
	}
	
	@Override
	public BooleanExpression toDnf() {
		BooleanExpression left=this.operandLeft.toDnf();
		BooleanExpression right=this.operandRight.toDnf();
		if(!(left instanceof Or) && !(right instanceof Or)) {
			return new And(left.toDnf(),right.toDnf());
		}	
		List<BooleanExpression>list1=left.disjunctiveTerms();
		List<BooleanExpression>list2=right.disjunctiveTerms();
		List<BooleanExpression>listResult=new LinkedList<BooleanExpression>();
		ListIterator<BooleanExpression> ite1=list1.listIterator();
		while(ite1.hasNext()) {
			ListIterator<BooleanExpression> ite2=list2.listIterator();
			BooleanExpression temp= ite1.next();
			while(ite2.hasNext()) 
				listResult.add(new And(temp, ite2.next()));
		}		
		ListIterator<BooleanExpression> iteResult=listResult.listIterator();
		BooleanExpression bigResult=iteResult.next();
		while(iteResult.hasNext()) {
			bigResult=new Or(bigResult,iteResult.next());
		}
	
		return bigResult;
	}
}
