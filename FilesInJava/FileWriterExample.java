import java.io.*;

public class FileWriterExample {
    public static void main(String[] args) {
        try {
            String content = "Hello, File Handling!";
            FileWriter writer = new FileWriter("output.txt");

            writer.write(content);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
