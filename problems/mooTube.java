import java.util.*;
import java.io.*;

public class mooTube {

	public static ArrayList[] graph;

	public static void main(String[] args) throws Exception {

		MyFileReaderUtil fileReader = new MyFileReaderUtil("mootube.in");
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
		int n = fileReader.nextInt();
		int q = fileReader.nextInt();
		graph = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<Edge>();
		}
		for (int i = 0; i < n - 1; i++) {
			int p1 = fileReader.nextInt() - 1;
			int p2 = fileReader.nextInt() - 1;
			int r = fileReader.nextInt();
			graph[p1].add(new Edge(p2, r));
			graph[p2].add(new Edge(p1, r));
		}
		for (int i = 0; i < q; i++) {
			int k = fileReader.nextInt();
			int node = fileReader.nextInt() - 1;
			out.println(floodfill(n, node, k));
		}
		out.close();
		fileReader.close();
	}
	
	public static int floodfill(int n, int node, int min) {
		LinkedList<Integer> q = new LinkedList<Integer>();
		q.offer(node);
		boolean[] visited = new boolean[n];
		visited[node] = true;
		int regions = 0;
		while (q.size() > 0) {
			int current = q.poll();
			regions++;
			for (Edge edg : (ArrayList<Edge>)graph[current]) {
				if (!visited[edg.video] && edg.w >= min) {
					q.offer(edg.video);
					visited[edg.video] = true;
				}
			}
		}
		return regions-1;
	}

	public static class MyFileReaderUtil {
		private BufferedReader reader;
		private StringTokenizer tokenizer;

		public MyFileReaderUtil(String filename) throws FileNotFoundException {
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
			String nextOne = next();
			return Integer.parseInt(nextOne);
		}

		public long nextLong() throws IOException {
			String nextOne = next();
			return Long.parseLong(nextOne);
		}

		public double nextDouble() throws IOException {
			String nextOne = next();
			return Double.parseDouble(nextOne);
		}

		public void close() throws IOException {
			reader.close();
		}
	}
	
	public static class Edge {
		public int video;
		public int w;

		public Edge(int vid, int weight) {
			video = vid;
			w = weight;
		}
	}
}
