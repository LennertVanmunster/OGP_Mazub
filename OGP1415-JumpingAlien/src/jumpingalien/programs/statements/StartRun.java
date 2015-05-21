package jumpingalien.programs.statements;

//import jumpingalien.model.*;
import jumpingalien.model.GameObject;
import jumpingalien.model.Orientation;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.expressions.*;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.DirectionType;

public class StartRun extends ActionStatement {
	public StartRun(Expression<DirectionType> direction, SourceLocation sourceLocation){
		super(sourceLocation);
		setDirection(direction);
	}
	
	public Expression<DirectionType> getDirection() {
		return this.direction;
	}

	public void setDirection(Expression<DirectionType> direction) {
		this.direction = direction;
	}
	
	public boolean checkType(){
		return this.getDirection().getType() instanceof DirectionType;
	}

	private Expression<DirectionType> direction;
	
	public void execute(Program program){
		if(this.isToBeExecuted() && !program.hasStopped()){
			if(program.hasTimeForStatement()){
				if(checkType()){
					program.decreaseTimerOneUnit();
					GameObject gameObject = program.getGameObject();
					gameObject.startMove(Orientation.DUMMY.convertDirectionIProgramFactory(((DirectionType) getDirection().evaluateLegalCase(program)).getValue()));
				}
				else{
					program.stop();
				}
				this.setToBeExecuted(false);
			}
			else{
				program.setTimeDepleted(true);
			}
		}
	}
}
