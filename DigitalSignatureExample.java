// Write a program in java to implement digital signature authenticity using RSA Algorithm.
import java.security.*;
import java.util.Base64;
import java.util.Scanner;

public class DigitalSignatureExample {

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public DigitalSignatureExample() throws NoSuchAlgorithmException {
        // Generate a KeyPair for signing and verifying
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        privateKey = keyPair.getPrivate();
        publicKey = keyPair.getPublic();
    }

    public String sign(String message) throws Exception {
        // Create a Signature object
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes());

        // Sign the message
        byte[] digitalSignature = signature.sign();
        return Base64.getEncoder().encodeToString(digitalSignature);
    }

    public boolean verify(String message, String signatureStr) throws Exception {
        // Create a Signature object
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(message.getBytes());

        // Verify the signature
        byte[] signatureBytes = Base64.getDecoder().decode(signatureStr);
        return signature.verify(signatureBytes);
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            // Create DigitalSignatureExample instance
            DigitalSignatureExample dse = new DigitalSignatureExample();

            // Input message
            System.out.print("Enter a message to sign: ");
            String message = scanner.nextLine();

            // Sign the message
            String signedMessage = dse.sign(message);
            System.out.println("Signed Message: " + signedMessage);

            // Verify the signature
            System.out.print("Enter the signed message to verify: ");
            String inputSignature = scanner.nextLine();
            boolean isVerified = dse.verify(message, inputSignature);

            if (isVerified) {
                System.out.println("The signature is valid.");
            } else {
                System.out.println("The signature is invalid.");
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
