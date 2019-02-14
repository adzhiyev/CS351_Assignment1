package numberConverter;

import java.util.Arrays;
import java.util.Scanner;


public class NumberConverter {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		//System.out.println("Enter a decimal number: ");

		//String value = input.next();
		binaryToOctal("1000010101");
		//String output = convertToBinary(value, 2);
		//System.out.println("conversion " + output);
		//String convergence = convertFromBinary(output, 2);
		//System.out.println("Back to Decimal: " + convergence);
	}

	/*
	 * This function converts a number to binary 
	 */
	public static String convertToBinary(String num, int base) {
		String result = "";
		if (!num.contains(".")) {
			result = intToBinary(num, base);
		}
		else if (num.contains(".")) {
			String[] splitNum = num.split("\\.");

			String intVal = intToBinary(splitNum[0], base);
			String remainderVal = remainderToBinary("." + splitNum[1], base);
			result = intVal + remainderVal;
		}
		return result;
	}

	public static String intToBinary(String num, int base) {
		String reverseVal = "";
		String result = "";
		int bitVal = 0;
		int intVal = Integer.parseInt(num);

		while (intVal > 0) {
			bitVal = intVal % base;
			intVal = intVal / base;
			reverseVal += bitVal;
		}

		for (int i = reverseVal.length() - 1; i >= 0; i--) {
			result += reverseVal.charAt(i);
		}

		while (result.length() < 32) {
			result = "0" + result;
		}

		System.out.println("length " + result.length());
		return result;

	}

	public static String remainderToBinary(String num, int base) {
		int bitVal = 0;
		double value = Double.parseDouble(num);
		String result = ".";
		
		
		for (int i = 0; i < 15; i++) {
			value = value * 2;
			bitVal = (int) value;			
			System.out.println("bitval " + bitVal + " value " + value);

			result += bitVal;
			if (value < 1.0001 && value > 0.999) {
				System.out.println("bit val at break point " + bitVal);
				break;
				
			}
			value = (value - Math.floor(value));
		}

		System.out.println("result " + result);
		return result;
	}

	public static String convertFromBinary(String num, int base) {

		String result = "";

		if (!num.contains(".")) {
			binaryToInt(num, base);
		}

		else if (num.contains(".")) {
			String[] splitNum = num.split("\\.");

			String intVal = binaryToInt(splitNum[0], base);
			String remainderVal = binaryToRemainder("." + splitNum[1], base);
			result = intVal + remainderVal;
		}

		return result;
	}

	public static String binaryToInt(String num, int base) {
		String reverseVal = "";
		String result = "";
		int value = 0;
		int intVal = 0;

		for (int i = num.length() - 1; i > 0; i--) {
			reverseVal += num.charAt(i);
		}

		System.out.println("reverse val " + reverseVal);

		for (int i = 0; i < reverseVal.length(); i++) {
			value = reverseVal.charAt(i) - 48;

			intVal += (value * Math.pow(base, i));
		}

		result = Integer.toString(intVal);
		System.out.println("result " + result);
		return result;
	}

	public static String binaryToRemainder(String num, int base) {

		String result = "";
		int bitVal = 0;
		double tempVal = 0;
		double remVal = 0;
		System.out.println("num " + num);
		
		for(int i = 1; i < num.length();i++) {
			bitVal = num.charAt(i) - 48;
			System.out.println("value " + bitVal);
			tempVal = bitVal * Math.pow(base, -i);
			remVal += tempVal;
			System.out.println("remVal " + remVal);
		}
		result = Double.toString(remVal).substring(1);
		System.out.println("result " + result);
		return result;
		

	}
	
	public static String binaryToOctal(String num) {
		
		String reverseString = "";
		String[] bitsArray = new String[12];
		for(int i = num.length() - 1; i > 0;i--) {		
			reverseString += num.charAt(i);			
		}
		
		for(int i = 0; i < reverseString.length();i++) {
			if(num.substring(i) !=null && num.substring(i+1) !=null && num.substring(i+2) !=null && num.substring(i+3) !=null ) {
			bitsArray[i] = num.substring(i,i+3);
		}
		}
		
		System.out.println(Arrays.deepToString(bitsArray));
		System.out.println("reverse string " + reverseString);

	
		return num;
	}
}

