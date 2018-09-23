package GameEntities;

/**
 * Represents result of one player in one round of blackjack.
 *
 * Author: SimRau
 */
public class Result {

    private Long id;
    private String name;
    private byte score;
    private short bet;
    private float price;
    private String note;
    private int gameNumber;
    private float playerAccount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getScore() {
        return score;
    }

    public void setScore(byte score) {
        this.score = score;
    }

    public short getBet() {
        return bet;
    }

    public void setBet(short bet) {
        this.bet = bet;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public float getPlayerAccount() {
        return playerAccount;
    }

    public void setPlayerAccount(float playerAccount) {
        this.playerAccount = playerAccount;
    }

    @Override
    public String toString() {
        return String.format("Result Game number: %d, result id: %d Name: %s; Score: %d; Bet: %d; Price: %.2f; " +
                        "Account state: %.2f,Note: %s",
                gameNumber,id, name, score, bet, price, playerAccount,note);
    }

    public int getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(int gameNumber) {
        this.gameNumber = gameNumber;
    }
}
