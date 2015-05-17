package jumpingalien.programs.statements;

import java.util.ArrayList;
import java.util.List;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;

public class Sequence extends Statement {
	public Sequence(List<Statement> statements, SourceLocation sourceLocation){
		super(sourceLocation);
		this.setStatements(statements);
	}
	
	public void setStatements(List<Statement> statements){
		this.statements=statements;
		for(Statement statement: statements){
			statement.setNestingStatement(this);
		}
	}
	
	public List<Statement> getStatements(){
		List<Statement> statementsClone = new ArrayList<Statement>(this.statements);
		return statementsClone;
	}
	
	List<Statement> statements = new ArrayList<Statement>();
	
	public void execute(Program program){
		if(this.isToBeExecuted()){
			if (program.hasTimeForStatement()){
				for(Statement statement: getStatements()){
					statement.execute(program);
				}
				this.setToBeExecuted(false);
			}
			else{
				program.setTimeDepleted(true);
			}
		}
	}
}
