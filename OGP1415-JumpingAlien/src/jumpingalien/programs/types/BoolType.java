package jumpingalien.programs.types;

import jumpingalien.programs.expressions.*;

public class BoolType extends Type {
	public BoolType(){
	}
	
	public Expression getDefaultValue(){
		return new False();
	}
}
