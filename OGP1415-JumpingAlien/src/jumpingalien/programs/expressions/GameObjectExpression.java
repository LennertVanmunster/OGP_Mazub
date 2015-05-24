package jumpingalien.programs.expressions;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.GameObjectType;
import jumpingalien.programs.types.ObjectType;
import jumpingalien.programs.types.Type;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
* @note this class is only used for testing purposes!
*/
public class GameObjectExpression extends Expression<GameObjectType>{
	public GameObjectExpression(GameObject gameObject, SourceLocation sourceLocation){
		super(sourceLocation);
		setGameObject(gameObject);
	}
	
	public GameObjectExpression(GameObject gameObject){
		this(gameObject, new SourceLocation(0,0));
	}
	
	public GameObject getGameObject() {
		return this.gameObject;
	}

	private void setGameObject(GameObject gameObject) {
		this.gameObject = gameObject;
	}

	GameObject gameObject=null;

	@Override
	public GameObjectType getType() {
		return new GameObjectType(gameObject);
	}

	@Override
	public GameObjectType evaluateLegalCase(Program program) {
		return new GameObjectType(gameObject);
	}
}
