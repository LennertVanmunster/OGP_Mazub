package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public abstract class Expression<T extends Type<?>> {
	
	public Expression(SourceLocation sourceLocation){
		this.setSourceLocation(sourceLocation);
	}
	
	public Expression(){
		this(null);
	}
	
	/**
	 * @return the sourceLocation
	 */
	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	/**
	 * @param sourceLocation the sourceLocation to set
	 */
	private void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}


	private SourceLocation sourceLocation;
	
	public abstract T getType();
	
	
	public void setStopProgram(boolean stop){
		this.stopProgram=stop;
	}
	
	public boolean getStopProgram(){
		return this.stopProgram;
	}

	private boolean stopProgram=false;	
	
	
	public abstract T evaluateLegalCase(Program program);
	
	public T evaluate(Program program){
		if(this.getStopProgram() || program.hasStopped()){
			program.stop();
			return null;
		}
		else{
			return evaluateLegalCase(program);
		}
	}
}
