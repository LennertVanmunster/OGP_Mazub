package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.types.*;

public abstract class Comparison<T extends Type<?>> extends BinaryOperation<BoolType, T>{

	public Comparison(Expression<T> left, Expression<T> right, SourceLocation sourceLocation){
		super(left, right, sourceLocation);
	}
	
	@Override
	public boolean checkType(Expression<T> expression) {
		return expression.getType() instanceof BoolType;
	}

	@Override
	public BoolType getType() {
		return new BoolType();
	}

}
