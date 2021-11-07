package utils;

public class Validator {
    public boolean isCS(String buffer) {
        String[] split = buffer.split(" ");
        return split[3].equals("CS");
    }

    public boolean isGrade(String s, String buffer) {
        String[] split = buffer.split(" ");
        String substring = split[0].substring(2, 4);
        return substring.equals("13");
    }
}
