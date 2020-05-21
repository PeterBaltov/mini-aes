package miniaesdemo;

import javafx.util.Pair;

/**
 * <p>
 * This class implements all of the necessary steps to encrypt and decrypt
 * strings with the Mini AES algorithm.
 * </p>
 * 
 * @author Team Caligula
 * @version 1.0
 * @since 0.1
 */
public class MiniAES {

	/**
	 * <p>
	 * The constructor is empty, this class doesn't contain any variables outside of
	 * its methods.
	 * </p>
	 */
	public MiniAES() {

	}

	/**
	 * <p>
	 * This method takes a 4 nibble segment and applies a given round key to it.
	 * This is done by getting the result of applying the XOR operation to each
	 * individual nibble.
	 * </p>
	 *
	 * @param plainText An Integer Array of four elements that stores a segment of
	 *                  the plaintext.
	 *
	 * @param key       An Integer Array of four elements that stores a single round
	 *                  key.
	 *
	 * @return Returns the resulting array of 4 nibbles with the round key applied
	 *         to it.
	 */
	private static int[] addRoundKey(int[] plainText, int[] key) {
		int[] result = new int[4];
		// Loop that goes through every part of the plainText Array
		// Using XOR the RoundKey is added to every part of the Array
		for (int iter = 0; iter < 4; iter++) {
			result[iter] = plainText[iter] ^ key[iter];
		}
		// End of Loop

		return result;
	}

	/**
	 * <p>
	 * This method substitutes the contents of the plainText array with values from
	 * the <b><u>{@link Tables#NIBBLE_SUB_TABLE}</u></b> table using the
	 * <b><u>{@link Tables#getNibbleSubValue(int)}</u></b> method.
	 * </p>
	 *
	 * @param plainText An Integer Array of four elements that stores a segment of
	 *                  the plaintext.
	 *
	 * @return Returns the resulting array of 4 nibbles substituted with values from
	 *         the NibbleSub table.
	 */
	private static int[] nibbleSub(int[] plainText) {
		int[] result = new int[4];
		// The Loop goes through the NibbleSub Table
		// and substitutes every part of the plainText Array
		for (int iter = 0; iter < 4; iter++) {
			result[iter] = Tables.getNibbleSubValue(plainText[iter]);
		}
		// End of Loop

		return result;
	}

	/**
	 * <p>
	 * This method substitutes the contents of the plainText array with values from
	 * the <b><u>{@link Tables#NIBBLE_SUB_TABLE}</u></b> table using the
	 * <b><u>{@link Tables#getInverseNibbleSubValue(int)}</u></b> method.
	 * </p>
	 *
	 * @param plainText An Integer Array of four elements that stores a segment of
	 *                  the plaintext.
	 *
	 * @return Returns the resulting array of 4 nibbles substituted with values from
	 *         the inverse NibbleSub table.
	 */
	private static int[] inverseNibbleSub(int[] plainText) {
		int[] result = new int[4];
		// Iterates through the Inverse NibbleSub table
		// to get the values that are replaced in the plain Text
		// and returns the variable "result"
		for (int iter = 0; iter < 4; iter++) {
			result[iter] = Tables.getInverseNibbleSubValue(plainText[iter]);
		}
		// End of Loop

		return result;
	}

	/**
	 * <p>
	 * This method substitutes the contents of the plainText array with values from
	 * the S-BOX table.
	 * </p>
	 *
	 * @param plainText An Integer Array of four elements that stores a segment of
	 *                  the plaintext.
	 *
	 * @return Returns the resulting array of 4 nibbles substituted with values from
	 *         the S-BOX table.
	 */
	private static int[] subBytes(int[] plainText) {
		int[] result = new int[4];
		// The Loop goes through the NibbleSub Table
		// and substitutes every part of the plainText Array
		for (int iter = 0; iter < 4; iter++) {
			result[iter] = Tables.getSBoxValue(plainText[iter]);
		}
		// End of Loop

		return result;
	}

