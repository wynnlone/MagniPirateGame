/**
 * @CMPT 125 Project2_V2
 *
 * @(#)Pirate_V2.java
 *
 * @ZHAO, Wenlong   301065774
 * @Hsuan, Jung Fu  301058647
 *
 * @version 2.00 2009/3/31
 */
 

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Pirate_V2 {
	Scanner scan=new Scanner(System.in);
	protected int code_pirate;
	protected String name_pirate;
	protected int life_pirate;
	protected int location_world_pirate;
	protected int location_planet_pirate;
	protected int code_planet_pirate;
	protected int magni_pirate;
	protected ArrayList<String> path_pirate;
	protected int type_pirate;
	
    public Pirate_V2(int code,String name,int life) {
    	code_pirate=code;
    	name_pirate=name;
    	life_pirate=life;
    	location_world_pirate=0;
    	location_planet_pirate=0;
    	magni_pirate=0;
    	path_pirate=new ArrayList<String>();
    	addPath_pirate();
    }
    
    public int get_code_pirate(){
    	return code_pirate;
    }

    public String get_name_pirate(){
    	return name_pirate;
    }
        
    public int get_life_pirate(){
    	return life_pirate;
    }
    
    public abstract int get_type_pirate();
    
    public int get_magni_pirate(){
    	return magni_pirate;
    }
    
    public int get_location_world_pirate(){
    	return location_world_pirate;
    }
    
    public int get_location_opposite_world_pirate(){
    	int location_opposite_world_pirate;
    	if(location_world_pirate==0){
    		location_opposite_world_pirate=1;
    	}
    	else{
    		location_opposite_world_pirate=0;
    	}
    	return location_opposite_world_pirate;
    }
    
    public int get_location_planet_pirate(){
    	return location_planet_pirate;
    }
            
    public void addMagni(int collected){
    	if(collected>0)
    		System.out.println(name_pirate+" got "+collected+" magni.");
    	else if (collected==0)
    		System.out.println(name_pirate+" got nothing.");
    	else
    		System.out.println(name_pirate+" lost "+collected*(-1)+" magni.");
    	magni_pirate=magni_pirate+collected;
    }
    
    public abstract void lostLife(int risk);
    
    public void move_pirate(int step,int num_planet){
    	int new_location=location_planet_pirate+step;
    	if(0<=new_location&&new_location<num_planet){
    		changeLocationPlanet(new_location);
    	}
    	else if(new_location>=num_planet){
    		move_pirate(step-num_planet,num_planet);
    	}
    	else{
    		move_pirate(step+num_planet,num_planet);
    	}
    }
    
    public void jump_pirate(boolean wormhole_start,boolean wormhole_end){
    	if( wormhole_start&&wormhole_end){
    		life_pirate=0;
    		System.out.println(name_pirate+" get trapped in wormhole!!!...");
    		path_pirate.add("Trapped");
    		location_world_pirate=-1;
    		location_planet_pirate=-1;
    		addPath_pirate();
    	}
    	else if(wormhole_start&&!wormhole_end){
    		changeWorld();
    		System.out.println(name_pirate+" has arrived Planet"+get_location_planet_pirate()+" in World"+get_location_world_pirate());
    	}
    }
    
    public void changeLocationPlanet(int new_location_planet){
    	location_planet_pirate=new_location_planet;
    	System.out.println("\n"+name_pirate+" has arrived Planet"+get_location_planet_pirate()+" in World"+get_location_world_pirate());
    }
    
    
    public void changeWorld(){
    	System.out.println(name_pirate+" jumped from World"+location_world_pirate+" to World"+(location_world_pirate+1)%2+" through a wormhole.");

    	location_world_pirate=(location_world_pirate+1)%2;
  }
    
    public void addPath_pirate(){
    	path_pirate.add("W"+get_location_world_pirate()+"P"+get_location_planet_pirate());
    }
    
    public void catastrophe(int location_planet){
    	if(location_planet==location_planet_pirate){
    		life_pirate=0;
    		System.out.println(name_pirate+" happend to meet the catastrophe.");
    		path_pirate.add("Destroied");
    		location_world_pirate=-1;
    		location_planet_pirate=-1;
    	}
    	else if(location_planet<location_planet_pirate){
    		location_planet_pirate=location_planet_pirate-1;
    	}
    	else if(location_planet<location_planet_pirate){
    		
    	}
    }
    
    public void addCatastrophePath_pirate(){
    	path_pirate.add("****");
    }
        
    public abstract void print_pirate();
    
}