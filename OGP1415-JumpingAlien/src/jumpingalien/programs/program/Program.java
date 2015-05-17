package jumpingalien.programs.program;

import java.util.Map;

import jumpingalien.model.GameObject;
import jumpingalien.model.World;
import jumpingalien.programs.types.*;
import jumpingalien.programs.statements.*;
import jumpingalien.programs.expressions.*;


public class Program {
	
	public Program(Statement mainStatement, Map<String, Type> globalVariables){
		setGlobalVariables(globalVariables);
		setMainStatement(mainStatement);
	}
	
	public Statement getMainStatement(){
		return this.mainStatement;
	}
	
	public void setMainStatement(Statement mainStatement){
		this.mainStatement=mainStatement;
	}
	
	private Statement mainStatement;
	
	public Map<String,Type> getGlobalVariables(){
		return this.globalVariables;
	}
	
	public void setGlobalVariables(Map<String,Type> globalVariables){
		for(Map.Entry<String, Type> entry: globalVariables.entrySet()){
			this.putGlobalVariable(entry.getKey(), entry.getValue());
		}
	}
	
	public void putGlobalVariable(String variableName, Type variableType, Expression value){
		if(matchesExpressionType(variableType, value)){
			this.globalVariables.put(variableName, variableType);
			this.globalVariableValues.put(variableName, value);
		}
		else{
			stop();
		}
	}
	
	public void putGlobalVariable(String variableName, Type variableType){
		this.globalVariables.put(variableName, variableType);
		this.globalVariableValues.put(variableName, variableType.getDefaultValue());
	}
	
	public Map<String,Expression> getGlobalVariableValues(){
		return this.globalVariableValues;
	}
	
	public boolean matchesExpressionType(Type type, Expression expression){
		return expression.getType().getClass().equals(type.getClass());
	}
	
	private Map<String,Type> globalVariables;
	
	private Map<String,Expression> globalVariableValues;
	
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
	}
	
	public void stop(){
		this.hasStopped=true;
	}
	
	public boolean hasStopped(){
		return this.hasStopped;
	}
	
	private boolean hasStopped=false;
	
	public boolean isWellFormed() {
		return true;
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
	
	private double TIME_UNIT=0.001;
	
	public void setGameObject(GameObject gameObject){
		this.gameObject = gameObject;
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


}