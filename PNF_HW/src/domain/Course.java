package domain;

import java.util.ArrayList;

public class Course {
    String id;
    String profName;
    String  courseName;
    ArrayList<String> preCourses;

    public Course(){}

    public Course(String id, String profName, String courseName, ArrayList<String> preCourses) {
        this.id = id;
        this.profName = profName;
        this.courseName = courseName;
        this.preCourses = preCourses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if(id.length() != 5) return;
        this.id = id;
    }

    public String getProfName() {
        return profName;
    }

    public void setProfName(String profName) {
        this.profName = profName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public ArrayList<String> getPreCourses() {
        return preCourses;
    }

    public void setPreCourse(ArrayList<String> preCourses) {
        this.preCourses = preCourses;
    }


}
