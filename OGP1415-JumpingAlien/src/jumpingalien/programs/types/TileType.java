package jumpingalien.programs.types;



public class TileType extends ObjectType<int[]>{

	public TileType(int[] tileArray) {
		super(tileArray);
	}
	
	public TileType(){
		this(new int[] {0,0});
	}
	
	public int [] getDefaultValue(){
//		List<Integer> defaultValue= new ArrayList<Integer>();
//		defaultValue.add(0);
//		defaultValue.add(0);
		int []defaultValue = {0,0};
		return defaultValue;
	}

}
