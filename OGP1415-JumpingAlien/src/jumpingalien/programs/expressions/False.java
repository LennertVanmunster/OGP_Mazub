package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;

public class False extends Expression {

	public False(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	
	public False(){
//		this(new SourceLocation(0,0));
		this(null);
	}
	
	@Override
	public Object evaluate(Program program){
		return false;
	}
	
	public Type getType(){
		return new BoolType();
	}
	
}
