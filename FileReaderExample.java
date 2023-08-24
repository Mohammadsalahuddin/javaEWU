import java.io.*;

public class FileReaderExample {
    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("input.txt");
            int character;

            while ((character = reader.read()) != -1) {
                System.out.print((char) character);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
