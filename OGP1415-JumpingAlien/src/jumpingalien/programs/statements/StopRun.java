package jumpingalien.programs.statements;

import jumpingalien.model.*;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.expressions.Expression;
import jumpingalien.programs.program.Program;

public class StopRun extends Statement {

	public StopRun(Expression direction, SourceLocation sourceLocation){
		super(sourceLocation);
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
			if(program.hasTimeForStatement()){
				program.decreaseTimerOneUnit();
				GameObject gameObject = program.getGameObject();
				gameObject.endMove((Orientation) getDirection().evaluate(program));
				this.setToBeExecuted(false);
			}
			else{
				program.setTimeDepleted(true);
			}
		}
	}
}
