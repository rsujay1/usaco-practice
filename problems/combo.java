import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
ID: sujay4
LANG: JAVA
TASK: combo
*/
public class combo {
	public static void main(String[] args) throws IOException {

		FileWriter fw = new FileWriter("combo.out", false);
		PrintWriter output = new PrintWriter(fw);
		FileReaderUtil fileReader = new FileReaderUtil("combo.in");

		int N = fileReader.nextInt();

		// read combos
		int c1 = fileReader.nextInt();
		int c2 = fileReader.nextInt();
		int c3 = fileReader.nextInt();

		int m1 = fileReader.nextInt();
		int m2 = fileReader.nextInt();
		int m3 = fileReader.nextInt();
		int total = 250;
		if (N < 5) {
			total = N * N * N;
		}

		int overlap = numberOfIntersections(c1, m1, N) * numberOfIntersections(c2, m2, N)
				* numberOfIntersections(c3, m3, N);

		if (total == overlap) {
			output.println(new Integer(total).toString());
		} else {
			output.println(new Integer(total - overlap).toString());
		}
		output.close();
		fileReader.close();
	}

	public static int numberOfIntersections(int c1, int c2, int N) {

		Set<Integer> r1 = validCellCombinations(c1, N);
		Set<Integer> r2 = validCellCombinations(c2, N);
		int count = 0;
		for (int i : r1) {
			if (r2.contains(i))
				count++;
		}
		return count;
	}

	public static Set<Integer> validCellCombinations(int cell, int N) {
		Set<Integer> r = new TreeSet<>();

		for (int i = -2; i <= 2; i++) {
			int value = cell + i;
			if (value < 1) {
				value = N + value;
			}
			if (value > N) {
				value = value % N;
			}
			if (value >= 1 && value <= N) {
				r.add(value);

			} else {
				r.add(cell);

			}
		}

		return r;
	}

	public static class FileReaderUtil {
		private BufferedReader reader;
		private StringTokenizer tokenizer;

		public FileReaderUtil(String filename) throws FileNotFoundException {
			FileReader fileReader = new FileReader(filename);
			reader = new BufferedReader(fileReader);
		}

		public String next() throws IOException {
			if (tokenizer == null || !tokenizer.hasMoreTokens()) {
				tokenizer = new StringTokenizer(reader.readLine());
				return tokenizer.nextToken();
			}
			return tokenizer.nextToken();
		}

		public int nextInt() throws IOException {
			return Integer.parseInt(next());
		}

		public long nextLong() throws IOException {
			return Long.parseLong(next());
		}

		public double nextDouble() throws IOException {
			return Double.parseDouble(next());
		}

		public void close() throws IOException {
			reader.close();
		}
	}
}
