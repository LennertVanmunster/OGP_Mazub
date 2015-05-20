package jumpingalien.programs.statements;

import jumpingalien.model.*;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.expressions.Expression;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.DirectionType;

public class StopRun extends Statement {

	public StopRun(Expression<DirectionType> direction, SourceLocation sourceLocation){
		super(sourceLocation);
		setDirection(direction);
	}
	
	public Expression<DirectionType> getDirection() {
		return this.direction;
	}

	public void setDirection(Expression<DirectionType> direction) {
		this.direction = direction;
	}

	private Expression<DirectionType> direction;
	
	public void execute(Program program){
		if(this.isToBeExecuted() && !program.hasStopped()){
			if(program.hasTimeForStatement()){
				program.decreaseTimerOneUnit();
				GameObject gameObject = program.getGameObject();
				gameObject.endMove(Orientation.DUMMY.convertDirectionIProgramFactory(((DirectionType) getDirection().evaluate(program)).getValue()));
				this.setToBeExecuted(false);
			}
			else{
				program.setTimeDepleted(true);
			}
		}
	}
}
