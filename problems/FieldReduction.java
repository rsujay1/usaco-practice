


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;


/*4 3
4 7
10 15
2 2
5 1*/


public class FieldReduction {
	
   public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter("reduce.out", false);
		PrintWriter out = new PrintWriter(fw);
		FileReaderUtil f = new FileReaderUtil("reduce.in");
		
		int N= f.nextInt();
		
		
		Node[] xNodes = new Node[N];
		Node[] yNodes = new Node[N];
		if (N < 4) {
			out.println(0);
			out.close();
			return;
		}

		for (int i = 0; i < N; i++) {
			long x = f.nextLong();
			long y = f.nextLong();
			Node xnode = new Node();
			xnode.v = xnode.x = x;
			xnode.y=y;
			xnode.pt = i;
			
			Node ynode = new Node();
			ynode.v = ynode.y = y;
			ynode.x = x;
			ynode.pt = i;
			
			xNodes[i] = xnode;
			yNodes[i] = ynode;
		}
		Arrays.sort(xNodes);
		Arrays.sort(yNodes);
		
		Node[] minxar= {xNodes[0],xNodes[1],xNodes[2],xNodes[3]};
		Node[] maxxar= {xNodes[N-1],xNodes[N-2],xNodes[N-3],xNodes[N-4]};
		
		Node[] minyar= {yNodes[0],yNodes[1],yNodes[2],yNodes[3]};
		Node[] maxyar= {yNodes[N-1],yNodes[N-2],yNodes[N-3],yNodes[N-4]};
		Set<Node> totalPts= new HashSet<>();
		
		for (int i =0 ; i<4; i++) {
			totalPts.add(minxar[i]);
			totalPts.add(maxxar[i]);
			totalPts.add(minyar[i]);
			totalPts.add(maxyar[i]);
		}
		List<Node> list = new ArrayList<>(totalPts); 
		
		
		long area = Long.MAX_VALUE;
		
		
		for (int i =0 ; i<list.size(); i++) {
			for (int j =i+1 ; j<list.size(); j++) {
				for (int k =j+1 ; k<list.size(); k++) {
					List<Node> tempList = new  ArrayList<>(); 
					for (int q=0;q<list.size();q++) {
						
						if(q != i && q != j && q != k) {
							
							tempList.add(list.get(q));	
							
						}
					}
					long x = computeMaxArea(tempList);
					
					if(area > x) {
						
						area = x;
					}
				}
			}
		}
		
		
		
		
		
		/*double midx = 0;
		double midy = 0;
		if (N%2 ==0) {
			midx= ((xNodes[N/2].v) + (xNodes[(N/2)-1]).v)/2.0;
			midy= ((yNodes[N/2].v) + (yNodes[(N/2)-1]).v)/2.0;
		}
		
		for (int i = 0; i < N; i++) {
			xNodes[i].diff = Math.abs(xNodes[i].v - midx);
			yNodes[i].diff = Math.abs(yNodes[i].v - midy);
		}
		
		Arrays.sort(xNodes,new NodeComparator());
		Arrays.sort(yNodes,new NodeComparator());
		Node[] sixnodes = {xNodes[N-1],xNodes[N-2],xNodes[N-3],yNodes[N-1],yNodes[N-2],yNodes[N-3]};
		Arrays.sort(sixnodes, new NodeComparator());
		
		
		Set<Integer> deletedPts= new HashSet<>();
		for (int i=5; i >=0 ;i--) {
			deletedPts.add(sixnodes[i].pt);
			if(deletedPts.size() == 3) {
				break;
			}
		}
		for (int i=3; i >=0 ;i--) {
			if(deletedPts.contains(minxar[i].pt)) {
				minxar[i].deleted = true;
			}
			if(deletedPts.contains(maxxar[i].pt)) {
				maxxar[i].deleted = true;
			}
			if(deletedPts.contains(minyar[i].pt)) {
				minyar[i].deleted = true;
			}
			if(deletedPts.contains(maxyar[i].pt)) {
				maxyar[i].deleted = true;
			}
		}
		
		long minX = 0;
		for (int i=0; i< 4;i++) {
			if (!minxar[i].deleted) {
				minX = minxar[i].v;
				break;
			}
		}
		long maxX = 0;
		for (int i=0; i< 4;i++) {
			if (!maxxar[i].deleted) {
				maxX = maxxar[i].v;
				break;
			}
		}
		long minY = 0;
		for (int i=0; i< 4;i++) {
			if (!minyar[i].deleted) {
				minY = minyar[i].v;
				break;
			}
		}
		long maxY = 0;
		for (int i=0; i< 4;i++) {
			if (!maxyar[i].deleted) {
				maxY = maxyar[i].v;
				break;
			}
		}*/
		
		//long area = (maxX-minX)* (maxY-minY);
		
		out.println(area);
		out.close();
	}


public static long computeMaxArea(List<FieldReduction.Node> tempList) {
	long x = 0l;
	Collections.sort(tempList,new XNodeComparator());
	long mx = tempList.get(0).x;
	long maxX = tempList.get(tempList.size()-1).x;
	//System.out.println(String.format(format, args)tempList.get(0).pt,  	(maxY - my));
	Collections.sort(tempList,new YNodeComparator());
	long my = tempList.get(0).y;
	long maxY = tempList.get(tempList.size()-1).y;
	System.out.println((maxX-mx) * 	(maxY - my));
	return (maxX-mx) * 	(maxY - my);
}

public static class XNodeComparator implements Comparator<Node> {

	@Override
	public int compare(FieldReduction.Node o1, FieldReduction.Node o2) {
		// TODO Auto-generated method stub
		return Double.compare(o1.x, o2.x);
	}
	   
   }

public static class YNodeComparator implements Comparator<Node> {

	@Override
	public int compare(FieldReduction.Node o1, FieldReduction.Node o2) {
		// TODO Auto-generated method stub
		return Double.compare(o1.y, o2.y);
	}
	   
   }
public static class Node implements Comparable<Node> {
	public long v;
	public double diff;
	public long x;
	public long y;
	boolean deleted;
	int pt;
	
	@Override
	public String toString() {
		return "Node [v=" + v + ", diff=" + diff + "]";
	}
	@Override
	public int compareTo(Node o) {
		
		return Long.compare(v, o.v);
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
