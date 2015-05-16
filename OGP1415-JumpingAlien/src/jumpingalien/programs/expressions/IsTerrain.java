/**
 * 
 */
package jumpingalien.programs.expressions;

import jumpingalien.model.Mazub;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.Type;

/**
 * @author Pieter
 *
 */
public class IsTerrain extends Expression {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public IsTerrain(Expression expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}

	/* (non-Javadoc)
	 * @see jumpingalien.programs.expressions.Expression#getType()
	 */
	@Override
	public Type getType() {
		return new BoolType();
	}

	@Override
	public Object evaluate(Program program) {
		int [] tile = (int[]) this.getExpression().evaluate(program);
		return program.getWorld().canHaveAsTilePosition(tile[0], tile[1]);
	}

}
