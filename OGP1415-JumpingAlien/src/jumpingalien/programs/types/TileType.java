package jumpingalien.programs.types;

import java.util.ArrayList;
import java.util.List;


public class TileType extends ObjectType<List<Integer>>{

	public TileType(List<Integer> tilePosition) {
		super(tilePosition);
	}
	
	public List<Integer> getDefaultValue(){
		List<Integer> defaultValue= new ArrayList<Integer>();
		defaultValue.add(0);
		defaultValue.add(0);
		return defaultValue;
	}

}
