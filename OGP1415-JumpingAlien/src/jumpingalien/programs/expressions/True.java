package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;

public class True extends Expression {
	
	public True(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	
	public Boolean evaluate(Program program){
		return true;
	}
	public Type getType(){
		return new BoolType();
	}

}
