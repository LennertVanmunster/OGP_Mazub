package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public abstract class BinaryOperation extends Expression{


	public BinaryOperation(Expression left, Expression right, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.setExpressionLeft(left);
		this.setExpressionRight(right);
	}
	
	/**
	 * @return the expressionLeft
	 */
	public Expression getExpressionLeft() {
		return expressionLeft;
	}

	/**
	 * @param expressionLeft the expressionLeft to set
	 */
	public void setExpressionLeft(Expression expressionLeft) {
		this.expressionLeft = expressionLeft;
	}

	/**
	 * @return the expressionRight
	 */
	public Expression getExpressionRight() {
		return expressionRight;
	}

	/**
	 * @param expressionRight the expressionRight to set
	 */
	public void setExpressionRight(Expression expressionRight) {
		this.expressionRight = expressionRight;
	}

	private Expression expressionLeft;
	
	private Expression expressionRight;
	

}
