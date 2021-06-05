import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;

public class Dynamic_Median {
    private static final String path = "../songs.txt";

    public static void main(String[] args)throws IOException {
        Boolean isAboutToEnd = false;
        int k = maxK();     //Asks for the capacity of the heap.
        int numOfLines = countLines(new File(path));
        PriorityQueue<Song> MaxQ = new PriorityQueue<>(2*k, new SongComparator());
        MinPriorityQueue<Song> MinQ = new MinPriorityQueue<>(2*k, new minComparator());
        Song median = null;
        File file =  new File(path);
        Scanner sc = new Scanner(file);
        int c = 0;
        int likes;
        int id;
        int remaining = numOfLines; //Remaining Songs

        while(sc.hasNext()){
            for(int i = 0; i<5; i++){
                if(!sc.hasNext()){
                    System.out.println("Reached EOF");
                    isAboutToEnd = true;
                    break;
                }
                id = sc.nextInt();
                StringBuilder title = new StringBuilder(sc.next());
                while (! sc.hasNextInt()) {
                    title.append(" ").append(sc.next());
                }
                likes = sc.nextInt();
                Song s = new Song(id, title.toString(), likes);
                MaxQ.insert(s);
                c++;

            }
            if(!isAboutToEnd) {
                MinQ.insert(MaxQ.getMax());
                MinQ.insert(MaxQ.getMax());
            }
            if(median!=null){
                MinQ.insert(median);
            }
            median = MaxQ.getMax();
            System.out.println("Median: " + median.toString());
            remaining -= c;
            if(remaining<5){

                for(int i = 0; i<remaining; i++){
                    id = sc.nextInt();
                    StringBuilder title = new StringBuilder(sc.next());
                    while (! sc.hasNextInt()) {
                        title.append(" ").append(sc.next());
                    }
                    likes = sc.nextInt();
                    Song s = new Song(id, title.toString(), likes);

                    if(median.getLikes()>s.getLikes()) {
                        MaxQ.insert(s);
                    }else{
                        MaxQ.insert(median);
                        median = s;
                    }
                }
                MaxQ.printHeap();
                MinQ.printHeap();
                System.out.println(median.toString());
            }
        }
        MaxQ.printHeap();
        MinQ.printHeap();
        System.out.println(median.toString());
    }

    private static int maxK(){
        int k;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a number, for the capacity of the heaps!");
        k = in.nextInt();
        return k;

    }

    public static int countLines(File aFile) throws IOException {
        LineNumberReader reader = null;
        try{
            reader = new LineNumberReader(new FileReader(aFile));
            while ((reader.readLine()) != null);
            return reader.getLineNumber();
        }catch (Exception ex) {
            return -1;
        }finally {
             if(reader != null)
                 reader.close();
        }
    }
}
