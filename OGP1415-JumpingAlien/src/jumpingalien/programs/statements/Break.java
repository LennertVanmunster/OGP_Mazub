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
	
	
	public Statement getLoopStatement(){
		Statement nestingStatement=this.getNestingStatement();
		while (!(nestingStatement instanceof While) && !(nestingStatement instanceof ForEach)){
			nestingStatement=nestingStatement.getNestingStatement();
		}
		return nestingStatement;
	}

	@Override
	public void execute(Program program) {
		if(this.isToBeExecuted()){
			this.getLoopStatement().setToBeExecuted(false);
		}
	}
	
	
}
