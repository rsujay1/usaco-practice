import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class grassPlanting {
	public static void main(String[] args) throws IOException {

		FileWriter fw = new FileWriter("planting.out", false);
		PrintWriter out = new PrintWriter(fw);
		FileReaderUtil f = new FileReaderUtil("planting.in");

		int n = f.nextInt();
		int[][] paths = new int[n][2];
		String[] line;
		for (int i = 0; i < n - 1; i++) {
			line = f.next().split(" ");
			paths[i][0] = Integer.valueOf(line[0]);
			paths[i][1] = Integer.valueOf(line[1]);
		}
		String[] answer = calc(n, paths);
		for (int i = 0; i < answer.length; i++) {
			out.println(answer[i]);
		}
		out.close();
	}

	public static String[] calc(int n, int[][] paths) {
		Grass[] fields = new Grass[n];
		int a, b;
		for (int i = 0; i < n - 1; i++) {
			a = paths[i][0] - 1;
			b = paths[i][1] - 1;

			if (fields[a] == null) {
				fields[a] = new Grass(a);

			}
			if (fields[b] == null) {
				fields[b] = new Grass(b);
			}

			fields[a].neighbors.add(b);
			fields[b].neighbors.add(a);

		}
		int minTypes = 1;
		boolean[] nums;
		for (int i = 0; i < n; i++) {
			nums = new boolean[n];
			for (int j = 0; j < fields[i].neighbors.size(); j++) {
				if (j > i)
					continue;
				Grass neighbor = fields[fields[i].neighbors.get(j)];
				nums[neighbor.grassType] = true;
				for (int k = 0; k < fields[neighbor.id].neighbors.size(); k++) {
					if (k > i)
						continue;
					nums[fields[fields[neighbor.id].neighbors.get(k)].grassType] = true;
				}
			}
			for (int j = 0; j < nums.length; j++) {
				if (!nums[j]) {
					if (j < minTypes) {
						fields[i].grassType = j;
					} else {
						fields[i].grassType = minTypes;
						minTypes++;
					}
					break;
				}
			}
		}
		String[] out = new String[1];
		out[0] = (minTypes - 1) + "";
		return out;
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

	public static class Grass {
		int id;
		int grassType;
		ArrayList<Integer> neighbors;

		public Grass(int id) {
			this.id = id;
			this.neighbors = new ArrayList<Integer>();
		}
	}
}
