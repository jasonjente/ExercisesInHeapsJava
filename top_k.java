import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class top_k {

    private static final String path = "C:\\Users\\jente\\IdeaProjects\\DomesDedomenwnErgasia2\\src\\songs.txt";
    public static void main(String args[])throws IOException{
        System.out.println("Hello and welcome. Type how many top hits you want to be shown.");
        Scanner sc = new Scanner(System.in);
        int numOfLines = countLines(new File(path));
        int n = sc.nextInt();

        while(n > numOfLines){
            System.out.println("Please enter a valid number less than "+numOfLines + "(Number of songs in the file).");
            n = sc.nextInt();
        }

        Song[] songArray = ArrayCreation(numOfLines);
        songArray = quickSort(songArray,0, songArray.length-1);

        System.out.println("Top " + n + " songs are : ");
        for(int i = 0; i< n; i++){
            System.out.println((i+1)+". Song ID : "+ songArray[i].getId()+ " - " + songArray[i].getTitle() + " ,Total number of likes : " + songArray[i].getLikes());
        }
    }
    public static int countLines(File aFile) throws IOException {
        LineNumberReader reader = null;
        try {
            reader = new LineNumberReader(new FileReader(aFile));
            while ((reader.readLine()) != null);
            return reader.getLineNumber();
        }catch (Exception ex) {
            return -1;
        } finally {
            if(reader != null)
                reader.close();
        }
    }
    public static boolean less(Song a , Song b){
        return a.getLikes() > b.getLikes();
    }


    private static void exch(Song[] a,int i, int j){
        Song t = a[i];
        a[i] = a [j];
        a[j] = t;
    }

    private static Song[] ArrayCreation(int n) throws FileNotFoundException {
        Scanner in = new Scanner(new File(path));
        Song[] a;
        a = new Song[n];
        int i =0;
        while(in.hasNext()){
            int id;
            int likes;
            String title;

            //read first int from txt
            id = in.nextInt();
            title = in.next();
            //simple loop to add the whole name of the song with whitespaces.
            while(!in.hasNextInt()){
                title =title + " " + in.next();
            }
            likes = in.nextInt();
            //System.out.println(id + " "+ title + " " + likes);
            a[i] = new Song (id, title, likes);
            i++;


        }
        return a;
    }

    private static Song[] quickSort(Song[] a, int p, int r){
        if ( r <= p)return null;
        int i = partition(a, p, r);
        quickSort(a,p,i-1);
        quickSort(a,i+1, r);

        return a;
    }
    static int partition(Song a[], int p, int r)
    { int i = p-1, j = r; Song v = a[r];
        for (;;) {
            while (less(a[++i], v))
            while (less(v, a[--j])) {
                if (j == p) break;
            }
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, i, r);
        return i;
    }

}


