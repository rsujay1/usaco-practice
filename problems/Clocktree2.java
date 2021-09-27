import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
4
11 10 11 11
1 2
2 3
2 4
 */
public class Clocktree2 {
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("clocktree.out", false);
        PrintWriter out = new PrintWriter(fw);
        FileReaderUtil f = new FileReaderUtil("clocktree.in");
        int size = 0;

        int N = f.nextInt();

        Room rooms[] = new Room[N];
        for (int i = 0; i < N; i++) {
            Room room = new Room();
            room.timing = f.nextInt();
            room.number = i;
            rooms[i] = room;
        }

        for (int i = 0; i < N-1; i++) {
            int r1= f.nextInt();
            int r2= f.nextInt();
            rooms[r1-1].edges.add(rooms[r2-1]);
            rooms[r2-1].edges.add(rooms[r1-1]);
        }
        int count = 0;

        for (int i=0; i<N;i++) {
            int time[] = new int[N];
            for (int j=0; j<N;j++) {
                time[j] = rooms[j].timing;
            }

            traverse(rooms[i], rooms[i], time);
            if ( time[i] % 12 == 0) {
                count++;
            }
        }

        out.println(count);
        out.close();
    }



    public static int traverse(Room curr, Room parent,int[] time){ // post order traversal
        for(Room i : curr.edges){
            if(i != parent) {
                time[curr.number] += (12 - traverse(i, curr,time));
                time[curr.number] = (time[curr.number] -1 ) %12 +1;
            }
        }
        time[parent.number]--;
        return time[curr.number];
    }


    public static class Room {

        int timing;
        int number;
        List<Room> edges = new ArrayList<>();
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

