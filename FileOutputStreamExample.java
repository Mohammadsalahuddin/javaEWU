import java.io.*;

public class FileOutputStreamExample {
    public static void main(String[] args) {
        try {
            String content = "Hello, File Handling!";
            FileOutputStream fos = new FileOutputStream("output.txt");

            byte[] bytes = content.getBytes();
            fos.write(bytes);

            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
