import java.io.*;

public class DataInputStreamExample {
    public static void main(String[] args) {
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream("data.dat"));

            int intValue = dis.readInt();
            double doubleValue = dis.readDouble();
            String stringValue = dis.readUTF();

            System.out.println("Int Value: " + intValue);
            System.out.println("Double Value: " + doubleValue);
            System.out.println("String Value: " + stringValue);

            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
