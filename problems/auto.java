

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class auto {
	public static void main (String[] args) throws IOException {
		FileWriter fw = new FileWriter("auto.out", false);
		PrintWriter output = new PrintWriter(fw);	
		FileReaderUtil fileReader = new FileReaderUtil("auto.in");
		
		int lb= 1; int ub = fileReader.nextInt();
		int w = fileReader.nextInt();
		String[] dWords = new String[ub]; 
		Map<String,Integer> map = new HashMap();
		for (int i = 0; i < ub ; i++ ) {
			dWords[i] = fileReader.next();
			map.put(dWords[i],i+1);
		}
		Arrays.sort(dWords);
		
		//smallest of its kind
		
		for (int i = 1; i <= w ; i++ ) {
			int pos = fileReader.nextInt();
			String search = fileReader.next();
			int a = 0; int b = ub-1;
			while(a!=b) {
				int mid = (a+b)/2;
				if (search.compareTo(dWords[mid]) <= 0) {
					b = mid;
				} else {
					a = mid+1;
				}
			}
			if (a+pos-1 < ub && dWords[a+pos-1].startsWith(search)) {
				output.println(map.get(dWords[a+pos-1]));
			} else {
				output.println(-1);
			}
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
		
		public char nextChar() throws IOException {
			return (char)reader.read();
		}
		
		public void close() throws IOException {
			 reader.close();
		}
	}
}