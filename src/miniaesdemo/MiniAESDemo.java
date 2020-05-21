package miniaesdemo;

import java.util.Scanner;
import javafx.application.Application;
import javafx.util.Pair;
import static miniaesdemo.Utilities.printLogo;

// Class for Testing the Mini-AES
/**
 * <p>
 * Class used to test the Mini-AES
 * </p>
 *
 * @author Team Caligula
 * @version 1.0
 * @since 0.1
 */
public class MiniAESDemo {
	/**
	 *
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// Application.launch(gui.class, args);

		Scanner scanner = new Scanner(System.in);
		int cliOption;

		cliMenu();

		do {
			System.out.print("\nInput: ");
			cliOption = scanner.nextInt();
			// Switch statement that iterates through our featuers depending on the input
			switch (cliOption) {
			case -1: {
				cliMenu();
				break;
			}
			case 1: {
				// Encryption of Binary String
				System.out.print("\nEnter binary string plaintext: ");
				scanner.nextLine();
				String plainText = scanner.nextLine();

				System.out.print("Enter a binary string key: ");
				String encryptionKey = scanner.nextLine();

				if (plainText.length() == 19 && encryptionKey.length() == 19) {
					System.out.println("\nCipher text: " + MiniAES.Encrypt(plainText, encryptionKey));
				} else {
					System.out.println("\nInvalid plaintext or key.");
				}

				break;
			}

			case 2: {
				// Encryption of Binary String with random Key
				System.out.print("\nEnter binary string plaintext: ");
				scanner.nextLine();
				String plainText = scanner.nextLine();

				// Cheking for correct input
				if (plainText.length() == 19) {
					Pair<String, String> result = new Pair<>("", "");

					try {
						result = MiniAES.Encrypt(plainText, true);
					} catch (Exception e) {
						System.out.println(e);
						System.exit(1);
					} finally {
						System.out.println("\nCipher text: " + result.getValue() + "\nRandom key: " + result.getKey());
					}
				} else {
					System.out.println("\nInvalid plaintext.");
				}

				break;
			}

			case 3: {
				// Dencryption of Binary String
				System.out.print("\nEnter binary string cipher text: ");
				scanner.nextLine();
				String cipherText = scanner.nextLine();

				System.out.print("Enter a binary string key: ");
				String encryptionKey = scanner.nextLine();

				if (cipherText.length() == 19 && encryptionKey.length() == 19) {
					System.out.println("\nDecrypted cipher text: " + MiniAES.Decrypt(cipherText, encryptionKey));
				} else {
					System.out.println("\nInvalid cipher text or key.");
				}

				break;
			}

			case 4: {
				// Encryption of Text
				System.out.print("\nEnter plaintext: ");
				scanner.nextLine();
				String plainText = scanner.nextLine();

				System.out.print("Enter a binary string key: ");
				String encryptionKey = scanner.nextLine();

				if (encryptionKey.length() == 19) {
					System.out.println("\nCipher text: " + MiniAES.EncryptText(plainText, encryptionKey));
				} else {
					System.out.println("\nInvalid key.");
				}

				break;
			}

			case 5: {
				// Encryption of Text with random Key
				System.out.print("\nEnter binary string plaintext: ");
				scanner.nextLine();
				String plainText = scanner.nextLine();

				Pair<String, String> result = new Pair<>("", "");

				try {
					result = MiniAES.EncryptText(plainText, true);
				} catch (Exception e) {
					System.out.println(e);
					System.exit(1);
				} finally {
					System.out.println("\nCipher text: " + result.getValue() + "\nRandom key: " + result.getKey());
				}

				break;
			}

			case 6: {
				// Decryption of Text with random Key
				System.out.print("\nEnter cipher text: ");
				scanner.nextLine();
				String cipherText = scanner.nextLine();

				System.out.print("Enter a binary string key: ");
				String encryptionKey = scanner.nextLine();

				if (encryptionKey.length() == 19) {
					System.out.println("\nDecrypted cipher text: " + MiniAES.DecryptText(cipherText, encryptionKey));
				} else {
					System.out.println("\nInvalid key.");
				}

				break;
			}
			}
		} while (cliOption != 0);
	}

	public static void cliMenu() {
		printLogo();

		System.out.println(" 1. Encrypt a binary string.\n" + " 2. Encrypt binary string with random key.\n"
				+ " 3. Decrypt a binary string\n" + " 4. Encrypt plaintext.\n"
				+ " 5. Encrypt plaintext with random key.\n" + " 6. Decrypt plaintext.\n\n" + " 0. Exit\n"
				+ "-1. Show menu.\n");
	}
}
