package jumpingalien.programs.types;

import jumpingalien.model.GameObject;

public class GameObjectType extends ObjectType<GameObject> {
	public GameObjectType(GameObject value){
		super(value);
	}
	
	public GameObjectType(){
		this(null);
	}
	
	public GameObject getDefaultValue(){
		return null;
	}

}