package jumpingalien.programs.statements;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.expressions.Expression;
import jumpingalien.programs.program.Program;

public class While extends Statement{
	public While(Expression condition, Statement body, SourceLocation sourceLocation){
		super(sourceLocation);
	}
	
	
	public Expression getCondition() {
		return this.condition;
	}
	public void setCondition(Expression condition) {
		this.condition = condition;
	}
	
	private Expression condition;

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
		if(this.isToBeExecuted()){
			if (program.hasTimeForStatement()){
				while(((boolean) condition.evaluate(program) && program.hasTimeForStatement()) || this.getCallSecondTime()){
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
