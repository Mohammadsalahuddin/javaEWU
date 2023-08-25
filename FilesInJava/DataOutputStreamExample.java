import java.io.*;

public class DataOutputStreamExample {
    public static void main(String[] args) {
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.dat"));

            dos.writeInt(42);
            dos.writeDouble(3.14159);
            dos.writeUTF("Hello, World!");

            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
