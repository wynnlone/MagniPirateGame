/**
 * @CMPT 125 Project2_V2
 *
 * @(#)Game_V2.java
 *
 * @ZHAO, Wenlong   301065774
 * @Hsuan, Jung Fu  301058647
 *
 * @version 2.00 2009/3/31
 */
 
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;


public class Game_V2 {
	Random generator=new Random();
	Scanner scan=new Scanner(System.in);
	private World_V2[] board;
	private Pirate_V2[] player;
	private int num_catastrophe;
	
	
    public Game_V2() {
    	creatBoard(2);
    	setWormholeGame();
    	setCatastrophe();
    	creatPlayer(2);
    }
    
    public void creatBoard(int num_world){
    	board=new World_V2[num_world];
    	
    	System.out.print("Enter the number of planets each world has: ");
    	int num_planet=scan.nextInt();

    	System.out.print("Enter the maximun number of magni each planet has: ");
    	int max_magni=scan.nextInt();
    	System.out.print("Enter the minimun number of magni each planet has: ");
    	int min_magni=scan.nextInt();
    	
    	System.out.print("Enter the maximun level of risk each planet has: ");
    	int max_risk=scan.nextInt();
    	System.out.print("Enter the minimun level of risk each planet has: ");
    	int min_risk=scan.nextInt();
    	
    	for(int code=0;code<num_world;code++){
    		board[code]=new World_V2(code,num_planet,max_magni, min_magni,max_risk, min_risk);
    	}
    }
    
    public void setWormholeGame(){
    	int num_wormhole,code_word,code_planet;
    	
    	do{
    		System.out.println("\nEnter the number of wormhole,");
    		System.out.println("the number should smaller than the total planets minus two start planets,");
    		System.out.print("which is "+(board[0].get_num_planet_world()-1)*2+": ");

    		num_wormhole=scan.nextInt();    			
    	}while(!(num_wormhole<((board[0].get_num_planet_world()-1)*2)));
    	
    	for(int i=0;i<num_wormhole;i++){
    		boolean worm_before=false;
    		do{
    			code_word=generator.nextInt(2);
    			code_planet=generator.nextInt(board[0].get_num_planet_world()-1)+1;
    			worm_before=board[code_word].get_wormhole_planet_world(code_planet);
    		}while(worm_before);
    		
    		board[code_word].setWormholeWorld(code_planet);
    	}
    }
    
    public void creatPlayer(int num_player){
    	player=new Pirate_V2[num_player];
    	for(int code=0;code<num_player;code++){
    		System.out.print("\nEnter the name for Pirate "+code+": ");
    		String name=scan.next();
    		
    		System.out.print("\nEnter the lives for Pirate "+code+": ");
    		int life=scan.nextInt();
    		    		
    		System.out.println("\nThe types of Pirate are defined as: \n");
    		System.out.println("0-Kind, get half magni, and lose lives.");
    		System.out.println("1-Aggressive, get all magni, and lose as twice lives as Kind.");
    		System.out.println("2-Ultra, get all magni, but lose half lives as Kind.\n");
    		System.out.print("So please choose the type for Pirate "+code+": ");
    		int type=scan.nextInt();
    		if(type==0){
    			player[code]=new Kind(code,name,life);
    		}else if(type==1){
    			player[code]=new Aggressive(code,name,life);
    		}else if(type==2){
    			player[code]=new Ultra(code,name,life);
    		}else{
    			System.out.println("\nNo such choice, assume 0-Kind.");
    			player[code]=new Kind(code,name,life);
    		}
    	}
    }
    
    public void move_jump_game(int code_pirate,int step){
    	
    	int location_world_pirate,location_planet_pirate,location_opposite_world_pirate,num_planet;
    	boolean wormhole_start,wormhole_end;
    	    	
    	location_world_pirate=player[code_pirate].get_location_world_pirate();
    	num_planet=board[location_world_pirate].get_num_planet_world();
    	player[code_pirate].move_pirate(step,num_planet);
    	
    	location_planet_pirate=player[code_pirate].get_location_planet_pirate();
    	
    	wormhole_start=board[location_world_pirate].get_wormhole_planet_world(location_planet_pirate);
    	location_opposite_world_pirate=player[code_pirate].get_location_opposite_world_pirate();
    	wormhole_end=board[location_opposite_world_pirate].get_wormhole_planet_world(location_planet_pirate);
    	player[code_pirate].jump_pirate(wormhole_start,wormhole_end);
    }
    
    public void search_game(int code_pirate){
    	
    	int location_world_pirate,location_planet_pirate,type_pirate,collected,risk,life_lost;
    	
    	location_world_pirate=player[code_pirate].get_location_world_pirate();
    	location_planet_pirate=player[code_pirate].get_location_planet_pirate();
    	type_pirate=player[code_pirate].get_type_pirate();
    	collected=board[location_world_pirate].searchWorld(location_planet_pirate,type_pirate);
    	
    	risk=board[location_world_pirate].get_risk_planet_world(location_planet_pirate);
    	player[code_pirate].addMagni(collected);
    	player[code_pirate].lostLife(risk);
    }
    
