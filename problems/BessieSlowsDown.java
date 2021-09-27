import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BessieSlowsDown {
	/*
	    2
		T 30
		D 10
	 */

	public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter("slowdown.out", false);
		PrintWriter out = new PrintWriter(fw);
		FileReaderUtil f = new FileReaderUtil("slowdown.in");
		
		int N= f.nextInt();
		
		List<Integer> dList = new ArrayList<>();
		
		List<Integer> tList = new ArrayList<> ();
		
		
		

		for (int i = 0; i < N; i++) {
			String str = f.next();
			int v = f.nextInt();
			if("D".equals(str)) {
				dList.add( Integer.valueOf(v));
			}  else {
				tList.add( Integer.valueOf(v));
			}
		}
		
		Collections.sort(dList);
		Collections.sort(tList);
		double dis = 0;
		double taken = 0;

		int di = 0;
		int ti = 0;
		double j = 2;
		double currentRate = 1.0;
		
		while (di < dList.size() || ti < tList.size()) {			
			if(di < dList.size() && ti < tList.size()) {
				double time = taken+(dList.get(di) - dis) / currentRate;
			
				if(time < tList.get(ti)) {
					dis= dList.get(di);
					taken = time;	               
	                currentRate = 1/j;
	                j++;
					di++;							
				} else if (time > tList.get(ti)){
					dis= dis + (tList.get(ti) - taken) * currentRate;
					taken = tList.get(ti);	               
	                currentRate = 1/j;
	                j++;
					ti++;
				} else {
					dis= dList.get(di);
					taken = time;
					j++;
	                currentRate = 1/j;
	                j++;
					di++;
					ti++;
				}
			} else if(di < dList.size()) {
				double time = taken+(dList.get(di) - dis) / currentRate;
				dis= dList.get(di);
				taken = time;	               
                currentRate = 1/j;
                j++;
				di++;				
			} else if(ti < tList.size()) {
				dis= dis + (tList.get(ti) - taken) * currentRate;
				taken = tList.get(ti);	               
                currentRate = 1/j;
                j++;
				ti++;				
			}
		}
		if (dis < 1000.0) {
			taken = taken + ((1000 -dis) / currentRate);
		}
		out.println(Math.round(taken));
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

