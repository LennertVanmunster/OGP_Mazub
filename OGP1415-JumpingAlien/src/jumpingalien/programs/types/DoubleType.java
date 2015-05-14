package jumpingalien.programs.types;

import jumpingalien.programs.expressions.*;

public class DoubleType extends Type {
	public DoubleType(){
		
	}
	
	public Expression getDefaultValue(){
		return new DoubleConstant(0.0);
	}
}