package Authorities;

import Authorities.CardManipulator;
import GameEntities.Dealer;
import GameEntities.Deck;
import GameEntities.Player;
import GameEntities.Result;

import java.util.*;

/**
 * Created by Simi on 21.9.2018.
 */
public class GameModerator {

    private Scanner scanner;
    private Dealer dealer;

    public GameModerator() {
        this.scanner = new Scanner(System.in);
        this.dealer = new Dealer();
    }

    public int initializeDecksCount() {
        System.out.println("Welcome! You are here to play Blackjack. " +
                            "With how many decks would you like to play? Choose a number between 1 and 8.");
        int decksCount = -1;
        String input = "";

        while (decksCount < 1 || decksCount > 8) {
            input = scanner.nextLine();

            try {
                decksCount = Integer.parseInt(input);
                if (decksCount < 1 || decksCount > 8) {
                    System.out.println("You inputed: " + input + ". Minimal number of decks is 1 and maximal 8. " +
                                        "Please enter integer number in this range.");
                }
            } catch (NumberFormatException e) {
                System.out.println("You inputed: " + input + ". You are supposed to enter " +
                                    "integer number between 1 and 8. Please try again.");
            }
        }
        return decksCount;
    }

    public List<Player> initializePlayers() {
        Set<String> names = new HashSet<>();
        List<Player> players = new ArrayList<>();
        int playersCount = getPlayersCount();
        for (int i = 1; i <= playersCount; i++) {
            Player player = initializePlayerName(i,names);
            player.setBet(initializeBet());
            players.add(player);
            names.add(player.getName());
        }

        players.add(this.dealer);
        return players;
    }

    private int getPlayersCount() {
        System.out.println("Cards are ready. Now please introduce players. " +
                "The dealer is PC. \nHow many people are going to play against it? The minimum is 1 and maximum is 6.");
        int playersCount = -1;
        String input = "";
        while (playersCount < 1 || playersCount > 6) {
            input = scanner.nextLine();
            try {
                playersCount = Integer.parseInt(input);
                if (playersCount < 1 || playersCount > 6) {
                    System.out.println("You inputed: " + input + ". Minimal number of players is 1 and maximal 6. " +
                            "Please enter integer number in this range.");
                }
            } catch (NumberFormatException e) {
                System.out.println("You inputed: " + input + ". You are supposed to enter integer number between 1 and 6. " +
                        "Please try again.");
            }
        }

        return playersCount;
    }

    private Player initializePlayerName(int number, Set<String> names) {
        System.out.println("Hi player number " + number +
                ". What is your name?");
        String name = scanner.nextLine();
        while (name.equals("Dealer") || names.contains(name)){
            if (name.equals("Dealer")) {
                System.out.println("Your name cannot be GameEntities.Dealer! Please choose another name.");
            } else {
                System.out.println("There already is a player with name " + name + "! Please choose another name.");
            }
            name = scanner.nextLine();
        }
        return new Player(name);
    }

    public short initializeBet(){
        System.out.println("How much would you like to bet? Minimal bet is 20$ and maximal 5000$.");
        short bet = -1;
        String input = "";
        while (bet < 20 || bet > 5000) {
            input = scanner.nextLine();
            try {
                bet = Short.parseShort(input);
                if (bet < 20 || bet > 5000) {
                    System.out.println("You inputed: " + input + ". Minimal bet is 20$ and maximal is 5000$. ");
                }
            } catch (NumberFormatException e) {
                System.out.println("You inputed: " + input + ". You are supposed to enter integer number higher than " +
                        "20$ and less than 5000$. Please try again.");
            }
        }
        return bet;
    }

    public void printCardsOfAllPlayers(List<Player> players, boolean finalPrint) {
        System.out.println("");
        for (int i = 0; i < players.size() - 1; i++) {
            Player player = players.get(i);
            player.printCardsOfPlayer();

        }
        dealer.printCardsOfPlayer(finalPrint);
    }

