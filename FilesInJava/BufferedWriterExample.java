import java.io.*;

public class BufferedWriterExample {
    public static void main(String[] args) {
        try {
            String content = "Hello, Buffered File Handling!";
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

            writer.write(content);
            writer.newLine(); // Write a new line

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
