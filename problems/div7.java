import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class div7 {
	public static void main (String[] args) throws IOException {
		FileWriter fw = new FileWriter("div7.out", false);
		PrintWriter output = new PrintWriter(fw);	
		FileReaderUtil fileReader = new FileReaderUtil("div7.in");
		
		int N = fileReader.nextInt();
		
		int[] prefix = new int[N+1];
		int[] left = {-1,-1,-1,-1,-1,-1,-1};
		
		int[] right = {-1,-1,-1,-1,-1,-1,-1};
		
		for (int i =1; i<= N; i++) {
			int value = fileReader.nextInt();
			prefix[i] = (prefix[i-1] + value) %7;
			
			if (left[prefix[i]] == -1) {
				left[prefix[i]] = i;
			}
			right[prefix[i]] = i;
		}
		
		int max = 0;
		for (int i =0; i<7;i++) {
			if (left[i] !=-1 && right[i] !=-1 && left[i] != right[i]) {
				if (max < right[i]-left[i]) {
					max = right[i]-left[i];
				}
			}
		}

		
		output.println(new Integer(max).toString());
		
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