package jumpingalien.model;

import java.util.HashSet;
import java.util.Set;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of schools. School are groups of slimes.
 * @invar	Each school has proper slimes.
 * 			|hasProperSlimes()
 * @author 	Lennert Vanmunster & Pieter Van Damme
 * @version	1.0
 *
 */

public class School {
	/**
	 * Create a new school with the given slimes as its slimes.
	 * @param 	slimes
	 * 			The slimes to be set as the slimes of this school.
	 * @post	The new school has all given slimes as its slimes and the school of all given slimes is this school.
	 * 			|for(slime in slimes)
	 * 			|	new.hasAsSlime(slime)==true
	 * 			|	slime.getSchool()==this
	 * @throws	IllegalArgumentException
	 * 			At least one of the slimes cannot be added to this school.
	 * 			|(!this.canHaveAsSlime(slime) || slime.getSchool()!=null)
	 */
	@Raw
	public School(Slime... slimes) throws IllegalArgumentException{
		for(Slime slime: slimes){
			this.addAsSlime(slime);
		}
	}
	
	/**
	 * Create a school with an empty set of slimes.
	 */
	public School(){
	}
	
	/**
	 * Set collecting the references to the slimes attached to this school
	 * @invar	Each element in the set of slimes references a slime that is an acceptable slime for this school.
	 * 			| for each slime in slimes:
	 * 			|	this.canHaveAsSlime(slime)
	 * @invar	Each slime in the set of slimes references this school as the school to which it is attached.
	 * 			| for each slime in slimes:
	 * 			| 	slime.getSchool()==this
	 */
	private Set<Slime> slimes= new HashSet<Slime>();
	
	/**
	 * Check whether this school has the given slime as one of its slimes.
	 * @param 	slime
	 * 			The slime to be checked.
	 * @return	True if the set of slimes of this world contains the given slime.
	 * 			|result==this.slimes.contains(slime)
	 */
	@Basic
	@Raw
	public boolean hasAsSlime(Slime slime){
		return this.slimes.contains(slime);
	}
	
	/**
	 * Check whether this school can have the given slime as one of its slimes.
	 * @param 	slime
	 * 			The slime to be checked.
	 * @return	If the slime is null, the result is false
	 * 			|if(slime==null)
	 * 			|	result==false
	 * 			Otherwise if the current number of slimes of this school is a valid number of slimes and this school is not terminated or the given slime is terminated, the result is true.
	 * 			|else
	 * 			|	result==((!this.isTerminated() || slime.isTerminated()) && this.isValidNbSlimes(this.getNbSlimes()))
	 */
	public boolean canHaveAsSlime(Slime slime){
		if(slime==null){
			return false;
		}
		else{
			return ((!this.isTerminated() || slime.isTerminated()) && this.isValidNbSlimes(this.getNbSlimes()));
		}
	}
	
	/**
	 * Check whether the given number of slimes is a valid number of slimes for all schools.
	 * @param nbSlimes
	 * 			The number of slimes must be greater than or equal to zero.
	 * @return	nbSlimes>=0.
	 */
	@Raw
	public boolean isValidNbSlimes(int nbSlimes){
		return nbSlimes>=0;
	}
	
	/**
	 * Check whether this school has proper slimes attached to it.
	 * @return	True if and only if this school can have each of its slimes as a slime attached to it, and
	 * 			if each of these slimes references this school as their school.
	 * 			|result==
	 * 			|	for each slime in slimes):
	 * 			|		(if (this.hasAsSlime(slime))
	 * 			|			then canHaveAsSlime(slime) && slime.getSchool()==this)
	 */
	public boolean hasProperSlimes(){
		for(Slime slime: this.slimes){
			if (!canHaveAsSlime(slime))
				return false;
			if (slime.getSchool()!=this)
				return false;
		}
		return true;
	}
	
	/**
	 * Add the given slime to the set of slimes attached to this school.
	 * @param 	slime
	 * 			The slime to be added.
	 * @post	This school has the given slime as one of its slimes.
	 * 			|new.hasAsSlime(slime)==true
	 * @post	The given slime references this school as the school to which it is attached.
	 * 			|(new slime).getSchool()==this
	 * @throws	IllegalArgumentException
	 * 			The school cannot have this slime as one of its slimes or the given slime already references another school.
	 * 			|!this.canHaveAsSlime(slime) || slime.getSchool()!=null
	 */
	public void addAsSlime(Slime slime){
		if (!this.canHaveAsSlime(slime) || slime.getSchool()!=null){
			throw new IllegalArgumentException();
		}
		this.slimes.add(slime);
		slime.setSchool(this);
	}
	
	/**
	 * Remove the given slime from the set of slimes attached to this school.
	 * @param 	slime
	 * 			The slime to be removed.
	 * @post	This school does not have the given slime as one of its slimes.
	 * 			|new.hasAsSlime(slime)==false
	 * @post	If this school has the given slime as one of its slimes, the given slime
	 * 			is no longer attached to any school.
	 * 			| if (this.hasAsSlime(slime)):
	 * 			|	(new slime).getSchool()==null
	 */
	public void removeAsSlime(Slime slime){
		if(this.hasAsSlime(slime)){
			this.slimes.remove(slime);
			slime.setSchool(null);
		}
	}
	
	/**
	 * Return the number of slimes attached to this school.
	 */
	@Basic
	@Raw
	public int getNbSlimes(){
		return slimes.size();
	}
	
	/**
	 * Return a copy of the set of slimes of this school.
	 */
	@Basic
	@Raw
	public Set<Slime> getAllSlimes(){
		Set<Slime> allSlimes= new HashSet<Slime>(this.slimes);
		return allSlimes;
	}
	
	/**
	 * Terminate this school.
	 * @post	Each slime in the set of slimes attached to this school is no longer attached to any school.
	 * 			| for each slime in slimes:
	 * 			|	(new slime).getSchool()==null
	 * @post	The number of slimes this school references is zero.
	 * 			| new.getNbSlimes()==0
	 * @post	This school is terminated
	 * 			|this.isTerminated()==true
	 */
	public void terminate(){
		for(Slime slime: this.slimes){
			slime.setSchool(null);
		}
		this.slimes.clear();
		this.isTerminated=true;
	}
	
	/**
	 * Return the terminated state of this school.
	 */
	public boolean isTerminated(){
		return this.isTerminated;
	}
	
	/**
	 * Variable storing the terminated state of this school.
	 */
	private boolean isTerminated=false;
}
