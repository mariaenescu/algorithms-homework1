import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Compresie {
	static class Resolve {
		public static final String INPUT_FILE = "compresie.in";
		public static final String OUTPUT_FILE = "compresie.out";
		private int n;
		private int[] A;
		private int m;
		private int[] B;

		public void solve() {
			readInput();
			writeOutput(getResult());
		}

		private void readInput() {
			try {
				Scanner sc = new Scanner(new File(INPUT_FILE));
				n = sc.nextInt();
				A = new int[n];
				for (int i = 0; i < n; i++) {
					A[i] = sc.nextInt();
				}
				m = sc.nextInt();
				B = new int[m];
				for (int i = 0; i < m; i++) {
					B[i] = sc.nextInt();
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(int result) {
			try {
				PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
				pw.printf("%d\n", result);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private int getResult() {
			int i = 0, j = 0, count = 0;

			for (int sumA = 0, sumB = 0; i < n && j < m; ) {
				if (sumA + A[i] == sumB + B[j]) {
					sumA = 0; // resetam sumele pentru urmatoarea comparatie
					sumB = 0;
					i++;
					j++;
					count++; // am gasit un alt element egal în ambele siruri
				} else if (sumA + A[i] < sumB + B[j]) {
					sumA += A[i++]; // construim sumele pentru comprimare
				} else {
					sumB += B[j++];
				}
			}

			if (i < n || j < m) {
				return -1;
			}
			return count;
		}

	}

	public static void main(String[] args) {
		new Resolve().solve();
	}
}
