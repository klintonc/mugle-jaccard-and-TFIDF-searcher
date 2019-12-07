# Mugle: Jaccard and TF-IDF Searcher

A Java ranked-retrieval engine that compares Jaccard similarity with TF-IDF cosine similarity on the LISA test collection.

This is **Project 2** for **ITCS414 Information Storage and Retrieval** at the Faculty of ICT, Mahidol University (MUICT).

## What it does

- **`JaccardSearcher`** scores each document as `|Q ∩ D| / |Q ∪ D|` over the unique terms of the query and the document.
- **`TFIDFSearcher`** weights terms by `(1 + log10 tf) × log10(1 + N / df)` and ranks documents by cosine similarity with the query.
- **`SearcherEvaluator`** computes precision, recall and F1 for the top-k results of each query against `relevance.txt`, then averages them over all queries.
- **`KProducer`** runs both searchers for k = 1 to 50 and writes each metric to a separate file.
- **`Searcher`**, **`Document`** and **`SearchResult`** load the collection, tokenize text (lowercase, split on non-alphanumeric characters) and sort results by score.

### Output

| File | Contents |
| --- | --- |
| `PJ.txt`, `RJ.txt`, `F1J.txt` | Jaccard precision, recall and F1 for k = 1 … 50, one value per line |
| `PT.txt`, `RT.txt`, `F1T.txt` | TF-IDF precision, recall and F1 for k = 1 … 50 |
| `Evaluation k from 1 to 50.txt` | Both searchers' `[P, R, F1]` at every k |
| `*.xlsx` | Charts comparing the two searchers |

These results come from the full LISA collection.

## How to run

Requires JDK 8 or newer. There are no other dependencies.

1. Compile and run from the project root:

   ```bash
   javac -d out code/*.java
   java -cp out KProducer
   ```

2. Results are written to `k/`.

`data/lisa/` holds a small sample collection (12 documents, 4 queries), so the project runs out of the box. To reproduce the full results, replace it with the [LISA collection](http://ir.dcs.gla.ac.uk/resources/test_collections/lisa/) converted to this format:

| File | Format, one entry per line |
| --- | --- |
| `documents.txt` | `<docId> TAB <text>` |
| `queries.txt` | `<queryId> TAB <text>` |
| `relevance.txt` | `<queryId> TAB <docId> <docId> ...` |

Because the tokenizer was rewritten, a fresh run on the full collection may differ slightly from the committed results.

## Authors

- Fha
- Klinton
- Ploy
