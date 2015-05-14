package jumpingalien.programs.expressions;

import jumpingalien.programs.types.*;

public class Null extends Expression{
	public Null(){
	}
	
	public Type getType(){
		return new GameObjectType();
	}
}
