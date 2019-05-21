package helpers;

import java.util.Random;

public class RandomDataHelper {
    private static final String NUMBERS = "0123456789";
    private static final String ENG_BIG = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ENG_SMALL = "abcdefghijklmnopqrstuvwxyz";
    public static String randomString(String alphabet, int len) {
        String letters = "";
        if (alphabet.equals("name")){
            letters = ENG_BIG + ENG_SMALL;
        } else {
            letters = NUMBERS + ENG_BIG + ENG_SMALL;
        }
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);

        for (int i = 0; i < len; i++)
            sb.append(letters.charAt(rnd.nextInt(letters.length())));
        return sb.toString();
    }

}
