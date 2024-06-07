/**
 * @CMPT 125 Project2_V2
 *
 * @(#)World_V2.java
 *
 * @ZHAO, Wenlong   301065774
 * @Hsuan, Jung Fu  301058647
 *
 * @version 2.00 2009/3/31
 */
 
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class World_V2{
	Random generator=new Random();
	Scanner scan=new Scanner(System.in);
	private int code_world;
	private int num_planet_world;
	private String name_world;
	private ArrayList<Planet_V2> world= new ArrayList<Planet_V2>();

    public World_V2(int code, int num_planet,int max_magni,int min_magni,int max_risk,int min_risk) {
    	num_planet_world=num_planet;
    	code_world=code;
    	name_world="World"+code;
    	addPlanet(num_planet,max_magni,min_magni,max_risk,min_risk);    	
    }
    
    public void addPlanet(int num_planet,int max_magni,int min_magni,int max_risk,int min_risk){
    	Planet_V2 earth=new Planet_V2(0,0,0);
    	world.add(earth);
    	
    	int num_magni;
    	int num_risk;
    	   	
    	for(int i=1;i<num_planet;i++){
    		num_magni = generator.nextInt(max_magni-min_magni)+min_magni;
    	    num_risk = generator.nextInt(max_risk-min_risk)+min_risk;

    		Planet_V2 planet=new Planet_V2(i,num_magni,num_risk);
    		world.add(planet);
    	}
    }
    
    public void setWormholeWorld(int code_planet){
    	Planet_V2 planet=world.get(code_planet);
    	planet.setWormholePlanet();
    }
    
    public int get_code_world(){
    	return code_world;
    }
    
    public String get_name_world(){
    	return name_world;
    }
    
    public int get_num_planet_world(){
    	num_planet_world=world.size();
    	return num_planet_world;
    }   
    public int get_risk_planet_world(int loaction_planet){
    	Planet_V2 planet=world.get(loaction_planet);
    	return planet.get_risk_planet();
    }
    
    public boolean get_wormhole_planet_world(int location_planet){
    	Planet_V2 planet=world.get(location_planet);
    	return planet.get_wormhole_planet();
    }

    public int searchWorld(int location_planet,int type_pirate){
    	int collected=0;
    	Planet_V2 planet=world.get(location_planet);
    	collected=planet.searchPlanet(type_pirate);
    	System.out.println("Plante"+location_planet+" in "+get_name_world()+" has been searched.");
   		return collected;
    }
    
    public void reLocatePlanet(){
    	for(int location=0;location<world.size();location++){
    		Planet_V2 planet=world.get(location);
    		planet.changeLocation(location);
    	}
    }
        
    public void catastrophe(int location_planet){
    	world.remove(location_planet);
    }
    
    public void print_world(){
    	System.out.println("\nWorld"+get_code_world()+" currently has the following trail\n");
    	for(int i=0;i<world.size();i++){
    		Planet_V2 planet=world.get(i);
    		planet.print_planet();    		
    	}
    	System.out.println();
    }

}
