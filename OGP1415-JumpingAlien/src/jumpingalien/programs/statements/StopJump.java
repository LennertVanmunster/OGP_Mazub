package jumpingalien.programs.statements;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;

public class StopJump extends ActionStatement{
	public StopJump(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	
	@Override
	public void execute(Program program) {
		if(this.isToBeExecuted() && !program.hasStopped()){
			if(program.hasTimeForStatement()){
				program.decreaseTimerOneUnit();
				program.getGameObject().endJump();
				this.setToBeExecuted(false);
			}
			else{
				program.setTimeDepleted(true);
			}
		}
	}
}