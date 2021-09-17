import java.io.*;
import java.util.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket sock = new Socket("127.0.0.1", 4000);

        System.out.println("Enter the file name");
        Scanner userInput = new Scanner(System.in);
        String fileName = userInput.nextLine();

        PrintWriter pWrite = new PrintWriter(sock.getOutputStream(), true);

        pWrite.println(fileName);

        Scanner contentReader = new Scanner(sock.getInputStream());

        while (contentReader.hasNextLine()) {
            System.out.println(contentReader.nextLine());
        }

        sock.close();
        contentReader.close();
        userInput.close();
        pWrite.close();
    }
}
