package jumpingalien.programs.types;

import jumpingalien.model.GameObject;
import jumpingalien.programs.expressions.*;
import jumpingalien.programs.program.Program;

public class GameObjectType extends Type {
	public GameObjectType(){
		
	}
	
	public Expression getDefaultValue(){
		return new Null();
	}

	@Override
	public Expression createExpression(Expression value, Program program) {
		return new GameObjectExpression((GameObject) value.evaluate(program));
	
	}
}