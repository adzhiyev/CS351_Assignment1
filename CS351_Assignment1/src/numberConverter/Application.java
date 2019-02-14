/*
 * @author Daniyal Adzhiyev 
 * Version: 0.0.1 Date: 
 * 1/25/2019 CS351
 * 
 *         This application creates a number converter between
 *         decimal, binary, octal, hexadecimal, ASCII value,
 *         RGB color value, and floating point decimal
 *         
 *         
 */
package numberConverter;

import java.util.Scanner;

/*
 * Main class holds application
 * 
 * *Need to create a NumberConverter Class to remove from main and make it simpler*
 */
public class Application {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		String number;
		// GUI object to access GUI class
		GUI application = new GUI();
		System.out.println("Enter a decimal number: ");
		number = input.next();
		input.close();
		// Strings to test output to console, need to be removed once
		// once everything is done, and no longer needed
		String binaryVal = decimalToBinary(number);
		System.out.println("Binary Result: " + binaryVal);
		String decimalVal = binaryToDecimal(binaryVal);
		System.out.println("Binary to Decimal: " + decimalVal);
		String octalVal = decimalToOctal(number);
		System.out.println("Decimal to Octal: " + octalVal);
		String hexadecimalVal = decimalToHexadec(number);
		System.out.println("Decimal to HexaDecimal: " + hexadecimalVal);

