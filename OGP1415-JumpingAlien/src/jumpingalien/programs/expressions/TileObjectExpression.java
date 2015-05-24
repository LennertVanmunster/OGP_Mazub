package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.TileType;

public class TileObjectExpression extends Expression<TileType> {
	public TileObjectExpression(int [] tile, SourceLocation sourceLocation){
		super(sourceLocation);
		setTile(tile);;
	}
	
	public TileObjectExpression(int [] tile){
		this(tile, new SourceLocation(0,0));
	}
	
	/**
	 * @return the tile
	 */
	public int[] getTile() {
		return tile;
	}

	/**
	 * @param tile the tile to set
	 */
	public void setTile(int[] tile) {
		this.tile = tile;
	}

	private int [] tile;

	@Override
	public TileType getType() {
		return new TileType(getTile());
	}

	@Override
	public TileType evaluateLegalCase(Program program) {
		return new TileType(getTile());
	}
}
