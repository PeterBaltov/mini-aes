
package miniaesdemo;

// Class for storage and generation of the Keys
/**
 * <p>
 * This class generates and stores encryption keys. It provides a
 * straightforward way to access the keys (<b><u>{@link #getKey0()},
 * {@link #getKey1()}, {@link #getKey2()}</u></b> methods).
 * </p>
 *
 * @author Team Caligula
 * @version 1.0
 * @since 0.1
 */
public class EncryptionDetails {
	// Creating Integer Arrays
	// to store the various keys

	/**
	 * <p>
	 * This variable is an integer array of four elements which stores the
	 * <b>initial</b> key. Which is used to calculate the rest of the keys using
	 * <b><u>{@link #generateKeys(int[])}</u></b> method.
	 * </p>
	 */
	private int[] key0;

	/**
	 * <p>
	 * This variable is an integer array of four elements which stores the
	 * <b>first</b> round key. Calculated using <b><u>{@link #key0}</u></b> with the
	 * <b><u>{@link #generateKey1()}</u></b> method.
	 * </p>
	 */
	private int[] key1;

	/**
	 * <p>
	 * This variable is an integer array of four elements which stores the
	 * <b>second</b> round key. Calculated using <b><u>{@link #key1}</u></b> with
	 * the <b><u>{@link #generateKey2()}</u></b> method.
	 * </p>
	 */
	private int[] key2;

	// Constructor Methods
	/**
	 * <p>
	 * Constructor method that initializes the local variables with empty objects.
	 * </p>
	 */
	public EncryptionDetails() {
		key0 = new int[4];
		key1 = new int[4];
		key2 = new int[4];
	}

	/**
	 * <p>
	 * Constructor method that takes in an <b>initial</b> key as a parameter and
	 * automatically generates the rest of the keys using the
	 * <b><u>{@link #generateKey1()}</u></b> and
	 * <b><u>{@link #generateKey2()}</u></b> methods.
	 * </p>
	 *
	 * @param initialKey A 16-bit binary String Array.
	 */
	public EncryptionDetails(int[] initialKey) {
		key0 = initialKey;
		key1 = generateKey1();
		key2 = generateKey2();
	}

	/**
	 * <p>
	 * This method takes in an <b>initial</b> key as a parameter and generates the
	 * rest of the keys using the <b><u>{@link #generateKey1()}</u></b> and
	 * <b><u>{@link #generateKey2()}</u></b> methods.
	 * </p>
	 * 
	 * @param key A variable that contains the initial key value.
	 */
	public void generateKeys(int[] key) {
		key0 = key;
		key1 = generateKey1();
		key2 = generateKey2();
	}

	/**
	 * <p>
	 * This method generates the <b>first</b> round key using the <b>initial</b>
	 * key.
	 * </p>
	 *
	 * @return Returns an Integer Array.
	 */
	private int[] generateKey1() {
		int[] key = new int[4];
		// Generating Key1 using the NibbleSub section
		// and the logical exclusive OR.
		key[0] = key0[0] ^ Tables.getNibbleSubValue(key0[3]) ^ 1;
		key[1] = key0[1] ^ key[0];
		key[2] = key0[2] ^ key[1];
		key[3] = key0[3] ^ key[2];

		// System.out.println(key);
		return key;
	}

	/**
	 * <p>
	 * This method generates the <b>second</b> round key, using the <b>first</b>
	 * round key.
	 * </p>
	 *
	 * @return Returns an Integer Array.
	 */
	private int[] generateKey2() {
		int[] key = new int[4];
		// Generating Key2 using the NibbleSub section
		// and the logical exclusive OR.
		key[0] = key1[0] ^ Tables.getNibbleSubValue(key1[3]) ^ 2;
		key[1] = key1[1] ^ key[0];
		key[2] = key1[2] ^ key[1];
		key[3] = key1[3] ^ key[2];

		// System.out.println(key);
		return key;
	}

	// Setter Method
	/**
	 * <p>
	 * Setter method for the <b>initial</b> key. It gives users the ability to set
	 * the private <b><u>{@link #key0}</u></b> variable.
	 * </p>
	 *
	 * @param key A 16-bit string containing the initial key.
	 */
	public void setKey0(String key) {
		key0 = Utilities.stringToIntArray(key);
	}

	// Getter Methods for Key0,Key1,Key2
	/**
	 * <p>
	 * Getter method for the <b>initial</b> key. It gives users the ability to get
	 * the private <b><u>{@link #key0}</u></b> variable.
	 * </p>
	 *
	 * @return Returns the initial key.
	 */
	public int[] getKey0() {
		return key0;
	}

	/**
	 * <p>
	 * Getter method for the <b>first</b> key. It gives users the ability to get the
	 * private <b><u>{@link #key1 }</u></b> variable.
	 * </p>
	 *
	 * @return Returns the first round key.
	 */
	public int[] getKey1() {
		return key1;
	}

	/**
	 * <p>
	 * Getter method for the <b>second</b> key. It gives users the ability to get
	 * the private <b><u>{@link #key2}</u></b> variable.
	 * </p>
	 *
	 * @return Returns the second round key.
	 */
	public int[] getKey2() {
		return key2;
	}

	// toString Methods using the intArraytoString Method in class Utilities.
	/**
	 * <p>
	 * This method uses the <b><u>{@link Utilities#intArrayToString(int[])}</u></b>
	 * method to convert the array into a string
	 * </p>
	 *
	 * @param arrayToConvert An Integer Array to be converted to string.
	 *
	 * @return Returns a 16-bit binary String.
	 */
	public String toString(int[] arrayToConvert) {
		return Utilities.intArrayToString(arrayToConvert);
	}
}
