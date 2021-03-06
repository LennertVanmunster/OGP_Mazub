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
		if(statements!=null){
			for(Statement statement: statements){
				statement.setNestingStatement(this);
			}
		}
	}
	
	public List<Statement> getStatements(){
		if(statements==null){
			return null;
		}
		else{
			List<Statement> statementsClone = new ArrayList<Statement>(this.statements);
			return statementsClone;
		}
	}
	
	List<Statement> statements = new ArrayList<Statement>();
	
	public void execute(Program program){
		if(this.isToBeExecuted() && !program.hasStopped()){
			if (program.hasTimeForStatement()){
				if(getStatements()!=null){
					for(Statement statement: getStatements()){
						statement.execute(program);
					}
				}
			}
			else{
				program.setTimeDepleted(true);
			}
		}
	}
	
	@Override
	public void setToBeExecuted(boolean toBeExecuted) {
		super.setToBeExecuted(toBeExecuted);
		if(getStatements()!=null){
			for(Statement statement: ((Sequence) this).getStatements()){
				if(statement!=null){
					statement.setToBeExecuted(toBeExecuted);
				}
			}
		}
	}
}
