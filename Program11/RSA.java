import java.util.*;
import java.math.BigInteger;

public class RSA {
    public static void main(String[] args) {
        
        Random r1 = new Random(System.currentTimeMillis());
        Random r2 = new Random(System.currentTimeMillis() * 10);

        BigInteger p = BigInteger.probablePrime(32, r1);
        BigInteger q = BigInteger.probablePrime(32, r2);
        BigInteger p1 = p.subtract(BigInteger.ONE);
        BigInteger q1 = q.subtract(BigInteger.ONE);
        BigInteger n = p.multiply(q);
        BigInteger z = p1.multiply(q1);

        int initPubKey = 2;
        while (true) {
            BigInteger gcd = z.gcd(new BigInteger(Integer.toString(initPubKey)));
            if (gcd.equals(BigInteger.ONE))
                break;
            initPubKey++;
        }

        BigInteger pubKey = new BigInteger(Integer.toString(initPubKey));
        BigInteger prvKey = pubKey.modInverse(z);

        System.out.println(" Public key = " + pubKey + ", " + n);
        System.out.println(" Private key = " + prvKey + ", " + n);
        
        System.out.println("Enter the message");
        Scanner sc = new Scanner(System.in);
        String message = sc.next();
        byte[] messageBytes = message.getBytes();

        for (int i = 0; i < messageBytes.length; i++) {
            BigInteger ascii = new BigInteger(Integer.toString(messageBytes[i]));
            BigInteger cipher = ascii.modPow(pubKey, n);
            System.out.println(" Cipher value = " + cipher);
            BigInteger decipheredVal = cipher.modPow(prvKey, n);
            int dVal = decipheredVal.intValue();
            System.out.println(" Deciphere character = " + (char)dVal);
        }

        sc.close();
    }
}
