/**
 * 
 */
package jumpingalien.programs.expressions;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.DoubleType;
import jumpingalien.programs.types.Type;

/**
 * @author Pieter
 *
 */
public class GetWidth extends Expression {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public GetWidth(Expression expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}


	/* (non-Javadoc)
	 * @see jumpingalien.programs.expressions.Expression#getType()
	 */
	@Override
	public Type getType() {
		return new DoubleType();
	}


	@Override
	public Object evaluate(Program program) {
		return (double)((GameObject)this.getExpression().evaluate(program)).getWidth();
	}

}
