import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class icyperimeter {

	public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter("perimeter.out", false);
		PrintWriter out = new PrintWriter(fw);
		FileReaderUtil f = new FileReaderUtil("perimeter.in");
		int a = 0;
		int p = 0;
		int N = f.nextInt();

		int[][] gT = new int[N][N];
		int[] visited = new int[N * N];

		for (int i = 0; i < N; i++) {
			String line = f.next();
			for (int j = 0; j < N; j++) {
				if (line.charAt(j) == '#') {
					gT[i][j] = 1;
				}
			}
		}

		boolean entered = false;

		List<Integer>[] g = new ArrayList[N * N];
		int[] prm = new int[N * N];
		for (int i = 0; i < N * N; i++) {
			g[i] = new ArrayList<Integer>();
			int x = (i / N);
			int y = i - (x * N);
			if (gT[x][y] == 1) {
				entered = true;
				// System.out.println(i+":"+x+":"+y);
				prm[i] = 4;
				if (x - 1 >= 0 && gT[x - 1][y] == 1) {
					int pos = (x - 1) * N + y;
					g[i].add(pos);
					prm[i]--;
					// System.out.println(i+":"+x+":"+y+":"+pos);
				}

				if (x + 1 < N && gT[x + 1][y] == 1) {
					int pos = (x + 1) * N + y;
					g[i].add(pos);
					prm[i]--;
					// System.out.println(i+":"+x+":"+y+":"+pos);
				}

				if (y - 1 >= 0 && gT[x][y - 1] == 1) {
					int pos = (x * N) + (y - 1);
					g[i].add(pos);
					prm[i]--;
					// System.out.println(i+":"+x+":"+y+":"+pos);
				}

				if (y + 1 < N && gT[x][y + 1] == 1) {
					int pos = (x * N) + (y + 1);
					g[i].add(pos);
					prm[i]--;
					// System.out.println(i+":"+x+":"+y+":"+pos);
				}
			}
		}

		int color = 1;
		PriorityQueue<Result> rq = new PriorityQueue<>();
		for (int i = 0; i < N * N; i++) {
			if (visited[i] == 0 && g[i].size() > 0) {
				Result result = new Result();
				floodfill(i, g, visited, color, result, prm);
				rq.add(result);
				color++;
			}
		}

		Result q = (rq.size() > 0) ? rq.poll() : (entered == false) ? new Result() : new Result(1, 4);
		out.println(q.a + " " + q.p);
		out.close();
	}

	private static class Result implements Comparable<Result> {
		public Result() {

		}

		public Result(int w, int r) {
			a = w;
			p = r;
		}

		public int a;
		public int p;

		@Override
		public int compareTo(Result o) {
			// TODO Auto-generated method stub
			return Integer.compare(a, o.a) == 0 ? Integer.compare(p, o.p) : -1 * Integer.compare(a, o.a);
		}
	}



	private static void floodfill(int node, List<Integer>[] g, int[] visited, int color, Result result, int[] prm) {
		// base case
		if (visited[node] != 0) {
			return;
		}
		visited[node] = color;
		result.a++;
		result.p += prm[node];
		// transitions
		for (int i : g[node]) {
			floodfill(i, g, visited, color, result, prm);
		}
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