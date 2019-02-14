package numberConverter;

import java.awt.Color;
import java.util.Arrays;
import java.util.Scanner;

public class Converter {

	public static void main(String[] args) {
		
		GUI converterGUI = new GUI();
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a value ");
		String value = input.next();
		System.out.print("Enter the base ");
		int base = input.nextInt();

		String result = asciiToBinary(value);
		//System.out.println("result " + result);
	}

	public static String convertFromDecimal(String num, int base) {
		String result = "";

		if (!num.contains(".")) {
			result = cfdIntPart(num, base);
		}

		else {
			String[] split = num.split("\\.");
			String intPart = cfdIntPart(split[0], base);
			String remPart = cfdRemainderPart(split[1], base);
			result = intPart + "." + remPart;

		}
		result = padNumber(result, base);
		return result;
	}

	public static String cfdIntPart(String num, int base) {
		String reverseVal = "";
		String result = "";
		double val = Double.parseDouble(num);
	//	System.out.println("val" + val);
		int indexVal = 0;

		while (val >= 1.0) {
			indexVal = (int) (val % base);
			// System.out.println("index Val" + indexVal);
			val = val / base;
			if (indexVal >= 10) {
				char hexChar = convertToHexChar(indexVal);
				reverseVal += hexChar;
			} else {
				reverseVal += indexVal;
			}
		}
		//System.out.println("reverseVal " + reverseVal);
		for (int i = reverseVal.length() - 1; i >= 0; i--) {
			result += reverseVal.charAt(i);
		}
		//System.out.println("RESULT " + result);
		return result;
	}

	public static String cfdRemainderPart(String num, int base) {

		double val = Double.parseDouble("." + num);
		int indexVal = 0;
		String result = "";

		for (int i = 0; i < 500; i++) {
			val = val * base;
			indexVal = (int) Math.floor(val);
			if (indexVal >= 10) {
				result += convertToHexChar(indexVal);
			} else {
				result += indexVal;
			}
		//	System.out.println("val " + val);

			

			val = val - Math.floor(val);

		}
		//System.out.println("result test " + result);
		return result;
	}

	public static String convertToDecimal(String num, int base) {
		String result = "";
		if (!num.contains(".")) {
			result = ctdIntPart(num, base);
		} else {

			String[] split = num.split("\\.");
			String intPart = split[0];
			String remainderPart = split[1];
			result = ctdIntPart(intPart, base) + "." + ctdRemainderPart(remainderPart, base);
		}
		
		return result;
	}

	public static String ctdIntPart(String num, int base) {
		int indexVal = 0;
		int value = 0;
		String result = "";
		String reverseVal = reverseString(num);
		for (int i = 0; i < num.length(); i++) {
			if (reverseVal.charAt(i) >= 65) {

				indexVal = convertFromHexChar(reverseVal.charAt(i));
			} else {
				indexVal = Integer.parseInt(reverseVal.substring(i, i + 1));
			}
			value += indexVal * Math.pow(base, i);
		}
		result = Integer.toString(value);
		return result;
	}

	public static String ctdRemainderPart(String num, int base) {
		int indexVal = 0;
		double value = 0;
		String result = "";
	//	System.out.println("remainder number " + num);
		for (int i = 0; i < num.length(); i++) {
			indexVal = num.charAt(i) - 48;
			value += indexVal * Math.pow(base, -i - 1);
		//	System.out.println("value " + value);
		}
		result = Double.toString(value).substring(2);
		//System.out.println("result " + result);
		return result;
	}

	public static String binaryToFloatingPoint(String num) {
		String paddedVal = num;
		while(paddedVal.length() < 32) {
			paddedVal = '0' + paddedVal;
		}
		String sign = paddedVal.substring(0, 1);
		String expBin = paddedVal.substring(1, 9);
		String mantissaBin = paddedVal.substring(9);
		String result = "";
		String expDecStr = convertToDecimal(expBin, 2);
		String mantissaDecStr = convertToDecimal("1." + mantissaBin, 2);
		//System.out.println("SIGN " + sign + " EXPONENTVALUE " + expBin + " MANTISSA " + mantissaBin);
		String baseValStr = mantissaDecStr;
		int exponentVal = Integer.parseInt(expDecStr) - 127;
		double baseVal = Double.parseDouble(baseValStr);

		double value = baseVal * Math.pow(2, exponentVal);

	//	System.out.println(exponentVal);
		//System.out.println(baseVal);

		result = Double.toString(value);

		return result;
	}

