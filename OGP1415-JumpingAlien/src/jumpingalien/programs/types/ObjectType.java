package jumpingalien.programs.types;


public abstract class ObjectType<T> extends Type<T>{

	public ObjectType(T value) {
		super(value);
	}

	@Override
	public void setValue(T value) {
		this.value=value;
	}



}
