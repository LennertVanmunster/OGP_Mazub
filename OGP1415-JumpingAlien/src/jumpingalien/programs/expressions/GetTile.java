package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.Type;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class GetTile extends Expression {


	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public GetTile(Expression x, Expression y, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.setX(x);
		this.setY(y);
	}

	/**
	 * @return the x
	 */
	private Expression getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	private void setX(Expression x) {
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
	private void setY(Expression y) {
		this.y = y;
	}

	private Expression x;
	
	private Expression y;
	

	@Override
	public Type getType() {
		return null;
	}

	@Override
	public Object evaluate(Program program) {
		return program.getWorld().getTilePositionAtPixelLocation((int) Math.floor((double) (this.getX().evaluate(program))),
				(int)Math.floor((double) (this.getY().evaluate(program))));
	}

}
