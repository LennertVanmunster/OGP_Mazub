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
	
	public Null(){
		this(new SourceLocation(0,0));
	}
	
	public Type<?> getType(){
		return new GameObjectType();
	}

	@Override
	public GameObjectType evaluate(Program program) {
		if(this.getStopProgram() || program.hasStopped()){
			program.stop();
			return null;
		}
		return new GameObjectType();
	}
}
