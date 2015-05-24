package jumpingalien.programs.types;



public class TileType extends ObjectType<int[]>{

	public TileType(int[] tileArray) {
		super(tileArray);
	}
	
	public TileType(){
		this(new int[] {0,0});
	}
	
	@Override
	public int [] getDefaultValue(){
		int []defaultValue = {0,0};
		return defaultValue;
	}

}
