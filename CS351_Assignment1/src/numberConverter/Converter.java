package numberConverter;

import java.awt.Color;
import java.util.Arrays;
import java.util.Scanner;

/*
 * This class handles all the conversions between different number bases
 * 
 */
public class Converter {

	/*
	 * Converts a number from a different base to decimal
	 * 
	 * @param String num The number to be converted
	 * 
	 * @param int base The base of the number that is being converted
	 * 
	 * @return String result The decimal result after conversion
	 */
	public static String convertFromDecimal(String num, int base) {
		String result = "";

		// Checks if the number doesn't have a decimal i.e. real value
		if (!num.contains(".")) {
			result = cfdIntPart(num, base); // converts the number
		}

		// else the number has a decimal value and is a real value
		else {
			String[] split = num.split("\\."); // splits the number by the "."
			String intPart = cfdIntPart(split[0], base); // converts the integer part
			String remPart = cfdRemainderPart(split[1], base); // converts the remainder or "real" part
			result = intPart + "." + remPart; // adds them back together to get the result
		}
		 result = padNumber(result, base);
		return result;
	}

	/*
	 * Converts the integer part of a number from decimal to another base
	 * 
	 * @param String num The number to be converted
	 * 
	 * @param int base The base of the number that is being converted
	 * 
	 * @return String result The integer value of a converted number
	 */
	private static String cfdIntPart(String num, int base) {
		String reverseVal = ""; // Will hold reverse value of number
		String result = ""; // result to be returned
		double val = Double.parseDouble(num);
		int indexVal = 0; // value of the number at each index position

		// Converts the reverse value of the result
		while (val >= 1.0) {
			indexVal = (int) (val % base);
			val = val / base;
			// when converting to Hex Values
			if (indexVal >= 10) {
				char hexChar = convertToHexChar(indexVal); // gets char value for hexadecimal conversion
				reverseVal += hexChar;
			} else {
				reverseVal += indexVal;
			}
		}
		result = reverseString(reverseVal); // reverses the string to get correct value
		result = padNumber(result, base); // pads the number with 0's to fit into 32-bits
		return result;
	}

	/*
	 * Converts the remainder part of a number from decimal to another base
	 * 
	 * @param String num The number to be converted
	 * 
	 * @param int base The base of the number that is being converted
	 * 
	 * @return String result The remainder value of a converted number
	 */
	private static String cfdRemainderPart(String num, int base) {

		double val = Double.parseDouble("." + num); // stores the remainder part as a double value
		int indexVal = 0; // value at index of number
		int iterationValue = 10; // number of times to iterate through the loop/cut off value for numbers after
									// decimal
		String result = "";

		// increases the iteration value for the case when it is calculating floating
		// point decimals
		// to allow for greater precision
		if (num.length() > 20) {
			iterationValue = num.length() * 5;
		}

		// converts the value of the result
		for (int i = 0; i < iterationValue; i++) {
			val = val * base;
			// get's the floor value and stores it as the index value, i.e. 1.14 is 1
			indexVal = (int) Math.floor(val);
			// for when converting to hexadecimal
			if (indexVal >= 10) {
				result += convertToHexChar(indexVal); // gets the the Hex Char of indexVal
			} else {
				result += indexVal; // adds the indexVal to the result
			}

			val = val - Math.floor(val); // gets the remainder of val, i.e. 1.36 is 0.36

		}
		return result;
	}

	/*
	 * Converts a number from decimal to a different base
	 * 
	 * @param String num The number to be converted
	 * 
	 * @param int base The base of the number that is being converted to decimal
	 * 
	 * @return String result The decimal result after conversion
	 */
	public static String convertToDecimal(String num, int base) {
		String result = "";
		// checks that number doesn't contain a decimal point and is a whole integer
		if (!num.contains(".")) {
			result = ctdIntPart(num, base); // converts the integer value
		}
		// if the number has a decimal point, i.e. a real number
		else {
			String[] split = num.split("\\."); // splits the number by the "."
			String intPart = split[0]; // the integer part
			String remainderPart = split[1]; // the remainder part
			// converts the integer value and the remainder value and adding them together
			result = ctdIntPart(intPart, base) + "." + ctdRemainderPart(remainderPart, base);
		}

		return result;
	}

