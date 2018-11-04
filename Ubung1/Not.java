package ex01;

import java.util.Map;

public class Not implements BooleanExpression
{
	BooleanExpression operand;
	public Not(BooleanExpression _operand) {
		this.operand=_operand;		
	}
	
	public BooleanExpression getOp() {
		return operand;
	}
	
	
	public String toMyString() {
		StringBuilder myString=new StringBuilder();
		myString.append(operand.toMyString());
		myString.append(" !" );
		return new String(myString);	
	}
	
	@Override
	public String toString() {
		StringBuilder myString=new StringBuilder();
		myString.append('(');
		myString.append("!" );
		myString.append(operand.toString());
		myString.append(')');
		return new String(myString);	
	}
	
	public boolean evaluate(Map<String, Boolean>map) {
		return !operand.evaluate(map);
	}
	
public BooleanExpression toDnf() {
		if(operand instanceof And){
			return new Or(new Not(((And)operand).operandLeft),new Not(((And)operand).operandRight)).toDnf();
		}
		else if(operand instanceof Or){
			return new And(new Not(((Or)operand).operandLeft),new Not(((Or)operand).operandRight)).toDnf();
		}
		else if(operand instanceof Not){
			return ((Not)operand).operand.toDnf();
		}
		return this;
	}
}
