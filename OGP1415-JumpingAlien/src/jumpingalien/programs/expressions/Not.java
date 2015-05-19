package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class Not extends Expression {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public Not(Expression expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}
	
	public Type getType(){
		return new BoolType();
	}

	@Override
	public Boolean evaluate(Program program) {
		return !(boolean)this.getExpression().evaluate(program);
	}

}
