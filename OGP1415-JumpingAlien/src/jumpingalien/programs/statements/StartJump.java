package jumpingalien.programs.statements;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;

public class StartJump extends ActionStatement{
	public StartJump(SourceLocation sourceLocation){
		super(sourceLocation);
	}

	@Override
	public void execute(Program program) {
		if(this.isToBeExecuted() && !program.hasStopped()){
			if(program.hasTimeForStatement()){
				program.decreaseTimerOneUnit();
				program.getGameObject().startJump();
				this.setToBeExecuted(false);
			}
			else{
				program.setTimeDepleted(true);
			}
		}
	}
}
