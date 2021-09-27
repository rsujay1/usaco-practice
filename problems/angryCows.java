import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class angryCows {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileWriter fw = new FileWriter("angry.out", false);
		PrintWriter output = new PrintWriter(fw);	
		FileReaderUtil fileReader = new FileReaderUtil("angry.in");
		
		long lowerbound = 0;
		long upperbound = 500000;
		long bales = fileReader.nextLong();
		int cows = fileReader.nextInt();
		long[] positions = new long[(int) bales];
		for (int i = 0; i < bales; i++) {
			positions[i] = fileReader.nextLong();
		}
		Arrays.sort(positions);
		while (upperbound != lowerbound) {
			long mid = (upperbound - lowerbound) / 2 + lowerbound;
			if (works(cows, positions, mid)) {
				upperbound = mid;
			} else {
				lowerbound = mid + 1;
			}
		}
		if (works(cows, positions, upperbound)) {
			output.println(upperbound);
		}
		//System.out.print(works(cows, positions, 3));
		output.close();
		fileReader.close();
	}
	
	public static boolean works(int cows, long[] pos, long range) {
		range *= 2;
		int count = 0;
		long start = pos[0];
		for (int i = 0; i < pos.length; i++) {
			if (pos[i] - start > range) {
				start = pos[i];
				count++;
			}
		}
		count++;
		return count <= cows;
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
