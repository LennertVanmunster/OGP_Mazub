/**
 * 
 */
package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;

/**
 * @author Pieter
 *
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
	public Object evaluate(Program program) {
		return this.getExpression().evaluate(program);
	}

}
