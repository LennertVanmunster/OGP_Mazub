package jumpingalien.programs.statements;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.expressions.Expression;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.BoolType;

public class While extends Statement{
	public While(Expression<BoolType> condition, Statement body, SourceLocation sourceLocation){
		super(sourceLocation);
		this.setCondition(condition);
		this.setBody(body);
	}
	
	
	public Expression<BoolType> getCondition() {
		return this.condition;
	}
	public void setCondition(Expression<BoolType> condition) {
		this.condition = condition;
	}
	
	private Expression<BoolType> condition;

	public Statement getBody() {
		return this.body;
	}

	public void setBody(Statement body) {
		this.body = body;
		body.setNestingStatement(this);
	}
	
	private Statement body;
	
	public boolean getCallSecondTime() {
		return this.callSecondTime;
	}


	public void setCallSecondTime(boolean callSecondTime) {
		this.callSecondTime = callSecondTime;
	}

	private boolean callSecondTime=false;
	
	public void execute(Program program){
		if(this.isToBeExecuted() && !program.hasStopped()){
			if (program.hasTimeForStatement()){
				while(((((BoolType) getCondition().evaluate(program)).getValue() && program.hasTimeForStatement()) || this.getCallSecondTime()) && !program.hasStopped()){
					if(this.getCallSecondTime()){
						this.setCallSecondTime(false);
					}
					else{
						program.decreaseTimerOneUnit();
						getBody().setToBeExecuted(true);
					}
					getBody().execute(program);
				}
				if (program.hasTimeForStatement()){
					program.decreaseTimerOneUnit();
					this.setToBeExecuted(false);
				}
				else{
					if(program.isTimeDepleted()){
						this.setCallSecondTime(true);
					}
				}
			}
			else{
				program.setTimeDepleted(true);
			}
		}	
	}
}
