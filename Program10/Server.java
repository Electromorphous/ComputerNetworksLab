import java.util.*;
import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        DatagramPacket packet;
        byte[] buffer = new byte[1024];

        DatagramSocket sock = new DatagramSocket(4000);

        packet = new DatagramPacket(buffer, buffer.length);

        sock.receive(packet);
        System.out.println(new String(packet.getData()));

        int port = packet.getPort();
        InetAddress ip = packet.getAddress();

        String message = sc.nextLine();
        buffer = message.getBytes();

        packet = new DatagramPacket(buffer, buffer.length, ip, port);
        sock.send(packet);

        sock.close();
        sc.close();
    }
}