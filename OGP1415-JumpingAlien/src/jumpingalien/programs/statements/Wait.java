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

	public double getTimer() {
		return this.timer;
	}

	public void setTimer(double timer) {
		this.timer = timer;
	}

	private double timer;
	
	@Override
	public void execute(Program program) {
		if(this.isToBeExecuted() && program.hasTimeForStatement()){
			program.setTimer(program.getTimer()- (double) this.getDuration().evaluate(program));
		}
	}
}