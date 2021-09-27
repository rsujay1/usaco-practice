import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;


/*
ID: sujay4
LANG: JAVA
TASK: marathon
*/
public class marathon {
	public static void main (String[] args) throws IOException {
		 
		MyFileReaderUtil fileReader = new MyFileReaderUtil("marathon.in");
		 PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("marathon.out")));
		int totalPosts = fileReader.nextInt();
		
		int ppX = 0;
		int ppY = 0;
		int pX = 0;
		int pY = 0;
		int x = 0;
		int y = 0;
		int maxSkipped = Integer.MIN_VALUE;
		int sum = 0;
		for (int i = 0; i <  totalPosts; i++) {
			if ( i >= 2) {
				ppX = pX;
				ppY = pY;		
			}
			
			if ( i >= 1) {
				pX = x;
				pY = y;		
			}
			
			x = fileReader.nextInt();
			y = fileReader.nextInt();
			
			if (i >= 2) {
				int regDistance = computeDdistance(ppX, pX, ppY, pY) + computeDdistance(pX, x, pY, y);
				int skipDistance = computeDdistance(ppX, x, ppY, y);
				int skipped = regDistance - skipDistance;
				if (skipped > maxSkipped) {
					maxSkipped = skipped;
				}
				sum += regDistance;
				if (i > 2) {
					sum -= computeDdistance(ppX, pX, ppY, pY);
				}
			}
			
		}
		
		out.println(new Integer(sum - maxSkipped).toString());
		
		out.close();
		
	}
	
	public static int computeDdistance(int x1, int x2, int y1, int y2) {
		return Math.abs(x2-x1)+Math.abs(y2-y1);
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
	}
}

