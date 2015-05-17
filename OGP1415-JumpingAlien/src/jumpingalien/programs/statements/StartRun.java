package jumpingalien.programs.statements;

import jumpingalien.model.*;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.expressions.*;
import jumpingalien.programs.program.Program;

public class StartRun extends Statement {
	public StartRun(Expression direction, SourceLocation sourceLocation){
		setDirection(direction);
	}
	
	public Expression getDirection() {
		return this.direction;
	}

	public void setDirection(Expression direction) {
		this.direction = direction;
	}

	private Expression direction;
	
	public void execute(Program program){
		if(this.isToBeExecuted()){
			GameObject gameObject = program.getGameObject();
			gameObject.startMove((Direction) getDirection().evaluate(program));
		}
	}
}
