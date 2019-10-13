package AddDays;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateAdder {
	private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private LocalDate inputDate;
	private int daysToAdd;

	public void setDaysToAdd(int daysToAdd) {
		this.daysToAdd = daysToAdd;
	}

	public String calculate() {
		LocalDate addedDate = inputDate.plusDays(daysToAdd);
		return addedDate.format(format);
	}

	public boolean isValidDate(String date) {
		boolean isValid = true;
		date = date.trim();
		try {
			inputDate = LocalDate.parse(date, format);
		} catch (DateTimeParseException e) {
			isValid = false;
		}
		return isValid;
	}
}
