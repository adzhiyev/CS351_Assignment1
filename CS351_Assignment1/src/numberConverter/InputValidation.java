package numberConverter;

public class InputValidation {

	public static boolean decimalValidation(String decNum) {
		double decimalNumber = 0;
		double maxNum = 2147483647;
		try {
			decimalNumber = Double.parseDouble(decNum);
		}

		catch (NumberFormatException e) {
			return false;
		}

		if ((decimalNumber >= 0) && (decimalNumber <= maxNum)) {
			return true;
		}
		return false;
	}

	public static boolean binaryValidation(String binaryNum) {

		if (binaryNum.length() > 32) {
			return false;
		}

		for (int i = 0; i < binaryNum.length(); i++) {
			int test = binaryNum.charAt(i);
			if (binaryNum.charAt(i) != '0' && binaryNum.charAt(i) != '1') {
				
				return false;
			}
		}
		return true;
	}
	
	public static boolean octalValidation(String octalNum) {
		int intVal;
		if (octalNum.length() > 10) {
			return false;
		}
		
		for (int i = 0; i < octalNum.length(); i++) {
			intVal = octalNum.charAt(i);
			if(intVal < 48 || intVal > 56) {
				return false;
			}
		}
	
		return true;
	}
	
	public static boolean hexValidation(String hexNum) {
		int intVal;
		
		if(hexNum.length() > 8) {
			return false;
		}
		
		for(int i = 0; i < hexNum.length(); i++) {
			intVal = hexNum.charAt(i);
			System.out.println("int Val "+ intVal);
			if((intVal < 48 || intVal > 56) && (intVal < 65 || intVal > 70)) {
				return false;
			}
		}
		return true;
		
	}
	
	public static boolean charValidation(String charString) {
		if(charString.length() > 4) {
			return false;
		}
		return true;
	}
	
	public static boolean floatValidation(String floatNum) {
		double floatNumber;
		try {
			floatNumber = Double.parseDouble(floatNum);
		}

		catch (NumberFormatException e) {
			System.out.println("at catch");
			return false;
		}
		double maxNumber = 1.7014E38;
		double minNumber = 1.175494E-38;		
		
		if(floatNumber > maxNumber || floatNumber < minNumber) {
			System.out.println("max num " + maxNumber);
			System.out.println("min num " + minNumber);
			System.out.println("float number" + floatNumber);
			System.out.println("at number val check");
			return false;
		}
		return true;
	}
}
