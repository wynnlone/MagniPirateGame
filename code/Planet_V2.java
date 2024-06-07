/**
 * @CMPT 125 Project2_V2
 *
 * @(#)Planet_V2.java
 *
 * @ZHAO, Wenlong   301065774
 * @Hsuan, Jung Fu  301058647
 *
 * @version 2.00 2009/3/31
 */
public class Planet_V2 {
	private String name_planet;
	private int location_planet;
	private int magni_planet;
	private int risk_planet;
	private boolean search_planet;
	private boolean wormhole_planet;
	
	
	public Planet_V2(int code, int magni, int risk) {
    	location_planet=code;
    	magni_planet=magni;
    	risk_planet=risk;
    	search_planet=false;
    	wormhole_planet=false;
    	
    }
        
    public String get_name_planet(){
    	return name_planet;
    }
    
    public int get_location_planet(){
    	return location_planet;
    }
    
    public int get_magni_planet(){
    	return magni_planet;
    }
        
    public int get_risk_planet(){
    	return risk_planet;
    }
    
    public boolean get_wormhole_planet(){
    	return wormhole_planet;
    }
    
    public boolean get_search_planet(){
    	return search_planet;
    }
    
    public void changeLocation(int location){
    	location_planet=location;
    }
    
    public int searchPlanet(int type_pirate){
    	int collected=0;
    	    	
   		if(search_planet==false){
   			search_planet=true;
   			   			
   			if(type_pirate==0){
   				collected=magni_planet/2;
    		 }
    		else{
    			collected=magni_planet;
    		}
   		}
   		
   		magni_planet=magni_planet-collected;
   		return collected;
    }
    
    public void setWormholePlanet(){
    	wormhole_planet=true;
    }
    
    public void print_planet(){
    	System.out.println(" Planet"+get_location_planet()+"\t has "+get_magni_planet()+"\t magni rocks, risk level: "+get_risk_planet()+
    		",\t and wormhole: "+get_wormhole_planet()+",\t and searched by any Pirate: "+get_search_planet()+".");
    }

    
}