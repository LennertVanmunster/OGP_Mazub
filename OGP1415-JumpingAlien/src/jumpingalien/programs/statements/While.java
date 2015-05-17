package jumpingalien.programs.statements;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.expressions.Expression;
import jumpingalien.programs.program.Program;

public class While extends Statement{
	public While(Expression condition, Statement body, SourceLocation sourceLocation){
		super(sourceLocation);
		this.setCondition(condition);
		this.setBody(body);
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
		body.setNestingStatement(this);
	}
	
	private Statement body;
	
	public void execute(Program program){
		if(this.isToBeExecuted()){
			while((boolean) this.getCondition().evaluate(program)){
				this.getBody().execute(program);
			}
		}	
	}
}
