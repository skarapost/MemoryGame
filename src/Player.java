public class Player {
    private int num;
    private int score;

    public Player(int num) {
        this.num = num;
        this.score = 0;
    }

    public int updatescore(int newscore) {
        return this.score += newscore;
    }
}