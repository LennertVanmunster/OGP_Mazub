package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public abstract class Expression {
	
	public Expression(Expression expression, SourceLocation sourceLocation){
		this.setSourceLocation(sourceLocation);
		this.setExpression(expression);
	}
	
	public Expression(SourceLocation sourceLocation){
		this(null,sourceLocation);
	}
	
	private Expression expression;
	
	/**
	 * @return the sourceLocation
	 */
	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	/**
	 * @param sourceLocation the sourceLocation to set
	 */
	private void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	/**
	 * @return the expression
	 */
	public Expression getExpression() {
		return expression;
	}

	/**
	 * @param expression the expression to set
	 */
	private void setExpression(Expression expression) {
		this.expression = expression;
	}

	private SourceLocation sourceLocation;
	
	public abstract Type getType();

	public abstract Object evaluate(Program program);
}
