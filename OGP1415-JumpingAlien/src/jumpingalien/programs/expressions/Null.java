package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class Null extends Expression<GameObjectType> {

	public Null(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	
	public GameObjectType getType(){
		return new GameObjectType();
	}

	@Override
	public GameObjectType evaluateLegalCase(Program program) {
		return new GameObjectType();
	}
}
