import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;



public class convention2{
	public static void main (String[] args) throws IOException {
		FileWriter fw = new FileWriter("convention2.out", false);
		PrintWriter output = new PrintWriter(fw);	
		FileReaderUtil f = new FileReaderUtil("convention2.in");
		int N = f.nextInt();
		
		PriorityQueue<Node> arrivalQueue = new PriorityQueue<>(new ArrivalComparator());
		
		for (int i =0; i < N;i++) {
			Node node = new Node();
			node.a = f.nextInt();
			node.d = f.nextInt();
			
			node.rank =i+1;
			arrivalQueue.add(node);
		}
		PriorityQueue<Node> rankQueue = new PriorityQueue<>();
		
		int tt = arrivalQueue.peek().a;
		
		int maxWt = 0;
		for (int i =0; i < N;i++) {
		 	if (rankQueue.isEmpty() && tt < arrivalQueue.peek().a) {
				tt = arrivalQueue.peek().a;
			} 
			
			Node node= getArrivalQueue(tt,arrivalQueue,rankQueue);
			
			
			int wt =  tt - node.a ;
			
			if (maxWt< wt) {
				maxWt = wt;
			}
			tt+= node.d;
		}
		output.println(maxWt);
		output.close();
		f.close();
	}
	
	private static Node getArrivalQueue(int minA, PriorityQueue<Node> nodes, PriorityQueue<Node> rankQueue) {
			while(!nodes.isEmpty()) {
				Node node = nodes.peek();
				if (node.a <= minA ) {
					rankQueue.add( nodes.poll());
				} else {
					 break;
				}
			}
		return rankQueue.poll();
    }
	
	public static class ArrivalComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			int v= Integer.compare(o1.a, o2.a);
			return v;
		}
		
	}


	public static class Node implements Comparable<Node>{		
		public int a ;
		public int d ;
		public int rank;
		@Override
		public int compareTo(Node o) {
			return   Integer.compare(rank, o.rank);
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
			return (char)reader.read();
		}
		
		public void close() throws IOException {
			 reader.close();
		}
	}
}