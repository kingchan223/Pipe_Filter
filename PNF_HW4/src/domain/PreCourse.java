package domain;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PreCourse {

    static HashMap<String, ArrayList<String>> preCourseMap = new HashMap<>();

    private PreCourse(){}

    private static class PreCourseHolder{
        private static final PreCourse preCourse = new PreCourse();
    }

    public static PreCourse getInstance(){
        return PreCourseHolder.preCourse;
    }

    public ArrayList<String> getPreCourseOf(String courseId){
        return preCourseMap.get(courseId);
    }

    public HashMap<String, ArrayList<String>> getPreCourseMap() {
        return preCourseMap;
    }

    public static void makePreCourse(byte[] buffer){
        String str = new String(buffer);
        String[] split = str.split(" ");
        if(split[0].length()==5){
            if(split.length>=4){
                ArrayList<String> preC = new ArrayList<>(Arrays.asList(split).subList(3, split.length));
                preCourseMap.put(split[0], preC);
            }
            else preCourseMap.put(split[0], null);
        }
    }
}
