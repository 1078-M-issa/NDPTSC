package sg.edu.rp.c346.id21001078.ndptsc;

import java.io.Serializable;

public class Song implements Serializable {
    private int id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public Song (int id ,String T, String S, int Y, int SR) {
        this.id = id;
        this.title = T;
        this.singers = S;
        this.year = Y;
        this.stars = SR;

    }

    public int getId() {
        return id;
    }

    public void setId(int i) { this.id = i; }

    public String getTitle() {
        return title;
    }

    public void setTitle (String T) { this.title = T; }

    public  String getSingers() {
        return singers;
    }

    public void setSingers(String S) { this.singers = S;}

    public int getYear() {
        return year;
    }

    public void setYear(int Y) { this.year = Y; }

    public int getStars() {
        return stars;
    }

    public void setStars(int s) { this.stars = s; }

    public String toStringClass() { return id + " \n Title : '" + title + "'" +
            "\n Artist : "   + singers + ""
            + "\n Year : " + year + ""
            + "\n Rating : " + stars; }


}
