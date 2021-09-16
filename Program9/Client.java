import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket sock = new Socket("127.0.0.1", 4000);
        System.out.println(" Enter the file name");

        Scanner sc = new Scanner(System.in);
        String fileName = sc.next();

        PrintWriter pWrite = new PrintWriter(sock.getOutputStream(), true);
        pWrite.println(fileName);

        BufferedReader socketRead = new BufferedReader(new InputStreamReader(sock.getInputStream()));

        String str;
        while ((str = socketRead.readLine()) != null) {
            System.out.println(str);
        }

        pWrite.close();
        socketRead.close();
        sc.close();
        sock.close();
    }
}
