package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.types.*;

public class Self extends Expression {


	public Self(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	
	public Type getType(){
		return new GameObjectType();
	}
	
}
