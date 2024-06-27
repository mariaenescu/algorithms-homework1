import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Oferta {
	static class Resolve {
		public static final String INPUT_FILE = "oferta.in";
		public static final String OUTPUT_FILE = "oferta.out";
		private int[] prices;
		private int N;
		private int K;
		private double[] dp;

		public void solve() {
			readInput();
			double result = getResult();
			writeOutput(result);
		}

		private void readInput() {
			try {
				Scanner sc = new Scanner(new File(INPUT_FILE));
				N = sc.nextInt();
				K = sc.nextInt();
				prices = new int[N];
				for (int i = 0; i < N; i++) {
					prices[i] = sc.nextInt();
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(double result) {
			try {
				PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
				pw.printf("%.1f\n", result);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private double getResult() {
			dp = new double[N + 1];
			dp[0] = 0;
			dp[1] = prices[0];

			for (int i = 2; i <= N; i++) {
				// Costul daca cumparam produsul i singur
				dp[i] = dp[i - 1] + prices[i - 1];

				// Cost pentru 2 produse consecutive => aplic reducere 50% pentru cel mai ieftin
				dp[i] = Math.min(dp[i], dp[i - 2] + prices[i - 2] + prices[i - 1]
						- Math.min(prices[i - 2], prices[i - 1]) / 2.0);

				// Cost pentru 3 produse consecutive => gratuitate cel mai ieftin produs
				if (i > 2) {
					int freeProduct = Math.min(prices[i - 3],
							Math.min(prices[i - 2], prices[i - 1]));
					dp[i] = Math.min(dp[i], dp[i - 3] + prices[i - 3] + prices[i - 2]
							+ prices[i - 1] - freeProduct);
				}
			}
			if (K == 1) {
				return dp[N];
			}
			return -1;
		}
	}

	public static void main(String[] args) {
		new Resolve().solve();
	}
}
