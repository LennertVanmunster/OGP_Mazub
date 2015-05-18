package jumpingalien.programs.types;

import jumpingalien.programs.expressions.*;
import jumpingalien.programs.program.Program;

public abstract class Type {
	public  Type(){
		
	}
	
	public abstract Expression getDefaultValue();
	
	public abstract Expression createExpression(Expression value, Program program);
}
