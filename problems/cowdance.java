
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class cowdance {
	public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter("cowdance.out", false);
		PrintWriter output = new PrintWriter(fw);
		FileReaderUtil f = new FileReaderUtil("cowdance.in");

		int N = f.nextInt();
		int tMax = f.nextInt();
		
		List<Integer> dList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			
			dList.add(f.nextInt());
		}
		
		int a = 1; int b = N;
		while(a<b) {
			if (works(a,dList,tMax)) {
				break;
			} 
			a++;
		}
		
		output.println(a);
		
		output.close();
		f.close();
	}

	public static class Node implements Comparable<Node>{
		int d;
		int size;
		@Override
		public int compareTo(Node o) {
			
			return -1 * Integer.compare(size, o.size);
		}
	}
	private static boolean works(int k, List<Integer> dList, int tMax ) {
	
		
		PriorityQueue<Node> q = new PriorityQueue<>(); 
		
		int j =0;
		while (j <dList.size()) {
			for (int i =j; i <j+k;i++) {
				if (i==dList.size()) {
					return true;
				}
				int size=0;
				if (j==0) {
					size = tMax - dList.get(i);
				} else {
					Node lowest = q.poll();
					size=lowest.size-dList.get(i);
				}
				if(size < 0) return false;
				Node node = new Node();
				node.d = dList.get(i);
				node.size =size;
				q.add(node);
			}
			j=j+k;
		}
		
		
		return true;
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
