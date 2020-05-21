package miniaesdemo;

/**
 * <p>
 * This class generates a random 16-bit key and stores it inside of a local
 * variable. It provides a straightforward way to access the randomKey variable
 * (<b><u>{@link #getRandomKey()}</u></b> and
 * <b><u>{@link #getRandomKeyString()}</u></b>).
 * </p>
 *
 * @author Team Caligula
 * @version 1.0
 * @since 0.5
 */
public class RandomKey {

	/**
	 * <p>
	 * This variable contains the random 16-bit binary key divided into four
	 * elements and stored in an integer array of four elements.
	 * </p>
	 */
	private int[] randomKey;

	/**
	 * <p>
	 * Constructor method that initializes the local
	 * <b><u>{@link #randomKey}</u></b> variable with a randomly generated key.
	 * </p>
	 */
	public RandomKey() {
		randomKey = new int[4];
		generateRandomKey();
	}

	/**
	 * <p>
	 * This method generates a random 16-bit binary key using <b>Math.random()</b>.
	 * </p>
	 */
	private void generateRandomKey() {
		// Iterates through the Array and sets random number
		for (int i = 0; i < 4; i++) {
			randomKey[i] = (int) (Math.random() * 15);
		}
		// End of loop
	}

	/**
	 * <p>
	 * Getter method for the randomly generated key. It gives users the ability to
	 * get the private <b><u>{@link #randomKey}</u></b> variable.
	 * </p>
	 *
	 * @return Returns the randomly generated key stored in an integer array of four
	 *         elements.
	 */
	public int[] getRandomKey() {
		return randomKey;
	}

	/**
	 * <p>
	 * Getter method for the randomly generated key converted to a string using the
	 * <b><u>{@link Utilities#intArrayToString(int[])}</u></b> method. It gives
	 * users the ability to get the private <b><u>{@link #randomKey}</u></b>
	 * variable.
	 * </p>
	 *
	 * @return Returns a 16-bit binary String
	 */
	public String getRandomKeyString() {
		return Utilities.intArrayToString(randomKey);
	}
}
