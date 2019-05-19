package helpers;

import java.util.Arrays;
import java.util.List;

public class ParserExpectedResult {
    public static List<String> parseFile (String data) {
        List<String> list = Arrays.asList(data.split("\n"));
        return list;
    }
}