	/*
	 * Converts the integer part of a number from another base to decimal
	 * 
	 * @param String num The number to be converted
	 * 
	 * @param int base The base of the number that is being converted from
	 * 
	 * @return String result The integer value of a converted number as a String
	 */
	public static String ctdIntPart(String num, int base) {
		int indexVal = 0; // value at index of the string
		int value = 0;
		String result = "";
		String reverseVal = reverseString(num); // reverses the string

		// converts to decimal the integer value
		for (int i = 0; i < num.length(); i++) {
			// for when there are hexadecimal characters
			if (reverseVal.charAt(i) >= 65) {
				indexVal = convertFromHexChar(reverseVal.charAt(i)); // gets decimal value of the hexChar
			} else {
				indexVal = Integer.parseInt(reverseVal.substring(i, i + 1)); // stores value at index into indexVal
			}
			value += indexVal * Math.pow(base, i); // calculates the total converted value
		}

		result = Integer.toString(value); // strings the integer value and stores it into result
		return result;
	}

	/*
	 * Converts the remainder part of a number from another base to decimal
	 * 
	 * @param String num The number to be converted
	 * 
	 * @param int base The base of the number that is being converted from
	 * 
	 * @return String result The remainder value of a converted number
	 */
	public static String ctdRemainderPart(String num, int base) {
		int indexVal = 0; // will hold value at each index position
		double value = 0; // will hold the total value
		String result = "";

		// converts total value of conversion
		for (int i = 0; i < num.length(); i++) {
			indexVal = num.charAt(i) - 48; // gets integer value from ascii value

			// if the index Value is a hex char
			if (indexVal >= 17) {
				indexVal = indexVal - 7; // gets correct hexadecimal value for the char
			}
			value += indexVal * Math.pow(base, -i - 1); // stores the total value of the converted number
		}
		// gets rid of the '0.' portion of the value and converts to string i.e.
		// 0.110101 converts to 110101
		result = Double.toString(value).substring(2);
		return result;
	}

	/*
	 * Converts from binary to IEEE 32-bit floating point value
	 * 
	 * @param String num the binaryValue to be be converter
	 * 
	 * @return String result the converted floating point value
	 */
	public static String binaryToFloatingPoint(String num) {
		String binaryVal = num;

		// if the number is a real value it trims the value to the integer portion only
		if (binaryVal.contains(".")) {
			binaryVal = binaryVal.substring(0, binaryVal.indexOf("."));
		}

		String paddedVal = padNumber(binaryVal, 2); // pads the binary number to fit in 32-bits
		String expBin = paddedVal.substring(1, 9); // E value of IEEE 32-bit in binary
		String mantissaBin = paddedVal.substring(9); // mantissa value IEEE 32-bit in binary
		String expDecStr = convertToDecimal(expBin, 2); // decimal value of E
		String baseValStr = convertToDecimal("1." + mantissaBin, 2); // converts the base to decimal
		int exponentVal = Integer.parseInt(expDecStr) - 127; // decimal value of exponent, E - 127 in IEEE
		double baseVal = Double.parseDouble(baseValStr); // converts the base to a double
		double value = baseVal * Math.pow(2, exponentVal); // calculates the value of the floating point

		String result = Double.toString(value);

		return result;
	}

