package domain;

import java.util.ArrayList;

public class Student {
    String id;
    String firstName;
    String lastName;
    String major;
    ArrayList<String> takeCourses;

    public Student(){}

    public Student(String id, String firstName, String lastName, String major, ArrayList<String> takeCourses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.major = major;
        this.takeCourses = takeCourses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if(id.length() != 8) return;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        if(major.length() != 2) return;
        this.major = major;
    }

    public ArrayList<String> getTakeCourses() {
        return takeCourses;
    }

    public void setTakeCourses(ArrayList<String> takeCourses) {
        this.takeCourses = takeCourses;
    }

//    public static Student makeStudent(){
//
//    }

//    public boolean checkMajor(String major, ){
//
//    }
}