	/**
	 * <p>
	 * This method substitutes the contents of the plainText array with values from
	 * the inverse S-BOX table.
	 * </p>
	 *
	 * @param plainText An Integer Array of four elements that stores a segment of
	 *                  the plaintext.
	 *
	 * @return Returns the resulting array of 4 nibbles substituted with values from
	 *         the inverse S-BOX table.
	 */
	private static int[] inverseSubBytes(int[] plainText) {
		int[] result = new int[4];
		// The Loop goes through the NibbleSub Table
		// and substitutes every part of the plainText Array
		for (int iter = 0; iter < 4; iter++) {
			result[iter] = Tables.getInverseSBoxValue(plainText[iter]);
		}
		// End of Loop

		return result;
	}

	/**
	 * <p>
	 * This method swaps the last two elements of the plaintext.
	 * </p>
	 *
	 * @param plainText An Integer Array of four elements that stores a segment of
	 *                  the plaintext.
	 *
	 * @return Returns the resulting array of 4 nibbles with swapped the last two
	 *         elements.
	 */
	private static int[] shiftRows(int[] plainText) {
		int value1 = plainText[2];
		int value2 = plainText[3];

		// Shifts the last two Nibbles
		plainText[2] = value2;
		plainText[3] = value1;

		return plainText;
	}

	/**
	 * <p>
	 * This method multiplies each element by an element of a constant array, the
	 * result of this multiplication is retrieved from
	 * <b><u>{@link Tables#MULTIPLICATION_TABLE}</u></b>.
	 * </p>
	 *
	 * @param plainText An Integer Array of four elements that stores a segment of
	 *                  the plaintext.
	 *
	 * @return Returns the resulting array of 4 nibbles multiplied by the constant
	 *         array.
	 */
	private static int[] mixColumns(int[] plainText) {
		final int[] constMatrix = new int[] { 3, 2, 2, 3 };

		int[] result = {
				/*
				 * Using Multiplication table and the constant matrix in the MixColumns Section
				 * every nibble of the plain Text Array is assign with new value(XOR).
				 */
				(Tables.getMixColumnValue(constMatrix[0], plainText[0])
						^ (Tables.getMixColumnValue(constMatrix[2], plainText[2]))),
				(Tables.getMixColumnValue(constMatrix[3], plainText[1])
						^ (Tables.getMixColumnValue(constMatrix[1], plainText[3]))),
				(Tables.getMixColumnValue(constMatrix[2], plainText[0])
						^ (Tables.getMixColumnValue(constMatrix[0], plainText[2]))),
				(Tables.getMixColumnValue(constMatrix[1], plainText[1])
						^ (Tables.getMixColumnValue(constMatrix[3], plainText[3]))) };

		return result;
	}

	/**
	 * <p>
	 * This method multiplies each element of the plaintext by a
	 * <b><u><a target='_blank' href=
	 * 'https://en.wikipedia.org/wiki/Rijndael_MixColumns#Matrix_representation'>Rijndael
	 * constant matrix</a></u></b>.
	 * </p>
	 * <p>
	 * Each value is multiplied by a row of the constant matrix. Each multiplication
	 * retrieves a value from a multiplication table.
	 * </p>
	 * <p>
	 * Example: <b><u>{@link Tables#getMUL2Value(int)}</u></b>.
	 * </p>
	 * 
	 * @param plainText An Integer Array of four elements that stores a segment of
	 *                  the plaintext.
	 *
	 * @return Returns the resulting array of 4 nibbles multiplied by the constant
	 *         matrix.
	 */
	private static int[] mixColumnsAES(int[] plainText) {
		int[] result = {
				(Tables.getMUL2Value(plainText[0]) ^ Tables.getMUL3Value(plainText[1]) ^ plainText[2] ^ plainText[3]),
				(plainText[0] ^ Tables.getMUL2Value(plainText[1]) ^ Tables.getMUL3Value(plainText[2]) ^ plainText[3]),
				(plainText[0] ^ plainText[1] ^ Tables.getMUL2Value(plainText[2]) ^ Tables.getMUL3Value(plainText[3])),
				(Tables.getMUL3Value(plainText[0]) ^ plainText[1] ^ plainText[2] ^ Tables.getMUL2Value(plainText[3])) };

		return result;
	}

