package jumpingalien.programs.statements;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import jumpingalien.model.GameObject;
import jumpingalien.part2.internal.tmxfile.data.Map;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.IProgramFactory.Kind;
import jumpingalien.part3.programs.IProgramFactory.SortDirection;
import jumpingalien.programs.expressions.*;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;

public class ForEach extends Statement{
	public ForEach(String variableName, Kind variableKind, Expression where, Expression sort,
			SortDirection sortDirection, Statement body, SourceLocation sourceLocation){
		super(sourceLocation);
		setVariableName(variableName);
		setVariableKind(variableKind);
		setWhere(where);
		setSort(sort);
		setSortDirection(sortDirection);
		setBody(body);
	}
	
	public String getVariableName() {
		return this.variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	
	public boolean canHaveAsVariableName(String variableName, Program program){
		return program.getGlobalVariables().containsKey(variableName) && program.getGlobalVariables().get(variableName) instanceof GameObjectType;
	}

	private String variableName;
	
	public Kind getVariableKind() {
		return this.variableKind;
	}

	public void setVariableKind(Kind variableKind) {
		this.variableKind = variableKind;
	}

	private Kind variableKind;
	
	public Expression getWhere() {
		return this.where;
	}

	public void setWhere(Expression where) {
		this.where = where;
	}

	private Expression where;
	
	public Expression getSort() {
		return this.sort;
	}

	public void setSort(Expression sort) {
		this.sort = sort;
	}

	private Expression sort;
	
	public SortDirection getSortDirection() {
		return this.sortDirection;
	}

	public void setSortDirection(SortDirection sortDirection) {
		this.sortDirection = sortDirection;
	}

	private SortDirection sortDirection;
	
	public Statement getBody() {
		return this.body;
	}

	public void setBody(Statement body) {
		this.body = body;
		body.setNestingStatement(this);
	}

	private Statement body;
	
	public int getLoopIndex() {
		return this.loopIndex;
	}

	public void setLoopIndex(int loopIndex) {
		this.loopIndex = loopIndex;
	}

	private int loopIndex=0;
	
	public boolean getCallSecondTime() {
		return this.callSecondTime;
	}


	public void setCallSecondTime(boolean callSecondTime) {
		this.callSecondTime = callSecondTime;
	}

	private boolean callSecondTime=false;
	
	public void execute(Program program){
		if(this.isToBeExecuted()){	
			if(program.hasTimeForStatement()){
				program.decreaseTimerOneUnit();
				if(!canHaveAsVariableName(getVariableName(),program)){
					program.stop();
				}
				else{
					List<GameObject> gameObjectList=this.createGameObjectList(program);
					while(this.getLoopIndex()<gameObjectList.size() && !program.isTimeDepleted()){
						GameObject gameObject= gameObjectList.get(this.getLoopIndex());
						program.putGlobalVariable(getVariableName(), new GameObjectType(), new GameObjectExpression(gameObject));
						if(!getCallSecondTime()){
							getBody().setToBeExecuted(true);
						}
						else{
							setCallSecondTime(false);
						}
						if(getWhere()!=null){
							if((boolean) getWhere().evaluate(program)){
								getBody().execute(program);
							}
						}
						else{
							getBody().execute(program);
						}
						this.setLoopIndex(this.getLoopIndex()+1);
					}
					if(program.isTimeDepleted()){
						this.setLoopIndex(this.getLoopIndex()-1);
						this.setCallSecondTime(true);
					}
					else{
						this.setLoopIndex(0);
						this.setToBeExecuted(false);
					}
				}
			}
			else{
				program.setTimeDepleted(true);
			}
		}
	}
	
	public List<GameObject> createGameObjectList(Program program){
		List<GameObject> gameObjectList=new ArrayList<GameObject>();
		switch(getVariableKind()){
		case ANY:
			gameObjectList.addAll(program.getGameObject().getWorld().getAllGameObjects());
			break;
		case MAZUB:
			gameObjectList.add(program.getGameObject().getWorld().getMazub());
			break;
		case SLIME:
			gameObjectList.addAll(program.getGameObject().getWorld().getSlimes());
			break;
		case PLANT:
			gameObjectList.addAll(program.getGameObject().getWorld().getPlants());
			break;
		case SHARK:
			gameObjectList.addAll(program.getGameObject().getWorld().getSharks());
			break;
		case BUZAM:
			gameObjectList.add(program.getGameObject().getWorld().getBuzam());
			break;
		case TERRAIN:
//			gameObjectList.addAll(program.getGameObject().getWorld().getTiles());
			break;
		}
		Expression sortExpression = getSort();
		if(sortExpression != null){
			HashMap<GameObject, Double> sortMap= new HashMap<GameObject, Double>();
			for(GameObject gameObject: gameObjectList){
				program.putGlobalVariable(getVariableName(), new GameObjectType(), new GameObjectExpression(gameObject));
				double sortDouble= (double) sortExpression.evaluate(program);
				Double sortDoubleObject = new Double(sortDouble);
				sortMap.put(gameObject, sortDoubleObject);
			}
			if(getSortDirection() != null){
				switch(getSortDirection()){
				case ASCENDING:
					Collections.sort(gameObjectList, (GameObject g1, GameObject g2) -> sortMap.get(g1).compareTo(sortMap.get(g2)));
					break;
				case DESCENDING:
					Collections.sort(gameObjectList, (GameObject g1, GameObject g2) -> sortMap.get(g2).compareTo(sortMap.get(g1)));
				}
			}
		}
		return gameObjectList;
	}
}
