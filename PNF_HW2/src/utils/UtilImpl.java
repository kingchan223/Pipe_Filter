package utils;

import java.util.List;

public class UtilImpl implements DefaultUtil{
    @Override
    public byte[] addCompulsory(String major, List<String> requiredId, byte[] buffer) {
        return DefaultUtil.super.addCompulsory(major, requiredId, buffer);
    }
}
