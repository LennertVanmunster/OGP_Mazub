package jumpingalien.programs.statements;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

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
	}

	private Statement body;
	
	public void execute(Program program){
		if(!canHaveAsVariableName(getVariableName(),program)){
			program.stop();
		}
		for(GameObject gameObject: createGameObjectList(program)){
			program.putGlobalVariable(getVariableName(), new GameObjectType(), new GameObjectExpression(gameObject));
			if(getWhere()!=null){
				if(true/*getWhere().evaluate()*/){
					getBody().execute(program);
				}
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
			//gameObjectList.addAll(program.getGameObject().getWorld().getTiles());
			break;
		}
		HashMap<GameObject, Double> sortMap= new HashMap<GameObject, Double>();
		for(GameObject gameObject: gameObjectList){
			program.putGlobalVariable(getVariableName(), new GameObjectType(), new GameObjectExpression(gameObject));
			Expression sortExpression = getSort();
			double sortDouble= sortExpression.evaluate();
			Double sortDoubleObject = new Double(sortDouble);
			sortMap.put(gameObject, sortDoubleObject);
		}
		switch(getSortDirection()){
		case ASCENDING:
			Collections.sort(gameObjectList, (GameObject g1, GameObject g2) -> sortMap.get(g1).compareTo(sortMap.get(g2)));
			break;
		case DESCENDING:
			Collections.sort(gameObjectList, (GameObject g1, GameObject g2) -> sortMap.get(g2).compareTo(sortMap.get(g1)));
		}
		return gameObjectList;
	}
}
