package utils;

import java.util.List;

public interface DefaultUtil {

    default byte[] addCompulsory(List<String> checkId, byte[] buffer){
        String str = new String(buffer);
        String[] splits = str.split(" ");
        if(!hasCourse(splits)) return buffer;//에라 추가
        for (String cId : checkId)
            if(!hasCheckId(cId, str)) str = addRequiredId(cId, str);
        str += "\n";
        return str.getBytes();
    }

    private String addRequiredId(String rId, String str) {
        str += " "+rId;
        return str;
    }

    boolean hasCheckId(String rId, String str);

    boolean hasCourse(String[] split);
}
