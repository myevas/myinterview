package helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DataFileHelper {
    public static String read(String fileName) throws IOException {
        String path = "";

        if(!GetOSName.getOsName().startsWith("Windows")){
            path = "/src/test/resources/" + fileName;
        } else {
            path = "\\src\\test\\resources\\" + fileName;
        }



        String filePath = System.getProperty("user.dir") + path;
        StringBuilder sb = new StringBuilder();
        File file = new File(filePath);
        if(!file.exists()){
            System.out.println("Файл не найден!");
        }
        try {
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
