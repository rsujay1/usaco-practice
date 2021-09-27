import java.io.*;
import java.util.*;

/*
14 50 2 32
 */
public class MilkPails2 {
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("pails.out", false);
        PrintWriter out = new PrintWriter(fw);
        FileReaderUtil f = new FileReaderUtil("pails.in");
        int size = 0;

        int X = f.nextInt();
        int Y = f.nextInt();
        int K = f.nextInt();
        int M = f.nextInt();
        LinkedList<Pair> queue = new LinkedList<>();
        queue.add(new Pair(0,0,0));
        List<Pair> finalList = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        while(!queue.isEmpty()) {
            Pair curr = queue.poll();
            if(curr.distance < K-1) {
                queue.addAll(nextOperations(curr, X, Y,visited));
            } else {
                finalList.addAll(nextOperations(curr, X, Y,visited));
            }
        }
        int min = Integer.MAX_VALUE;
        for (Pair p :finalList ) {
            if ( min > Math.abs(M - (p.b1+p.b2))) {
                min = Math.abs(M - (p.b1+p.b2));
            }
        }

        out.println(min);
        out.close();
    }

    private static List<Pair> nextOperations(Pair in, int X, int Y, Set<String> visited) {
        visited.add(in.b1+":"+in.b2);
        List<Pair> out = new ArrayList<>();
        //fill b1

         Pair p = new Pair();
         p.b1 = X;
         p.b2 = in.b2;
         p.distance =in.distance+1;
         if(!visited.contains(p.b1+":"+p.b2)) {
             out.add(p);
             p.print();
         }

        //fill b2

        p = new Pair();
        p.b1 = in.b1;
        p.b2 = Y;
        p.distance =in.distance+1;
        if(!visited.contains(p.b1+":"+p.b2)) {
            out.add(p);
            p.print();
        }
        //empty b1

         p = new Pair();
         p.b1 = 0;
         p.b2 = in.b2;
         p.distance =in.distance+1;
        if(!visited.contains(p.b1+":"+p.b2)) {
            out.add(p);
            p.print();
        }

        //empty b2

         p = new Pair();
        p.b2 = 0;
        p.b1 = in.b1;
        p.distance =in.distance+1;
        if(!visited.contains(p.b1+":"+p.b2)) {
            out.add(p);
            p.print();
        }
        //transfer b1->b2
         p = new Pair();
        int diff = Math.min(Y - in.b2, in.b1);
        p.b2 = in.b2+diff;
        p.b1 = in.b1-diff;
        p.distance =in.distance+1;
        if(!visited.contains(p.b1+":"+p.b2)) {
            out.add(p);
            p.print();
        }
        //transfer b2->b1
        p = new Pair();
        diff = Math.min(X - in.b1, in.b2);
        p.b1 = in.b1+diff;
        p.b2 = in.b2-diff;
        p.distance =in.distance+1;
        if(!visited.contains(p.b1+":"+p.b2)) {
            out.add(p);
            p.print();
        }
        //keep

        p = new Pair();
        p.b2 = in.b2;
        p.b1 = in.b1;
        p.distance =in.distance+1;
        out.add(p);
        p.print();
        return out;
    }

    public static class Pair {

        public Pair() {

        }
        public Pair(int b1, int b2, int distance) {
            this.b1 = b1;
            this.b2 = b2;
            this.distance = distance;
        }

        int b1;
        int b2;
        int distance;

        public  void print() {
            //System.out.println(this.toString());
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "b1=" + b1 +
                    ", b2=" + b2 +
                    ", distance=" + distance +
                    '}';
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