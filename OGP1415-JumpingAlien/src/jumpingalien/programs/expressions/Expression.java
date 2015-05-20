package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public abstract class Expression<T> {
	
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
	
	public abstract Type<?> getType();
	
	
	public void setStopProgram(boolean stop){
		this.stopProgram=stop;
	}
	
	public boolean getStopProgram(){
		return this.stopProgram;
	}

	private boolean stopProgram=false;	
	
	
	public abstract Type<?> evaluate(Program program);
}
