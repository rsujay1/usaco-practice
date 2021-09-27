

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MirrorField {

	public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter("mirror.out", false);
		PrintWriter out = new PrintWriter(fw);
		FileReaderUtil f = new FileReaderUtil("mirror.in");
		
		int N = f.nextInt();
		int M = f.nextInt();
		Node[][] c = new Node[N][M];
		for (int i= 0 ; i < N; i++) {
			String ln = f.next();
			for (int j= 0 ; j < M; j++) {
				Node n = new Node();
				c[i][j] =n;
				n.i = i;
				n.j =j;
				char ch = ln.charAt(j);
				n.isType1 = ch == '/';
				
			}
		}
		int maxSize = -1;
		for (int i = 0; i < N;i++) {
			int x = i;
			int y = -1;
			int size = computeSize(x, y, c,N,M);
			if ( maxSize < size) {
				maxSize = size;
			}
		}
		for (int i = 0; i < N;i++) {
			int x = i;
			int y = M;
			int size = computeSize(x, y, c,N,M);
			if ( maxSize < size) {
				maxSize = size;
			}
		}
		for (int i = 0; i < M;i++) {
			int x = -1;
			int y = i;
			int size = computeSize(x, y, c,N,M);
			if ( maxSize < size) {
				maxSize = size;
			}
		}
		for (int i = 0; i < M;i++) {
			int x = N;
			int y = i;
			int size = computeSize(x, y, c,N,M);
			if ( maxSize < size) {
				maxSize = size;
			}
		}
		
		out.println(maxSize);
		out.close();
	}	


	private static int computeSize(int x, int y, MirrorField.Node[][] c, int N, int M) {
		
		int cX = -1;
		int cY = -1;
		if (x >= 0 && x < N) {
			cX= x;
			if (y == -1) {
				cY = y+1;
			} else {
				cY = y-1;
			}
		} else if (y >= 0 && y < M) {
			cY = y;
			if (x == -1) {
				cX = x+1;
			} else {
				cX = x-1;
			}
		}
		
		Node current = c[cX][cY];
		int size = 1;
		
		while(current != null) {
			
			Node t = current.next(x, y,c, N,M);
			
			if (t != null) {
				
				size++;
			}
			x= current.i;
			y=current.j;
			current = t;
			
		}
		
		return size;
	}


	public static class Node{
		public int i;
		public int j;
		public boolean isType1 = false; //2,3 - /
		
		public Node next(int x, int y, Node[][] all, int N, int M) {
			Node n = null;
			int nextX=-1, nextY =-1;
			if (isType1) {
				if (x== i-1 && y ==j) {
					nextX= i;
					nextY= j-1;
				} else if (x== i+1 && y ==j) {
					nextX= i;
					nextY= j+1;
				} else if (x== i && y ==j-1) {
					nextX= i-1;
					nextY= j;
				} else if (x== i && y ==j+1) {
					nextX= i+1;
					nextY= j;
				}
				
			} else {
				//  1,2
				if (x== i-1 && y ==j) {
					nextX= i;
					nextY= j+1;
				} else if (x== i+1 && y ==j) {
					nextX= i;
					nextY= j-1;
				} else if (x== i && y ==j-1) {
					nextX= i+1;
					nextY= j;
				} else if (x== i && y ==j+1) {
					nextX= i-1;
					nextY= j;
				}
			}
			if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < M) {
				n = all[nextX][nextY];
			}
			return n;
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
