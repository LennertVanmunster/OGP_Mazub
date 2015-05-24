package jumpingalien.programs.statements;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.expressions.Expression;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.DoubleType;

public class Wait extends ActionStatement{

	public Wait(Expression<DoubleType> duration, SourceLocation sourceLocation){
		super(sourceLocation);
		this.setDuration(duration);
	}
	
	private void setDuration(Expression<DoubleType> duration) {
		this.duration = duration;
	}
	
	/**
	 * @return the duration
	 */
	public Expression<DoubleType> getDuration() {
		return duration;
	}

	private Expression<DoubleType> duration;

	public double getTimer() {
		return this.timer;
	}

	public void setTimer(double timer) {
		this.timer = timer;
	}

	private double timer=Double.MAX_VALUE;
	
	@Override
	public void execute(Program program) {
		if(this.isToBeExecuted() && !program.hasStopped()){
			if(program.hasTimeForStatement()){
				if(this.getTimer()>0){
					double duration;
					try{
						duration=((DoubleType) this.getDuration().evaluateLegalCase(program)).getValue();
						if(duration<program.TIME_UNIT && duration > 0){
							duration=program.TIME_UNIT;
						}
						else if(duration<0){
							program.stop();
							return;
						}
					}catch(NullPointerException exc){
						return;
					}
					this.setTimer(program.getTimer() - duration);
				}
				else{
					this.setTimer(this.getTimer()+program.getTimer());
				}
				program.setTimer(this.getTimer());
				if(program.getTimer()>0){
					this.setTimer(Double.MAX_VALUE);
					this.setToBeExecuted(false);;
				}
			}
			else{
				program.setTimeDepleted(true);
			}
		}
	}
}