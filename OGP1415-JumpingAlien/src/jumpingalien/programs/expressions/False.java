package jumpingalien.programs.expressions;

import jumpingalien.programs.types.*;

public class False extends Expression {

	public False(){
		
	}
	
	public Type getType(){
		return new BoolType();
	}
}
