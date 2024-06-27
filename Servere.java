import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Servere {
	private static final double PRECISION = 0.1;

	static class Resolve {
		public static final String INPUT_FILE = "servere.in";
		public static final String OUTPUT_FILE = "servere.out";
		private int N;
		private double[] P;
		private double[] C;
		private double max_value;
		private double powerMiddle;
		private double powerRight;
		private double powerLeft;

		public void solve() {
			readInput();
			writeOutput(getResult());
		}

		private void readInput() {
			try {
				Scanner sc = new Scanner(new File(INPUT_FILE));
				N = sc.nextInt();
				P = new double[N];
				for (int i = 0; i < N; i++) {
					P[i] = sc.nextDouble();
				}
				C = new double[N];
				max_value = Double.MIN_VALUE;
				for (int i = 0; i < N; i++) {
					C[i] = sc.nextDouble();

					if (C[i] > max_value) {
						max_value = C[i];
					}

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
			double left = 0;
			double right = max_value;
			while (left < right - PRECISION) {
				final double middle = left + (right - left) / 2;

				powerLeft = Double.MAX_VALUE;
				powerMiddle = Double.MAX_VALUE;
				powerRight = Double.MAX_VALUE;
				getMinimumPowerMismatch(middle);

				if (powerLeft <= powerMiddle && powerMiddle >= powerRight) {
					return powerMiddle;
				} else if (powerLeft < powerMiddle) {
					left = middle;
				} else {
					right = middle;
				}
			}

			return 0;
		}

		private void getMinimumPowerMismatch(double x) {
			for (int i = 0; i < N; i++) {
				powerMiddle = Math.min(powerMiddle, P[i] - Math.abs(x - C[i]));
				powerLeft = Math.min(powerLeft, P[i] - Math.abs((x - PRECISION) - C[i]));
				powerRight = Math.min(powerRight, P[i] - Math.abs((x + PRECISION) - C[i]));
			}
		}
	}

	public static void main(String[] args) {
		new Resolve().solve();
	}
}