import java.util.Scanner;

/**
 * Created by Simi on 21.9.2018.
 */
public class GameModerator {

    public int initializeDecksCount(){
        System.out.println("Welcome! You are here to play Blackjack. " +
                "With how many decks would you like to play? Choose a number between 1 and 8.");
        Scanner scanner = new Scanner(System.in);
        int decksCount = -1;
        String input = "";
        while (decksCount < 1 || decksCount > 8) {
            input = scanner.nextLine();
            try {
                decksCount = Integer.parseInt(input);
                if (decksCount < 1 || decksCount > 8){
                    System.out.println("You inputed: " + input + ". Minimal number of decks is 1 and maximal 8. Please enter integer number in this range.");
                }
            } catch (NumberFormatException e) {
                System.out.println("You inputed: " + input + ". You are supposed to enter integer number between 1 and 8. Please try again.");
            }
        }
        return decksCount;
    }
}
