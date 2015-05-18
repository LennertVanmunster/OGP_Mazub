package jumpingalien.programs.types;

import jumpingalien.programs.expressions.*;
import jumpingalien.programs.program.Program;

public class DoubleType extends Type {
	public DoubleType(){
		
	}
	
	public Expression getDefaultValue(){
		return new DoubleConstant(0.0);
	}

	@Override
	public Expression createExpression(Expression value, Program program) {
		return new DoubleConstant((double) value.evaluate(program));
	}
}