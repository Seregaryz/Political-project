package obj;

import java.io.File;
import java.util.Date;

public class News {

    private int id;
    private String header, text, preview;
    private String img;
    private Date date;

    public News(int id, String header, String text, String preview, String imgPath, Date date) {
        this.id = id;
        this.header = header;
        this.text = text;
        this.preview = preview;
        img = imgPath;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date.toString();
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImgPath() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    @Override
    public String toString() {
        return id + "";
    }
}
