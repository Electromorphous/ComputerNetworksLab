import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serSock = new ServerSocket(4000);
        System.out.println("Server ready for connection.");

        Socket sock = serSock.accept();
        System.out.println("Connection successful and waiting for chatting");

        BufferedReader fileRead = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        String fileName = fileRead.readLine();

        try {
            BufferedReader contentReader = new BufferedReader(new FileReader(fileName));

            PrintWriter pWrite = new PrintWriter(sock.getOutputStream(), true);

            String str;
            while ((str = contentReader.readLine()) != null) {
                pWrite.println(str);
            }

            pWrite.close();
            contentReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

        sock.close();
        serSock.close();
        fileRead.close();
    }
}
