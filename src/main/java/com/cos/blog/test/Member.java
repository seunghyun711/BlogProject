package com.cos.blog.test;

public class Member {
    /*
    아래 변수들을 private으로 선언하여 해당 변수들에 직접 접근하지 않게하고, 이를 이용하는 메서드를 public으로 만들어 메서드를 통해서만
    접근할 수 있게 만든다.
     */
    private int id;
    private String username;
    private String password;
    private String email;

    public Member(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