	/**
	 * <p>
	 * This method multiplies each element of the plaintext by a
	 * <b><u><a target='_blank' href=
	 * 'https://en.wikipedia.org/wiki/Rijndael_MixColumns#InverseMixColumns'>Rijndael
	 * inverse constant matrix</a></u></b>.
	 * </p>
	 * <p>
	 * Each value is multiplied by a row of the inverse constant matrix. Each
	 * multiplication retrieves a value from a multiplication table.
	 * </p>
	 * <p>
	 * Example: <b><u>{@link Tables#getMUL14Value(int)}</u></b>.
	 * </p>
	 * 
	 * @param cipherText An Integer Array of four elements that stores a segment of
	 *                   the ciphertext.
	 *
	 * @return Returns the resulting array of 4 nibbles multiplied by the constant
	 *         matrix.
	 */
	private static int[] inverseMixColumnsAES(int[] cipherText) {
		int[] result = {
				(Tables.getMUL14Value(cipherText[0]) ^ Tables.getMUL11Value(cipherText[1])
						^ Tables.getMUL13Value(cipherText[2]) ^ Tables.getMUL9Value(cipherText[3])),
				(Tables.getMUL9Value(cipherText[0]) ^ Tables.getMUL14Value(cipherText[1])
						^ Tables.getMUL11Value(cipherText[2]) ^ Tables.getMUL13Value(cipherText[3])),
				(Tables.getMUL13Value(cipherText[0]) ^ Tables.getMUL9Value(cipherText[1])
						^ Tables.getMUL14Value(cipherText[2]) ^ Tables.getMUL11Value(cipherText[3])),
				(Tables.getMUL11Value(cipherText[0]) ^ Tables.getMUL13Value(cipherText[1])
						^ Tables.getMUL9Value(cipherText[2]) ^ Tables.getMUL14Value(cipherText[3])) };

		return result;
	}

