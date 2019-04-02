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

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		ScoreResults results = (ScoreResults) o;

		if (correctCount != results.correctCount) {
			return false;
		}
		return size == results.size;
	}

	@Override
	public int hashCode() {
		int result = correctCount;
		result = 31 * result + size;
		return result;
	}
}
