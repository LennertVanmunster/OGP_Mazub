package jumpingalien.programs.statements;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.expressions.Expression;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.Type;

public class Assignment extends Statement {
	public Assignment(String variableName, Type<?> variableType, Expression<? extends Type<?>> value, SourceLocation sourceLocation){
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
	
	public Expression<? extends Type<?>> getValue(){
		return this.value;
	}
	
	private Expression<? extends Type<?>> value;
	
	public boolean matchesValueType(Type<?> variableType, Expression<? extends Type<?>> value){
		return value.getType().getClass().equals(variableType.getClass());
	}
	
	public void execute(Program program){
		if(this.isToBeExecuted() && !program.hasStopped()){
			if (program.hasTimeForStatement()){
				program.decreaseTimerOneUnit();
				if(!matchesValueType(this.getVariableType(),this.getValue())){
					program.stop();
					return;
				}
				Type<?> valueToSet;
				try{
					valueToSet=this.getValue().evaluateLegalCase(program);
				} catch (NullPointerException exc){
					program.stop();
					return;
				}
				program.putGlobalVariable(this.getVariableName(), valueToSet);
				this.setToBeExecuted(false);
			}
			else{
				program.setTimeDepleted(true);
			}
		}
	}
}