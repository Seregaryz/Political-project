package obj;

import java.util.Date;

public class Comment {

    private int idComment, idPost;
    private User sender;
    private String text;
    private Date date;

    public Comment(int idComment, User sender, int idPost, String text, Date date) {
        this.idComment = idComment;
        this.sender = sender;
        this.idPost = idPost;
        this.text = text;
        this.date = date;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
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
}
