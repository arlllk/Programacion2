////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019. Arley Henostroza Mazmela
////////////////////////////////////////////////////////////////////////////////

package CalculateCheckpay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CheckpayListener implements ActionListener, KeyListener {
	CalculateCheckpay context;

	CheckpayListener(CalculateCheckpay context) {
		this.context = context;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == context.calcNewButton) {
			if (context.calcNewButton.getText().equals(context.CALCULAR)) {
				context.getCalculatedValues();
				if (context.numberCruncher.isSuccessful()) {
					context.calcNewButton.setText(context.NUEVO);
					context.calcNewButton.setMnemonic(KeyEvent.VK_N);
					context.calcNewButton.updateUI();
				}
			} else {
				context.getCalculatedValues();
				context.baseInput.setText("");
				context.hourInput.setText("");
				context.hourlyPayInput.setText("");
				context.salaryWithoutDiscountsInput.setText("");
				context.missedDaysInput.setText("");
				context.discountForMissedInput.setText("");
				context.amountDiscountedInput.setText("");
				context.totalSalaryInput.setText("");
				context.calcNewButton.setText(context.CALCULAR);
				context.calcNewButton.setMnemonic(KeyEvent.VK_C);
				context.calcNewButton.updateUI();
			}
		} else if (e.getSource() == context.cancelButton) {
			//Que deberia de hacer esto??
		} else if (e.getSource() == context.quitButton) {
			if (JOptionPane.showConfirmDialog(null, "Confirmar Salida", "SALIR",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				this.context.dispose();
			}
		} else if (e.getSource() == context.bonus3) {
			if (context.calcNewButton.getText().equals(context.CALCULAR)) {
				context.getCalculatedValues();
				if (context.numberCruncher.isSuccessful()) {
					context.calcNewButton.setText(context.NUEVO);
					context.calcNewButton.setMnemonic(KeyEvent.VK_N);
					context.calcNewButton.updateUI();
					context.updateValues();
				}

			}
			context.getCalculatedValues();
			context.updateValues();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_TAB) {
			context.passFocusToNext();
		} else if (e.getKeyChar() == KeyEvent.VK_DELETE) {
			((JTextField) e.getSource()).setText("");
		} else if (e.getKeyChar() == KeyEvent.VK_PERIOD) {
			//This only avoid the next one to be activated
			//I bet there is a better way but is 3:30AM so...
		} else if (!Character.isDigit(e.getKeyChar())) {
			e.consume();
		} else if (((JTextField) e.getSource()).getForeground() == Color.RED) {
			((JTextField) e.getSource()).setForeground(Color.BLACK);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
