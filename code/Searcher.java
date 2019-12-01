import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Base class for searchers. Loads the documents and provides the tokenizer.
 *
 * Document and query files have one entry per line: <id> TAB <text>
 */
public abstract class Searcher {
	protected List<Document> documents = null;

	public Searcher(String docFilename) {
		this.documents = parseDocumentFromFile(docFilename);
	}

	/**
	 * Returns the top k results for the query, highest score first.
	 */
	public abstract List<SearchResult> search(String queryString, int k);

	/**
	 * Lowercases the text and splits it on anything that is not a letter or a digit.
	 */
	public static List<String> tokenize(String text) {
		List<String> tokens = new ArrayList<String>();
		for (String token : text.toLowerCase().split("[^a-z0-9]+")) {
			if (!token.isEmpty()) tokens.add(token);
		}
		return tokens;
	}

	public static List<Document> parseDocumentFromFile(String filename) {
		List<Document> docs = new ArrayList<Document>();
		try {
			for (String line : Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8)) {
				line = line.trim();
				if (line.isEmpty()) continue;
				String[] parts = line.split("\\t", 2);
				String text = parts.length > 1 ? parts[1] : "";
				docs.add(new Document(Integer.parseInt(parts[0].trim()), text, tokenize(text)));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return docs;
	}
}
