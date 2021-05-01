package assignmnets;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Simulator {
	
	public static void main(String[] args) {
		
		Assignment1 assignment = new Assignment1();
		System.out.println(assignment.printNumberInWord(10));
		
		System.out.println(assignment.reverseString("Devaraj"));
		
		String a = "something";
		a = "nothing";
		System.out.println(a);
		
		StringBuilder sb = new StringBuilder("Devaraj");
		System.out.println(sb.reverse());
		
		assignment.camelcase("thisIsTheTestForCamelCase");
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		LocalDate date = LocalDate.now();
		System.out.println(date);
				
		
	}

}
