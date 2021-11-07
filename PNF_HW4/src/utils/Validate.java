package utils;

import domain.PreCourse;

import java.util.ArrayList;
import java.util.HashMap;

public class Validate {
    public boolean checkPreCourse(byte[] buffer){
        String str = new String(buffer);
        String[] split = str.split(" ");
        HashMap<String, ArrayList<String>> preCourseMap = PreCourse.getInstance().getPreCourseMap();
        for (int i = 4; i < split.length; i++)
            if(!hasCourseOf(str, preCourseMap.get(split[i]))) return false;
        return true;
    }

    private boolean hasCourseOf(String str, ArrayList<String> preCourses) {
        if(preCourses!=null){
            for (String preCourse : preCourses)
                if(!str.contains(preCourse)) return false;
        }
        return true;
    }
}
