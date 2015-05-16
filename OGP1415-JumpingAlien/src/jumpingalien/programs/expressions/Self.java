package jumpingalien.programs.expressions;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;

public class Self extends Expression {


	public Self(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	
	public GameObject evaluate(Program program){
		return program.getGameObject();
	}
	
	public Type getType(){
		return new GameObjectType();
	}
	
}
