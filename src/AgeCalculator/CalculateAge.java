////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019. Arley Henostroza Mazmela
////////////////////////////////////////////////////////////////////////////////

package AgeCalculator;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CalculateAge {
	int EdadMaxima = 122;
	LocalDate inputDate;
	LocalDate currentDate;
	DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public CalculateAge() {
		currentDate = LocalDate.now();
	}

	public String calculate() throws IllegalArgumentException {
		if (currentDate.compareTo(inputDate) <= 0) {
			throw new IllegalArgumentException("Fecha Incorrecta: La fecha " + inputDate.format(format) + " es en el futuro");
		}
		Period difference = inputDate.until(currentDate);
		if (difference.getYears() > 122) {
			throw new IllegalArgumentException("Fecha Incorrecta: La edad de " + difference.getYears() + " es muy alta");
		}
		return PrintAge(difference);
	}

	public boolean validateDate(String date) {
		boolean isValid = true;
		date = date.trim();
		try {
			inputDate = LocalDate.parse(date, format);
		} catch (DateTimeParseException e) {
			isValid = false;
		}
		return isValid;
	}

	public String PrintAge(Period period) {
		String years = String.valueOf(period.getYears());
		String months = String.valueOf(period.getMonths());
		String days = String.valueOf(period.getDays());

		return "Tiene " + years + " años, " + months + " meses y " + days + " dias";
	}
}
