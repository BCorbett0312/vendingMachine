package utils;

public class StringUtil {

    public static String pad(String toPad, Integer length){
        return String.format("%1$-"+length+"s", toPad);
    }
}
