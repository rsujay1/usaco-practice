

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;



public class cownomics{
	public static void main (String[] args) throws IOException {
		FileWriter fw = new FileWriter("cownomics.out", false);
		PrintWriter output = new PrintWriter(fw);	
		FileReaderUtil f = new FileReaderUtil("cownomics.in");
		
		int N = f.nextInt();
		int M = f.nextInt();
		String s[] = new String[N];
		String n[] = new String[N];
		for (int i =0; i < N;i++) {
			s[i] = f.next();
		}
		for (int i =0; i < N;i++) {
			n[i] = f.next();
		}
		int total = 0;
		
		for (int i =0; i < M; i++) {
			for (int j =i+1; j < M; j++) {
				for (int k =j+1; k < M; k++) {
					Set<String> finders = new HashSet<>();
					Set<String> matchers = new HashSet<>();
					for (String astr:s) {
						finders.add(astr.charAt(i)+":"+astr.charAt(j) +":"+astr.charAt(k));
					}
					for (String str:n) {
						matchers.add(str.charAt(i)+":"+str.charAt(j) +":"+str.charAt(k));
					}
					int bs = matchers.size();
					matchers.removeAll(finders);
					if (bs == matchers.size()) {
						total++;
					}
				}
			}
		}

		output.println(total);
		output.close();
		f.close();
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
