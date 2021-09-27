import java.io.*;
import java.util.*;

public class MilkVisits2 {
/*
5 5
HHGHG
1 2
2 3
2 4
1 5
1 4 H
1 4 G
1 3 G
1 3 H
5 5 H
     */
    public static int N ;
    public static int M ;
    public static Node[] ns;

    public static void main(String[] args) throws IOException {
      //  FileWriter fw = new FileWriter("milkvisits.out", false);
       // FileReaderUtil s = new FileReaderUtil("milkvisits.in");
        Scanner s = new Scanner(System.in);
        N = s.nextInt();
        ns = new Node[N];
        M = s.nextInt();
        String data = s.next();
        int j = 0;
        for (char c : data.toCharArray()) {
            Node n = new Node();
            n.pos = j;
            if (c == 'G') {
                n.data = 1;
            } else {
                n.data = 2;
            }
            ns[j] = n;
            j++;

        }
        for (int i = 0; i < N - 1; i++) {
            int x = s.nextInt();
            int y = s.nextInt();
            ns[x - 1].edges.add(ns[y - 1]);
            ns[y - 1].edges.add(ns[x - 1]);
        }

        int group = 1;
        for (int k = 0; k < N; k++) {
            if(ns[k].group == -1) {
                //floodfill(k,group);
                bfs(k,group);
            }
            group++;
        }
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < M; i++) {
            int start = s.nextInt() - 1;
            int end = s.nextInt() - 1;
            int choice = s.next().equals("G") ? 1 : 2;
            //System.out.println(start + ":" + end + ":" + ":" + choice);

            if (ns[start].data == choice || ns[end].data == choice) {
                out.append("1");
            } else {
                if(ns[start].group != ns[end].group) {
                    out.append("1");
                } else {
                    out.append("0");
                }
            }
        }
        //PrintWriter ofile = new PrintWriter(fw);
        //ofile.println(out);
       // ofile.close();
        s.close();
        System.out.println(out.toString());
    }

    private static void floodfill(int k, int group) {
        Node c = ns[k];
        if(ns[k].group == group) {
            return;
        }
        ns[k].group = group;

        for(Node adj: ns[k].edges) {
            if(adj.pos != k && adj.data == ns[k].data) {
                floodfill(adj.pos,group);
            }
        }
    }

    private static void bfs(int k, int group) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(k);
        while(!queue.isEmpty()) {
            Node c = ns[queue.poll()];
            c.group = group;
            for(Node adj: c.edges) {
                if(adj.group != group && adj.pos != k && adj.data == c.data) {
                    queue.add(adj.pos);
                }
            }
        }
    }

    public static class Node {
        public int data;
        public int pos;
        public List<Node> edges = new ArrayList<>();
        public int group =-1;
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

        public String nextLine() throws IOException {
            return reader.readLine();
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