	/**
	 * <p>
	 * This method encapsulates the operations done in the encryption process of the
	 * Mini-AES. The process is broken down into a few steps:
	 * </p>
	 *
	 * <ol>
	 * <li><b><u>{@link #addRoundKey(int[], int[])}</u></b>: Adding the initial key
	 * to the plain text.</li>
	 *
	 * <li><b><u>{@link #nibbleSub(int[])}</u></b>: Substituting the values in the
	 * result of the <b><u>{@link #addRoundKey(int[], int[])}</u></b> step with
	 * values from the <b><u>{@link Tables#NIBBLE_SUB_TABLE}</u></b> table using the
	 * <b><u>{@link Tables#getNibbleSubValue(int)}</u></b> method.</li>
	 *
	 * <li><b><u>{@link #shiftRows(int[])}</u></b>: Shifting the last two elements
	 * of the result of the <b><u>{@link #nibbleSub(int[])}</u></b> step.</li>
	 *
	 * <li><b><u>{@link #mixColumns(int[])}</u></b>: Multiplying the result the
	 * <b><u>{@link #shiftRows(int[])}</u></b> step by a constant array.</li>
	 *
	 * <li><b><u>{@link #addRoundKey(int[], int[])}</u></b>: Adding the first round
	 * key to the result of the <b><u>{@link #mixColumns(int[])}</u></b> step.</li>
	 *
	 * <li><b><u>{@link #nibbleSub(int[])}</u></b>: Substituting the values in the
	 * result of the <b><u>{@link #addRoundKey(int[], int[])}</u></b> step with
	 * values from the <b><u>{@link Tables#NIBBLE_SUB_TABLE}</u></b> table using the
	 * <b><u>{@link Tables#getNibbleSubValue(int)}</u></b> method.</li>
	 *
	 * <li><b><u>{@link #shiftRows(int[])}</u></b>: Shifting the last two elements
	 * of the result of the <b><u>{@link #nibbleSub(int[])}</u></b> step.</li>
	 *
	 * <li><b><u>{@link #addRoundKey(int[], int[])}</u></b>: Adding the second round
	 * key to the result of the <b><u>{@link #shiftRows(int[])}</u></b> step.</li>
	 * </ol>
	 *
	 * @param plainText An Integer Array of four elements that stores a segment of
	 *                  the plaintext.
	 *
	 * @param key       An Integer Array of four elements that stores a single round
	 *                  key.
	 *
	 * @return Returns an encrypted binary string that stores the ciphertext.
	 */
	public static String Encrypt(String plainText, String key) {
		// Converting the plain Text and the key to Integer Arrays
		int[] plainTextArray = Utilities.stringToIntArray(plainText);
		int[] keyArray = Utilities.stringToIntArray(key);

		EncryptionDetails encryptionDetails = new EncryptionDetails(keyArray);

		// Adding the Round Key Zero
		plainTextArray = addRoundKey(plainTextArray, encryptionDetails.getKey0());

		// Goes through the nibble substitution
		plainTextArray = nibbleSub(plainTextArray);

		// The last two nibbles are shifted
		plainTextArray = shiftRows(plainTextArray);

		// Goes through the mixing of the columns using the Multiplication Table
		plainTextArray = mixColumns(plainTextArray);

		// Adding Round Key One
		plainTextArray = addRoundKey(plainTextArray, encryptionDetails.getKey1());

		// Goes through the nibble substitution again
		plainTextArray = nibbleSub(plainTextArray);

		// The last two nibbles are shifted again
		plainTextArray = shiftRows(plainTextArray);

		// Adding the final Round Key Two
		plainTextArray = addRoundKey(plainTextArray, encryptionDetails.getKey2());

		return Utilities.intArrayToString(plainTextArray);
	}

	/**
	 * <h2>Encrypt</h2>
	 *
	 * <p>
	 * This method works the same way as the
	 * <b><u>{@link #Encrypt(String plainText, String key)}</u></b> method, but it
	 * generates a random key instead of the user inputting one.
	 * </p>
	 *
	 * @param plainText         An Integer Array of four elements that stores a
	 *                          segment of the plaintext.
	 *
	 * @param generateRandomKey A boolean indicating if the user wants a random key
	 *                          to be generated.
	 * 
	 * @exception Exception on false <b>generateRandomKey</b> value.
	 *
	 * @return Returns an encrypted binary string that stores the ciphertext.
	 */
	public static Pair Encrypt(String plainText, boolean generateRandomKey) throws Exception {
		if (!generateRandomKey) {
			throw new Exception(
					"GenerateRandomKey variable set to false. Please provide a 16-bit binary key in it's place and re-start.");
		}

		RandomKey randomKey = new RandomKey();
		String key = randomKey.getRandomKeyString();
		// Converting the plain Text and the key to Integer Arrays
		int[] plainTextArray = Utilities.stringToIntArray(plainText);
		int[] keyArray = Utilities.stringToIntArray(key);

		EncryptionDetails encryptionDetails = new EncryptionDetails(keyArray);

		// Adding the Round Key Zero
		plainTextArray = addRoundKey(plainTextArray, encryptionDetails.getKey0());

		// Goes through the nibble substitution
		plainTextArray = nibbleSub(plainTextArray);

		// The last two nibbles are shifted
		plainTextArray = shiftRows(plainTextArray);

		// Goes through the mixing of the columns using the Multiplication Table
		plainTextArray = mixColumns(plainTextArray);

		// Adding Round Key One
		plainTextArray = addRoundKey(plainTextArray, encryptionDetails.getKey1());

		// Goes through the nibble substitution again
		plainTextArray = nibbleSub(plainTextArray);

		// The last two nibbles are shifted again
		plainTextArray = shiftRows(plainTextArray);

		// Adding the final Round Key Two
		plainTextArray = addRoundKey(plainTextArray, encryptionDetails.getKey2());

		Pair<String, String> result = new Pair<>(key, Utilities.intArrayToString(plainTextArray));

		return result;
	}

