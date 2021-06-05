import java.util.Comparator;
public class minComparator implements Comparator<Song> {
    @Override
    public int compare(Song o1, Song o2) {
        // 1 < 2
        if(o1.getLikes() > o2.getLikes()){
            return 1;

        }else if(o1.getLikes()  == o2.getLikes() ) {
            //1 == 2
            if (o1.getTitle().compareToIgnoreCase(o2.getTitle()) == 1) {
                return  1;
            }else if (o1.getTitle().compareToIgnoreCase(o2.getTitle()) == 0){
                return 0;
            }else{
                return -1;
            }
        }else{
            //1>2
            return -1;
        }
    }
}
