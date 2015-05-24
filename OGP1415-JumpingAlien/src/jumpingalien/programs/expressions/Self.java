package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class Self extends Expression<GameObjectType> {


	public Self(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	
	@Override
	public GameObjectType evaluateLegalCase(Program program){
		return new GameObjectType(program.getGameObject());
	}
	
	@Override
	public GameObjectType getType(){
		return new GameObjectType();
	}
	
}
