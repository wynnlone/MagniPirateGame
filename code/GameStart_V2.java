/**
 * @CMPT 125 Project2_V2
 *
 * @(#)GameStart_V2.java
 *
 * @ZHAO, Wenlong   301065774
 * @Hsuan, Jung Fu  301058647
 *
 * @version 2.00 2009/3/31
 */
 
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class GameStart_V2 {
	Scanner scan = new Scanner(System.in);
	Random generator=new Random();
	private int die;
	private int turn;
	private boolean gameover;
	private Game_V2 game;
	
    public GameStart_V2() {
    	game=new Game_V2();
    	gameover=false;
    	die=0;
    	System.out.print("\nAnd how many turns in this game?: ");//One Player plays once count as one turn.
    	turn=scan.nextInt();
    }
    
    public static void main(String[] args) {
    	int playagain=0;
    	Scanner scan2 = new Scanner(System.in);
        System.out.println("Welcome to Magni rocks in outer space game!!!\n");
        
        while(playagain==0){
        	System.out.println("Please set up the game:\n");
        	GameStart_V2 run=new GameStart_V2();        	
        	run.all();        	
        	System.out.print("\nWould you like to play again? 0-Yes, 1-No:");
        	playagain=scan2.nextInt();
        }
        System.out.print("You have chosen to exit, bye-bye!");
    }
    
    public void all(){
        while(turn>0&&!gameover){
            System.out.println("\nHere is the the new boards before next move:");
            game.print_board_game();
            game.print_player_game();
            System.out.print("Press any button, then Enter to roll die: ");
            String c=scan.next();
            System.out.println("--------------------------------------------------------------------");
            die=generator.nextInt(13)-6;//a number between -6 and 6
            System.out.println("\nThe die is "+die+".");
            System.out.println("It's Pirate"+turn%2+"'s turn...");
            
            game.move_jump_game(turn%2,die);
            gameover=game.check_over_game();
            if(gameover){//if game over, end of while loop             	
            }else{
            game.search_game(turn%2);
            gameover=game.check_over_game();
            if(gameover){//if game over, end of while loop           	
            }else{
            game.addPath_game(turn%2);
            game.rob_game(turn%2,(turn+1)%2);
            game.fight_game(turn%2,(turn+1)%2);
            gameover=game.check_over_game();
            if(gameover){//if game over, end of while loop           	
            }else{
            game.catastrophe();
            gameover=game.check_over_game();
            }}}
        

            turn--;
            System.out.println("\nThere are "+turn+" turns left/unplayed.");
    	}
    	System.out.println("\nThe game is over. Here is the board.");
        game.print_board_game();
        System.out.println("\nHere is the players.");
        game.print_player_game();
        System.out.println("\nHere is the reasult.");
    	game.print_winner_game();    	
    }
}
