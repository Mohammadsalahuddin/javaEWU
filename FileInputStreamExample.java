import java.io.*;

public class FileInputStreamExample {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("input.txt");
            int character;

            while ((character = fis.read()) != -1) {
                System.out.print((char) character);
            }

            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
