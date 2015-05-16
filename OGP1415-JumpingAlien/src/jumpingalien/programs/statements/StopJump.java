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
		program.getGameObject().endJump();
	}
}