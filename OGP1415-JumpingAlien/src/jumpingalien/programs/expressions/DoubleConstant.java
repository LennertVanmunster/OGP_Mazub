package jumpingalien.programs.expressions;

public class DoubleConstant extends Expression {


	/**
	 * @param value
	 */
	public DoubleConstant(double value) {
		this.value = value;
	}
	
	
	public double getValue() {
		return value;
	}


	public void setValue(double value) {
		this.value = value;
	}


	private double value;
}