	/**
	 * <p>
	 * This method encapsulates the operations done in the decryption process of the
	 * Mini-AES. The process is broken down into a few steps:
	 * </p>
	 *
	 * <ol>
	 * <li><b><u>{@link #addRoundKey(int[], int[])}</u></b>: Adding the second round
	 * key to the result of the <b><u>{@link #shiftRows(int[])}</u></b> step.</li>
	 *
	 * <li><b><u>{@link #inverseNibbleSub(int[])}</u></b>: Substituting the values
	 * in the result of the <b><u>{@link #addRoundKey(int[], int[])}</u></b> step
	 * with the values from the <b><u>{@link Tables#NIBBLE_SUB_TABLE}</u></b> table
	 * using the <b><u>{@link Tables#getInverseNibbleSubValue(int)}</u></b>
	 * method.</li>
	 *
	 * <li><b><u>{@link #shiftRows(int[])}</u></b>: Shifting the last two elements
	 * of the result of the (<b><u>{@link #inverseNibbleSub(int[])}</u></b>)
	 * step.</li>
	 *
	 * <li><b><u>{@link #addRoundKey(int[], int[])}</u></b>: Adding the first round
	 * key to the result of the <b><u>{@link #shiftRows(int[])}</u></b> step.</li>
	 *
	 * <li><b><u>{@link #mixColumns(int[])}</u></b>: Multiplying the result the
	 * <b><u>{@link #addRoundKey(int[], int[])}</u></b> step by a constant
	 * array.</li>
	 *
	 * <li><b><u>{@link #inverseNibbleSub(int[])}</u></b>: Substituting the values
	 * in the result of the <b><u>{@link #addRoundKey(int[], int[])}</u></b> step
	 * with the values from the <b><u>{@link Tables#NIBBLE_SUB_TABLE}</u></b> table
	 * using the <b><u>{@link Tables#getInverseNibbleSubValue(int)}</u></b>
	 * method.</li>
	 *
	 * <li><b><u>{@link #shiftRows(int[])}</u></b>: Shifting the last two elements
	 * of the result of the <b><u>{@link #inverseNibbleSub(int[])}</u></b>
	 * step.</li>
	 *
	 * <li><b><u>{@link #addRoundKey(int[], int[])}</u></b>: Adding the initial key
	 * to the result of the <b><u>{@link #shiftRows(int[])}</u></b> step.</li>
	 * </ol>
	 *
	 * @param cipherText An Integer Array of four elements that stores a segment of
	 *                   the cipherText.
	 *
	 * @param key        An Integer Array of four elements that stores a single
	 *                   round key.
	 *
	 * @return Returns an encrypted binary string that stores the plainText.
	 */
	public static String Decrypt(String cipherText, String key) {
		// Converting the cipher Text and the key to Integer Arrays
		int[] cipherTextArray = Utilities.stringToIntArray(cipherText);
		int[] keyArray = Utilities.stringToIntArray(key);

		EncryptionDetails encryptionDetails = new EncryptionDetails(keyArray);

		// Adding the final Round Key - Key Two
		cipherTextArray = addRoundKey(cipherTextArray, encryptionDetails.getKey2());

		// Goes through the nibble substitution but using the inverse table
		cipherTextArray = inverseNibbleSub(cipherTextArray);

		// The last two nibbles are shifted
		cipherTextArray = shiftRows(cipherTextArray);

		// Adding the Round Key One
		cipherTextArray = addRoundKey(cipherTextArray, encryptionDetails.getKey1());

		// Goes through the mixing of the columns using the Multiplication Table
		cipherTextArray = mixColumns(cipherTextArray);

		// Goes through the nibble substitution but using the inverse table
		cipherTextArray = inverseNibbleSub(cipherTextArray);

		// The last two nibbles are shifted
		cipherTextArray = shiftRows(cipherTextArray);

		// Adding the Round Key Zero
		cipherTextArray = addRoundKey(cipherTextArray, encryptionDetails.getKey0());

		return Utilities.intArrayToString(cipherTextArray);
	}

