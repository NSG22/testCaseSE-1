package ex01;

import java.util.Map;

public class Var implements BooleanExpression
{
	final String name;
	Var(String _name){
		this.name=_name;
	};
	
	public String toMyString() {
		return name;
	}
	
	public String toString() {
		return name;
	}
	public String getName(){
		return name;
	}
	public boolean evaluate(Map<String, Boolean>map) {
			return map.get(name);
	}
	
public BooleanExpression toDnf() {
	return this;
		
	}
}
