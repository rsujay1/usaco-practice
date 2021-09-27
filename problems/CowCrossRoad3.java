import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class CowCrossRoad3 {

	public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter("countcross.out", false);
		PrintWriter out = new PrintWriter(fw);
		FileReaderUtil f = new FileReaderUtil("countcross.in");
	
		int N = f.nextInt();
		int K = f.nextInt();
		int R = f.nextInt();

		Field[][] gT = new Field[N][N];
		
		for (int i = 0; i< N; i++) {
			for (int j = 0; j < N; j++) {
				gT[i][j] = new Field();
			}
		}
		for (int i = 0; i< R; i++) {
			int x1=f.nextInt()-1;
			int y1=f.nextInt()-1;
			int x2=f.nextInt()-1;
			int y2=f.nextInt()-1;
			if (gT[x1][y1].roads ==null) {
				gT[x1][y1].roads = new HashSet<>(); 
			}
			gT[x1][y1].roads.add(x2+":"+y2);
			if (gT[x2][y2].roads ==null) {
				gT[x2][y2].roads = new HashSet<>(); 
			}
			gT[x2][y2].roads.add(x1+":"+y1);
		}
		
		for (int i = 0; i< K; i++) {
			int x1=f.nextInt()-1;
			int y1=f.nextInt()-1;
			gT[x1][y1].cowIn = true;				
		}
		
		
		int color = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (gT[i][j].color ==0) {
					floodfill(N,i,j,color,gT);
					color++;
				}
			}
		}
		int cCounts[] = new int[color-1];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (gT[i][j].cowIn) {
					int pos= gT[i][j].color-1;
					cCounts[pos]++;
				}
			}
		}
		int total = 0;
		for (int i = 0; i < color-1; i++) {
			for (int j = i+1; j < color-1; j++) {
				total +=cCounts[i] * cCounts[j];
			}
		}
		
		
	
		out.println(total);
		out.close();
	}

	private static class Field  {
		public boolean cowIn;
		public Set<String> roads;
		public int color;
	}



	private static void floodfill(int N, int x, int y, int color, Field[][] gT) {
		// base case
		Field fd = gT[x][y];
		if (fd.color != 0) {
			return;
		}
		fd.color = color;
		
		// transitions
		if (x - 1 >= 0 && gT[x - 1][y].color == 0 && !(fd.roads != null && fd.roads.contains((x-1)+":"+y))) {
			floodfill(N,x-1, y, color, gT);
		}
		if (x + 1 < N && gT[x + 1][y].color == 0 && !(fd.roads != null && fd.roads.contains((x+1)+":"+y))) {
			floodfill(N,x+1, y, color, gT);
		}
		if (y - 1 >= 0 && gT[x ][y-1].color == 0 && !(fd.roads != null && fd.roads.contains(x+":"+(y-1)))) {
			floodfill(N,x, y-1, color, gT);
		}
		if (y + 1 < N && gT[x ][y+1].color == 0 && !(fd.roads != null && fd.roads.contains(x+":"+(y+1)))) {
			floodfill(N,x, y+1, color, gT);
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