import java.math.BigInteger;
import java.util.Scanner;

public class DiffieHellman {

    private BigInteger privateKey;
    private BigInteger publicKey;
    private BigInteger sharedSecret;

    // Prime number and base (generator)
    private static final BigInteger P = new BigInteger("23"); // A small prime number
    private static final BigInteger G = new BigInteger("5");  // A small generator

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Niraj, enter your private key (as a number): ");
        BigInteger nirajPrivateKey = scanner.nextBigInteger();
        System.out.print("Manu, enter your private key (as a number): ");
        BigInteger manuPrivateKey = scanner.nextBigInteger();

        DiffieHellman niraj = new DiffieHellman();
        DiffieHellman manu = new DiffieHellman();

        // Set private keys
        niraj.setPrivateKey(nirajPrivateKey);
        manu.setPrivateKey(manuPrivateKey);

        // Generate public keys
        niraj.generatePublicKey();
        manu.generatePublicKey();

        // Display public keys
        System.out.println("Niraj's Public Key: " + niraj.getPublicKey());
        System.out.println("Manu's Public Key: " + manu.getPublicKey());

        // Exchange public keys and compute shared secret
        niraj.computeSharedSecret(manu.getPublicKey());
        manu.computeSharedSecret(niraj.getPublicKey());

        // Display shared secrets
        System.out.println("Niraj's Shared Secret: " + niraj.getSharedSecret());
        System.out.println("Manu's Shared Secret: " + manu.getSharedSecret());

        // Verify that both shared secrets are equal
        if (niraj.getSharedSecret().equals(manu.getSharedSecret())) {
            System.out.println("Shared secrets match!");
        } else {
            System.out.println("Shared secrets do not match!");
        }

        scanner.close();
    }

    public void setPrivateKey(BigInteger privateKey) {
        this.privateKey = privateKey;
    }

    public void generatePublicKey() {
        // Calculate the public key: G^privateKey mod P
        publicKey = G.modPow(privateKey, P);
    }

    public void computeSharedSecret(BigInteger otherPublicKey) {
        // Calculate the shared secret: otherPublicKey^privateKey mod P
        sharedSecret = otherPublicKey.modPow(privateKey, P);
    }

    public BigInteger getPublicKey() {
        return publicKey;
    }

    public BigInteger getSharedSecret() {
        return sharedSecret;
    }
}
