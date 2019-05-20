package helpers;

public class GetOSName {
    public static String getOsName(){
        String name = System.getProperty("os.name");
        return name;
    }
}
