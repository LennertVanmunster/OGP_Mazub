package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.Type;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class ReadVariable<T extends Type<?>> extends Expression<T> {

	/**
	 * @param variableName
	 * @param variableType
	 * @param sourceLocation 
	 */
	public ReadVariable(String variableName, T variableType, SourceLocation sourceLocation) {
		super(sourceLocation);
		setVariableName(variableName);
		setVariableType(variableType);
	}

	public String getVariableName() {
		return this.variableName;
	}
	
	private void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	
	private String variableName;
	
	public T getVariableType() {
		return variableType;
	}
	
	private void setVariableType(T variableType) {
		this.variableType = variableType;
	}

	private T variableType;
	
	@Override
	public T evaluateLegalCase(Program program){
		@SuppressWarnings("unchecked")
		T variable =  (T) program.getGlobalVariables().get(getVariableName());
		if(!variable.getClass().equals(getVariableType().getClass())){
			program.stop();
			return null;
		}
		return variable;
	}

	@Override
	public T getType() {
		return getVariableType();
	}


}