		String floatVal = decimalToFloat(number);
		System.out.println("Decimal to Floating Decimal: " + floatVal);
		String floatToDecimal = floatToDecimal(floatVal);
		System.out.println("Float to Decimal: " + floatToDecimal);
		String octalToDecimal = octalToDecimal(octalVal);
		System.out.println("Octal to Decimal: " + octalToDecimal);
		String hexToDec = hexadecToDecimal(hexadecimalVal);
		System.out.println("HexaDecimal to Decimal " + hexToDec);
	}

	/*
	 * Converts from decimal to binary
	 * 
	 * *Need to change to make all binary numbers 32 bit*
	 */
	public static String decimalToBinary(String number) {

		long bitVal = 0; // holds the bitVal of the current digit being evaluated
		String reverseVal = ""; // binary string that holds the generated reverse value
		String result = ""; // binary string
		// Checks to see if the binary String doesn't have a ".", ensures it is an
		// integer
		if (!number.contains(".")) {
			System.out.println("test1");
			long decVal = Long.parseLong(number); // converts to integer Long

			// converts each decimal to binary
			while (decVal > 0) {
				bitVal = decVal % 2;
				decVal = decVal / 2; // divides decVal by 2 and changes it to that value
				reverseVal += bitVal;
			}

			// creates the binary string
			for (int i = reverseVal.length() - 1; i >= 0; i--) {
				result += reverseVal.charAt(i);
			}

			return result;
		} else {

			System.out.println("Test");
			String[] split = number.split("\\."); // creates an array with 2 String values the integer and decimal parts
			String realBinary = "";

			String intPart = split[0]; // Integer part
			String realPart = "." + split[1]; // Decimal part
			long intVal = Long.parseLong(intPart);
			double realVal = Double.parseDouble(realPart);
			System.out.println(split[0]);
			System.out.println(split[1]);

			// converts the Integer part to Binary
			while (intVal > 0) {
				bitVal = intVal % 2;
				intVal = intVal / 2;
				reverseVal += bitVal;
			}
			// converts the Decimal part to Binary
			for (int i = 0; i < 25; i++) {
				realVal = realVal * 2;
				bitVal = (int) realVal % 2;
				realBinary += bitVal;
				result+= bitVal;
				System.out.println("test: " + realVal);
				if (realVal == 1.00000) {					
					break;
				}
			}
			// Adds the two results back together to get the actual number needed
			result += reverseVal + "." + realBinary;

		}

		return result;
	}

	/*
	 * Converts from binary to decimal
	 */
	public static String binaryToDecimal(String number) {

		int bitVal = 0; // bit character either 0 or 1
		int decVal = 0;
		String result = "";

		// checks to make sure number is not Decimal Value
		if (!number.contains(".")) {
			for (int i = 0; i < number.length(); i++) {
				bitVal = number.charAt(number.length() - i - 1) - 48; // subtract 48 to get ASCII character

				System.out.println("bit value: " + bitVal);

				decVal += (bitVal * Math.pow(2, i)); // Decimal Value converted from the bitVal
				System.out.println("int value: " + number);

			}
			result = Integer.toString(decVal);
			return result;
		}
		// if decimal value splits into two numbers before and after decimal point
		else {

			double decIntVal = 0;
			String[] split = number.split("\\."); // array with the 2 values that are split at "."
			String strInteger = split[0]; // Integer part
			String strDecimal = split[1]; // decimal part

			long intValue = Long.parseLong(strInteger);
			double decimalValue = Double.parseDouble(strDecimal);
			// does same thing as with just a regular integer for the "integer" part of the
			// real number
			for (int i = 0; i < strInteger.length(); i++) {
				bitVal = strInteger.charAt(strInteger.length() - i - 1) - 48;

				decVal += (bitVal * Math.pow(2, i));
			}

			String strIntConvert = Long.toString(decVal);
			System.out.println("int convert " + strIntConvert);

			System.out.println("STR DECIMAL " + strDecimal);
			// Gets the "decimal" portion of the number
			for (int i = 0; i < strDecimal.length(); i++) {
				bitVal = strDecimal.charAt(i) - 48; // subtract 48 to get ASCII character

				System.out.println("bit value: " + bitVal);

				decIntVal += (bitVal * Math.pow(2, -i - 1)); // Decimal Value converted from the bitVal
				System.out.println(decIntVal);
				System.out.println("dec value: " + decIntVal);
				System.out.println("TEST@" + 1 * Math.pow(2, -1));

			}

			double total = decVal + decIntVal; // adds the numbers back together
			String strDecConvert = Double.toString(total); // converts back to string
			System.out.println("dec convert " + strDecConvert);

			result = strDecConvert;
		}
		return result;
	}

	/*
	 * Converts from decimal to Octal
	 * 
	 * *Need to change to 32 bit*
	 */
	public static String decimalToOctal(String number) {

		long decVal = Long.parseLong(number);
		long octVal = 0;
		String reverseVal = "";
		String result = "";

		// converts from decimal to octal
		while (decVal > 0) {
			octVal = decVal % 8;
			decVal = decVal / 8;

			reverseVal += octVal;
		}
		// reverses the "reversed String" to get the actual value
		for (int i = reverseVal.length() - 1; i >= 0; i--) {
			result += reverseVal.charAt(i);
		}

		return result;
	}

	/*
	 * Converts from octal to Decimal
	 */
	public static String octalToDecimal(String number) {

		int intOct = Integer.parseInt(number);
		int octVal = 0;
		int decVal = 0;
		String result = "";

		// Converts to decimal
		for (int i = 0; i < number.length(); i++) {
			octVal = number.charAt(number.length() - i - 1) - 48; // subtract 48 to get ASCII character

			System.out.println("oct value: " + octVal);

			decVal += (octVal * Math.pow(8, i));
			System.out.println("int value: " + number);

		}

		result = Integer.toString(decVal);
		return result;
	}

	/*
	 * Converts from decimal to hexadecimal
	 */
	public static String decimalToHexadec(String number) {

		long decVal = Long.parseLong(number);
		int hexVal = 0;
		String reverseVal = "";
		String result = "";

		// Converts to hexadecimal
		while (decVal > 0) {
			hexVal = (int) decVal % 16;
			decVal = decVal / 16;

			if (hexVal >= 0 && hexVal <= 9) {
				reverseVal += hexVal;
			} else if (hexVal == 10) {
				reverseVal += "A";
			} else if (hexVal == 11) {
				reverseVal += "B";
			} else if (hexVal == 12) {
				reverseVal += "C";
			} else if (hexVal == 13) {
				reverseVal += "D";
			} else if (hexVal == 14) {
				reverseVal += "E";
			} else {
				reverseVal += "F";
			}
		}

		// reversed "reversed string" to get actual value
		for (int i = reverseVal.length() - 1; i >= 0; i--) {
			result += reverseVal.charAt(i);
		}

		return result;
	}

	/*
	 * Converts from hexadecimal to Decimal
	 */
	public static String hexadecToDecimal(String number) {

		int hexVal = 0;
		int decVal = 0;
		String result = "";

		// converts to decimal
		for (int i = 0; i < number.length(); i++) {

			// converts decimal value to hexadecimal characters
			if (number.charAt(i) >= '0' && number.charAt(i) <= '9') {
				hexVal = Integer.parseInt(number.substring(i, i + 1)); // subtract 48 to get ASCII character
				System.out.println("hex val test " + hexVal);

			} else if (number.charAt(i) == 'A') {
				hexVal = 10;
			} else if (number.charAt(i) == 'B') {
				hexVal = 11;
			} else if (number.charAt(i) == 'C') {
				hexVal = 12;
			} else if (number.charAt(i) == 'D') {
				hexVal = 13;
			} else if (number.charAt(i) == 'E') {
				hexVal = 14;
			} else if (number.charAt(i) == 'F') {
				hexVal = 15;
			}
			System.out.println(hexVal);
			decVal += (hexVal * Math.pow(16, number.length() - i - 1));

		}

		result = Integer.toString(decVal);

		return result;
	}

	/*
	 * Converts from decimal to float
	 * 
	 * *This value is wrong and shows the scientific notation for the decimal, not
	 * the floating point decimal*
	 */
	public static String decimalToFloat(String number) {

		long decVal = Long.parseLong(number);
		String result = number.charAt(0) + "." + number.substring(1) + "e+" + (number.length() - 1);

		return result;
	}

	/*
	 * Converts number from Floating point to decimal
	 * 
	 * *This value is wrong and converts from scientific notation to decimal*
	 */
	public static String floatToDecimal(String number) {

		String floatString = number;
		String[] split = floatString.split("\\+"); // Splits string based on where "+" sign is

		String base = split[0]; // base value
		String exp = split[1]; // exponent value

		base = base.substring(0, base.length() - 1); // gets the number before the "e"

		System.out.println("base " + base + " exp " + exp);
		double dblBase = Double.parseDouble(base);
		double dblExp = Double.parseDouble(exp);
		double num = dblBase * Math.pow(10, dblExp);
		int numInt = (int) num;
		String result = Integer.toString(numInt);
		System.out.println(numInt);
		return result;
	}

}
