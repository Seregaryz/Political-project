package obj;


public class User {

    private int id;
    private String email, password, nickname, username, surname;
    private Sex sex;

    public User(int id, String email, String password, String nickname, String username, String surname, String sex) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.username = username;
        this.surname = surname;
        if (sex.equals("male")) {
            this.sex = Sex.male;
        } else if (sex.equals("female")) {
            this.sex = Sex.female;
        }
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

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    public enum Sex {
        male, female;
    }

}


