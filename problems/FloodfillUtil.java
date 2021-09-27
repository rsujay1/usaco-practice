import misc.week6.HorseShoes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FloodfillUtil {
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("hshoe.out", false);
        PrintWriter out = new PrintWriter(fw);
        HorseShoes.FileReaderUtil f = new HorseShoes.FileReaderUtil("hshoe.in");
        int N = f.nextInt();
        HorseShoes.Node[][] c = new HorseShoes.Node[N][N];
        for (int i = 0; i < N; i++) {
            String ln = f.next();
            if ((i == 0 && ln.charAt(0) == ')') || N == 1) {
                out.println(0);
                out.close();
                return;
            }
            for (int j = 0; j < N; j++) {
                HorseShoes.Node n = new HorseShoes.Node();
                c[i][j] = n;
                n.x = i;
                n.y = j;
                char ch = ln.charAt(j);
                n.data = ch == '(' ? 1 : 2;

            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                HorseShoes.Node n = c[i][j];
                int x = n.x;
                int y = n.y;
                if (x - 1 >= 0) {
                    n.adjs.add(c[x - 1][y]);
                }
                if (x + 1 < N) {
                    n.adjs.add(c[x + 1][y]);
                }
                if (y - 1 >= 0) {
                    n.adjs.add(c[x][y - 1]);
                }
                if (y + 1 < N) {
                    n.adjs.add(c[x][y + 1]);
                }
            }
        }
        floodfill(0, 0, c, new ArrayList<>());
        int maxSize = 8;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                HorseShoes.Node n = c[i][j];
                for (List<HorseShoes.Node> trail : n.parentTrails) {
                    int ind = 0;
                    int begin = 0;
                    int end = 0;
                    while (ind < trail.size() && trail.get(ind).data == 1) {
                        begin++;
                        ind++;
                    }
                    while (ind < trail.size() && trail.get(ind).data == 2) {
                        end++;
                        ind++;
                    }
                    if (end >= begin && maxSize < 2 * begin) {
                        maxSize = 2 * begin;
                    }
                }
            }
        }
        out.println(maxSize);
        out.close();
    }

    public static void floodfill(int x, int y, HorseShoes.Node[][] c, List<HorseShoes.Node> path) {
        HorseShoes.Node cn = c[x][y];
        if (path.contains(cn)) {
            return;
        }
        cn.parentTrails.add(path);
        path.add(cn);
        for (HorseShoes.Node i : cn.adjs) {
            List<HorseShoes.Node> newPath = new ArrayList<>();
            newPath.addAll(path);
            floodfill(i.x, i.y, c, newPath);
        }
    }

    public static class Node {
        public int x;
        public int y;
        public int data;
        public int validSize;
        public List<HorseShoes.Node> adjs = new ArrayList<>();
        public List<List<HorseShoes.Node>> parentTrails = new ArrayList<>();
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

        public String nextLine() throws IOException {
            return reader.readLine();
        }

        public char nextChar() throws IOException {
            return (char) reader.read();
        }

        public void close() throws IOException {
            reader.close();
        }
    }
}