package assignmnets;

public class Assignment1 {

	public String printNumberInWord(int number) {

		String numberInWord;

		if (number >= 0 && number <= 9) {
			switch (number) {
			case 0:
				numberInWord = "Zero";
				break;
			case 1:
				numberInWord = "One";
				break;
			case 2:
				numberInWord = "Two";
				break;
			case 3:
				numberInWord = "Three";
				break;
			case 4:
				numberInWord = "Four";
				break;
			case 5:
				numberInWord = "Five";
				break;
			case 6:
				numberInWord = "Six";
				break;
			case 7:
				numberInWord = "Seven";
				break;
			case 8:
				numberInWord = "Eight";
				break;
			case 9:
				numberInWord = "Nine";
				break;
			default:
				numberInWord = "Other";
				break;
			}
		} else {
			numberInWord = "Numebr is less than zero or greater than 9";
		}
		return numberInWord;
	}

	// reverse String
	public String reverseString(String str) {

		String reverseString = "";

		char[] mainString = str.toCharArray();

		for (int i = mainString.length - 1; i >= 0; i--) {
			reverseString = reverseString + mainString[i];
		}
		return reverseString;
	}

	// Split Camel case letter

	public int camelcase(String s) {

		int numberOfCharChanger = 0;
		//String[] camelCaseString = s.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
		String[] camelCaseToArray = s.split("(?=[A-Z])");
		for (int i = 0; i < camelCaseToArray.length; i++) {
			System.out.println(camelCaseToArray[i]);
			numberOfCharChanger++;
		}
		return numberOfCharChanger;
	}

	// Library fine
	public static int libraryFine(int d1, int m1, int y1, int d2, int m2, int y2) {

		int fine = 0;

		if (y1 > y2) {
			fine = 10000;
		} else if (y1 == y2) {
			if (m1 > m2) {
				fine = (m1 - m2) * 500;
			} else if (m1 == m2) {
				if (d1 > d2) {
					fine = (d1 - d2) * 15;
				}
			}
		}
		return fine;
	}
}
