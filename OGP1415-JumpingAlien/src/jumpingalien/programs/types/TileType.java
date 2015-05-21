package jumpingalien.programs.types;

import java.util.ArrayList;
import java.util.List;


public class TileType extends ObjectType<int[]>{

	public TileType(int[] tileArray) {
		super(tileArray);
	}
	
	public int [] getDefaultValue(){
//		List<Integer> defaultValue= new ArrayList<Integer>();
//		defaultValue.add(0);
//		defaultValue.add(0);
		int []defaultValue = {0,0};
		return defaultValue;
	}

}
