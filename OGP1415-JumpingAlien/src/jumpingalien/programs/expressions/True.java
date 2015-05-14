package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;

public class True extends Expression {
	
	public True(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	
	public boolean execute(){
		return true;
	}

}
