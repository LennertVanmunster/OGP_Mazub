package jumpingalien.programs.expressions;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class Self extends Expression {


	public Self(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	
	@Override
	public GameObject evaluate(Program program){
		return program.getGameObject();
	}
	
	@Override
	public Type getType(){
		return new GameObjectType();
	}
	
}