	public static String floatingPointToBinary(String num) {
		String result = "";
		String strVal = "";
		String sign = "0";
		double dblVal = Double.parseDouble(num);
		String mantissa = "";
		int expValue;
		int decimalEVal;
		String binaryEVal = "";
	//	System.out.println(dblVal);

		strVal = String.format("%.32f", dblVal);

		result = convertFromDecimal(strVal, 2);

		int decimalIndex = result.indexOf(".");
		int oneIndex = result.indexOf("1");

		if (dblVal >= 1) {
			mantissa = result.substring(1, decimalIndex);

			expValue = decimalIndex - 1;
		} else {
			mantissa = result.substring(oneIndex);
			expValue = -1 * oneIndex;
		}

		decimalEVal = expValue + 127;

		binaryEVal = convertFromDecimal(Integer.toString(decimalEVal), 2);

		while (binaryEVal.length() < 8) {
			binaryEVal = '0' + binaryEVal;
		}
		if(binaryEVal.length() > 8) {
			binaryEVal = binaryEVal.substring(0,8);
		}
		if (mantissa.length() > 23) {
			mantissa = mantissa.substring(0, 23);
		}
		System.out.println("exp " + binaryEVal);
		result = sign + binaryEVal + mantissa;
		System.out.println(result);
		String test = convertToDecimal(result, 2);
		System.out.println(result);
		return result;
	}
	
	public static String binaryToASCII(String num) {
		String binaryNum = num;
		String[] strValues = new String[4];
		String result = "";
		while(binaryNum.length() < 32) {
			binaryNum = '0' + binaryNum;
		}
		
		System.out.println(binaryNum);
		for(int i = 0; i < 28; i = i+8) {
			int j = 0;
			strValues[j] = binaryNum.substring(i, i + 8);
			result +=  (char) Integer.parseInt(convertToDecimal(strValues[j], 2));		
			j++;
		}
		
		return result;
	}
	
	public static String asciiToBinary(String num) {
		String result = "";
		char[] charValues = new char[4];
		int intVal;
		String temp = "";
		
		for(int i = 0; i < num.length(); i++) {
		
			charValues[i] = num.charAt(i);
			System.out.println("CHARVALUES " + charValues[i]);
			System.out.println("char at " + i + " " + charValues[i]);
			System.out.println("char values at " + i + " " + charValues[i]);
			intVal = charValues[i];
			System.out.println("INT VALUES " + intVal);
			System.out.println("int val " + intVal);
			temp = convertFromDecimal(Integer.toString(intVal), 2).substring(24,32);
		
			System.out.println("TEMP " + temp);
			result += temp;
		}
		
		System.out.println("result " + result + " result length " + result.length());
		return result;
		
	}

	public static char convertToHexChar(int hexVal) {
		char result;

		if (hexVal == 10) {
			result = 'A';
		} else if (hexVal == 11) {
			result = 'B';
		} else if (hexVal == 12) {
			result = 'C';
		} else if (hexVal == 13) {
			result = 'D';
		} else if (hexVal == 14) {
			result = 'E';
		} else {
			result = 'F';
		}
		return result;
	}

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
	
	public static String colorToHexString(Color color) {
		String red = Integer.toString(color.getRed());
		String green = Integer.toString(color.getGreen());
		String blue = Integer.toString(color.getBlue());
		
		
		String redHexVal = convertFromDecimal(red, 16).substring(6,8);
		String greenHexVal = convertFromDecimal(green, 16).substring(6,8);
		String blueHexVal = convertFromDecimal(blue, 16).substring(6,8);
		
		String hexColorVal = "00" + redHexVal + greenHexVal + blueHexVal;
				
		return hexColorVal;
		
	}

	public static String reverseString(String str) {

		String result = "";
		for (int i = str.length() - 1; i >= 0; i--) {
			result += str.charAt(i);
		}

		return result;
	}
	
	public static String padNumber(String num, int base) {		
		String paddedVal = num;
		
		if(base == 2) {
			while(paddedVal.length() < 32) {
				paddedVal = '0' + paddedVal;
			}
		}
			else if(base == 8) {
				while(paddedVal.length() < 10) {
					paddedVal = '0' + paddedVal;
				}
			}
				else if(base == 16) {
					while (paddedVal.length() < 8) {
						paddedVal = '0' + paddedVal;
					}
				}
		
		return paddedVal;
	}

	public static String convertFromSN(String num) {

		String[] split = num.split("e|E(\\+|-)?");
		System.out.println(split[0].length());
		int distance = split[0].length() - 2;
		System.out.println("dist " + distance);
		String result = split[0].replace(".", "");

		int expVal = Integer.parseInt(split[1]);

		expVal = expVal - distance;
		System.out.println(split[0]);
		System.out.println("expVal " + expVal);

		for (int i = 0; i < expVal; i++) {
			result += "0";
		}

		System.out.println("Result " + result);

		return result;
	}
	
}
