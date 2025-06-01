import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ConsoleTools {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        while (true) {
            System.out.println("Welcome to Gehtsoft Technical Assessment");
            System.out.println("Please choose an option:");
            System.out.println("1. Caesar Cipher Encryption");
            System.out.println("2. Caesar Cipher Decryption");
            System.out.println("3. Arithmetic Expression Evaluation");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            String option = reader.readLine().trim();

            switch (option) {
                case "1": {
                    CaesarCipher.encryptDecryptMenu(CaesarCipher.ENCRYPT_TYPE);
                    break;
                }
                case "2": {
                    CaesarCipher.encryptDecryptMenu(CaesarCipher.DECRYPT_TYPE);
                    break;
                }
                case "3": {
                    System.out.print("Enter arithmetic expression: ");
                    String expression = reader.readLine().trim();
                    try {
                        double result = ArithmeticExpressionEvaluator.evaluateExpression(expression);
                        System.out.println("Result: " + result);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }
                case "4": {
                    System.out.println("Finished!");
                    return;
                }
                default:
                    System.out.println("Invalid option. Try again.");
            }

            System.out.print("Continue? (y/n): ");
            String answer = reader.readLine().trim();
            if (!answer.equalsIgnoreCase("y")) {
                break;
            }
        }
    }
}