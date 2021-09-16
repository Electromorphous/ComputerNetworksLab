import java.util.Scanner;

public class CRC {
    static String method(String dividend, String divisor) {

        int padding = divisor.length() - 1;

        int shift = dividend.length() - divisor.length();
        while (shift >= 0) {
            dividend = Integer.toBinaryString(Integer.parseInt(dividend, 2) ^ Integer.parseInt(divisor, 2) << shift);
            shift = dividend.length() - divisor.length();
        }

        if (dividend.length() < padding) {
            StringBuilder dividendBuilder = new StringBuilder(dividend);
            dividendBuilder.insert(0, "0".repeat(padding - dividend.length()));
            dividend = dividendBuilder.toString();
        }

        System.out.println(" After division : " + dividend);
        return dividend;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StringBuilder dividend;
        String polynomial = "101010011";
        int padding = polynomial.length() - 1;

        System.out.println(" Enter the data to encrypt and transmit");
        String data = sc.next();
        dividend = new StringBuilder(data);

        dividend.append("0".repeat(padding));
        String checkSum = method(dividend.toString(), polynomial);

        data += checkSum;
        System.out.println(" CheckSum = " + checkSum);
        System.out.println(" Codeword transmitted = " + data);

        System.out.println(" Enter data received");
        String receivedData = sc.next();

        String syn = method(receivedData, polynomial);

        if (Long.parseLong(syn) == 0)
            System.out.println(" No errors");
        else
            System.out.println(" There was error in transmission");

        sc.close();
    }
}
