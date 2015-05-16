package jumpingalien.programs.statements;

import jumpingalien.model.*;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.expressions.Expression;
import jumpingalien.programs.program.Program;

public class StopRun {

	public StopRun(Expression direction, SourceLocation sourceLocation){
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
		GameObject gameObject = program.getGameObject();
		gameObject.endMove((Direction) getDirection().evaluate(program));
	}
}