    public boolean check_over_game(){
    	boolean game_over=false;
    	for(int code=0; code<2; code++){
    		if(player[code].get_life_pirate()<=0){
    			System.out.println("And "+player[code].get_name_pirate()+" is dead!...");
    			game_over=true;
    		}
    	}
    	return game_over;
    }
        
    public void rob_game(int code0,int code1){
    	if(player[code0].get_location_world_pirate()==player[code1].get_location_world_pirate()){
    		if(player[code0].get_location_planet_pirate()==player[code1].get_location_planet_pirate()){
    			if(!(player[code0].get_type_pirate()==player[code1].get_type_pirate()&&player[code0].get_type_pirate()==0)){//Only Kind don't rob each other
    				System.out.println("\nRobbing!!!..."+player[code1].get_name_pirate()+" is robbed by"+player[code0].get_name_pirate()+"...");
    				int rob=player[code1].get_magni_pirate();
    				player[code0].addMagni(rob);
    				player[code1].addMagni(rob*(-1));    				
    			}
    		}
    	}
    	
    }
    
        public void fight_game(int code0,int code1){//for BONUS  Points 
    	if(player[code0].get_location_world_pirate()==player[code1].get_location_world_pirate()){
    		if(player[code0].get_location_planet_pirate()==player[code1].get_location_planet_pirate()){
    			if(!(player[code0].get_type_pirate()==player[code1].get_type_pirate()&&player[code0].get_type_pirate()==0)){//Only Kind don't fighting each other
    				System.out.println("\nFighting!!!..."+player[code1].get_name_pirate()+" is againsting with "+player[code0].get_name_pirate()+"...");
    				player[code0].lostLife(player[code0].get_life_pirate()/5);//the lives lost is 20% of it's lives times the risk function.
    				player[code1].lostLife(player[code1].get_life_pirate()/5);
    			}
    		}
    	}
    	
    }

    
    public void setCatastrophe(){
    	do{
    	System.out.println("\nEnter the maxinum number of Catastrophes, ");
    	System.out.println("the number should smaller than the number of planets in each world ,");
    	System.out.print("which is "+board[0].get_num_planet_world()+": ");
    	num_catastrophe=scan.nextInt();
    	}while(!(num_catastrophe<board[0].get_num_planet_world()));

    }
        
    public void catastrophe(){
    	int happen=generator.nextInt(4);
    	if (happen==1&&num_catastrophe>0){
    		int location_planet=generator.nextInt(board[0].get_num_planet_world()-1)+1;
    		System.out.println("\nCatastrophe!!!...Planet"+location_planet+" have been destoried in both worlds.\n");
    		
    		for (int i=0;i<2;i++){
    			player[i].addCatastrophePath_pirate();
    			player[i].catastrophe(location_planet);
    			board[i].catastrophe(location_planet);
    			board[i].reLocatePlanet();
    			player[i].addPath_pirate();
    		}
    		
    		num_catastrophe=num_catastrophe-1;
    	}
    }
        
    public void addPath_game(int code_pirate){
    	player[code_pirate].addPath_pirate();    	
    }
    
    public void print_board_game(){
    	for(int i=0;i<board.length;i++){
    		World_V2 world=board[i];
    		world.print_world();
    	}
    }
    
    public void print_player_game(){
    	for(int i=0;i<player.length;i++){
    		player[i].print_pirate();
    	}
    }
    
    public void print_winner_game(){
    	if(player[0].get_life_pirate()<=0&&player[1].get_life_pirate()<=0){
    		System.out.println("Both player are dead, no winer.");
    	}
    	else if (player[0].get_life_pirate()>0&&player[1].get_life_pirate()<=0){
    		System.out.println(player[0].get_name_pirate()+" is still survived while "+player[1].get_name_pirate()+" is dead.");
    		System.out.println("Therefore the winner is: "+player[0].get_name_pirate());
    		player[0].print_pirate();
    	} 			
    	else if (player[1].get_life_pirate()>0&&player[0].get_life_pirate()<=0){
    		System.out.println(player[1].get_name_pirate()+" is still survived while "+player[0].get_name_pirate()+" is dead.");
    		System.out.println("Therefore the winner is: "+player[1].get_name_pirate());
    		player[1].print_pirate();
    	}
    	else{
    		System.out.print("Both are survived,");
    		System.out.println("so the winner is based on the magni rocket collected, ");
    		
    		if(player[0].get_magni_pirate()>player[1].get_magni_pirate()){
    			System.out.println("and the winner is: "+player[0].get_name_pirate());
    			player[0].print_pirate();
    		}
    		else if(player[1].get_magni_pirate()>player[0].get_magni_pirate()){
    			System.out.println("and the winner is: "+player[1].get_name_pirate());
    			player[1].print_pirate();
    		}
    		else{
    			System.out.println(" and the number of magni collected is the same, so it's a tie.");
    		}	
    	}
    }
    
    
    
    
}