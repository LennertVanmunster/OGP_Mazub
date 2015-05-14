package jumpingalien.programs.program;

import java.util.Map;
import jumpingalien.programs.types.*;
import jumpingalien.programs.statements.*;


public class Program {
	
	public Program(Statement mainStatement, Map<String, Type> globalVariables){
		this.mainStatement=mainStatement;
		this.globalVariables=globalVariables;
	}
	
	public Statement getMainStatement(){
		return this.mainStatement;
	}
	
	public Map<String,Type> getGlobalVariables(){
		return this.globalVariables;
	}

	private Statement mainStatement;
	
	private Map<String,Type> globalVariables;
	
	public void execute(double deltaTime){
	}
	
	public boolean isWellFormed() {
		return true;
	}

}
