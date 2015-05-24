package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.types.BoolType;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public abstract class BoolExpression extends Expression<BoolType> {

	public BoolExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	
	@Override
	public BoolType getType(){
		return new BoolType();
	}

}
