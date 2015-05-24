package jumpingalien.programs.program;

import java.util.HashMap;
import java.util.Map;

import jumpingalien.model.GameObject;
import jumpingalien.model.World;
import jumpingalien.programs.types.*;
import jumpingalien.programs.statements.*;


public class Program {
	
	public Program(Statement mainStatement, Map<String, Type<?>> globalVariables){
		setGlobalVariables(globalVariables);
		setMainStatement(mainStatement);
	}
	
	public Program(){
		this(null, new HashMap<String, Type<?>>());
	}
	
	public Statement getMainStatement(){
		return this.mainStatement;
	}
	
	public void setMainStatement(Statement mainStatement){
		this.mainStatement=mainStatement;
	}
	
	private Statement mainStatement;
	
	public Map<String,Type<?>> getGlobalVariables(){
		Map<String,Type<?>> globalVariables= new HashMap<String, Type<?>>(this.globalVariables);
		return globalVariables;
	}
	
	public void setGlobalVariables(Map<String,Type<?>> globalVariables){
		for(Map.Entry<String, Type<?>> entry: globalVariables.entrySet()){
			this.putGlobalVariable(entry.getKey(), entry.getValue());
		}
	}
	
	public void putGlobalVariable(String variableName, Type<?> variableType){
		this.globalVariables.put(variableName, variableType);
	}
	
	
	private Map<String,Type<?>> globalVariables = new HashMap<String, Type<?>>();
	
	
	public void execute(double deltaTime){
		if(!this.hasStopped()){
			setTimer(deltaTime);
			getMainStatement().execute(this);
			if(!this.isTimeDepleted()){
				getMainStatement().setToBeExecuted(true);
			}
			else{
				setTimeDepleted(false);
			}
		}
		else{
			if(getGameObject()!=null){
				getGameObject().endMove(getGameObject().getDirection());
			}
			if(printedOnce==false){
				System.out.println("");
				System.out.println("Type error in program!");
				System.out.println(this.getGameObject());
				printedOnce=true;
			}
		}
	}
	private boolean printedOnce=false;
	
	public void stop(){
		this.hasStopped=true;
	}
	
	public boolean hasStopped(){
		return this.hasStopped;
	}
	
	private boolean hasStopped=false;
	
	
	public boolean isWellFormedActionStatements(Statement statement) {
		if(statement instanceof Sequence){
			boolean isWellFormedSequence=true;
			for(Statement subStatement: ((Sequence) statement).getStatements() ){
				if(!isWellFormedActionStatements(subStatement)){
					isWellFormedSequence=false;
				}
			}
			return isWellFormedSequence;
		}
		else if(statement instanceof If){
			return (isWellFormedActionStatements(((If) statement).getIfBody()) && isWellFormedActionStatements(((If) statement).getElseBody()));
		}
		else if(statement instanceof ForEach){
			return ( isWellFormedActionStatements(((ForEach) statement).getBody()));
		}
		else if (statement instanceof While){
			return isWellFormedActionStatements(((While) statement).getBody());
		}
		else if (statement instanceof ActionStatement){
			if(statement.getLoopStatement()==null){
				return true;
			}
			else if(statement.getLoopStatement() instanceof ForEach){
				return false;
			}
			else{
				return true;
			}
		}
		else{
			return true;
		}
	}
	
	public boolean isWellFormedBreakStatements(Statement statement){
		if(statement instanceof Sequence){
			boolean isWellFormedSequence=true;
			for(Statement subStatement: ((Sequence) statement).getStatements() ){
				if(!isWellFormedBreakStatements(subStatement)){
					isWellFormedSequence=false;
				}
			}
			return isWellFormedSequence;
		}
		else if(statement instanceof If){
			return (isWellFormedBreakStatements(((If) statement).getIfBody()) && isWellFormedBreakStatements(((If) statement).getElseBody()));
		}
		else if(statement instanceof ForEach){
			return ( isWellFormedBreakStatements(((ForEach) statement).getBody()));
		}
		else if (statement instanceof While){
			return isWellFormedBreakStatements(((While) statement).getBody());
		}
		else if (statement instanceof Break){
			if(statement.getLoopStatement()==null){
				return false;
			}
			else if((statement.getLoopStatement() instanceof ForEach) || (statement.getLoopStatement() instanceof While)){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return true;
		}
	}
	
	public boolean isWellFormed(){
		return isWellFormedActionStatements(this.getMainStatement()) && isWellFormedBreakStatements(this.getMainStatement());
	}
	
	public double getTimer() {
		return this.timer;
	}

	public void setTimer(double timer) {
		this.timer = timer;
	}
	
	public void decreaseTimerOneUnit(){
		setTimer(getTimer()-TIME_UNIT);
	}
	
	public boolean hasTimeForStatement(){
		return getTimer()>0;
	}

	public boolean isTimeDepleted() {
		return this.timeDepleted;
	}

	public void setTimeDepleted(boolean timeDepleted) {
		this.timeDepleted = timeDepleted;
	}

	private double timer=Integer.MAX_VALUE;
	
	private boolean timeDepleted=false;
	
	public double TIME_UNIT=0.001;

	
	public void setGameObject(GameObject gameObject) throws IllegalArgumentException{
		if(!this.canHaveAsGameObject(gameObject)){
			throw new IllegalArgumentException();
		}
		this.gameObject = gameObject;
	}
	
	public boolean canHaveAsGameObject(GameObject gameObject){
		if(this.isTerminated()){
			return gameObject==null;
		}
		else{
			return gameObject==null || !gameObject.isTerminated();
		}
	}
	
	public boolean hasProperGameObject(){
		return gameObject==null || gameObject.getProgram()==this;
	}
	
	public GameObject getGameObject(){
		return this.gameObject;
	}
	
	private GameObject gameObject = null;
	
	public World getWorld(){
		GameObject gameObject = this.getGameObject();
		if(gameObject != null)
			return gameObject.getWorld();
		else
			return null;
	}
	
	public void terminate(){
		if(!this.isTerminated() && getGameObject()!=null){
			gameObject.setProgram(null);
			this.setGameObject(null);
		}
		this.isTerminated=true;
	}
	
	public boolean isTerminated(){
		return this.isTerminated;
	}
	
	private boolean isTerminated=false;
}