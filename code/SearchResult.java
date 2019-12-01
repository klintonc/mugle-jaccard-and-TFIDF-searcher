/**
 * A document and its score for a query. Sorting puts the highest score first,
 * and breaks ties by the lower document id.
 */
public class SearchResult implements Comparable<SearchResult> {
	private Document document;
	private double score;

	public SearchResult(Document document, double score) {
		this.document = document;
		this.score = score;
	}

	public Document getDocument() {
		return document;
	}

	public double getScore() {
		return score;
	}

	@Override
	public int compareTo(SearchResult other) {
		int byScore = Double.compare(other.score, score);
		if (byScore != 0) return byScore;
		return Integer.compare(document.getId(), other.document.getId());
	}

	@Override
	public String toString() {
		return document.getId() + "\t" + score;
	}
}
