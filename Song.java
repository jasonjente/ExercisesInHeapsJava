
public class Song implements Comparable<Song> {
    private int id; //1-9999
    private String title; //eos 80 xaraxtires
    private int likes;


    // private int views;
    //song getters

    public int getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }
    public int getLikes() {
        return likes;
    }
    //song setters
    public void setTitle(String title) {
        this.title = title;
    }
    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setId(int id){
    this.id = id;
    }


    public Song(int id, String title, int likes) {
        this.id = id;
        this.title = title;
        this.likes = likes;
    }

    @Override
    public String toString() {
        return ("Track id " + this.getId() + ", number of likes " + this.getLikes() + ", " + this.getTitle()+"\n");
    }

    public int compareTo(Song song) {
        if(this.getLikes() > song.getLikes()){
            return 1;
        }else{
            return 0;
        }
    }
}
