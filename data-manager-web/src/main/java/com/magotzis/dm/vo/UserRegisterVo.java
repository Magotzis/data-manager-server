package com.magotzis.dm.vo;

/**
 * @author dengyq on 10:48 2018/3/30
 */
public class UserRegisterVo {

    private String username;

    private String password;

    private String rpassword;

    private String fullname;

    private String email;

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

    public String getRpassword() {
        return rpassword;
    }

    public void setRpassword(String rpassword) {
        this.rpassword = rpassword;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserRegisterVo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rpassword='" + rpassword + '\'' +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
