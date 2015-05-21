package jumpingalien.programs.statements;


import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;

public abstract class Statement {
	public Statement(SourceLocation sourceLocation){
		setSourceLocation(sourceLocation);
	}
	
	public Statement(){
		this(null);
	}
	
	public void setSourceLocation(SourceLocation sourceLocation){
		this.sourceLocation=sourceLocation;
	}
	
	public SourceLocation getSourceLocation(){
		return this.sourceLocation;
	}
	
	private SourceLocation sourceLocation = null;
	
	public Statement getNestingStatement() {
		return this.nestingStatement;
	}

	public void setNestingStatement(Statement nestingStatement) {
		this.nestingStatement = nestingStatement;
	}

	private Statement nestingStatement = null;
	
	public Statement getLoopStatement(){
		Statement nestingStatement=this.getNestingStatement();
		if(nestingStatement==null){
			return null;
		}
		while (!(nestingStatement instanceof While) && !(nestingStatement instanceof ForEach)){
			nestingStatement=nestingStatement.getNestingStatement();
			if(nestingStatement==null){
				return null;
			}
		}
		return nestingStatement;
	}
	
	public boolean isToBeExecuted() {
		return this.toBeExecuted;
	}

	public void setToBeExecuted(boolean toBeExecuted) {
		this.toBeExecuted = toBeExecuted;
	}

	protected boolean toBeExecuted=true;
	
	public abstract void execute(Program program);
}
