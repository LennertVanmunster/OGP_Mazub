/**
 * 
 */
package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.types.Type;

/**
 * @author Pieter
 *
 */
public class GetTile extends Expression {


	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public GetTile(Expression x, Expression y, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public Expression getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(Expression x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public Expression getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(Expression y) {
		this.y = y;
	}

	private Expression x;
	
	private Expression y;
	
	/* (non-Javadoc)
	 * @see jumpingalien.programs.expressions.Expression#getType()
	 */
	@Override
	public Type getType() {
		return null;
	}

}
