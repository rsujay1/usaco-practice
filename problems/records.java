
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

public class records {
	public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter("records.out", false);
		PrintWriter output = new PrintWriter(fw);
		FileReaderUtil f = new FileReaderUtil("records.in");

		int N = f.nextInt();
		Map<String,Integer> m = new HashMap<>();
		int max = 0;
		
		String[] row = new String[3];
		for (int i = 0; i < N; i++) {
			row[0]= f.next();
			row[1]= f.next();
			row[2]= f.next();
			Arrays.sort(row);
			String key = row[0]+row[1]+row[2];
			int v = 1;
			if (m.containsKey(key)) {
				 v = m.get(key)+1;
			}
			if (max < v) {
				max =  v;
			}
			m.put(key, v);
		}
		
		
		output.println(max);
		
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
			return (char) reader.read();
		}

		public void close() throws IOException {
			reader.close();
		}
	}
}
