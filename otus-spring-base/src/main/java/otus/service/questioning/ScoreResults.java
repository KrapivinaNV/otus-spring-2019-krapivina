package otus.service.questioning;

public class ScoreResults {

	private final int correctCount;

	private final int size;

	public ScoreResults(int correctCount, int size) {
		this.correctCount = correctCount;
		this.size = size;
	}

	public int getCorrectCount() {
		return correctCount;
	}

	public int getSize() {
		return size;
	}
}