	/*
	 * Converts from an IEEE floating point value to its binary equivalent
	 * 
	 * @param String num the floating point value
	 * 
	 * @return String result the converted binary value
	 */
	public static String floatingPointToBinary(String num) {
		char sign = '0'; //value of the sign, set to '0' for positive
		double dblVal = Double.parseDouble(num);  //converted floating point stored as a double
		String mantissa = ""; // mantissa value
		int expValue; // exponent value
		int decimalEVal; // E value as a decimal
		String binaryEVal = ""; // E value as binary

		//formatted double value to display number 'written out' NOT in scientific notation
		String strVal = String.format("%.60f", dblVal); 
		System.out.println("strVal " + strVal);
		//converts to binary
		String binaryValue = convertFromDecimal(strVal, 2);
		
		//gets the index of where the '.' is
		int decimalIndex = binaryValue.indexOf(".");
		int oneIndex = binaryValue.indexOf("1");
		expValue = decimalIndex - oneIndex - 1;
		
		
		
		//if the floating point value is greater than or equal to 1
		if (dblVal >= 1) {			
			binaryValue = binaryValue.substring(binaryValue.indexOf('1'), binaryValue.indexOf('.')); 
			mantissa = binaryValue.substring(1); //gets the mantissa value
			
	
		} 
		//if the floating point value is less than 1
		else {

			binaryValue = binaryValue.substring(decimalIndex); //trims the result to start from the decimal point	
			oneIndex = binaryValue.indexOf("1"); //index of where the first 1 is in the binaryValue
			mantissa = binaryValue.substring(oneIndex + 1); //gets the mantissa from the binaryValue
			expValue = expValue+1; //adds one to the expValue to get correct value when the float value < 1
		}
		
		//trims the mantissa to the first 23 digits to fit
		if (mantissa.length() > 23) {
				mantissa = mantissa.substring(0,23);
			}
		
		//if mantissa is less than 23 it adds trailing '0's
		while(mantissa.length() < 23) {
			mantissa+= '0';
		}
		
		decimalEVal = expValue + 127; //gets the Decimal value of E
		binaryEVal = convertFromDecimal(Integer.toString(decimalEVal), 2); //gets the binary value of E
		oneIndex = binaryEVal.indexOf("1"); //index of where the first 1 is in the binaryEVal
		binaryEVal = binaryEVal.substring(oneIndex); //trims the index of excessive 0's
		
		//makes sure the EVal has a length of 8
		while(binaryEVal.length() < 8) {
			binaryEVal = '0' + binaryEVal;
		}	
	
		String result = sign + binaryEVal + mantissa; //complete IEE binary equivalent

		return result;
	}
	
	/*
	 * This method converts a 32-bit binary number into it's ASCII equivalent
	 * 
	 * @param String num the binary value to be converted
	 * 
	 * @return result The converted ASCII characters
	 */
	public static String binaryToASCII(String num) {

		String binaryNum = num;
		String[] strValues = new String[4]; //array to hold the binary strings for each char
		String result = "";

		//check if the binary number has a decimal
		if (num.contains(".")) {
			binaryNum = binaryNum.substring(0, num.indexOf(".")); //trims the binaryNum to only its integer part

		}

		binaryNum = padNumber(binaryNum, 2); //pads the binary value to fit 32-bit value  

		//splits the binary string into 4 to hold binary value of the characters
		for (int i = 0; i < 28; i = i + 8) {
			int j = 0; //index of binary String array
			strValues[j] = binaryNum.substring(i, i + 8); //adds 8 bits to each array
			
			//while looping through converts each binary value to decimal and then its character equivalent
			//then adding the char to the result
			result += (char) Integer.parseInt(convertToDecimal(strValues[j], 2));
			j++;
		}

		return result;
	}
	/*
	 * Converts ASCII character to its 32-bit binary value
	 * 
	 * @param charString The character string to be converted
	 * 
	 * @return result the converted binary value
	 */
	public static String asciiToBinary(String charString) {
		String result = "";
		char[] charValues = new char[4]; //array to hold each char value of string
		int intVal; //holds the integer value for each char
	
		//calculates the binary value of the charString
		for (int i = 0; i < charString.length(); i++) {

			charValues[i] = charString.charAt(i); //adds to the array the charValue
			intVal = charValues[i]; //converts the char to integer
			//converts the decimal value to binary then getting the last 8 bits for each char
			//and adding to the binary result string
			result+= convertFromDecimal(Integer.toString(intVal), 2).substring(24, 32);
			
		}
		
		result = padNumber(result, 2); //pads the binaryVal with 0's to fit 32 bits

		return result;

	}

