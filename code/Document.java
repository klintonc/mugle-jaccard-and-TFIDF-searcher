import java.util.List;

/**
 * A document (or a query) with an id, its raw text, and its tokens.
 */
public class Document {
	private int id;
	private String rawText;
	private List<String> tokens;

	public Document(int id, String rawText, List<String> tokens) {
		this.id = id;
		this.rawText = rawText;
		this.tokens = tokens;
	}

	public int getId() {
		return id;
	}

	public String getRawText() {
		return rawText;
	}

	public List<String> getTokens() {
		return tokens;
	}

	@Override
	public String toString() {
		return "[" + id + "] " + rawText;
	}
}
