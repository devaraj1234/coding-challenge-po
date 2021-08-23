package CreditCard;

import java.util.ArrayList;
import java.util.List;

public class ValidateCardNumber {

	public static void main(String[] args) {
		System.out.println(validateCard(1234567890123456L));
		System.out.println(validateCard(1234567890123452L));
	}

	public static boolean validateCard(long cardNumber) {

		StringBuilder num = new StringBuilder(Long.toString(cardNumber));

		if (num.length() >= 14 && num.length() <= 19) {

			int lastDigit = Integer.parseInt(num.substring(num.length() - 1));
			num.setLength(num.length() - 1);
			num.reverse();
			List<Integer> list = new ArrayList<>();

			for (int i = 0; i < num.length(); i++) {
				if (i % 2 == 0) {
					int intValue = Character.getNumericValue(num.charAt(i)) * 2;
					if (String.valueOf(intValue).length() == 2) {
						String doubleDigitValue = String.valueOf(intValue);
						int sumOfDoubleDigit = 0;
						for (int j = 0; j < doubleDigitValue.length(); j++) {
							sumOfDoubleDigit += Character.getNumericValue(doubleDigitValue.charAt(j));
						}
						list.add(sumOfDoubleDigit);
					} else {
						list.add(intValue);
					}
				} else {
					list.add(Character.getNumericValue(num.charAt(i)));
				}
			}

			int sumOfArrays = 0;
			for (Integer number : list) {
				sumOfArrays += number;
			}
			String abc = String.valueOf(sumOfArrays);
			int lastDigitOfSumOfArray = Integer.parseInt(abc.substring(abc.length() - 1));
			if ((10 - lastDigitOfSumOfArray) == lastDigit) {
				return true;
			}
			return false;
		}
		return false;
	}
}