	/**
	 * <p>
	 * This method divides an ASCII string into a matrix of 4 columns and however
	 * many rows are necessary. It proceeds to encrypt every segment using a user
	 * provided binary key. It follows the same process as the
	 * <b><u>{@link #Encrypt(java.lang.String, java.lang.String)}</u></b> method.
	 * </p>
	 * 
	 * @param plainText A string that stores the plaintext.
	 *
	 * @param key       A string that stores a binary key.
	 *
	 * @return Returns an encrypted hexadecimal string that stores the ciphertext.
	 */
	public static String EncryptText(String plainText, String key) {
		// Converting the plain Text and the key to Integer Arrays
		int[][] plainTextMatrix = Utilities.segmentAndPadData(plainText);
		int[] keyArray = Utilities.stringToIntArray(key);

		String encryptedText = "";
		final int plainTextMatrixLength = plainTextMatrix.length;

		EncryptionDetails encryptionDetails = new EncryptionDetails(keyArray);

		for (int iter = 0; iter < plainTextMatrixLength; iter++) {
			plainTextMatrix[iter] = addRoundKey(plainTextMatrix[iter], encryptionDetails.getKey0());

			plainTextMatrix[iter] = subBytes(plainTextMatrix[iter]);

			plainTextMatrix[iter] = shiftRows(plainTextMatrix[iter]);

			plainTextMatrix[iter] = mixColumnsAES(plainTextMatrix[iter]);

			plainTextMatrix[iter] = addRoundKey(plainTextMatrix[iter], encryptionDetails.getKey1());

			plainTextMatrix[iter] = subBytes(plainTextMatrix[iter]);

			plainTextMatrix[iter] = shiftRows(plainTextMatrix[iter]);

			plainTextMatrix[iter] = addRoundKey(plainTextMatrix[iter], encryptionDetails.getKey2());
		}

		for (int iter = 0; iter < plainTextMatrixLength; iter++) {
			encryptedText += Utilities.intArrayToHexString(plainTextMatrix[iter]);
		}

		return encryptedText;
	}

