import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

import java.util.Scanner;

public class Game {
    private int roll = 0;
    private int[] rolls = new int[21]; //Potential Max Rolls = 21 (Note: Upon reflection, using ArrayList might have been better in this situation.

    public void roll(int...rolls){
        for(int knockedDown : rolls){
            if(knockedDown < 0 || knockedDown >10){
                System.out.println("Game Failed: No negative numbers or anything that exceeds 10");
            }
            else{
                setRoll(knockedDown);
            }
        }
    }
    public void setRoll(int knockedDown){
        if(rolls.length < 0){
            System.out.println("Test Failed: Please only include numbers between 0-10");
        }else{
            rolls[roll++] = knockedDown;
        }
    }

    public int[] getRoll(){
        return rolls;
    }
    public int score(){
        int score = 0;
        int pointer  = 0; //keeps track of the frames

        if(rolls.length < 10){
            System.out.println("Invalid input: There hasn't been enough rolls made yet.");
        }
        for(int frame = 0; frame < 10; frame++){
            if(isStrike(pointer)){
                //Strike Case
                score += 10 + rolls[pointer+1] + rolls[pointer+2];
                pointer++;  //increment pointer
            } else if (isSpare(pointer)){
                score += 10 +rolls[pointer +2];
                pointer+= 2;

            }else{
                score += rolls[pointer] + rolls[pointer+1];
                pointer+=2;
            }
        }
        return score;
    }

    private boolean isSpare(int pointer) {
        return rolls[pointer] + rolls[pointer+1] == 10;
    }
    //Checks if the roll is a strike.
    private boolean isStrike(int pointer) {
        return rolls[pointer] == 10;
    }

    //The main method will run all test cases inside class TestCases.java ensuring the methods are being tested properly.
    public static void main(String[] args) {

        int choice;
        //Get User Choice
        System.out.println("Please enter the following:" + "\n"+
                "1. Run Tests on test case"+  "\n" +
                "2. Input your own bowling scores (Play a game)");

        Scanner console = new Scanner(System.in);

        try{
            choice = console.nextInt();
            switch (choice){
                case 1:
                    //System.out.print("\033\143"); //Clear Screen
                    JUnitCore junit = new JUnitCore();
                    junit.addListener(new TextListener(System.out));
                    junit.run(TestCases.class);
                    break;
                case 2:
                    TestCases x = new TestCases();
                    System.out.println("Play a game!");
                    x.UserInput();
                    break;
                default:
                    System.out.println("Invalid Input");
                    break;
            }

        } catch (Exception e) {
            System.out.println("Invalid Input, please enter an integer instead.");
            e.printStackTrace();
        }

    }
}
