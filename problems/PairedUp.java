import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


/*4 3
4 7
10 15
2 2
5 1*/


public class PairedUp {
	
   public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter("pairup.out", false);
		PrintWriter out = new PrintWriter(fw);
		FileReaderUtil f = new FileReaderUtil("pairup.in");
		
		int N= f.nextInt();
		
		
		Node[] in = new Node[N];
		
		
		for (int i = 0; i < N; i++) {
			int count = f.nextInt();
			int value = f.nextInt();
			Node n = new Node();
			n.count = count;
			n.value = value;
			in[i] = n;			
		}
		Arrays.sort(in);
		int p=0,q=N-1;
		long max = 0;
		
		while(p < q) {
			in[p].count--;
			long v1 = in[p].value;
			if(in[p].count == 0) {
				p =p+1;
			}
			in[q].count--;
			long v2 = in[q].value;
			if(in[q].count == 0) {
				q =q-1;
			}
			if(max < v1+v2) {
				max = v1+v2;
			}
		  
		}
		
		out.println(max);
		out.close();
	}
		
	public static class Node implements Comparable<Node> {
		public long count;
		public long value;
		
		@Override
		public String toString() {
			return "Node [count=" + count + ", value=" + value + "]";
		}
		@Override
		public int compareTo(Node o) {
			
			return Long.compare(value, o.value);
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
