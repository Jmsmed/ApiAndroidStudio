package com.example.api;


public class User {
    private int id;
    private String email;
    private  String f_name;
    private String l_name;
    private String avatar;

    public User(int id, String fname, String lname, String email, String avatar){
        this.id = id;
        this.email = email;
        this.f_name = fname;
        this.l_name = lname;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }
    public String getId_str() { //To get the ID in a string format to be used in the textView
        return Integer.toString(id);
    }

    public String getEmail() {
        return email;
    }

    public String getF_name() {
        return f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", "
                + "first_name=" + f_name + ", "
                + "last_name=" + l_name + ", "
                + "email=" + email + "]";
    }
}
