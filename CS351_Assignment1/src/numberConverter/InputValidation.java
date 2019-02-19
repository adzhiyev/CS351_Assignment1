/*
 * @author Daniyal Adzhiyev 
 * Version: 1.1.0
 *  Date: 2/19/2019 
 *  CS351 - Assignment 1
 * 
 *         This application creates a number converter between
 *         decimal, binary, octal, hexadecimal, ASCII value,
 *         RGB color value, and floating point decimal
 *               
 */
package numberConverter;

/*
 * This class handles the input validation to make sure input for all values are accurate and within bounds of the limitations
 * of this number converter.
 */
public class InputValidation {

	/*
	 * Checks to see if input for a decimal number is valid
	 * 
	 * @param String decNum the decimal number to be evaluated
	 * 
	 * @return boolean the value if the expression is true or not
	 */
	public static boolean decimalValidation(String decNum) {

		double decimalNumber = 0;
		double maxNumber = 4294967295d;

		// tries to parse the string as a double
		try {
			decimalNumber = Double.parseDouble(decNum); // parses string as a double
		}

		// if not able to parse returns false
		catch (NumberFormatException e) {
			return false;
		}
		// checks to see if decimal number is within bounds for conversion
		if ((decimalNumber >= 0) && (decimalNumber <= maxNumber)) {
			return true;
		}

		return false;
	}

	/*
	 * Checks to see if input for a binary number is valid
	 * 
	 * @param String binaryNum the binary number to be evaluated
	 * 
	 * @return boolean the value if the expression is true or not
	 */
	public static boolean binaryValidation(String binaryNum) {

		int maxLength = 32; // max length of a binary string to fit in 32-bits

		// checks if length of the binary number is within bounds
		if (binaryNum.length() > maxLength) {
			return false;
		}

		// checks to see if values in the string don't contain a 0 or 1
		for (int i = 0; i < binaryNum.length(); i++) {

			// if it finds an invalid digit in the string it returns false
			if (binaryNum.charAt(i) != '0' && binaryNum.charAt(i) != '1') {

				return false;
			}
		}
		return true;
	}

	/*
	 * Checks to see if input for an octal number is valid
	 * 
	 * @param String octalNum the octal number to be evaluated
	 * 
	 * @return boolean the value if the expression is true or not
	 */
	public static boolean octalValidation(String octalNum) {

		int indexVal; // value of the character at each index
		int maxLength = 10; // sets max length of number string to 10, to fit inside 32-bits

		// checks if the number string is within allowed length
		if (octalNum.length() > maxLength) {
			return false;
		}

		int intZeroVal = 48; // char integer value at 0
		int intSevenVal = 56; // char integer value at 7
		// checks that the intVal is not in between 0 and 7
		for (int i = 0; i < octalNum.length(); i++) {
			indexVal = octalNum.charAt(i); // integer value of the character at i

			// if it is not between 0 and 7 returns false
			if (indexVal < intZeroVal || indexVal > intSevenVal) {
				return false;
			}
		}

		return true;
	}

	/*
	 * Checks to see if input for a Hexadecimal number is valid
	 * 
	 * @param String hexNum the hexadecimal number to be evaluated
	 * 
	 * @return boolean the value if the expression is true or not
	 */
	public static boolean hexValidation(String hexNum) {
		int maxLength = 8; // sets max length of number string to 8 for hexadecimal 32 bit values

		// if number is larger than max length return false
		if (hexNum.length() > maxLength) {
			return false;
		}

		int indexVal; // char Value at each position of the string
		int intZeroVal = 48; // char integer value at 0
		int intNineVal = 57; // char integer value at 9
		int intAVal = 65; // char integer value at A
		int intFVal = 70;// char integer value at F

		// checks if the strings characters are all between 0 and F
		for (int i = 0; i < hexNum.length(); i++) {
			indexVal = hexNum.charAt(i); // integer value of the character at i

			// if outside the allowed character value of 0 - F returns false;
			if ((indexVal < intZeroVal || indexVal > intNineVal) && (indexVal < intAVal || indexVal > intFVal)) {
				return false;
			}
		}
		return true;

	}

	/*
	 * Checks to see if input for an ASCII character is valid
	 * 
	 * @param String charString the character values to be evaluated
	 * 
	 * @return boolean the value if the expression is true or not
	 */
	public static boolean charValidation(String charString) {
		int maxLength = 4; // max length of character string

		// checks if the charString is greater than 4, if so returns false
		if (charString.length() > maxLength) {
			return false;
		}

		return true;
	}

	/*
	 * Checks to see if input for an IEE floating point number is valid
	 * 
	 * @param String floatNum the float number to be evaluated
	 * 
	 * @return boolean the value if the expression is true or not
	 */
	public static boolean floatValidation(String floatNum) {
		double floatNumber; // float number as a double

		// tries to parse the string as a Double
		try {
			floatNumber = Double.parseDouble(floatNum); // parses the string as a double
		}

		// if not able to parse as a double returns false
		catch (NumberFormatException e) {
			return false;
		}
		double maxNumber = 1.7014E38; // maximum value of floating point
		double minNumber = 1.175494E-38; // minimum value of floating point

		// checks if floatNumber is not within bounds, if so returns false
		if (floatNumber > maxNumber || floatNumber < minNumber) {
			return false;
		}
		return true;
	}
}
