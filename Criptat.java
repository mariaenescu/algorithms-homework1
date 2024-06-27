import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Criptat {
	static class Resolve {
		public static final String INPUT_FILE = "criptat.in";
		public static final String OUTPUT_FILE = "criptat.out";
		private final List<String> words = new ArrayList<>();
		private int N;
		private int maxLengthPassword = Integer.MIN_VALUE;
		private List<Character> allLetters;

		static class WordRatio {
			String word;
			double ratio;

			WordRatio(String word, double ratio) {
				this.word = word;
				this.ratio = ratio;
			}
		}

		public void solve() {
			readInput();
			writeOutput(getResult());
		}

		private void readInput() {
			try (Scanner sc = new Scanner(new File(INPUT_FILE))) {
				N = sc.nextInt();
				sc.nextLine();
				for (int i = 0; i < N; i++) {
					words.add(sc.nextLine().trim());

				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(int result) {
			try (PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE))) {
				pw.println(result);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private int getResult() {
			addAllLetters();

			for (int l = 0; l < allLetters.size(); l++) {
				// fiecare litera isi are propriul portofoliu de cuvinte
				List<WordRatio> portfolioWords = new ArrayList<>();
				for (String word : words) {
					addInPortofolioWord(portfolioWords, word, allLetters.get(l));
				}

				// sortez descrescator cuvintele in functie de raportul lor
				portfolioWords.sort((w1, w2) -> Double.compare(w2.ratio, w1.ratio));

				StringBuilder password = new StringBuilder();
				int totalCountDominantLetter = 0;

				for (WordRatio portfolioWord : portfolioWords) {
					password.append(portfolioWord.word);
					int countLetterForCurrentWord = (int) (portfolioWord.ratio
							* portfolioWord.word.length());
					totalCountDominantLetter += countLetterForCurrentWord;

					if (totalCountDominantLetter <= password.length() / 2) {
						// nu mai contorizez lungimea cuvantul curent in parola
						password.setLength(password.length() - portfolioWord.word.length());
						totalCountDominantLetter -= countLetterForCurrentWord;
					}
				}

				maxLengthPassword = Math.max(maxLengthPassword, password.length());
			}

			return maxLengthPassword;
		}

		private void addInPortofolioWord(List<WordRatio> portfolioWords, String word,
			char currentLetter) {
			double ratio = (double) countDominantLetter(word, currentLetter) / word.length();
			portfolioWords.add(new WordRatio(word, ratio));
		}

		private void addAllLetters() {
			allLetters = new ArrayList<>();
			for (String word : words) {
				char[] letters = word.toCharArray();
				for (char letter : letters) {
					if (!allLetters.contains(letter)) {
						allLetters.add(letter);
					}
				}
			}
		}

		private int countDominantLetter(String word, char character) {
			int count = 0;
			for (int i = 0; i < word.length(); i++) {
				if (word.charAt(i) == character) {
					count++;
				}
			}
			return count;
		}
	}

	public static void main(String[] args) {
		new Resolve().solve();
	}
}
