package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.BoolType;
import jumpingalien.programs.types.GameObjectType;
import jumpingalien.programs.types.ObjectType;


/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class IsDead extends CheckerExpression {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public IsDead(Expression<ObjectType<?>> expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}


	@Override
	public BoolType evaluate(Program program) {
		if(this.getStopProgram() || program.hasStopped()){
			program.stop();
			return null;
		}
		return new BoolType(((GameObjectType) this.getExpression().evaluate(program)).getValue().isTerminated());
	}


	@Override
	public boolean checkType(Expression<ObjectType<?>> expression) {
		return expression.getType() instanceof GameObjectType;
	}
}
