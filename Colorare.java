import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Colorare {
	private static final long MODULO = 1000000007;

	static class Resolve {
		public static final String INPUT_FILE = "colorare.in";
		public static final String OUTPUT_FILE = "colorare.out";
		public int K;
		public ArrayList<Pair> pairs;

		static class Pair {
			public int X;
			public char T;

			public Pair(int X, char T) {
				this.X = X;
				this.T = T;
			}
		}

		public void solve() {
			readInput();
			writeOutput(getResult());
		}

		private void readInput() {
			try {
				Scanner sc = new Scanner(new File(INPUT_FILE));
				K = sc.nextInt();
				pairs = new ArrayList<>();
				for (int i = 0; i < K; i++) {
					int X = sc.nextInt();
					char T = sc.next().charAt(0);
					pairs.add(new Pair(X, T));
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(long result) {
			try {
				PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
				pw.printf("%d", result);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public long getResult() {
			long result;

			// Primul element il tratez separat
			if (pairs.get(0).T == 'H') { // (X H)
				result = 6 * pow(3, pairs.get(0).X - 1);
			} else { // (X V)
				result = 3 * pow(2, pairs.get(0).X - 1);
			}

			for (int i = 1; i < pairs.size(); i++) {
				// Daca 2 zone consecutive sunt de acelasi tip
				if (pairs.get(i).T == pairs.get(i - 1).T) {
					if (pairs.get(i).T == 'H') { // (X1 H) (X H) <=> (X1 + X)H
						result *= pow(3, pairs.get(i).X);
					} else { // (X1 V) (X V) <=> (X1 + X)V
						result *= pow(2, pairs.get(i).X);
					}
				} else {
					// Daca 2 zone consecutive sunt de tipuri diferite
					if (pairs.get(i).T == 'H') { // V si restul (X H)
						result *= 2 * pow(3, pairs.get(i).X - 1);
					} else { // H si restul (X V)
						result *= pow(2, pairs.get(i).X - 1);
					}
				}

				result %= MODULO;
			}

			return result;
		}

		private long pow(long base, int exponent) {
			long result = 1;
			for (; exponent > 0; exponent >>= 1) {
				if ((exponent & 1) == 1) {
					result = (result * base) % MODULO;
				}
				base = (base * base) % MODULO;
			}

			return result;
		}
	}

	public static void main(String[] args) {
		new Resolve().solve();
	}
}
