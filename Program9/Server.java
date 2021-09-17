import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serSock = new ServerSocket(4000);
        System.out.println("Server ready");

        Socket sock = serSock.accept();
        System.out.println("Connection established");

        Scanner fileNameReader = new Scanner(sock.getInputStream());
        String fileName = fileNameReader.nextLine();

        try {

            Scanner contentReader = new Scanner(new FileReader(fileName));

            PrintWriter pWrite = new PrintWriter(sock.getOutputStream(), true);

            while (contentReader.hasNextLine()) {
                pWrite.println(contentReader.nextLine());
            }

            contentReader.close();
            pWrite.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

        serSock.close();
        sock.close();
        fileNameReader.close();
    }
}
