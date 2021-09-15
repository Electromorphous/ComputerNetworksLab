import java.util.Scanner;

public class CRC {
    public String method(String dividend, String divisor) {
        int shift = dividend.length() - divisor.length();
        while (shift >= 0) {
            // XORing the string
            dividend = Integer.toBinaryString(Integer.parseInt(dividend, 2) ^ Integer.parseInt(divisor, 2) << shift);
            shift = dividend.length() - divisor.length();
        }
        if (dividend.length() < 16) {
            StringBuilder dividendBuilder = new StringBuilder(dividend);
            while (dividendBuilder.length() != 16)
                dividendBuilder.insert(0, "0");
            dividend = dividendBuilder.toString();
        }
        System.out.println("Dividend = " + dividend);
        return dividend;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String data;
        String checkSum;
        String syn;
        StringBuilder dividend;
        String receivedData;
        int padding;
        String polynomial = "10001000000100001";
        // Data to be transmitted
        System.out.println("Enter the data to be encrypted and transmitted");
        data = sc.next();
        dividend = new StringBuilder(data);
        padding = polynomial.length() - 1;
        // Zero padding of dividend based on the polynomial
        dividend.append("0".repeat(padding));
        CRC obj = new CRC();
        checkSum = obj.method(dividend.toString(), polynomial);

        // Generated codeword
        data += checkSum;
        System.out.println("Sender checkSum = " + checkSum);
        System.out.println("Codeword transmitted over network = " + data);
        // Data received by the receiver
        System.out.println("Enter the data received by the receiver");
        receivedData = sc.next();
        syn = obj.method(receivedData, polynomial);
        // Generated syn bits after checking checkSum
        if (Long.parseLong(syn) == 0)
            System.out.println("No error in data transmission");
        else
            System.out.println("Error in data transmission");

        sc.close();
    }
}
