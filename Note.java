import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Note implements Serializable {
    private String title;
    private ArrayList<String> body;
    private LocalDate date;
    public Note(String title , ArrayList<String> body){
        this.title = title;
        this.body = body;
        this.date = LocalDate.now();
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setBody(ArrayList<String> body) {this.body = body;}
    public String getTitle() {
        return title;
    }
    public ArrayList<String> getBody() {return body;}
    public LocalDate getDate(){return date;}
}