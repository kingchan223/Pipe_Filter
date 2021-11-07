package utils;

import java.util.List;

public interface DefaultUtil {

    default byte[] addCompulsory(String major, List<String> requiredId, byte[] buffer){
        String str = new String(buffer);
        String[] splits = str.split(" ");
        if(!validateCourse(splits)) return buffer;//에라 추가
        for (String rId : requiredId)
            if(!validateRequired(rId, str)) str = addRequiredId(rId, str);
        str += "\n";
        return str.getBytes();
    }

    private String addRequiredId(String rId, String str) {
        str += " "+rId;
        return str;
    }

    private boolean validateRequired(String rId, String str){
        return str.contains(rId);
    }

    private boolean validateCourse(String[] split){
        return split.length >= 5;
    }
}