    public void manageGame(List<Player> players, Deck deck){
        CardManipulator cardManipulator = new CardManipulator();
        for(int i = 0; i < players.size() - 1; i++){
            Player player = players.get(i);
            while (!player.isStanding() && !player.isSurrender()) {
                managePlayerAnswer(player, deck,cardManipulator);
                cardManipulator.checkTrop(player);
            }
        }
    }
    public List<Result> printFinalScore(List<Player> players){
        System.out.println("Results: ");
        List<Result> results = new ArrayList<>();
        for (int i = 0; i < players.size() - 1; i++){
            Player player = players.get(i);

            Result result = new Result();
            result.setName(player.getName());
            result.setBet((short)player.getBet());
            result.setScore((byte)player.getActualCount());

            System.out.println(getFinalOfPlayer(player,result));
            results.add(result);
        }

        System.out.println("Dealer: " + dealer.getActualCount() + ". " +
                ((dealer.getActualCount() > 21) ? "Dealer has trop." : ""));
        return results;
    }

    public String getFinalOfPlayer(Player player, Result result){
        if (player.hasBlackJack()){
            result.setNote("Blackjack");
            result.setPrice((float)(player.getBet()*1.5));
            return (player.getName() + ": Blackjack. Congratulation, you have won :) You get back your bet "
                    + (float)player.getBet() + "$ and you won " + (float)(player.getBet()*1.5) + "$.");
        } else {
            String out = "";
            if(player.getActualCount() > 21){
                result.setNote("Lost");
                result.setPrice(player.getBet() * -1);
                out = ". You have trop, so you have lost " + (float)player.getBet() + "$.";
            } else if(player.isSurrender()){
                result.setNote("Surrender");
                result.setPrice((float)(player.getBet()*-1)/2);
                out = ". You have surrendered. You get back half of your bet " + (float)player.getBet()/2 + "$.";
            } else if (dealer.getActualCount() > player.getActualCount() && dealer.getActualCount() < 22){
                result.setNote("Lost");
                result.setPrice(player.getBet() * -1);
                out = ". You have less points than dealer, so you have lost " + (float)player.getBet() + "$.";
            } else if(dealer.getActualCount() < player.getActualCount() || dealer.getActualCount() > 21){
                result.setNote("Win");
                result.setPrice((float) player.getBet());
                out = ". Congratulation, you have won :) You get back your bet " + (float)player.getBet() +
                        "$ and you won " + (float)player.getBet() + "$.";
            } else {
                result.setNote("Draw");
                result.setPrice(0);
                out = ". It is a draw. You get back your bet " + (float)player.getBet() + "$.";
            }
            return (player.getName() + ": " + player.getActualCount() + out);
        }
    }

    public void managePlayerAnswer(Player player, Deck deck, CardManipulator manipulator) {
        String input = "";

        System.out.println("\n" + player.getName() + ", it is your turn!");
        player.printCardsOfPlayer();
        dealer.printCardsOfPlayer(false);
        System.out.println("You can hit (input H), stand (input S) " +
                (canSurrender(player) ? "or surrender (input Q)" : "and you cannot surrender") +
                ". When you have chosen, press ENTER.");

        while (!input.equals("S") && !input.equals("H") &&  !input.equals("Q")) {
            input = scanner.nextLine().toUpperCase();
            if (!reactToAnswer(input,manipulator,player,deck)){
                input = "X";
            }
        }
    }

    private boolean reactToAnswer(String input, CardManipulator manipulator, Player player, Deck deck){
        switch (input){
            case "H":
                manipulator.hit(player,deck);
                return true;
            case "S":
                player.setStanding(true);
                System.out.println(player.getName() + " is standing.");
                return true;
            case "Q":
                return reactToSurrender(player);
            default:
                System.out.println("You inputed: " + input + ". You are supposed to enter S, H or Q. Please try again.");
        }
        return false;
    }

    private boolean reactToSurrender(Player player){
        if (canSurrender(player)){
            player.surrender();
            System.out.println(player.getName() + " has surrendered.");
            return true;
        } else {
            System.out.println("You cannot surrender. You can hit (input H) or stand (input S).");
            return false;
        }
    }

    public Player getDealer() {
        return dealer;
    }

    private boolean canSurrender(Player player){
        return !(dealer.hasBlackJack() || player.getCardsOnHand().size() > 2);
    }
}




