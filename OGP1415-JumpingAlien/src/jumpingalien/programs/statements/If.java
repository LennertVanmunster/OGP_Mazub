package jumpingalien.programs.statements;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.expressions.Expression;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.BoolType;

public class If extends Statement{
	public If(Expression<BoolType> condition, Statement ifBody, Statement elseBody, SourceLocation sourceLocation){
		super(sourceLocation);
		setCondition(condition);
		setIfBody(ifBody);
		if(elseBody != null){
			setElseBody(elseBody);
		}
	}
	
	public If(Expression<BoolType> condition, Statement ifBody, Statement elseBody){
		this(condition, ifBody, elseBody, new SourceLocation(0,0));
	}
	
	public Expression<BoolType> getCondition() {
		return this.condition;
	}
	public void setCondition(Expression<BoolType> condition) {
		this.condition = condition;
	}

	private Expression<BoolType> condition;
	
	public Statement getIfBody() {
		return this.ifBody;
	}

	public void setIfBody(Statement ifBody) {
		this.ifBody = ifBody;
		ifBody.setNestingStatement(this);
	}

	private Statement ifBody;
	
	public Statement getElseBody() {
		return this.elseBody;
	}

	public void setElseBody(Statement elseBody) {
		this.elseBody = elseBody;
		elseBody.setNestingStatement(this);
	}

	private Statement elseBody;
	
	public void execute(Program program){
		if(this.isToBeExecuted() && !program.hasStopped()){
			if (program.hasTimeForStatement()){
				program.decreaseTimerOneUnit();
				boolean condition;
				try{
					condition=((BoolType) getCondition().evaluate(program)).getValue();
				}catch(NullPointerException exc){
					return;
				}
				if(condition){
					getIfBody().execute(program);
				}
				else if(getElseBody() != null){
					getElseBody().execute(program);
				}
				this.setToBeExecuted(false);
			}
			else{
				program.setTimeDepleted(true);
			}
		}
	}
	
}
