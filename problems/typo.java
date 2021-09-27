
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class typo {
	public static void main (String[] args) throws IOException {
		FileWriter fw = new FileWriter("typo.out", false);
		PrintWriter output = new PrintWriter(fw);	
		FileReaderUtil fileReader = new FileReaderUtil("typo.in");
		long count = 0;
		long count1 =0, count2 = 0;
		
		String str = fileReader.next();
		
		char[] in = str.toCharArray();
		
		
		int[] prefix= new int[str.length()+1];
		Map<Integer,Integer> negatives= new TreeMap<>();
		Map<Integer,Integer> ones= new TreeMap<>();
		
		int k=1;
		for (char c:in) {
		   if (c =='(') {
			   count1++;
			   prefix[k] = prefix[k-1] + 1;
		   } else {
			   count2++;
			   prefix[k] = prefix[k-1] - 1;
		   }
		   if (prefix[k] < 0){
			   negatives.put(k, prefix[k]);
		   } else {
			   if (prefix[k] == 1) {
				   ones.put(k, prefix[k]);
			   }
		   }
		   
		   k++;
		}
		
			for (int i =0;i < in.length;i++) {
				if (count1 < count2) {
					if (i < in.length-1 &&  in[i] == ')' && goodFlip(i+1,in[i], prefix,negatives,ones) ) {
						count++;
					}	
				} else {
					if (i > 0 && in[i] == '(' && goodFlip(i+1,in[i], prefix, negatives, ones) ) {
						count++;
					}					
				}				
			
		}
		
		output.println(
				Long.valueOf(count).toString() );
		
				
		output.close();
		fileReader.close();
	}
	
	
	private static boolean goodFlip( int index, char t, int[] prefix, Map<Integer,Integer> negatives, Map<Integer,Integer> ones) {
		
		int value = 0;
		int change = 0;
		if (t == '(') {
			value = prefix[prefix.length-1] -2; 
			change = -2;
		} else {
			value = prefix[prefix.length-1] +2; 
			change = +2;
		}
		if (value == 0) {
			Iterator<Integer> keys = negatives.keySet().iterator();
			while (keys.hasNext()) {
				int key = keys.next();
				if (key < index) {
					return false;
				} else {
					if (negatives.get(key) +change < 0 ) {
						return false;
					}
				}				
			}
			
			if ( change == -2) {
				Iterator<Integer> onesKeys = ones.keySet().iterator();
				while (onesKeys.hasNext()) {
					int key = onesKeys.next();
					if (key >= index) {
						return false;
					}			
				}
			}
			
			return  true;
		} else {
			return false;
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
			return (char)reader.read();
		}
		
		public void close() throws IOException {
			 reader.close();
		}
	}
}