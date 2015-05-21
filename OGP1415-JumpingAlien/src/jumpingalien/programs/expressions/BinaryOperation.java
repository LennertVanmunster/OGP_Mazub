package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public abstract class BinaryOperation<T0, T1> extends Expression<T0>{


	public BinaryOperation(Expression<T1> left, Expression<T1> right, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.setExpressionLeft(left);
		this.setExpressionRight(right);
	}
	
	/**
	 * @return the expressionLeft
	 */
	public Expression<T1> getExpressionLeft() {
		return expressionLeft;
	}

	/**
	 * @param expressionLeft the expressionLeft to set
	 */
	private void setExpressionLeft(Expression<T1> expressionLeft) {
		if(!checkType(expressionLeft)){
			setStopProgram(true);
		}
		this.expressionLeft = expressionLeft;
	}

	/**
	 * @return the expressionRight
	 */
	public Expression<T1> getExpressionRight() {
		return expressionRight;
	}

	/**
	 * @param expressionRight the expressionRight to set
	 */
	private void setExpressionRight(Expression<T1> expressionRight) {
		if(!checkType(expressionRight)){
			setStopProgram(true);
		}
		this.expressionRight = expressionRight;
	}
	
	public abstract boolean checkType(Expression<T1> expression);

	private Expression<T1> expressionLeft;
	
	private Expression<T1> expressionRight;
	

}
