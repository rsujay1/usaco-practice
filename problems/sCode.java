import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class sCode {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileWriter fw = new FileWriter("scode.out", false);
		PrintWriter output = new PrintWriter(fw);
		FileReaderUtil fileReader = new FileReaderUtil("scode.in");

		String message = fileReader.next();
		output.println(recurse(message, 0));
		fileReader.close();
		output.close();
	}

	public static int recurse(String message, int count) {
		int len = message.length();
		if (len % 2 == 0) {
			return count;
		}
		int answer = count;
		int front = recurse(message.substring(0, len / 2 + 1), 1);
		int back = 0;
		if (message.substring(len / 2).equals(message.substring(0, len / 2 + 1))) {
			back = front;
		} else {
			back = recurse(message.substring(len / 2), 1);
		}
		
		if (message.substring(len / 2 + 1).equals(message.substring(0, len / 2))) {
			answer += (front + back);
		}
		if (message.substring(1, len / 2 + 1).equals(message.substring(len / 2 + 1))) {
			answer += front;
		}
		if (message.substring(len / 2, len - 1).equals(message.substring(0, len / 2))) {
			answer += back;
		}
		return answer;
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
