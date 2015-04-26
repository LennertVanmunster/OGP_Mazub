package jumpingalien.model;

import java.util.HashSet;
import java.util.Set;

public class School {
	public School(Slime... slimes){
		for(Slime slime: slimes){
			this.addAsSlime(slime);
			slime.setSchool(this);
		}
	}
	
	private Set<Slime> slimes= new HashSet<Slime>();
	
	public boolean hasAsSlime(Slime slime){
		return this.slimes.contains(slime);
	}
	
	public boolean canHaveAsSlime(Slime slime){
		return((this.isValidNbSlimes(this.getNbSlimes()) && slime != null && !this.isTerminated()) || (this.isTerminated() && slime==null));
	}
	
	public boolean isValidNbSlimes(int nbSlimes){
		return true;
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
		if (!this.canHaveAsSlime(slime)){
			throw new IllegalArgumentException();
		}
		this.slimes.add(slime);
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
	
	public Set<Slime> getAllSlimes(){
		Set<Slime> allSlimes= new HashSet<Slime>(this.slimes);
		return allSlimes;
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
