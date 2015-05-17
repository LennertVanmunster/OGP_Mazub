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

	private double timer=Double.MAX_VALUE;
	
	@Override
	public void execute(Program program) {
		if(this.isToBeExecuted()){
			if(program.hasTimeForStatement()){
				if(this.getTimer()>0){
					this.setTimer(program.getTimer() - (double) this.getDuration().evaluate(program));
				}
				else{
					this.setTimer(this.getTimer()+program.getDeltaTime());
				}
				program.setTimer(this.getTimer());
			}
			else{
				program.setTimeDepleted(true);
			}
		}
	}
}