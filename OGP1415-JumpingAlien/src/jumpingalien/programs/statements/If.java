package jumpingalien.programs.statements;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.expressions.Expression;
import jumpingalien.programs.program.Program;

public class If extends Statement{
	public If(Expression condition, Statement ifBody, Statement elseBody, SourceLocation sourceLocation){
		super(sourceLocation);
		setCondition(condition);
		setIfBody(ifBody);
		setElseBody(elseBody);
	}
	
	public If(Expression condition, Statement ifBody, Statement elseBody){
		this(condition, ifBody, elseBody, new SourceLocation(0,0));
	}
	
	public Expression getCondition() {
		return this.condition;
	}
	public void setCondition(Expression condition) {
		this.condition = condition;
	}

	private Expression condition;
	
	public Statement getIfBody() {
		return this.ifBody;
	}

	public void setIfBody(Statement ifBody) {
		this.ifBody = ifBody;
	}

	private Statement ifBody;
	
	public Statement getElseBody() {
		return this.elseBody;
	}

	public void setElseBody(Statement elseBody) {
		this.elseBody = elseBody;
	}

	private Statement elseBody;
	
	public void execute(Program program){
		if(condition.evaluate()){
			getIfBody().execute(program);
		}
		else{
			getElseBody().execute(program);
		}
	}
	
}
