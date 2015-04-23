package jumpingalien.model;

import java.util.HashSet;
import java.util.Set;

public class School {
	public School(){
	}
	
	private Set<Slime> slimes= new HashSet<Slime>();
	
	public boolean hasAsSlime(Slime slime){
		return this.slimes.contains(slime);
	}
	
	public boolean canHaveAsSlime(Slime slime){
		return((this.getNbSlimes()<10 && slime != null && !this.isTerminated()) || (slime.isTerminated() && slime==null));
	}
	
	public boolean hasProperSlimes(){
		for(Slime slime: this.slimes){
			if (!canHaveAsSlime(slime))
				return false;
			if (slime.getSchool()!=this)
				return false;
		}
		return true;
	}
	
	public void addAsSlime(Slime slime){
		if (!this.canHaveAsSlime(slime) || slime.getSchool()!=null){
			throw new IllegalArgumentException();
		}
		this.slimes.add(slime);
		slime.setSchool(this);
	}
	
	public void removeAsSlime(Slime slime){
		if(this.hasAsSlime(slime)){
			this.slimes.remove(slime);
			slime.setSchool(null);
		}
	}
	
	public int getNbSlimes(){
		return slimes.size();
	}
	
	public void terminate(){
		for(Slime slime: this.slimes){
			slime.setSchool(null);
		}
		this.slimes.clear();
		this.isTerminated=true;
	}
	
	public boolean isTerminated(){
		return this.isTerminated;
	}
	
	private boolean isTerminated=false;
}
