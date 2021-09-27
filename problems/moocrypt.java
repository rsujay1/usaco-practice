import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class moocrypt {
	public static void main(String[] args) throws IOException {

		FileWriter fw = new FileWriter("moocrypt.out", false);
		PrintWriter output = new PrintWriter(fw);
		FileReaderUtil fileReader = new FileReaderUtil("moocrypt.in");

		int N = fileReader.nextInt();
		int M = fileReader.nextInt();
		String[] p = new String[N]; 
		for (int i =0 ; i < N; i++) {
			p[i] = fileReader.next();
		}
		Map<String,Integer> mooCounts = new HashMap<>();
		for (int i =0 ; i < N; i++) {
			for (int j =0 ; j < M; j++) {
				computePossibleMoos(i,j,p, mooCounts, N, M);
			}
		}
		
		Entry<String, Integer> result = maxEntry(mooCounts);
		if (result != null) {
			output.println(result.getValue().toString());
			output.close();
		} else {
			output.println("0");
			output.close();
		}
		fileReader.close();
	}
	

	private static void computePossibleMoos(int r, int c, String[] p, Map<String,Integer> mooCounts, int N, int M) {
		Character current = p[r].charAt(c);
		if ( c+1 < M && c+2 < M) {
			pushToMoocounts(mooCounts,current, p[r].charAt(c+1),p[r].charAt(c+2));
		}
		
		if ( c+1 < M && c+2 < M && r+1 < N && r+2 < N) {
			pushToMoocounts(mooCounts,current, p[r+1].charAt(c+1),p[r+2].charAt(c+2));
		}
		
		if ( r+1 < N && r+2 < N) {
			 pushToMoocounts(mooCounts,current, p[r+1].charAt(c),p[r+2].charAt(c));
		}
		
		if ( c-1 >=  0 && c-2 >= 0 && r+1 < N && r+2 < N) {
			 pushToMoocounts(mooCounts,current, p[r+1].charAt(c-1),p[r+2].charAt(c-2));
		}
	}
	
	private static void pushToMoocounts(Map<String,Integer> mooCounts, Character c1, Character c2, Character c3) {
		 char[] charArray = {c1,c2,c3};
		 Set<Character> chars = new HashSet<>();
		 chars.add(c1);
		 chars.add(c2);
		 chars.add(c3);
		 if ((chars.size() == 2) && ((c1==c2 && c1 != 'O') || (c2 == c3 && c2 !='O'))) {
			 Arrays.sort(charArray);
			 String str = new String(charArray);
			 if(mooCounts.containsKey(str)) {
				 mooCounts.put(str, mooCounts.get(str)+1);
			 } else {
				 mooCounts.put(str, 1);
			 }
		 }		 
	}

	private static Entry<String, Integer> maxEntry (Map<String,Integer> map) {
		Map.Entry<String, Integer> maxEntry = null;

		for (Map.Entry<String, Integer> entry : map.entrySet())
		{
		    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
		    {
		        maxEntry = entry;
		    }
		}
		return maxEntry;
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
