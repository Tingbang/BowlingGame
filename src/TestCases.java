//Importing junit testing framework
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.Scanner;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


public class TestCases {

    //Initial game instance
    private Game game;

    @Before //Before annotation is used here to ensure a new instance is generated before each test case.
    public void setUp(){
         game = new Game();
    }

    @Test
    public void GutterGame(){

        game.roll(0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0);
        assertThat(game.score(), is(0));    //assertThat -  this asserts that
        System.out.println("Testcase 1 - Bowl all 0's  - Score: " + game.score() +
                " Game ScoreBoard: " + (Arrays.toString(game.getRoll()))+ " Scorecard: " + game.score());
    }
    @Test
    public void AllOnes(){
        //A test case that states that the player got 1s for 10 frames.
        game.roll(1,1, 1,1, 1,1, 1,1, 1,1,1,1, 1,1, 1,1, 1,1, 1,1);
        assertThat(game.score(), is(20)); //Check that the expected output is 38
        System.out.println("Testcase 2 - Bowl all 1's  - Score: " + game.score() +
                " Game ScoreBoard: " + (Arrays.toString(game.getRoll()))+ " Scorecard: " + game.score());
    }

    @Test
    public void ManyNum(){
        game.roll(5,5, 1,1, 5,5, 1,1, 1,1,1,1, 1,1, 1,1, 1,1, 1,1);
        assertThat(game.score(), is(38)); //Check that the expected output is 38
        System.out.println("Testcase 3 - A normal game -" +
                " Game ScoreBoard: " + (Arrays.toString(game.getRoll()))+ " Scorecard: " + game.score());
    }
    @Test
    public void Negatives(){
        //Won't break the game it will throw an error message.
        game.roll(10,0, 1,-1, 5,5, 1,1, 1,1, 1,1, 1,1, 1,1, 1,1, 1,1);
        assertThat(game.score(), is(37));
        System.out.println("Testcase 4 - Checking for negatives - " +
                " Game ScoreBoard: " + (Arrays.toString(game.getRoll()))+ " Scorecard: " + game.score());
    }
    @Test
    public void PerfectGame(){
        game.roll(10,10,10,10,10,10,10,10,10,10,10,10); // 12 as there is 2 bonus balls
        assertThat(game.score(), is(300));
        System.out.println("Testcase 5 - The Perfect Game -  " +
                " Game ScoreBoard: " + (Arrays.toString(game.getRoll()))+ " Scorecard: " + game.score());
        //System.out.println("Game ScoreBoard: " + (Arrays.toString(game.getRoll()))+ "");
    }

    @Test
    public void Blanks(){
        //Game game = new Game();
        //A test case that states that only partiallys  the array. Sinze Java's Arrays are of a fixed size it will automatically.
        //populate the rest of the array with 0's
        game.roll(10,0, 1,9);
        System.out.println("Testcase 6 - No full game played - " +
                " Game ScoreBoard: " + (Arrays.toString(game.getRoll()))+ " Scorecard: " + game.score());

    }

    //Fundamentally this function works as intended
    //Needs to be refactored to display scores propery
    //It calculates the correct scores but doesn't account for
    //not all games are gonna take 21 potential shots.
    //The application could be revised to reflect this.
    public void UserInput(){
        //Scanner input = new Scanner(System.in);
        Game game = new Game();
        int s = 0;
        int[] tmp_roll = new int[21]; //Potential Max Rolls = 21
        System.out.println("Please enter your score for the 1st frame");
        //Loop 10 Times (2xPer Frame unless they score a strike
        //Take 2 Inputs
        //Check if is strike or spare.
        do{
            Scanner input = new Scanner(System.in);
            if(input.hasNextLine()){
                int user_input = Integer.parseInt(input.next());

                if(user_input < 0 || user_input > 10){
                    System.out.println("Please enter a valid score between 0-10");
                     input.next();
                }else{
                    game.roll(user_input);
                    System.out.println("You Rolled a: " + user_input);
                    System.out.println("Scoreboard" + Arrays.toString(game.getRoll()));
                    System.out.println("Current Score is:" + game.score());

                }
            }

            s++;

        }
        while(s != 21);

        if(s == 21){
            System.out.println("Final Score: " + game.score());
        }
    }


}
