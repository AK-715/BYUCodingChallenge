import java.util.*;
import javax.swing.*;


public class Hangman extends JPanel{

    private  ArrayList<String> words = new ArrayList<String>(Arrays.asList("phone", "mouse", "computer", "headphones", "camera", "engineer", "developer", "code", "outlet", "browser"));;
    private  ArrayList<Character> guesses;
    private  boolean result;
    private  boolean game;
    private  String word;



    public static void main(String[] args){
        System.out.println(" ■■■   ■■■     ■■    ■■■    ■     ■■■■■      ■■■    ■■■     ■■    ■■■    ■");
        System.out.println("  ■     ■      ■■     ■■    ■    ■    ■       ■■    ■■      ■■     ■■    ■");
        System.out.println("  ■     ■     ■ ■     ■ ■   ■   ■■            ■■■  ■■■     ■ ■     ■ ■   ■");
        System.out.println("  ■     ■     ■ ■■    ■ ■■  ■   ■             ■ ■  ■ ■     ■ ■■    ■ ■■  ■");
        System.out.println("  ■■■■■■■    ■■  ■    ■  ■  ■   ■             ■ ■■■  ■    ■■  ■    ■  ■  ■");
        System.out.println("  ■     ■    ■   ■    ■  ■■ ■   ■     ■■      ■  ■■  ■    ■   ■    ■  ■■ ■");
        System.out.println("  ■     ■    ■■■■■■   ■   ■■■   ■■    ■       ■  ■   ■    ■■■■■■   ■   ■■■");
        System.out.println("  ■     ■   ■■    ■   ■    ■■    ■    ■       ■      ■   ■■    ■   ■    ■■");
        System.out.println(" ■■■   ■■■ ■■■   ■■■ ■■■   ■■     ■■■■■      ■■■    ■■■ ■■■   ■■■ ■■■   ■■");

        Hangman ng = new Hangman();
        ng.NewGame();           //Start game
    }

    public void NewGame(){
        System.out.println("Welcome to HANG MAN!");     //greet, instantiate variables
        System.out.println("Guess the word!");
        guesses = new ArrayList<>();
        result = false;
        game = false;
        Random rand = new Random();
        word = words.get(rand.nextInt(10));     //generate random number between 0 - 9, and pick a word from List
        for (int i = 0; i < word.length(); i++){
            System.out.print("-");
        }
        GameRun(guesses, word, result, game);           //send parameters to GameRun function
    }


    public void GameRun(ArrayList<Character> guesses, String word, boolean result, boolean game){
        while(true){                            // loop until the game is done. 
            PlayerGuess(guesses);
            result = PlayerGuess(word, guesses);
            if (result == true){
                game = true;
                break;
            }
            }
    
        if(game == true){                           
            System.out.println("Play again? (\"yes\" or \"no\")" );
            Scanner keyboard = new Scanner(System.in);                  //Ask players if continue or not. 
            String cont = keyboard.nextLine();
            if(cont.contains("yes")){                               // if yes, then call NewGame again
                NewGame();
            } else{
                System.out.println("Tank You For Playing!");
            }
        }

    }

    public void PlayerGuess(ArrayList<Character> guesses){      //Ask players to imput from keyboard
        System.out.println("\nPlease enter a letter!");
        Scanner keyboard = new Scanner(System.in);          
        String answer = keyboard.next();
        guesses.add(answer.charAt(0));                  //Incase of the player imputs multiple letters, the program gets only one letter at the top.
    }

    public static boolean PlayerGuess(String word, ArrayList<Character> guesses){
        int correct = 0;
        int check = 0;
        int incorrect = 0;
        int right = 0;
        ArrayList<Character> incorrectChars = new ArrayList<>();
        for (int i = 0; i < word.length(); i++){                    //if the collection of letters the player imputs has a correct letter, then show them.
            if (guesses.contains(word.charAt(i))){
                System.out.print(word.charAt(i));
                correct ++;
            }
            else{
            System.out.print("-");
            }
        }
        for(char c : guesses){                          //count incorrect answers
            check = 0;
            for(int i = 0; i < word.length(); i++){
                if (c == word.charAt(i)){
                    check = 0;
                    break;
                }else{
                    check++;
                }
            }
            if(check > 0){
                incorrectChars.add(c);
                incorrect ++;
            }

        }
        System.out.print("\nIncorrect Guesses : ");
        for(char c : incorrectChars){
            System.out.print(c);
        }
        System.out.println();

        right = guesses.size() - incorrect;

        if (correct == word.length()){
            System.out.println("Congradulations!!");
            System.out.println("The game took " + guesses.size() + " guesses!");
            System.out.println(right + " correct guesses, and " + incorrect + " incorrect guesses.");
            return true;
        } else{
            return false;
        }
    }

}