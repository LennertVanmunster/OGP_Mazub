package jumpingalien.programs.expressions;

import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.Type;

public class ReadVariable extends Expression {

	/**
	 * @param variableName
	 * @param variableType
	 */
	public ReadVariable(String variableName, Type variableType) {
		setVariableName(variableName);
		setVariableType(variableType);
	}

	
	public String getVariableName() {
		return this.variableName;
	}
	
	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	
	private String variableName;
	
	public Type getVariableType() {
		return variableType;
	}
	
	public void setVariableType(Type variableType) {
		this.variableType = variableType;
	}

	private Type variableType;
	
	public Expression evaluate(Program program){
		return program.getGlobalVariableValues().get(getVariableName());
	}

	@Override
	public Type getType() {
		return getVariableType();
	}


}
