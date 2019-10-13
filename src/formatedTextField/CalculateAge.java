////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019. Arley Henostroza Mazmela
////////////////////////////////////////////////////////////////////////////////

package formatedTextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CalculateAge {
	public static String calculate(String stringDate) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		LocalDate presentDate = LocalDate.now();

		return "";
	}
}
