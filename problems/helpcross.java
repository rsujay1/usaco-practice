import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class helpcross {
	public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter("helpcross.out", false);
		PrintWriter output = new PrintWriter(fw);
		FileReaderUtil f = new FileReaderUtil("helpcross.in");

		int C= f.nextInt();
		int N = f.nextInt();
		
		int[] chickenT = new int[C];
		
		PriorityQueue<Node> cows = new PriorityQueue<>();
		for (int i = 0; i < C; i++) {
			chickenT[i] =(f.nextInt());
		}
		Arrays.sort(chickenT);
		
		for (int i = 0; i < N; i++) {
			Node n = new Node();
			n.a = f.nextInt();
			n.b = f.nextInt();
			n.helpers = countInRange(chickenT,C,n.a,n.b);
			cows.add(n);
		}
		PriorityQueue<Node> rankQueue = new PriorityQueue<>(new HelperComparator());
		
		int count=0;
		int cI =0;
		while ((cows.size() > 0 ||rankQueue.size() > 0) && cI < chickenT.length) {
			
			int helpTime = chickenT[cI];
			while(rankQueue.size() > 0) {
				Node cow = rankQueue.peek();
				if (cow.b < helpTime) {
					rankQueue.poll();
				} else {
					cow = rankQueue.poll();
					cows.add(cow);
					break;
				}
			}
			Node cow = cows.peek();
			while(cows.size() > 0) {
				cow = cows.peek();
				if( cow.a <= helpTime && cow.b >= helpTime) {
					cow = cows.poll();
					cow.helpers--;
					rankQueue.add(cow);
				} else {
					break;
				}
			}
			if (rankQueue.size() > 0) {
				Node tt= rankQueue.poll();
				cI++;
				count++;
			} else if(cow.a > helpTime) { 
				cI++;
			} else {
				cows.poll();
			}
		}
		
		
		output.println(count);
		
		output.close();
		f.close();
	}
	
	 // function to find first index >= x 
    static int lowerIndex(int arr[], int n, int x) 
    { 
        int l = 0, h = n - 1; 
        while (l <= h)  
        { 
            int mid = (l + h) / 2; 
            if (arr[mid] >= x) 
                h = mid - 1; 
            else
                l = mid + 1; 
        } 
        return l; 
    } 
      
    // function to find last index <= y 
    static int upperIndex(int arr[], int n, int y) 
    { 
        int l = 0, h = n - 1; 
        while (l <= h)  
        { 
            int mid = (l + h) / 2; 
            if (arr[mid] <= y) 
                l = mid + 1; 
            else
                h = mid - 1; 
        } 
        return h; 
    } 
      
    // function to count elements within given range 
    static int countInRange(int arr[], int n, int x, int y) 
    { 
        // initialize result 
        int count = 0; 
        count = upperIndex(arr, n, y) -  
                lowerIndex(arr, n, x) + 1; 
        return count; 
    } 

	public static class Node implements Comparable<Node>{
		int a;
		int b;
		int helpers;
		@Override
		public String toString(){
			return b+"";
		}
		@Override
		public int compareTo(Node o) {
			
			return Long.compare(a, o.a);
		}
	}
	
	public static class HelperComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			// TODO Auto-generated method stub
			return Long.compare(o1.helpers, o2.helpers)==0?Long.compare(o1.b, o2.b)==0?Long.compare(o2.a, o1.a):Long.compare(o1.b, o2.b):Long.compare(o1.helpers, o2.helpers);
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
