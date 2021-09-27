import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Shuffle {

	public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter("shuffle.out", false);
		PrintWriter out = new PrintWriter(fw);
		FileReaderUtil f = new FileReaderUtil("shuffle.in");
		
		int N = f.nextInt();
		Node[] c = new Node[N];		
		
		
		for (int i = 0; i < N; i++) {
			c[i] = new Node();
			c[i].dPos=f.nextInt()-1;
			c[i].color = 0;
		}		
		
		int cycleSize = 0;
		
		
		for (int i = 0; i < N; i++) {
			if(c[i].color == 0 ) {
				cycleSize +=findCycleSize(i, c);
			}
		}
		
		
		out.println(cycleSize);
		out.close();
	}
	
	public static int findCycleSize(int nonVistedIndex, Node[] c) {
		int cycleSize = 0;
		int visited = 1; 
		
		int cycleBeginIndex = -1;
		List<Integer> visitedL = new ArrayList<>();
		int p = nonVistedIndex;
		while(true) {
			if (c[p].color == visited)
			for(int i:visitedL) {
				if(p == i ) {
					cycleBeginIndex = i;
					break;
				}							
			}			
			
		    if (cycleBeginIndex >= 0) {
		    	int i = cycleBeginIndex;
		    	cycleSize++;
		    	while(true) {
		    		if (c[i].dPos != cycleBeginIndex) {
		    			cycleSize++;
		    			i = c[i].dPos;
		    			
		    			
		    		} else {
		    			break;
		    		}
		    	}
		    	if(cycleSize > 1) { 
		    		return cycleSize;
		    	}
		    } 
		    
		    if (c[p].color == visited) {
		    	break;
		    } else {
		    	c[p].color = visited;
		    	visitedL.add(p);
		    	p = c[p].dPos;
		    	cycleBeginIndex = -1;
		    }
	    
		}
		return cycleSize;
		
	}

	public static class Node {
		
		public int color;
		public int dPos;
	
		
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

