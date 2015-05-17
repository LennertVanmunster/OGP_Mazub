package jumpingalien.programs.statements;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;

public class StopJump extends Statement{
	public StopJump(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	
	public StopJump(){
		this(new SourceLocation(0,0));
	}

	@Override
	public void execute(Program program) {
		if(this.isToBeExecuted()){
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