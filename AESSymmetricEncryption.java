
// Write a program in java to implement to implement a hashing mechanism using the SHA -256 Algorithm.

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * experiment1
 */
public class AESSymmetricEncryption {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for input
        System.out.print("Enter a string to hash: ");
        String input = scanner.nextLine();

        // Hash the input and display the output
        String hashedOutput = hashWithSHA256(input);
        System.out.println("SHA-256 Hash: " + hashedOutput);

        // Close the scanner
        scanner.close();
    }

    public static String hashWithSHA256(String input) {
        try {
            // Create a MessageDigest instance for SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Hash the input string
            byte[] hashBytes = digest.digest(input.getBytes());

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0'); // Append leading zero if necessary
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }
}
