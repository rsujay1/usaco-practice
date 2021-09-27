import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class bcount {
	public static void main (String[] args) throws IOException {
		FileWriter fw = new FileWriter("bcount.out", false);
		PrintWriter output = new PrintWriter(fw);	
		FileReaderUtil fileReader = new FileReaderUtil("bcount.in");
		
		int N = fileReader.nextInt();
		int Q = fileReader.nextInt();
		int[] onePrefix = new int[N+1];
		int[] twoPrefix = new int[N+1];
		int[] threePrefix = new int[N+1];
		for (int i =1; i<= N; i++) {
			int value = fileReader.nextInt();
			
			onePrefix[i] = onePrefix[i-1] + 0;
			twoPrefix[i] = twoPrefix[i-1] + 0;
			threePrefix[i] = threePrefix[i-1] + 0;
			
			if (value == 1) {
				onePrefix[i] = onePrefix[i-1] + 1;
			} else if (value == 2) {
				twoPrefix[i] = twoPrefix[i-1] + 1;
			} else  if (value == 3) {
				threePrefix[i] = threePrefix[i-1] + 1;
			}
		}
		
		
		for (int q =1; q<= Q; q++) {
			int a =  fileReader.nextInt();
			int b =  fileReader.nextInt();
			output.println(
					Integer.valueOf(onePrefix[b] - onePrefix[a-1]).toString() + " " +
				    Integer.valueOf(twoPrefix[b] - twoPrefix[a-1]).toString() + " "+
				    Integer.valueOf(threePrefix[b] - threePrefix[a-1]).toString());
		}
		
		
		output.close();
		fileReader.close();
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
		public void close() throws IOException {
			 reader.close();
		}
	}
}