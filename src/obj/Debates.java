package obj;

import java.util.Date;

public class Debates {
    private int id;
    private String topic;
    private Date date;
    private User creator, secondDebater;

    public Debates(int id, String topic, Date date, User creator, User secondDebater) {
        this.id = id;
        this.topic = topic;
        this.date = date;
        this.creator = creator;
        this.secondDebater = secondDebater;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getSecondDebater() {
        return secondDebater;
    }

    public void setSecondDebater(User secondDebater) {
        this.secondDebater = secondDebater;
    }
}
