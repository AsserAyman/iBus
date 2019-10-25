package iBUS.Project.Persons;

import java.util.ArrayList;

public abstract class Person {
    private static ArrayList<Person> people = new ArrayList<>();
    private final String nationalID;
    private final String name;
    private final String gender;
    private int age;
    private String username;
    private String password;
    private String email;
    private String sequrityQuestion;
    private String sequrityAnswer;
    private String phone;

    public Person(String nationalID, String name, String gender, int age, String username, String password, String email, String sequrityQuestion, String sequrityAnswer, String phone) {
        this.nationalID = nationalID;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.username = username;
        this.password = password;
        this.email = email;
        this.sequrityQuestion = sequrityQuestion;
        this.sequrityAnswer = sequrityAnswer;
        this.phone = phone;
    }

    public static ArrayList<Person> getPeople() {
        return people;
    }

    public static void setPeople(ArrayList<Person> people) {
        Person.people = people;
    }

    public static boolean isUsernameUnqiue(String username) {
        for (Person p : people) {
            if (p.username.equals(username))
                return false;
        }
        return true;
    }

    public static Person isInfoValid(String username, String email, String sequrityQuestion, String sequrityAnswer) {
        for (Person p : people) {
            if (p.username.equals(username) && p.email.equals(email) && p.sequrityQuestion.equals(sequrityQuestion) && p.sequrityAnswer.equals(sequrityAnswer))
                return p;
        }
        return null;
    }

    public static void changePassword(String newPassword, String username, String email, String sequrityQuestion, String sequrityAnswer) {
        if (isInfoValid(username, email, sequrityQuestion, sequrityAnswer) != null) {
            isInfoValid(username, email, sequrityQuestion, sequrityAnswer).setPassword(newPassword);
        }
    }

    public static Person login(String username, String password) {
        for (Person p : people
        ) {
            if (p.username.equals(username) && p.password.equals(password))
                return p;
        }
        return null;
    }

    public String getNationalID() {
        return nationalID;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public String getSequrityQuestion() {
        return sequrityQuestion;
    }

    public void setSequrityQuestion(String sequrityQuestion) {
        this.sequrityQuestion = sequrityQuestion;
    }

    public String getSequrityAnswer() {
        return sequrityAnswer;
    }

    public void setSequrityAnswer(String sequrityAnswer) {
        this.sequrityAnswer = sequrityAnswer;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
