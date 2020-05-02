
public class GameOverExeption extends Exception {
	private int score;
	public GameOverExeption(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
}
