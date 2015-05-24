package jumpingalien.programs.types;


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
}
