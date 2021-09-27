import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baseball {
	public static void main (String[] args) throws IOException {
		FileWriter fw = new FileWriter("baseball.out", false);
		PrintWriter output = new PrintWriter(fw);	
		FileReaderUtil fileReader = new FileReaderUtil("baseball.in");
		int N = fileReader.nextInt();
		int[] input = new int[N];
		for (int i =0; i<N; i++) {
			input[i] = fileReader.nextInt();
		}
		Arrays.sort(input);
		int count =0;
		for (int i =0; i<N; i++) {
			for(int j=i+1;j<N;j++) {
				int diff = input[j]-input[i];				
				for(int k=j+1; k<N;k++) {
					if(input[k]- input[j] >= diff &&  input[k]- input[j] <= 2*diff){
						//System.out.println(input[i]+","+input[j]+","+input[k]);
						count++;
					}
				}
			}
		}
		
		output.println(Integer.valueOf(count).toString());
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
		
		public char nextChar() throws IOException {
			return (char)reader.read();
		}
		
		public void close() throws IOException {
			 reader.close();
		}
	}
}