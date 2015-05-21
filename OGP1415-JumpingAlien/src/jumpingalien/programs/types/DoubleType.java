package jumpingalien.programs.types;


public class DoubleType extends Type<Double> {
	public DoubleType(Double value){
		super(value);
	}
	
	public DoubleType(int value){
		this((double) value);
	}
	
	public DoubleType(){
		this(0.0);
	}
	
	public Double getDefaultValue(){
		return 0.0;
	}


	public void setValue(Double value) {
		if(value==Math.floor(value) && !Double.isInfinite(value)){
			this.setInteger(true);
			double integer = (int) value.doubleValue();
			this.value=integer;
		}
		else{
			this.value=value;
		}
	}	

	public boolean isInteger() {
		return isInteger;
	}

	public void setInteger(boolean isInteger) {
		this.isInteger = isInteger;
	}
	
	private boolean isInteger=false;
}