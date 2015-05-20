package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;

public abstract class UnaryOperation<T0,T1> extends Expression<T0> {

	public UnaryOperation(Expression<T1> expression, SourceLocation sourceLocation){
		super(sourceLocation);
		this.setExpression(expression);
	}
	
	private Expression<T1> expression;

	/**
	 * @return the expression
	 */
	public Expression<T1> getExpression() {
		return expression;
	}

	/**
	 * @param expression the expression to set
	 */
	private void setExpression(Expression<T1> expression) {
		if(!checkType(expression)){
			setStopProgram(true);
		}
		this.expression = expression;
	}
	
	public abstract boolean checkType(Expression<T1> expression);
	
	public abstract Type<?> evaluate(Program program);
}
