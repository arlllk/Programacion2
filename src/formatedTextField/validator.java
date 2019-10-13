package formatedTextField;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class validator {
	public static boolean validateDate(String date) {
		boolean isValid = true;

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		format.setLenient(false);
		try {
			format.parse(date);
		} catch (ParseException e) {
			isValid = false;
		}
		return isValid;
	}
}
