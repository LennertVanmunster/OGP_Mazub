package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.DoubleType;
import jumpingalien.programs.types.GameObjectType;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class GetWidth extends GetDoubleValueOf {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public GetWidth(Expression<GameObjectType> expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}

	@Override
	public DoubleType evaluate(Program program) {
		if(this.getStopProgram() || program.hasStopped()){
			program.stop();
			return null;
		}
		return new DoubleType(((GameObjectType) this.getExpression().evaluate(program)).getValue().getWidth());
	}

}