	/*
	 * Converts an integer value into its hex character
	 * 
	 * @param integer intVal the integer equivalent of the hex value
	 * 
	 * @return char result the converted hex character
	 */
	public static char convertToHexChar(int intVal) {
		char result;

		if (intVal == 10) {
			result = 'A';
		} else if (intVal == 11) {
			result = 'B';
		} else if (intVal == 12) {
			result = 'C';
		} else if (intVal == 13) {
			result = 'D';
		} else if (intVal == 14) {
			result = 'E';
		} else {
			result = 'F';
		}
		return result;
	}

	/*
	 * Converts a hex character to its integer value
	 * 
	 * @param char hexVal the hexValue to be converted
	 * 
	 * @return integer result the converted integer value
	 */
	public static int convertFromHexChar(char hexVal) {
		int result = 0;

		if (hexVal == 'A') {
			result = 10;
		} else if (hexVal == 'B') {
			result = 11;
		} else if (hexVal == 'C') {
			result = 12;
		} else if (hexVal == 'D') {
			result = 13;
		} else if (hexVal == 'E') {
			result = 14;
		} else if (hexVal == 'F') {
			result = 15;
		}
		return result;
	}
	
	/*
	 * converts a color to its Hex Value equivalent
	 * 
	 * @param Color color the color to be converted
	 * 
	 * @return String hexColorVal the Hex Value of the color
	 */
	public static String colorToHexString(Color color) {
		String red = Integer.toString(color.getRed()); //gets the red integer value
		String green = Integer.toString(color.getGreen()); //gets the green integer value
		String blue = Integer.toString(color.getBlue()); //gets the blue integer value

		//converts the integer values of the colors to hexadecimal
		String redHexVal = convertFromDecimal(red, 16).substring(6, 8); 
		String greenHexVal = convertFromDecimal(green, 16).substring(6, 8);
		String blueHexVal = convertFromDecimal(blue, 16).substring(6, 8);
	
		//adds the alpha '00' and hexvalues together to get the hexadecimal string value
		String hexColorVal = "00" + redHexVal + greenHexVal + blueHexVal;
		
		return hexColorVal;

	}
	/*
	 * This method reverse a String
	 * 
	 * @param String str the String to be reversed
	 * 
	 * @return result the reversed string
	 */
	public static String reverseString(String str) {

		String result = "";
		for (int i = str.length() - 1; i >= 0; i--) {
			result += str.charAt(i);
		}

		return result;
	}

	/*
	 * This method pads the numbers with '0's in the front to fit 32 bit values
	 * depending on the base
	 * 
	 * @param String num the number to be padded
	 * @param int Base the base value of the number
	 * 
	 * @return result the padded Number
	 */
	public static String padNumber(String num, int base) {
		String paddedVal = num;
		//pads the string depending on the base of the number to fit 32-bit values  
		if (base == 2) {
			while (paddedVal.length() < 32) {
				paddedVal = '0' + paddedVal;
			}
		} else if (base == 8) {
			while (paddedVal.length() < 10) {
				paddedVal = '0' + paddedVal;
			}
		} else if (base == 16) {
			while (paddedVal.length() < 8) {
				paddedVal = '0' + paddedVal;
			}
		}

		return paddedVal;
	}
	
	/*
	 * Calculates the floor value for a real number
	 * 
	 * @param String num the number to be "trimmed" to the floor value
	 * 
	 * @return String result the floor value of the number
	 */
	public static String getFloorVal(String num) {
		String floorVal = num;
		
		//If the number is a whole integer
		if (!num.contains(".")) {
			return floorVal;
		}
		
		//if the number is a real value
		else {
			String[] split = num.split("\\."); //splits the number by the decimal point
			floorVal = split[0]; //floor value, which is the integer part of the number
		}
		return floorVal;
	}
}
