package jumpingalien.programs.expressions;

import jumpingalien.programs.types.*;

public class True extends Expression {
	
	public True(){
		
	}
	
	public boolean evaluate(){
		return true;
	}
	
	public Type getType(){
		return new BoolType();
	}

}
