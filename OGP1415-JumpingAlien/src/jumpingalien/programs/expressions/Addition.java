package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class Addition extends MathematicalExpression {

	public Addition(Expression left, Expression right,
			SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}
	
	@Override
	public Double evaluate(Program program) {
		return (double) this.getExpressionLeft().evaluate(program) + 
				(double) this.getExpressionRight().evaluate(program);
	}

}
