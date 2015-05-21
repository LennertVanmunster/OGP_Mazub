package jumpingalien.programs.statements;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;

public class Skip extends ActionStatement{
	public Skip(SourceLocation sourceLocation){
		super(sourceLocation);
	}

	@Override
	public void execute(Program program) {
		if(this.isToBeExecuted() && !program.hasStopped()){
			if( program.hasTimeForStatement()){
				program.decreaseTimerOneUnit();
				this.setToBeExecuted(false);
			}
			else{
				program.setTimeDepleted(true);
			}
		}
	}
}