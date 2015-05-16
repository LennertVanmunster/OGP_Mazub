package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;

public class Null extends Expression {

	public Null(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	
	public Null(){
		this(new SourceLocation(0,0));
	}
	
	public Type getType(){
		return new GameObjectType();
	}

	@Override
	public Object evaluate(Program program) {
		return null;
	}
}