	/**
	 * <p>
	 * This method divides an ASCII string into a matrix of 4 columns and however
	 * many rows are necessary. It proceeds to encrypt every segment using a
	 * randomly generated binary key. It follows the same process as the
	 * <b><u>{@link #EncryptText(java.lang.String, java.lang.String)}</u></b>
	 * method.
	 * </p>
	 * 
	 * @param plainText         A string that stores the plaintext.
	 *
	 * @param generateRandomKey A boolean indicating if the user wants a random key
	 *                          to be generated.
	 * 
	 * @exception Exception on false <b>generateRandomKey</b> value.
	 *
	 * @return Returns an encrypted hexadecimal string that stores the ciphertext.
	 */
	public static Pair EncryptText(String plainText, boolean generateRandomKey) throws Exception {
		if (!generateRandomKey) {
			throw new Exception(
					"GenerateRandomKey variable set to false. Please provide a 16-bit binary key in it's place and re-start.");
		}

		RandomKey randomKey = new RandomKey();
		String key = randomKey.getRandomKeyString();
		// Converting the plain Text and the key to Integer Arrays
		int[][] plainTextMatrix = Utilities.segmentAndPadData(plainText);
		int[] keyArray = Utilities.stringToIntArray(key);

		String encryptedText = "";
		final int plainTextMatrixLength = plainTextMatrix.length;

		EncryptionDetails encryptionDetails = new EncryptionDetails(keyArray);

		for (int iter = 0; iter < plainTextMatrixLength; iter++) {
			plainTextMatrix[iter] = addRoundKey(plainTextMatrix[iter], encryptionDetails.getKey0());

			plainTextMatrix[iter] = subBytes(plainTextMatrix[iter]);

			plainTextMatrix[iter] = shiftRows(plainTextMatrix[iter]);

			plainTextMatrix[iter] = mixColumnsAES(plainTextMatrix[iter]);

			plainTextMatrix[iter] = addRoundKey(plainTextMatrix[iter], encryptionDetails.getKey1());

			plainTextMatrix[iter] = subBytes(plainTextMatrix[iter]);

			plainTextMatrix[iter] = shiftRows(plainTextMatrix[iter]);

			plainTextMatrix[iter] = addRoundKey(plainTextMatrix[iter], encryptionDetails.getKey2());
		}

		for (int iter = 0; iter < plainTextMatrixLength; iter++) {
			encryptedText += Utilities.intArrayToHexString(plainTextMatrix[iter]);
		}

		Pair<String, String> result = new Pair<>(key, encryptedText);

		return result;
	}

	/**
	 * <p>
	 * This method divides an ASCII string into a matrix of 4 columns and however
	 * many rows are necessary. It proceeds to decrypt every segment using a user
	 * provided binary key. It follows the same process as the
	 * <b><u>{@link #Decrypt(java.lang.String, java.lang.String)}</u></b> method.
	 * </p>
	 * 
	 * @param cipherText A hexadecimal string that stores the ciphertext.
	 *
	 * @param key        A string that stores a binary key.
	 *
	 * @return Returns an encrypted hexadecimal string that stores the ciphertext.
	 */
	public static String DecryptText(String cipherText, String key) {
		int[][] cipherTextMatrix = Utilities.segmentData(Utilities.hexStringToIntArray(cipherText));
		int[] keyArray = Utilities.stringToIntArray(key);

		String decryptedText = "";
		final int cipherTextMatrixLength = cipherTextMatrix.length;

		EncryptionDetails encryptionDetails = new EncryptionDetails(keyArray);

		for (int iter = 0; iter < cipherTextMatrixLength; iter++) {
			cipherTextMatrix[iter] = addRoundKey(cipherTextMatrix[iter], encryptionDetails.getKey2());

			cipherTextMatrix[iter] = inverseSubBytes(cipherTextMatrix[iter]);

			cipherTextMatrix[iter] = shiftRows(cipherTextMatrix[iter]);

			cipherTextMatrix[iter] = addRoundKey(cipherTextMatrix[iter], encryptionDetails.getKey1());

			cipherTextMatrix[iter] = inverseMixColumnsAES(cipherTextMatrix[iter]);

			cipherTextMatrix[iter] = inverseSubBytes(cipherTextMatrix[iter]);

			cipherTextMatrix[iter] = shiftRows(cipherTextMatrix[iter]);

			cipherTextMatrix[iter] = addRoundKey(cipherTextMatrix[iter], encryptionDetails.getKey0());
		}

		cipherTextMatrix[cipherTextMatrixLength - 1] = Utilities
				.removePadding(cipherTextMatrix[cipherTextMatrixLength - 1]);

		for (int iter = 0; iter < cipherTextMatrixLength; iter++) {
			for (int iter2 = 0; iter2 < cipherTextMatrix[iter].length; iter2++) {
				decryptedText += (char) (cipherTextMatrix[iter][iter2]);
			}
		}

		return decryptedText;
	}

}
