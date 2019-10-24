package obj;

import java.util.Date;

public class Debates {
    private int id;
    private String topic;
    private Date date;

    public Debates(int id, String topic, Date date) {
        this.id = id;
        this.topic = topic;
        this.date = date;
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
}
