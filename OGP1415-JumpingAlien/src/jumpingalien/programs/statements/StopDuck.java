package jumpingalien.programs.statements;

import jumpingalien.model.Buzam;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;

public class StopDuck extends ActionStatement{
	public StopDuck(SourceLocation sourceLocation){
		super(sourceLocation);
	}

	@Override
	public void execute(Program program) {
		if(this.isToBeExecuted() && !program.hasStopped()){
			if( program.hasTimeForStatement()){
				program.decreaseTimerOneUnit();
				if(program.getGameObject() instanceof Buzam){
					((Buzam)program.getGameObject()).endDuck();
				}
				this.setToBeExecuted(false);
			}
			else{
				program.setTimeDepleted(true);
			}
		}
	}
}