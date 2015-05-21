package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.Type;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class ReadVariable extends Expression<Type<?>> {

	/**
	 * @param variableName
	 * @param variableType
	 * @param sourceLocation 
	 */
	public ReadVariable(String variableName, Type<?> variableType, SourceLocation sourceLocation) {
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
	
	public Type<?> getVariableType() {
		return variableType;
	}
	
	private void setVariableType(Type<?> variableType) {
		this.variableType = variableType;
	}

	private Type<?> variableType;
	
	@Override
	public Type<?> evaluateLegalCase(Program program){
		Type<?> variable=  program.getGlobalVariables().get(getVariableName());
		if(!variable.getClass().equals(getVariableType().getClass())){
			program.stop();
			return null;
		}
		return variable;
	}

	@Override
	public Type<?> getType() {
		return getVariableType();
	}


}
