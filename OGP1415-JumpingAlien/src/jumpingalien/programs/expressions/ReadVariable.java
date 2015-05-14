package jumpingalien.programs.expressions;

import jumpingalien.programs.types.Type;

public class ReadVariable extends Expression {

	/**
	 * @param variableName
	 * @param variableType
	 */
	public ReadVariable(String variableName, Type variableType) {
		this.variableName = variableName;
		this.variableType = variableType;
	}

	private String variableName;
	
	public String getVariableName() {
		return variableName;
	}
	
	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	
	public Type getVariableType() {
		return variableType;
	}
	
	public void setVariableType(Type variableType) {
		this.variableType = variableType;
	}

	private Type variableType;


}
