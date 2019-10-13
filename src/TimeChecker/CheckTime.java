////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019. Arley Henostroza Mazmela
////////////////////////////////////////////////////////////////////////////////

package TimeChecker;

import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CheckTime {
	private static final DateTimeFormatter formatCivilMinutes = DateTimeFormatter.ofPattern("kk:mm");
	private static final DateTimeFormatter formatMilitaryMinutes = DateTimeFormatter.ofPattern("HH:mm");
	private static final DateTimeFormatter formatCivil = DateTimeFormatter.ofPattern("kk");
	private static final DateTimeFormatter formatMilitary = DateTimeFormatter.ofPattern("HH");
	LocalTime presentTime;
	LocalTime inputTime;
	private boolean isMilitaryTimeUsed;
	private boolean isMinutesChecked;

	CheckTime() {
		presentTime = LocalTime.now();
		isMilitaryTimeUsed = false;
		isMinutesChecked = false;
	}

	public void refreshTime() {
		presentTime = LocalTime.now();
	}

	public MessageCarry calculate() throws IllegalArgumentException {
		if (isMinutesChecked) {
			if (inputTime.getHour() == presentTime.getHour()) {
				if (inputTime.getMinute() == presentTime.getMinute()) {
					return new MessageCarry(Color.BLACK, "La hora al minuto es la correcta");
				} else {
					return new MessageCarry(Color.RED, "La Hora es correcta, el minuto no");
				}
			} else {
				return new MessageCarry(Color.RED, "Ni la hora ni los minutos son correctos");
			}
		} else {
			if (inputTime.getHour() == presentTime.getHour()) {
				return new MessageCarry(Color.BLACK, "La hora es la correcta");
			} else {
				return new MessageCarry(Color.RED, "La hora no es correcta");
			}
		}
	}

	public boolean isValidTime(String time) {
		if (isMinutesChecked) {
			return validateTimeCivilMilitar(time, formatMilitaryMinutes, formatCivilMinutes);
		} else {
			time = time.substring(0, 2);
			return validateTimeCivilMilitar(time, formatMilitary, formatCivil);
		}
	}

	private boolean validateTimeCivilMilitar(String time, DateTimeFormatter formatMilitary, DateTimeFormatter formatCivil) {
		if (isMilitaryTimeUsed) {
			try {
				inputTime = LocalTime.parse(time, formatMilitary);
			} catch (DateTimeParseException e) {
				return false;
			}
		} else {
			try {
				inputTime = LocalTime.parse(time, formatCivil);
				if (presentTime.getHour() >= 12) {
					inputTime = inputTime.plusHours(12);
				}
			} catch (DateTimeParseException e) {
				return false;
			}
		}
		return true;
	}

	public void setMilitaryTimeUsed(boolean militaryTimeUsed) {
		isMilitaryTimeUsed = militaryTimeUsed;
	}

	public void setMinutesChecked(boolean minutesChecked) {
		isMinutesChecked = minutesChecked;
	}
}
