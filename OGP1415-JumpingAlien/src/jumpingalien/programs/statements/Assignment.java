package jumpingalien.programs.statements;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.expressions.Expression;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.Type;

public class Assignment extends Statement {
	public Assignment(String variableName, Type<?> variableType, Expression<Type<?>> value, SourceLocation sourceLocation){
		super(sourceLocation);
		this.variableName=variableName;
		this.variableType=variableType;
		this.value=value;
	}
	
	public String getVariableName(){
		return this.variableName;
	}
	
	String variableName;
	
	public Type<?> getVariableType(){
		return this.variableType;
	}
	
	private Type<?> variableType;
	
	public Expression<Type<?>> getValue(){
		return this.value;
	}
	
	private Expression<Type<?>> value;
	
	public boolean matchesValueType(Type<?> variableType, Expression<Type<?>> value){
		return value.getType().getClass().equals(variableType.getClass());
	}
	
	public void execute(Program program){
		if(this.isToBeExecuted() && !program.hasStopped()){
			if (program.hasTimeForStatement()){
				program.decreaseTimerOneUnit();
				if(!matchesValueType(this.getVariableType(),this.getValue())){
					program.stop();
				}
				program.putGlobalVariable(this.getVariableName(), this.getValue().evaluate(program));
				this.setToBeExecuted(false);
			}
			else{
				program.setTimeDepleted(true);
			}
		}
	}
}