/**
 * 
 */
package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.DoubleType;
import jumpingalien.programs.types.Type;
import jumpingalien.model.*;

/**
 * @author Pieter
 *
 */
public class GetHeigth extends Expression {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public GetHeigth(Expression expression, SourceLocation sourceLocation) {
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
		return (double)((GameObject)this.getExpression().evaluate(program)).getHeight();
	}

}
