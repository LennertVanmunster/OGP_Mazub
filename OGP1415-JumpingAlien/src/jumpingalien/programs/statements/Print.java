package jumpingalien.programs.statements;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.expressions.Expression;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.Type;

public class Print extends Statement{
	public Print(Expression<Type<?>> value, SourceLocation sourceLocation){
		setValue(value);
	}
	
	public Expression<Type<?>> getValue() {
		return this.value;
	}

	public void setValue(Expression<Type<?>> value) {
		this.value = value;
	}

	private Expression<Type<?>> value;

	@Override
	public void execute(Program program) {
		if(this.isToBeExecuted() && !program.hasStopped()){
			if (program.hasTimeForStatement()){
				program.decreaseTimerOneUnit();
				System.out.println(value.evaluateLegalCase(program));
				this.setToBeExecuted(false);
			}
			else{
				program.setTimeDepleted(true);
			}
		}
	}
}
