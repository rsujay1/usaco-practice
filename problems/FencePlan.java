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

public class FencePlan {

	public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter("fenceplan.out", false);
		PrintWriter out = new PrintWriter(fw);
		FileReaderUtil f = new FileReaderUtil("fenceplan.in");
	
		int N = f.nextInt();
		int M = f.nextInt();
		

		Cow[] gT = new Cow[N];
		
		for (int i = 0; i< N; i++) {
			gT[i] = new Cow();
			gT[i].x= f.nextInt();
			gT[i].y= f.nextInt();
		}
		
		for (int i = 0; i< M; i++) {
			int c1= f.nextInt()-1;
			int c2 = f.nextInt()-1;
			gT[c1].edges.add(c2);
			gT[c2].edges.add(c1);
		}		
		
		List<Result> results = new ArrayList<>();
		int color = 1;
		for (int i = 0; i < N; i++) {
			if (gT[i].color ==0) {
				Result r = new Result();
				floodfill(N,i,color,gT,r);
				results.add(r);
				color++;
			}
		}		
		
		Collections.sort(results);
		out.println(results.size()>0? results.get(0).getValue():0);
		out.close();
	}
	public static class Result implements Comparable<Result>{
		public int lx=Integer.MAX_VALUE;
		public int ly=Integer.MAX_VALUE;
		public int hx;
		public int hy;
		public int getValue() {
			return 2*((hx-lx)+(hy-ly));
		}
		@Override
		public int compareTo(Result o) {
			return Integer.compare((hx-lx)+(hy-ly), (o.hx-o.lx)+(o.hy-o.ly));
		}
	}
	public static class Cow  {
		public int x;
		public int y;
		public List<Integer> edges =new ArrayList<>();
		public int color;
	}



	private static void floodfill(int N, int i,  int color, Cow[] gT, Result r) {
		// base case
		Cow fd = gT[i];
		if (fd.color != 0) {
			return;
		}
		fd.color = color;
		if (r.hx < fd.x) {
			r.hx = fd.x; 
		}
		if (r.hy < fd.y) {
			r.hy = fd.y; 
		}
		if (r.lx > fd.x) {
			r.lx = fd.x; 
		}
		if (r.ly > fd.y) {
			r.ly = fd.y; 
		}
		
		for(int j:fd.edges) {
			floodfill(N,j,  color, gT,r);
		}
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
