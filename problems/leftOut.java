import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class leftOut {

	public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter("leftout.out", false);
		PrintWriter out = new PrintWriter(fw);
		FileReaderUtil f = new FileReaderUtil("leftout.in");
		int n = f.nextInt();
		boolean[][] photo = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			String line = f.next();
			for (int j = 0; j < n; j++) {
				if (line.charAt(j) == 'L') {
					photo[i][j] = true;
				}
			}
		}
		for (int i = 1; i < n; i++) {
			photo[i][0] = photo[i][0] ^ photo[0][0];
			for (int j = 1; j < n; j++) {
				photo[i][j] = photo[i][j] ^ photo[0][j] ^ photo[i][0];
			}
		}
		if (count(1, 1, n - 1, n - 1, false, photo) == 0) {
			out.println("1 1");
			out.close();
			return;
		}
		if (count(1, 1, n - 1, n - 1, true, photo) == n - 1) {
			for (int j = 1; j < n; j++) {
				if (count(1, j, n - 1, j, true, photo) == n - 1) {
					out.println("1 " + (j + 1));
					out.close();
					return;
				}
			}
			for (int i = 1; i < n; i++) {
				if (count(i, 1, i, n - 1, true, photo) == n - 1) {
					out.println("" + (i + 1) + " 1");
					out.close();
					return;
				}
			}
			out.println("-1");
			out.close();
			return;
		}
		if (count(1, 1, n - 1, n - 1, true, photo) != 1) {
			out.println("-1");
			out.close();
			return;
		}
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < n; j++) {
				if (photo[i][j]) {
					out.println("" + (i + 1) + " " + (j + 1));
				}
			}
		}
		out.close();
	}

	public static int count(int x, int y, int x2, int y2, boolean bool, boolean[][] photo) {
		int ans = 0;
		for (int i = x; i <= x2; i++) {
			for (int j = y; j <= y2; j++) {
				if (photo[i][j] == bool) {
					ans++;
				}
			}
		}
		return ans;
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

		public char nextChar() throws IOException {
			return (char) reader.read();
		}

		public void close() throws IOException {
			reader.close();
		}
	}
}