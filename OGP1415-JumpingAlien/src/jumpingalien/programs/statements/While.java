package jumpingalien.programs.statements;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.expressions.Expression;
import jumpingalien.programs.program.Program;

public class While extends Statement{
	public While(Expression condition, Statement body, SourceLocation sourceLocation){
		super(sourceLocation);
	}
	
	
	public Expression getCondition() {
		return this.condition;
	}
	public void setCondition(Expression condition) {
		this.condition = condition;
	}
	
	private Expression condition;

	public Statement getBody() {
		return this.body;
	}

	public void setBody(Statement body) {
		this.body = body;
	}
	
	private Statement body;
	
	public void execute(Program program){
		while(condition.evaluate()){
			body.execute(program);
		}
	}
}
