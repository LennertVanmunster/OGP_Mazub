package jumpingalien.programs.statements;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.IProgramFactory.Kind;
import jumpingalien.part3.programs.IProgramFactory.SortDirection;
import jumpingalien.programs.expressions.*;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;

public class ForEach extends Statement{
	public ForEach(String variableName, Kind variableKind, Expression<BoolType> where, Expression<DoubleType> sort,
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
	
	public Expression<BoolType> getWhere() {
		return this.where;
	}

	public void setWhere(Expression<BoolType> where) {
		this.where = where;
	}

	private Expression<BoolType> where;
	
	public Expression<DoubleType> getSort() {
		return this.sort;
	}

	public void setSort(Expression<DoubleType> sort) {
		this.sort = sort;
	}

	private Expression<DoubleType> sort;
	
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
		if(this.isToBeExecuted() && !program.hasStopped()){	
			if(program.hasTimeForStatement()){
				program.decreaseTimerOneUnit();
				if(!canHaveAsVariableName(getVariableName(),program)){
					program.stop();
				}
				else{
					if(!getCallSecondTime()){
						List<ObjectType<?>> objectTypeList=this.createObjectTypeList(program);
						if(getWhere()!=null){
							objectTypeList=filterObjectTypeList(objectTypeList, program);
						}
						if(getSort()!=null){
							objectTypeList=sortObjectTypeList(objectTypeList, program);
						}
						setObjectTypeList(objectTypeList);
					}
					if(this.getObjectTypeList()==null){
						return;
					}
					while(((this.getLoopIndex()<getObjectTypeList().size() && !program.isTimeDepleted() && this.isToBeExecuted()) || getCallSecondTime()) && !program.hasStopped()){
						ObjectType<?> objectType= getObjectTypeList().get(this.getLoopIndex());
						program.putGlobalVariable(getVariableName(), objectType);
						if(!getCallSecondTime()){
							getBody().setToBeExecuted(true);
						}
						else{
							setCallSecondTime(false);
						}
						getBody().execute(program);
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
	
	private List<ObjectType<?>> createObjectTypeList(Program program){
		List<ObjectType<?>> objectTypeList=new ArrayList<ObjectType<?>>();
		List<GameObject> gameObjectList=new ArrayList<GameObject>();
		boolean isTerrain=false;
		switch(getVariableKind()){
		case ANY:
			gameObjectList.addAll(program.getGameObject().getWorld().getAllGameObjects());
			gameObjectList.remove(null);
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
			int[][] tileArray=program.getGameObject().getWorld().getTiles();
			for(int i=0; i<tileArray.length; i++){
				for(int j=0; j<tileArray[i].length; j++){
					objectTypeList.add(new TileType(new int[]{i,j}));
				}
			}
			isTerrain=true;
			break;
		}
		if(!isTerrain){
			for(GameObject gameObject: gameObjectList){
				objectTypeList.add(new GameObjectType(gameObject));
			}
		}
		return objectTypeList;
	}
		
	private List<ObjectType<?>> sortObjectTypeList(List<ObjectType<?>> objectTypeList, Program program){
		Expression<DoubleType> sortExpression = getSort();
		HashMap<ObjectType<?>, Double> sortMap= new HashMap<ObjectType<?>, Double>();
		for(ObjectType<?> objectType: objectTypeList){
			program.putGlobalVariable(getVariableName(), objectType);
			double sortDouble;
			try{
				sortDouble= ((DoubleType) sortExpression.evaluateLegalCase(program)).getValue();
			} catch (NullPointerException exc){
				return null;
			}
			Double sortDoubleObject = new Double(sortDouble);
			sortMap.put(objectType, sortDoubleObject);
		}
		if(getSortDirection() != null){
			switch(getSortDirection()){
			case ASCENDING:
				Collections.sort(objectTypeList, (ObjectType<?> g1, ObjectType<?> g2) -> sortMap.get(g1).compareTo(sortMap.get(g2)));
				break;
			case DESCENDING:
				Collections.sort(objectTypeList, (ObjectType<?> g1, ObjectType<?> g2) -> sortMap.get(g2).compareTo(sortMap.get(g1)));
			}
		}
		return objectTypeList;
	}
	
	private List<ObjectType<?>> filterObjectTypeList(List<ObjectType<?>> objectTypeList, Program program){
		objectTypeList = objectTypeList.stream().filter(objectType -> {program.putGlobalVariable(getVariableName(), objectType);
		try{
			return ((BoolType) getWhere().evaluateLegalCase(program)).getValue();
		}catch(NullPointerException exc){
			return false;
		}}).collect(Collectors.toList());
		return objectTypeList;
	}
	
	public List<ObjectType<?>> getObjectTypeList() {
		return this.objectTypeList;
	}

	public void setObjectTypeList(List<ObjectType<?>> objectTypeList) {
		this.objectTypeList = objectTypeList;
	}

	List<ObjectType<?>> objectTypeList =new ArrayList<ObjectType<?>>();
	
	@Override
	public void setToBeExecuted(boolean toBeExecuted) {
		super.setToBeExecuted(toBeExecuted);
		if(getBody()!=null){
			getBody().setToBeExecuted(toBeExecuted);
		}
	}
}
