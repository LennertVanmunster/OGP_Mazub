package jumpingalien.programs.statements;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.expressions.Expression;
import jumpingalien.programs.program.Program;

public class Wait extends Statement{

	public Wait(Expression duration, SourceLocation sourceLocation){
		super(sourceLocation);
		this.setDuration(duration);
	}
	
	private void setDuration(Expression duration) {
		this.duration = duration;
	}
	
	/**
	 * @return the duration
	 */
	public Expression getDuration() {
		return duration;
	}

	private Expression duration;
 
//	public Wait(){
//		this(new SourceLocation(0,0));
//	}

	@Override
	public void execute(Program program) {
	}
}