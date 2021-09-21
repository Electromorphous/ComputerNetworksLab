import java.util.*;

public class L {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int maxBufferSize;
        int curBufferSize = 0;
        int num;
        int outRate;
        int[] packetSizes;
        int[] packetTimes;

        System.out.println("Enter maximum buffer size");
        maxBufferSize = sc.nextInt();
        System.out.println("Enter output rate");
        outRate = sc.nextInt();
        System.out.println("Enter number of packets");
        num = sc.nextInt();

        packetSizes = new int[num];
        packetTimes = new int[num];

        System.out.println("Enter arrival time of each packet in order");
        for (int i = 0; i < num; i++)
            packetTimes[i] = sc.nextInt();

        int count = 0;
        int time = 0;

        while (count < num) {
            if (time == packetTimes[count]) {
                Random rn = new Random();
                packetSizes[count] = (rn.nextInt(10) + 1) * 10;

                System.out.println("\n Packet " + (count + 1) + " with size " + packetSizes[count] + " has arrived at time " + time);
                System.out.println("Current Buffer size is " + curBufferSize);

                if (curBufferSize + packetSizes[count] > maxBufferSize) {
                    System.out.println("It is a non-conforming packet as buffer limit has been exceeded");
                }
                else {
                    System.out.println("It is a conforming packet");
                    curBufferSize += packetSizes[count];
                }
                count++;

            }

            time++;
            curBufferSize = Math.max(0, curBufferSize - outRate);
        }

        sc.close();
    }
}