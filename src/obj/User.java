package obj;


public class User {

    private int id;
    private String email, password, nickname, username, surname, sex, photoPath;

    public User(int id, String email, String password, String nickname, String username,
                String surname, String sex, String photoPath) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.username = username;
        this.surname = surname;
        this.sex = sex;
        this.photoPath = photoPath;
    }

    public User() {
    }


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

}


