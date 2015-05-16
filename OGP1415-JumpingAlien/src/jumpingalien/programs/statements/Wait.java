package jumpingalien.programs.statements;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;

public class Wait extends Statement{
	public Wait(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	
	public Wait(){
		this(new SourceLocation(0,0));
	}

	@Override
	public void execute(Program program) {
	}
}