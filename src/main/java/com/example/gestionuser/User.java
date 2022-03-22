package com.example.gestionuser;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String username;
    private String password;
    private int gender;

    private static final int MALE = 1;
    private static final int FEMALE = 2;
    private static final int OTHER = 3;

    public User() {
    }

    public User(String email, String username, String password, int gender) throws Exception{
        checkErrors(email, username, password, gender);
        this.email = email;
        this.username = username;
        this.password = password;
        this.gender = gender;
    }

    private void checkErrors(String email, String username, String password, int gender)throws Exception {
        checkEmail(email);
        checkUserName(username);
        checkPassword(password);
        checkGender(gender);
    }
    private Pattern ValidEmailRegex(){
        return Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",Pattern.CASE_INSENSITIVE);

    }

    private void checkEmail(String email) throws Exception{
        Matcher match = ValidEmailRegex().matcher(email);
        if(!match.find()) throw new Exception("The email doesn't match with the parameters of a email");
    }

    private void checkUserName(String username) throws Exception{
        if (username.length()==0) throw new Exception("The username must not be empty");
    }

    private boolean checkPattern(String password) {
        return (password.length()<7 || !password.matches(".*\\d.*"));
    }

    private void checkPassword(String password) throws Exception{

      if(checkPattern(password)) throw new Exception("The pattern of the pass must have a number and be a length of 7 or more");

    }

    private void checkGender(int gender) throws Exception{
        if(gender != MALE && gender != FEMALE && gender != OTHER ) throw new Exception("The gender must be 1 for male or 2 for female");
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception{
        checkEmail(email);
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws Exception{
        checkUserName(username);
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception{
        checkPassword(password);
        this.password = password;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) throws Exception{
        this.gender = gender;
        checkGender(gender);
    }

    public Long getId() {
        return id;
    }
}
