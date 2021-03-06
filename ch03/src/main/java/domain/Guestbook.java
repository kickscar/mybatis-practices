package domain;

import java.util.Date;

public class Guestbook {
    private Long no;
    private String name;
    private String password;
    private String message;
    private Date regDate;

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "Guestbook{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", message='" + message + '\'' +
                ", regDate='" + regDate + '\'' +
                '}';
    }
}
