package jumpingalien.programs.statements;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;

public class StartJump extends Statement{
	public StartJump(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	
	public StartJump(){
		this(new SourceLocation(0,0));
	}

	@Override
	public void execute(Program program) {
		if(this.isToBeExecuted()){
			program.getGameObject().startJump();
		}
	}
}
