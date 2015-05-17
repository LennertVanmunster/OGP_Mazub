package jumpingalien.programs.statements;

import jumpingalien.programs.expressions.Expression;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.Type;

public class Assignment extends Statement{
	public Assignment(String variableName, Type variableType, Expression value){
		this.variableName=variableName;
		this.variableType=variableType;
		this.value=value;
	}
	
	public String getVariableName(){
		return this.variableName;
	}
	
	String variableName;
	
	public Type getVariableType(){
		return this.variableType;
	}
	
	Type variableType;
	
	public Expression getValue(){
		return this.value;
	}
	
	Expression value;
	
	public void execute(Program program){
		if(this.isToBeExecuted()){
			program.putGlobalVariable(this.getVariableName(), this.getVariableType(), this.getValue());
		}
	}
}