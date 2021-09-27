import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class cow {
	public static void main (String[] args) throws IOException {
		FileWriter fw = new FileWriter("cow.out", false);
		PrintWriter output = new PrintWriter(fw);	
		FileReaderUtil fileReader = new FileReaderUtil("cow.in");
		int cowCount = 0;
		int N = fileReader.nextInt();
		if (N > 2) {
			//String cowStr = fileReader.next();
			//char[] in = cowStr.substring(0,N).toCharArray();
			
			int cIndex =0;
			int cCount = 0;
			int coCount = 0;
			int coIndex = 0;
			
			
			
			for (int i =0 ; i < N ;i++) {
				char c = fileReader.nextChar();
				if (c == 'C') {
					cIndex = i;
					cCount++;
				}
				
				if (c == 'O') {
					if (i > cIndex) {
						coIndex = i;
						coCount = coCount + cCount;
					}
				}
				
				if (c == 'W') {
						if (i > coIndex) {
							cowCount = cowCount + coCount;
						}					
					
				}
			}
		}
		
		output.println(
				Integer.valueOf(cowCount).toString());		
		
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
		public char nextChar() throws IOException {
			return (char)reader.read();
		}
	}
}
