package jumpingalien.programs.statements;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;

public class Skip extends Statement{
	public Skip(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	
	public Skip(){
		this(new SourceLocation(0,0));
	}

	@Override
	public void execute(Program program) {
	}
}