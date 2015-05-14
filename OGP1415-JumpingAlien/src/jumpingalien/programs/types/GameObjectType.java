package jumpingalien.programs.types;

import jumpingalien.programs.expressions.*;

public class GameObjectType extends Type {
	public GameObjectType(){
		
	}
	
	public Expression getDefaultValue(){
		return new Null();
	}
}
