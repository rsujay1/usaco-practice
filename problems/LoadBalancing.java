import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;
/*
7
7 3
5 5
7 13
3 1
11 7
5 3
9 1
 */
public class LoadBalancing {
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("balancing.out", false);
        PrintWriter out = new PrintWriter(fw);
        FileReaderUtil f = new FileReaderUtil("balancing.in");

        int n = f.nextInt();
        Node[] list = new Node[n];
        for(int i = 0; i < n; i++) {

            list[i] = new Node(f.nextInt(), f.nextInt());
        }
        Arrays.sort(list);
        int ret = list.length;
        for(int i = 0; i < n; i++) {
            ArrayList<Node> below = new ArrayList<Node>();
            ArrayList<Node> above = new ArrayList<Node>();
            for(int j = 0; j < n; j++) {
                if(list[j].y <= list[i].y) {
                    below.add(list[j]);
                }
                else {
                    above.add(list[j]);
                }
            }
            int belowIndex = 0;
            int aboveIndex = 0;
            while(belowIndex < below.size() || aboveIndex < above.size()) {
                int xBorder = 0;
                if(belowIndex < below.size() && aboveIndex < above.size()) {
                    xBorder = Math.min(below.get(belowIndex).x, above.get(aboveIndex).x);
                } else if(belowIndex < below.size()) {
                    xBorder = below.get(belowIndex).x;
                } else {
                    xBorder = above.get(aboveIndex).x;
                }

                while(belowIndex < below.size() && below.get(belowIndex).x == xBorder) {
                    belowIndex++;
                }
                while(aboveIndex < above.size() && above.get(aboveIndex).x == xBorder) {
                    aboveIndex++;
                }
                ret = Math.min(ret, Math.max(Math.max(belowIndex, below.size() - belowIndex), Math.max(aboveIndex, above.size() - aboveIndex)));
            }
        }
        out.println(ret);
        out.close();
    }

    static class Node implements Comparable<Node> {
        public int x,y;

        public Node(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }
        public int compareTo(Node s) {
            return x - s.x;
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
        public String nextLine() throws IOException {
            return reader.readLine();
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
