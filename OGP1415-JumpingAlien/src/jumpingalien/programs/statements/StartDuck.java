package jumpingalien.programs.statements;

import jumpingalien.model.Buzam;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;

public class StartDuck extends Statement{
	public StartDuck(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	
	public StartDuck(){
		this(new SourceLocation(0,0));
	}

	@Override
	public void execute(Program program) {
		if(this.isToBeExecuted()){
			if(program.getGameObject() instanceof Buzam){
				((Buzam)program.getGameObject()).startDuck();
			}
		}
	}
}