package jumpingalien.programs.statements;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;

public class Break extends Statement {
	public Break(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	
	public Break(){
		this(new SourceLocation(0,0));
	}

	@Override
	public void execute(Program program) {
		
		
	}
	
	
}
