

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


public class herding {
	public static void main (String[] args) throws IOException {
		FileWriter fw = new FileWriter("herding.out", false);
		PrintWriter output = new PrintWriter(fw);	
		FileReaderUtil fileReader = new FileReaderUtil("herding.in");
		int N = fileReader.nextInt();
		int[] positions= new int[N];
		
		
		for (int i=0; i < N; i++)  {
			positions[i] = fileReader.nextInt();
		}
		Arrays.sort(positions);
		
		int numberOfblanks = (positions[N-1] - positions[0]+1-N);
		int max = numberOfblanks - Math.min(positions[1]-positions[0]-1, positions[N-1]-positions[N-2]-1);
		
		//-----min
		int maxDensity=0;
		int density =0;
		int left = positions[0];
		int right = positions[0]+N-1;
		int maxLeft = 0;
		
		int i = 0;
		
		while(true) {
			if(positions[i] <= right) {
				density++;
				i++;
				if (i > N-1) {
					if (maxDensity < density) {
						maxDensity = density;
						maxLeft = left;
					}
					break;
				}
			}  else {
				if (maxDensity < density) {
					maxDensity = density;	
					maxLeft = left;
				}
				left = positions[i];
				right = left+N-1;
				
				density=0;
			}			
		}
		
		
		int min = N-maxDensity;
		System.out.println(maxLeft+N-1 < positions[N-1]);
		System.out.println(positions[N-1]-maxLeft);
		System.out.println(Arrays.binarySearch(positions, maxLeft+N-1 ));
		if ( min==1 && positions[N-1]-maxLeft > N && Arrays.binarySearch(positions, maxLeft+N-1 ) < 0) {
			min++;
		}
		
		
		output.println(min);
		output.println(max);
		
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