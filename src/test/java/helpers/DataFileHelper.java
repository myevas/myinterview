package helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DataFileHelper {
    /**Чтение из файла*/
    public static String read(String fileName) throws IOException {
        //Этот спец. объект для построения строки

        String filePath = System.getProperty("user.dir") + fileName;
        StringBuilder sb = new StringBuilder();
        File file = new File(filePath);
        if(!file.exists()){
            file.createNewFile();
        }
        try {
            //Объект для чтения файла в буфер
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                //В цикле построчно считываем файл
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                //Также не забываем закрыть файл
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
