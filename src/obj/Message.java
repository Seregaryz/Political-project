package obj;

import java.util.Date;

public class Message {

    private String text;
    private Date date;
    private User sender;
    private Debates debates;

    public Message(String text, Date date, User sender, Debates debates) {
        this.text = text;
        this.date = date;
        this.sender = sender;
        this.debates = debates;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String getStringDate() {
        return date.toString();
    }

    public Long getLongData(){return date.getTime();}


    public void setDate(Date date) {
        this.date = date;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Debates getDebates() {
        return debates;
    }

    public void setDebates(Debates debates) {
        this.debates = debates;
    }
}
