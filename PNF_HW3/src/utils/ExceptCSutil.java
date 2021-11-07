package utils;

import java.util.List;

public class ExceptCSutil implements DefaultUtil{

    @Override
    public byte[] addCompulsory(List<String> checkId, byte[] buffer) {
        String str = new String(buffer);
        String[] splits = str.split(" ");
        if(!hasCourse(splits)) return buffer;
        for (String cId : checkId)//17651 17652
            if(hasCheckId(cId, str)) str = deleteRequiredId(cId, str.split(" "));
        str += "\n";
        return str.getBytes();
    }

    private String deleteRequiredId(String cId, String[] splits) {
        String retVal = "";
        for (String split : splits)
            if(!split.equals(cId)) retVal += (split+" ");
        return retVal;
    }

    @Override
    public boolean hasCheckId(String cId, String str) {
        return str.contains(cId);
    }

    @Override
    public boolean hasCourse(String[] split) {
        return split.length >= 5;
    }
}
