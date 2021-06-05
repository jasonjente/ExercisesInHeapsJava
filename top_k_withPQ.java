import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class top_k_withPQ {

    private static final String path = "C:\\Users\\jente\\IdeaProjects\\DomesDedomenwnErgasia2\\src\\songs.txt";
    public static void main(String[] args) throws IOException {
        File file =  new File(path);
        Scanner sc = new Scanner(file);
        int k = maxK();
       // int maxlines = top_k.countLines(file);
        PriorityQueue<Song> pq = new PriorityQueue<>(2*k, new SongComparator());
        int c = 0;  //num of songs added in pq

        while (sc.hasNext()&&c<k) {
            int likes;
            int id = sc.nextInt();
            StringBuilder title = new StringBuilder(sc.next());
            while (! sc.hasNextInt()) {
                title.append(" ").append(sc.next());
            }
            likes = sc.nextInt();
            Song s = new Song(id, title.toString(), likes);
            pq.insert(s);
            c++;

        }
        pq.printHeap();

        Song min = pq.getLast();

        while(sc.hasNext()){
            int id = sc.nextInt();
            StringBuilder title = new StringBuilder(sc.next());
            while (! sc.hasNextInt()) {
                title.append(" ").append(sc.next());
            }

            int likes = sc.nextInt();
            Song s = new Song(id, title.toString(), likes);
            if(s.getLikes()>min.getLikes()){
                pq.removeLast(k);
                pq.insert(s);
            }

        }
        pq.printHeap();

    }
    private static int maxK(){
        int k;
        Scanner in = new Scanner(System.in);
        System.out.println("Type how many new songs you want to see!");
        k = in.nextInt();
        return k;
    }

}
