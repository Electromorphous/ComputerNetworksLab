import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) throws IOException {
        DatagramSocket sock = new DatagramSocket();
        DatagramPacket packet;
        byte[] buffer;

        InetAddress ip = InetAddress.getByName("127.0.0.1");
        String message = "Hello, I'm client. Send me message.";
        buffer = message.getBytes();
        packet = new DatagramPacket(buffer, buffer.length, ip, 4000);
        sock.send(packet);

        buffer = new byte[1024];
        packet = new DatagramPacket(buffer, buffer.length);
        sock.receive(packet);
        System.out.println(new String(packet.getData()));

        sock.close();
    }
}