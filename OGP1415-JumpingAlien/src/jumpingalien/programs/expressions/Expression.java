package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.types.*;

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
	public void setSourceLocation(SourceLocation sourceLocation) {
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
	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	private SourceLocation sourceLocation;
	
	public abstract Type getType();

}