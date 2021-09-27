
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class RestStops {
	/*
	    10 2 4 3
		7 2
		8 1
	 */

	public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter("reststops.out", false);
		PrintWriter out = new PrintWriter(fw);
		FileReaderUtil f = new FileReaderUtil("reststops.in");
		
		int L= f.nextInt();
		int N = f.nextInt();
		int rf= f.nextInt();
		int rb= f.nextInt();

		Node[] stops = new Node[N];
		

		for (int i = 0; i < N; i++) {
			long d = f.nextLong();
			Node n = new Node();
			n.pos = d;
			n.treat = f.nextInt();
			n.rdiff = rf-rb;
			stops[i] = n;
		}

		Arrays.sort(stops);
		Long total = 0L;
		long prev = 0;
		for (int i = 0; i < N; i++) {
			Node c= stops[i];
			if(c.pos < prev) {
				continue;
			}
			long treat = (c.pos -prev) * c.rdiff * c.treat;
			
			total += treat;
			prev = stops[i].pos;
			
		}
		out.println(total);
		out.close();
	}

	public static class Node implements Comparable<Node>{
		@Override
		public String toString() {
			return "Node [pos=" + pos + ", treat=" + treat + "]";
		}

		public long pos;
		public int treat;
		public int rdiff;


		@Override
		public int compareTo(RestStops.Node o) {			
			return -1 * Long.compare(treat,o.treat);
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

