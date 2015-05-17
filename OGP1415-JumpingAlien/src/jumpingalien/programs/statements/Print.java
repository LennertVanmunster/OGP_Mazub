package jumpingalien.programs.statements;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.expressions.Expression;
import jumpingalien.programs.program.Program;

public class Print extends Statement{
	public Print(Expression value, SourceLocation sourceLocation){
		setValue(value);
	}
	
	public Print(Expression value){
		this(value, new SourceLocation(0,0));
	}
	
	public Expression getValue() {
		return this.value;
	}

	public void setValue(Expression value) {
		this.value = value;
	}

	private Expression value;

	@Override
	public void execute(Program program) {
		if(this.isToBeExecuted()){
			if (program.hasTimeForStatement()){
				program.decreaseTimerOneUnit();
				System.out.println(value.evaluate(program));
				this.setToBeExecuted(false);
			}
			else{
				program.setTimeDepleted(true);
			}
		}
	}
}
