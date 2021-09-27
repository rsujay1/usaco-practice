
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;


/*
 ())(
 * 
 */


public class ClumsyCows {
	
   public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter("clumsy.out", false);
		PrintWriter out = new PrintWriter(fw);
		FileReaderUtil f = new FileReaderUtil("clumsy.in");		
		
		String in = f.next();
		Stack<Character> s = new Stack<>();
		int flip = 0;
		for (int i = 0; i < in.length(); i++) {
			if(in.charAt(i) == '(') {
				s.push(in.charAt(i));
			} else {
				if(s.size() > 0) {
					s.pop();
				} else {
					s.push(in.charAt(i));
					flip++;
				}
			}
		}
		
		if (!s.isEmpty()) {
			flip += s.size()/2;
		}
		out.println(flip);
		out.close();
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
