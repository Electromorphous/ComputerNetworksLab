import java.util.Random;
import java.util.Scanner;

public class Leaky {
    public static void main(String[] args) {

        int time, outputRate, maxBufferSize, numberOfPackets;
        int count, curBufferSize = 0;

        Scanner in = new Scanner(System.in);
        System.out.println("Enter the maximum size of buffer");
        maxBufferSize = in.nextInt();
        System.out.println("Enter the output rate of packets from the buffer");
        outputRate = in.nextInt();
        System.out.println("Enter the number of arriving packets");
        numberOfPackets = in.nextInt();
        int[] packetSizes = new int[numberOfPackets];
        int[] arrTimeOfPackets = new int[numberOfPackets];
        System.out.println("Enter the time of arrival of packets");
        for (count = 0; count < numberOfPackets; count++)
            arrTimeOfPackets[count] = in.nextInt();
        time = 0;
        count = 0;

        while (count < numberOfPackets) {
            if (time == arrTimeOfPackets[count]) {
                Random rn = new Random();
                packetSizes[count] = (rn.nextInt(10) + 1) * 10;
                System.out.println(" Packet " + (count + 1) + " has arrived & its size is:" + packetSizes[count]);
                System.out.println(" Current Size of buffer: " + curBufferSize);
                if (curBufferSize + packetSizes[count] < maxBufferSize) {
                    curBufferSize += packetSizes[count];
                    System.out.println(" Packet " + (count + 1) + " arriving at " + arrTimeOfPackets[count]
                            + " is a CONFORMING PACKET\n");
                } else {
                    System.out.println(" Packet " + (count + 1) + " arriving at " + arrTimeOfPackets[count]
                            + " is a NON-CONFORMING PACKET as it exceeds the buffer limit\n");
                }
                count++;
            }
            time++;
            curBufferSize -= outputRate;
            if (curBufferSize < 0)
                curBufferSize = 0;
        }

        in.close();
    }
}