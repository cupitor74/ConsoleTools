import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CaesarCipher {
    private static final String ENGLISH_SYMBOLS_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String ENGLISH_SYMBOLS_UPPER = ENGLISH_SYMBOLS_LOWER.toUpperCase();
    private static final String CYRILLIC_SYMBOLS_LOWER = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final String CYRILLIC_SYMBOLS_UPPER = CYRILLIC_SYMBOLS_LOWER.toUpperCase();
    public static final String ENCRYPT_TYPE = "Encrypt";
    public static final String DECRYPT_TYPE = "Decrypt";

    /**
     * Encrypts the entered text using Caesar cipher algorithm with the known shift.
     *  @param text Text to encrypt
     *  @param shift Shift value
     *  @return Encrypted string
     */
    private static String encrypt(String text, int shift) {
        return caesarCipher(text, shift);
    }

    /**
     * Decrypts the entered text using Caesar cipher algorithm with the known shift.
     * @param text Text to decrypt
     * @param shift Shift value
     * @return Decrypted string
     */
    private static String decrypt(String text, int shift) {
        return caesarCipher(text, -shift);
    }

    /**
     * Provide  Menu for Encrypt or Decrypt operation the entered text using Caesar cipher algorithm with the known shift.
     * @param encryptDecryptType Type of operation. "Encrypt" to encrypt, "Decrypt" to decrypt
     */
    public static void encryptDecryptMenu(String encryptDecryptType) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        System.out.println("Caesar Cipher (" + encryptDecryptType +  " mode)");
        System.out.println("Choose 1 to use Console");
        System.out.println("Choose 2 to use data from the file");
        System.out.println("Choose 3 to exit");
        System.out.print("Enter your choice: ");

        String operation = reader.readLine().trim();
        String enteredText = "";
        switch (operation) {
            case "1": {
                System.out.println(encryptDecryptType + " mode:");
                System.out.print("Enter text to " +  encryptDecryptType.toLowerCase() + ": ");

                enteredText = reader.readLine().trim();

                handlerOperation(encryptDecryptType, enteredText);
                break;
            }
            case "2": {
                System.out.println(encryptDecryptType + " mode:");
                System.out.print("Enter file path: ");
                String filePath = reader.readLine().trim();

                if (!Files.exists(Paths.get(filePath))) {
                    System.out.println("File not found: " + filePath);
                    System.out.println("Please check that the file exists and the path is correct.");
                    return;
                }

                enteredText = new String(Files.readAllBytes(Paths.get(filePath)));
                handlerOperation(encryptDecryptType, enteredText);
                break;
            }
            case "3": {
                System.out.println(encryptDecryptType + " has been finished!");
                return;
            }
            default:
                System.out.println("Invalid operation");
        }
    }


    /**
     * Applies Caesar cipher shift to each character of the entered text.
     * Supports Russian and English languages, preserving case and non-alphabetic symbols.
     * @param text Input text
     * @param shift Shift amount
     * @return Transformed string
     */
    private static String caesarCipher(String text, int shift) {
        ArrayList<Character> chars = new ArrayList<>();
        /* defines the language of the entered text by char and creates the new test using the known shift */
        for (char c : text.toCharArray()) {
            if (ENGLISH_SYMBOLS_LOWER.indexOf(c) != -1) {
                chars.add(newCharShift(c, shift, ENGLISH_SYMBOLS_LOWER));
            } else if (ENGLISH_SYMBOLS_UPPER.indexOf(c) != -1) {
                chars.add(newCharShift(c, shift, ENGLISH_SYMBOLS_UPPER));
            } else if (CYRILLIC_SYMBOLS_LOWER.indexOf(c) != -1) {
                chars.add(newCharShift(c, shift, CYRILLIC_SYMBOLS_LOWER));
            } else if (CYRILLIC_SYMBOLS_UPPER.indexOf(c) != -1) {
                chars.add(newCharShift(c, shift, CYRILLIC_SYMBOLS_UPPER));
            } else {
                chars.add(c);
            }
        }

        char[] charArray = new char[chars.size()];
        for (int i = 0; i < chars.size(); i++) {
            charArray[i] = chars.get(i);
        }
        return new String(charArray);
    }

    /**
     * Returns a shifted character using Caesar cipher within the specified alphabet.
     * @param c Character to shift
     * @param shift Shift
     * @param alphabet Alphabet string to use
     * @return Shifted character
     */
    private static char newCharShift(char c, int shift, String alphabet) {
        int index = alphabet.indexOf(c);
        int newIndex = (index + shift) % alphabet.length();
        if (newIndex < 0) {
            newIndex += alphabet.length();
        }
        return alphabet.charAt(newIndex);
    }

    /**
     * Attempts to decrypt the entered text using all possible shifts using Caesar cipher.
     * Prints all possible plaintexts.
     * @param text Encrypted text
     */
    private static void allDecrypt(String text) {
        String alphabetType = getAlphabet(text);

        if (alphabetType == null) {
            System.out.println("Alphabet of the text is not defined.");
            return;
        }

        System.out.println("Possible variants without known shift (" + alphabetType + "):");

        int usedShift = alphabetType.equals("LATIN") ? ENGLISH_SYMBOLS_LOWER.length() : CYRILLIC_SYMBOLS_UPPER.length();

        for (int i = 1; i < usedShift; i++) {
            String result = caesarCipher(text, -i);
            System.out.println("Used Shift: " + i + ", Decrypted text: " + result);
        }
    }

    /**
     * Detects if the entered text uses Latin or Cyrillic language.
     * @param text Input string
     * @return "LATIN", "CYRILLIC", or null if language is not detected
     */
    private static String getAlphabet(String text) {
        for (char ch : text.toCharArray()) {

            if (ENGLISH_SYMBOLS_LOWER.indexOf(ch) != -1 || ENGLISH_SYMBOLS_UPPER.indexOf(ch) != -1) {
                return "LATIN";
            } else if (CYRILLIC_SYMBOLS_LOWER.indexOf(ch) != -1 || CYRILLIC_SYMBOLS_UPPER.indexOf(ch) != -1) {
                return "CYRILLIC";
            }
        }
        return null;
    }

    /**
     * selects encryption/decryption operation depending on the type
     * @param encryptDecryptType used encrypt/decrypt type defined like "ENCRYPT_TYPE" or "DECRYPT_TYPE"
     * @param enteredText Input string to encrypt/decrypt
     */
    private static void handlerOperation(String encryptDecryptType, String enteredText) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        int shift = 0;
        boolean isCorrect = false;
        while (!isCorrect) {
            System.out.print("Enter the used shift (can be positive and negative): ");
            String enteredShift = reader.readLine().trim();

                try {
                    shift = encryptDecryptType.equals(DECRYPT_TYPE) && enteredShift.isEmpty() ? 0 : Integer.parseInt(enteredShift);
                    isCorrect = true;
                } catch (NumberFormatException e) {
                    System.out.println("Error: '" + enteredShift + "' is not integer value! Try again.");
                }
        }

        String encryptDecryptText = "";
        if (encryptDecryptType.equals(ENCRYPT_TYPE)) {
            encryptDecryptText = encrypt(enteredText, shift);
            System.out.println("Your entered text: " + enteredText + ", shift: " + shift);
            System.out.println("New Text: " + encryptDecryptText);
        } else if (encryptDecryptType.equals(DECRYPT_TYPE) && shift != 0) {
            encryptDecryptText = decrypt(enteredText, shift);
            System.out.println("Your entered text: " + enteredText + ", shift: " + shift);
            System.out.println("New Text: " + encryptDecryptText);
        }
        else if (encryptDecryptType.equals(DECRYPT_TYPE) && (shift == 0 || String.valueOf(shift).isEmpty()) ) {
            allDecrypt(enteredText);
        }
    }
}
