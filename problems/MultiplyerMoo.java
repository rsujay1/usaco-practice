import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/*
4
2 3 9 3
4 9 9 1
9 9 1 7
2 1 1 9
 */

public class MultiplyerMoo {

	public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter("multimoo.out", false);
		PrintWriter out = new PrintWriter(fw);
		FileReaderUtil f = new FileReaderUtil("multimoo.in");
	
		int N = f.nextInt();
		
		Node[][] gT = new Node[N][N];
		
		for (int i = 0; i< N; i++) {
			for (int j = 0; j < N; j++) {
				Node n = new Node();
				n.x= i;
				n.y= j;
				n.data= f.nextLong();
				gT[i][j] = n;
			}
		}
		
		
		int color = 1;
		List<Group> groups = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (gT[i][j].color ==0) {
					Group gp = new Group();
					groups.add(gp);
					gp.color = color;
					HashSet<Long> data = new HashSet<>();
					data.add(gT[i][j].data);
					floodfill(N,i,j,color,gT,data, gp);
					color++;
				}
			}
		}
		int maxGroupSize = 0;
		for (int i = 0; i < groups.size(); i++) {
			Group cgp = groups.get(i);
			for (int j = 0; j < cgp.size; j++) {
				Set<Group> adj= getAdjGroup(cgp, gT, N);
				cgp.adj = adj;
				if(maxGroupSize < cgp.size) {
					maxGroupSize = cgp.size;
				}
			}
		}
		
		
		
		
		int maxCoupleSize = 0;
		for (int i = 0; i < groups.size(); i++) {
			Group cgp = groups.get(i);
			for (Group adj :cgp.adj) {
				HashSet<Long> data = new HashSet<>();
				data.addAll(cgp.data);
				data.addAll(adj.data);
				Result r = new Result();
				floodfill(N,cgp.groupdNodes.get(0).x,cgp.groupdNodes.get(0).y,color,gT,data, r);			
				color++;
				if(maxCoupleSize < r.size) {
					maxCoupleSize = r.size;
				}
			}
		}
		
		out.println(maxGroupSize);
		out.println(maxCoupleSize);
		out.close();
	}
	
	private static Set<Group> getAdjGroup(Group cgp, Node[][] gT, int N) {
		Set<Group> adj= new HashSet<>();
		for (int j = 0; j < cgp.groupdNodes.size(); j++) {
			Node fd = cgp.groupdNodes.get(j);
			int x = fd.x;
			int y = fd.y;
			
			if (x - 1 >= 0  &&  !fd.edges.contains((x-1)+":"+y)) {
				adj.add(gT[x-1][y].group);
			}
			if (x + 1 < N  && !fd.edges.contains((x+1)+":"+y)) {
				adj.add(gT[x+1][y].group);
			}
			if (y - 1 >= 0  &&  !fd.edges.contains(x+":"+(y-1))) {
				adj.add(gT[x][y-1].group);
			}
			if (y + 1 < N  && !fd.edges.contains(x+":"+(y+1))) {
				adj.add(gT[x][y+1].group);
			}
		}
		return adj; 
	}
	
	private static void floodfill(int N, int x, int y, int color, Node[][] gT, HashSet<Long> data, Group gp) {
		// base case
		Node fd = gT[x][y];
		if (fd.color != 0) {
			return;
		}
		fd.color = color;
		gp.size++;
		gp.data = data;
		gp.groupdNodes.add(fd);
		fd.group = gp; 
		
		
		// transitions
		if (x - 1 >= 0 && gT[x - 1][y].color == 0 && !(fd.edges != null && fd.edges.contains((x-1)+":"+y)) && data.contains(gT[x - 1][y].data)) {
			fd.edges.add(gT[x - 1][y]);
			floodfill(N,x-1, y, color, gT,data,gp);
		}
		if (x + 1 < N && gT[x + 1][y].color == 0 && !(fd.edges != null && fd.edges.contains((x+1)+":"+y)) && data.contains(gT[x + 1][y].data)) {
			fd.edges.add(gT[x + 1][y]);
			floodfill(N,x+1, y, color, gT,data,gp);
		}
		if (y - 1 >= 0 && gT[x ][y-1].color == 0 && !(fd.edges != null && fd.edges.contains(x+":"+(y-1)))&& data.contains(gT[x ][y-1].data)) {
			fd.edges.add(gT[x][y-1]);
			floodfill(N,x, y-1, color, gT,data,gp);
		}
		if (y + 1 < N && gT[x ][y+1].color == 0 && !(fd.edges != null && fd.edges.contains(x+":"+(y+1)))&& data.contains(gT[x ][y+1].data)) {
			fd.edges.add(gT[x ][y+1]);
			floodfill(N,x, y+1, color, gT,data,gp);
		}
		
	}
	
	private static void floodfill(int N, int x, int y, int color, Node[][] gT, HashSet<Long> data, Result result) {
		// base case
		Node fd = gT[x][y];
		if (fd.color == color) {
			return;
		}
		fd.color = color;
		
		result.size++;
		
		// transitions
		if (x - 1 >= 0 && gT[x - 1][y].color != color &&  data.contains(gT[x - 1][y].data)) {
			floodfill(N,x-1, y, color, gT,data,result);
		}
		if (x + 1 < N && gT[x + 1][y].color != color  && data.contains(gT[x + 1][y].data)) {
			floodfill(N,x+1, y, color, gT,data,result);
		}
		if (y - 1 >= 0 && gT[x ][y-1].color != color && data.contains(gT[x ][y-1].data)) {
			floodfill(N,x, y-1, color, gT,data,result);
		}
		if (y + 1 < N && gT[x ][y+1].color != color && data.contains(gT[x ][y+1].data)) {
			floodfill(N,x, y+1, color, gT,data,result);
		}
		
	}
	
	public static class Result {
		public int size;
	}
	
	public static class Node  {
		public int x;
		public int y;
		public Long data;
		public int size;		
		public List<Node> edges =new ArrayList<>();
		public int color;
		public Group group;
	}
	
	public static class Group implements Comparable<Group>{
		
		public List<Node> groupdNodes =new ArrayList<>();
		public int color;
		public int size;
		public Set<Long> data;
		public Set<Group> adj;
		@Override
		public int compareTo(Group o) {
			return -1 * Integer.compare(size,o.size);
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
