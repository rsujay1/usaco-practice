
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BessieGetsEven {
	/*
	    10
B 2
E 5
S 7
I 10
O 16
M 19
B 3
G 1
I 9
M 2
	 */

	public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter("geteven.out", false);
		PrintWriter out = new PrintWriter(fw);
		FileReaderUtil f = new FileReaderUtil("geteven.in");
		
		int N= f.nextInt();
		
		int b[] = {0,0};
		int e[] = {0,0};
		int s[] = {0,0};
		int i[] = {0,0};
		int g[] = {0,0};
		int o[] = {0,0};
		int m[] = {0,0};		
		

		for (int k = 0; k < N; k++) {
			String str = f.next();
			int v = f.nextInt();
			//B,E,S,I,G,O,M
			if("B".equals(str)) {
				if(v%2 ==0) {
					b[0]++;					
				} else {
					b[1]++;	
				}
				
			} else if("E".equals(str)) {
				if(v%2 ==0) {
					e[0]++;					
				} else {
					e[1]++;	
				}
			} else if("S".equals(str)) {
				if(v%2 ==0) {
					s[0]++;					
				} else {
					s[1]++;	
				}
			} else if("I".equals(str)) {
				if(v%2 ==0) {
					i[0]++;					
				} else {
					i[1]++;	
				}
			} else if("G".equals(str)) {
				if(v%2 ==0) {
					g[0]++;					
				} else {
					g[1]++;	
				}
			} else if("O".equals(str)) {
				if(v%2 ==0) {
					o[0]++;					
				} else {
					o[1]++;	
				}
			} else if("M".equals(str)) {
				if(v%2 ==0) {
					m[0]++;					
				} else {
					m[1]++;	
				}
			}
		}
		
		
		int total = 0;
		for (int B =0 ; B<2; B++) {
			for (int E =0 ; E<2; E++) {
				for (int S =0 ; S<2; S++) {
					for (int I =0 ; I<2; I++) {
						for (int M =0 ; M<2; M++) {
							for (int G =0 ; G<2; G++) {
								for (int O =0 ; O<2; O++) {		
									int sum =(B+E+S+S+I+E) *(G+O+E+S) *(M+O+O);
									if(sum%2 == 0) {
									   total +=  b[B]*e[E]*s[S]*i[I]*g[G]*o[O]*m[M];
									} 
								}
							}
						}
					}
				}
			}
		}
		
		out.println(total);
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

