package GameEntities;

/**
 * Created by Simi on 23.9.2018.
 */
public class Result {

    private Long id;
    private String name;
    private byte score;
    private short bet;
    private float price;
    private String note;

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

    @Override
    public String toString() {
        return String.format("Result Id: %d Name: %s; Score: %d; Bet: %d; Price: %.2f; Note: %s",
                id, name, score, bet, price, note);
    }
}
