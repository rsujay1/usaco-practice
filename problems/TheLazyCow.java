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


public class TheLazyCow {
	
   public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter("lazy.out", false);
		PrintWriter out = new PrintWriter(fw);
		FileReaderUtil f = new FileReaderUtil("lazy.in");
		
		int N= f.nextInt();
		int K= f.nextInt();
		
		Node[] in = new Node[N];
		

		for (int i = 0; i < N; i++) {
			int s = f.nextInt();
			int p = f.nextInt();
			Node n = new Node();
			n.pos = p;
			n.prefix =0;
			n.size=s;
			in[i] = n;
		}
		Arrays.sort(in);
		in[0].prefix = in[0].size;
		for (int i = 1; i < N;i++) {
		  in[i].prefix = in[i-1].prefix+in[i].size;
		}
		long max = 0;
		int i =0;
		long start =0;
		int tempE=0;
		while(i < N) {
			int st = i;
			long end = in[i].pos+K*2+1;
			
			if (end >= in[N-1].pos) {				
				end = in[N-1].pos+1; 
			}
			i = tempE;
			while( i < N && in[i].pos < end ) {
				i++;
			}
			tempE = i-1;
			long size = in[i-1].prefix - start;
			if(max < size) {				
				max = size;
			}
			i = st+1;
			if (i <N)
			start = in[i-1].prefix;	
			
		}
		
		out.println(max);
		out.close();
	}
		
	public static class Node implements Comparable<Node> {
		public long pos;
		public long size;
		public long prefix;
		@Override
		public String toString() {
			return "Node [pos=" + pos + ", size=" + size + "]";
		}
		@Override
		public int compareTo(Node o) {
			
			return Long.compare(pos, o.pos);
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
