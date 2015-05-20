package jumpingalien.programs.types;

import jumpingalien.programs.expressions.*;
import jumpingalien.programs.program.Program;

public abstract class Type<T> {
	public Type(T value){
		setValue(value);
	}
	
	public Type(){
		this(null);
	}
	
	public abstract void setValue(T value);
	
	public T getValue(){
		return this.value;
	}
	
	public abstract T getDefaultValue();
	
	protected T value;
	
//	public abstract Expression createExpression(Expression value, Program program);
}
