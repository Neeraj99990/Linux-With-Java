// Write a program in java to use HMAC to encrypt a message.


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class HMACAuthenticationSHA256 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Get the original message and secret key from the user
        System.out.print("Enter the original message: ");
        String originalMessage = scanner.nextLine();
        
        System.out.print("Enter the secret key: ");
        String secretKey = scanner.nextLine();
        
        try {
            // Calculate HMAC for the original message
            String originalHMAC = calculateHMAC(originalMessage, secretKey);
            System.out.println("Original HMAC: " + originalHMAC);
            
            // Ask for a message to verify
            System.out.print("Enter the message to verify: ");
            String messageToVerify = scanner.nextLine();
            
            // Ask for the secret key again for verification
            System.out.print("Enter the secret key for verification: ");
            String verifySecretKey = scanner.nextLine();
            
            // Calculate HMAC for the new message with the provided key
            String hmacToVerify = calculateHMAC(messageToVerify, verifySecretKey);
            System.out.println("HMAC of the message to verify: " + hmacToVerify);
            
            // Check if HMACs match and secret keys match
            if (originalHMAC.equals(hmacToVerify) && secretKey.equals(verifySecretKey)) {
                System.out.println("The message is authentic.");
            } else {
                System.out.println("The message is NOT authentic.");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    public static String calculateHMAC(String data, String key) throws Exception {
        // Create a secret key from the provided key string
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA256");
        
        // Create an HMAC SHA-256 Mac instance
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKeySpec);
        // Compute the HMAC
        byte[] hmacBytes = mac.doFinal(data.getBytes());     
        // Encode the HMAC as a Base64 string
        return Base64.getEncoder().encodeToString(hmacBytes);
    }
}
 
