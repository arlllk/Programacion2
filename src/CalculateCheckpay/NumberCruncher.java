package CalculateCheckpay;

import javax.swing.*;
import java.awt.*;

import static CalculateCheckpay.Validator.isValid;

public class NumberCruncher {
	CalculateCheckpay info;

	double baseSalary = -1;
	double discount = -1;
	double totalSalary = -1;

	int additionalDiscount = 0;
	int additionalBonus = 0;

	NumberCruncher(CalculateCheckpay base) {
		info = base;
	}

	boolean genericTester(JTextField input) {
		if (!isValid(input.getText())) {
			input.setForeground(Color.RED);
			input.selectAll();
			return false;
		}
		return true;
	}

	boolean ValidateBaseInfo() {
		if (!genericTester(info.baseInput)) {
			return false;
		}
		if (!genericTester(info.hourInput)) {
			return false;
		}
		return genericTester(info.hourlyPayInput);
	}

	void calculateWithoutDiscount() {
		double base, payForHour, hoursDone;
		getBonusState();
		if (ValidateBaseInfo()) {
			base = Double.parseDouble(info.baseInput.getText());
			hoursDone = Double.parseDouble(info.hourInput.getText());
			payForHour = Double.parseDouble(info.hourlyPayInput.getText());
			baseSalary = base + (payForHour * hoursDone) + (((baseSalary * 100) * additionalBonus) / 10000);
		} else {
			baseSalary = -1;
		}

	}

	boolean ValidateDiscountInfo() {
		if (!genericTester(info.missedDaysInput)) {
			return false;
		}
		return genericTester(info.discountForMissedInput);
	}

	void calculateDiscount() {
		double missedDays, discountForEachDay;
		if (ValidateDiscountInfo()) {
			missedDays = Double.parseDouble(info.missedDaysInput.getText());
			discountForEachDay = Double.parseDouble(info.discountForMissedInput.getText());
			discount = missedDays * discountForEachDay;
		} else {
			discount = -1;
		}
	}

	void calculateExtraPenalty() {
		if (discount >= 100 && discount < 200) {
			additionalDiscount = 2;
		} else if (discount >= 200) {
			additionalDiscount = 5;
		} else {
			additionalDiscount = 0;
		}
	}

	void calculateTotalSalary() {
		calculateWithoutDiscount();
		calculateDiscount();
		calculateExtraPenalty();
		getBonusState();
		totalSalary = baseSalary - discount - (((baseSalary * 100) * additionalDiscount) / 10000);
	}

	private void getBonusState() {
		if (info.bonus3.isSelected()) {
			additionalBonus = 3;
		} else {
			additionalBonus = 0;
		}
	}

	boolean isSuccessful() {
		if (this.baseSalary == -1) {
			return false;
		}
		if (this.discount == -1) {
			return false;
		}
		return this.totalSalary != -1;
	}

}
