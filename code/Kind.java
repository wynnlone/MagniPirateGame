/**
 * @CMPT 125 Project2_V2
 *
 * @(#)Kind.java
 *
 * @ZHAO, Wenlong   301065774
 * @Hsuan, Jung Fu  301058647
 *
 * @version 2.00 2009/3/31
 */
 
public class Kind extends Pirate_V2 {
    
    public Kind(int code,String name,int life) {
    	super(code,name,life);
    	type_pirate=0;
    }
    
    public int get_type_pirate(){
    	return type_pirate;
    }
    
    public void lostLife(int risk){
    life_pirate= get_life_pirate()-risk;
    System.out.println(name_pirate+" lost "+risk+" lives.");
    }
    
    public void print_pirate(){
    	System.out.println("\nHere is the state for "+get_name_pirate()+", Kind,\n ");
    	System.out.println("is now in World"+get_location_world_pirate()+", Planet"+get_location_planet_pirate()+", has "+get_magni_pirate()+" magni rocks, and "+get_life_pirate()+" lives left.");
    	System.out.println("\nHere is the path the player has gone through: ");
    	System.out.print("[");
    	for(int i=0;i<path_pirate.size();i++){
    		String location=path_pirate.get(i);
    		System.out.print(location);
    		System.out.print(",");
    	}
    	System.out.println("...]\n");
    }
}