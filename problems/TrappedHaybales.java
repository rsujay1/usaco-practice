import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class TrappedHaybales {
	/*
	    5
8 1
1 4
8 8
7 15
4 20
	 */

	public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter("trapped.out", false);
		PrintWriter out = new PrintWriter(fw);
		FileReaderUtil f = new FileReaderUtil("trapped.in");
		int N= f.nextInt();
		Node[] in = new Node[N];
		

		for (int i = 0; i < N; i++) {
			int s = f.nextInt();
			int p = f.nextInt();
			Node n = new Node();
			n.pos = p;
			n.size=s;
			in[i] = n;
		}
		Arrays.sort(in);
		long total=0;
		
		for (int i = 0; i < N-1;i++) {
			int dis = 0;
			
			int p =i;
			int q = p+1;	
			Node t1 = in[p];
			Node t2 = in[q];
			while(p >= 0 && q < N) {
				
				dis = in[q].pos - in[p].pos;
				if(in[p].size < dis && in[q].size < dis) { 
					p--;
					q++;
				} else	if(in[p].size < dis) {
					p--;					
				} else if (in[q].size < dis) {
					q++;
				} else {
					total +=(t2.pos-t1.pos);
					break;
				}
			}
		}			
		
		out.println(total);
		out.close();
	}

	
	public static class Node implements Comparable<Node> {
		public int pos;
		public int size;
		@Override
		public String toString() {
			return "Node [pos=" + pos + ", size=" + size + "]";
		}
		@Override
		public int compareTo(Node o) {
			
			return Integer.compare(pos, o.pos);
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


