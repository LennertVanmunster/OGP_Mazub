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

//	@Override
//	public Expression createExpression(Expression value, Program program) {
//		boolean newvalue = (boolean) value.evaluate(program);
//		if(newvalue){
//			return new True();
//		}
//		else
//			return new False();
//	}

	public void setValue(Boolean value) {
		this.value = value;
	}
	
}