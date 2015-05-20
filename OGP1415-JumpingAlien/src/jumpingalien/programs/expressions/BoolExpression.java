package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.types.BoolType;
import jumpingalien.programs.types.Type;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public abstract class BoolExpression extends Expression<BoolType> {

	public BoolExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	
	@Override
	public Type<?> getType(){
		return new BoolType();
	}

}
