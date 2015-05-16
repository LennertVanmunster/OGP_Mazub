package jumpingalien.programs.statements;


import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;

public abstract class Statement {
	public Statement(SourceLocation sourceLocation){
		setSourceLocation(sourceLocation);
	}
	
	public Statement(){
	}
	
	public void setSourceLocation(SourceLocation sourceLocation){
		this.sourceLocation=sourceLocation;
	}
	
	public SourceLocation getSourceLocation(){
		return this.sourceLocation;
	}
	
	private SourceLocation sourceLocation = new SourceLocation(0,0);
	
	public abstract void execute(Program program);
}
