package jumpingalien.programs.types;

import jumpingalien.programs.expressions.*;
import jumpingalien.programs.program.Program;

public class BoolType extends Type {
	public BoolType(){
	}
	
	@Override
	public Expression getDefaultValue(){
		return new False();
	}

	@Override
	public Expression createExpression(Expression value, Program program) {
		boolean newvalue = (boolean) value.evaluate(program);
		if(newvalue){
			return new True();
		}
		else
			return new False();
	}
}