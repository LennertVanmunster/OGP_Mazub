package jumpingalien.programs.types;


public class BoolType extends Type<Boolean> {
	public BoolType(Boolean value){
		super(value);
	}
	
	
	public BoolType(){
		this(false);
	}
	
	@Override
	public Boolean getDefaultValue(){
		return false;
	}


	public void setValue(Boolean value) {
		this.value = value;
	}
	
}