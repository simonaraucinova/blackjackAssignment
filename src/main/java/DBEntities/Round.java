package DBEntities;

/**
 * Created by Simi on 23.9.2018.
 */
public class Round {

    private Long id;
    private int roundNumber;
    private int gameNumber;

    public Round() {
    }

    public Round(int roundNumber, int gameNumber) {
        this.roundNumber = roundNumber;
        this.gameNumber = gameNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(int gameNumber) {
        this.gameNumber = gameNumber;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    @Override
    public String toString() {
        return "Round{" +
                "id=" + id +
                ", roundNumber=" + roundNumber +
                ", gameNumber=" + gameNumber +
                '}';
    }
}
