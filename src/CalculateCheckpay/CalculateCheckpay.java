////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019. Arley Henostroza Mazmela
////////////////////////////////////////////////////////////////////////////////

package CalculateCheckpay;

import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/*
Calculo del sueldo

baseLabel baseInput
cantidad de horas LBL INPUT
Monto total LBL INPUT
faltas LBL INPUT
DESCuento por faltas LBL INPUT
sueldo LBL INPUT


BOTONES:
Radio 2% de descuento
Radio 5% de descuento
CheckBox Bonificacion del 3% sobre la base
NUEVO -> Calcular
CANCELAR
SALIR
( Agregar mnemotecnics for buttons)

Sueldo = base +(cantidad de horas x Monto) - Descuento + Bonificacion
Descuento = (cantFaltas x DescuentoXFaltas) + %Calculado*Base

Si el descuento llega a estos valores se aplica esta penalidad porcentual
100-200 -> 2%
>200 -> 5%


Un caso precalculado en Excel (I say Unit Tests)


 */

public class CalculateCheckpay extends JFrame {
	final String CALCULAR = "Calcular";
	final String NUEVO = "Nuevo";
	CheckpayListener listener = new CheckpayListener(this);
	NumberCruncher numberCruncher = new NumberCruncher(this);
	JLabel baseLabel = new JLabel("Sueldo Base:");
	JLabel hourLabel = new JLabel("Cantidad de Horas trabajadas:");
	JLabel hourlyPayLabel = new JLabel("Sueldo por hora:");
	JLabel salaryWithoutDiscountsLabel = new JLabel("Salario sin descuentos:");
	JLabel missedDaysLabel = new JLabel("Dias faltados:");
	JLabel discountForMissedLabel = new JLabel("Descuento por dias faltados:");
	JLabel amountDiscountedLabel = new JLabel("Cantidad descontada:");
	JLabel totalSalaryLabel = new JLabel("Sueldo:");

	JTextField baseInput = new JTextField();
	JTextField hourInput = new JTextField();
	JTextField hourlyPayInput = new JTextField();
	JTextField salaryWithoutDiscountsInput = new JTextField();
	JTextField missedDaysInput = new JTextField();
	JTextField discountForMissedInput = new JTextField();
	JTextField amountDiscountedInput = new JTextField();
	JTextField totalSalaryInput = new JTextField();

	ButtonGroup discounts = new ButtonGroup();
	JRadioButton discount2 = new JRadioButton("Descuento de 2%");
	JRadioButton discount5 = new JRadioButton("Descuento de 5%");

	JCheckBox bonus3 = new JCheckBox("Bonificacion de 3% sobre base");

	JButton calcNewButton = new JButton(CALCULAR);
	JButton cancelButton = new JButton("Cancelar");
	JButton quitButton = new JButton("Salir");


	CalculateCheckpay() {
		setUp();
		buildUI();
		postBuildUI();
	}

	public static void main(String[] args) {
		new CalculateCheckpay();
	}

	private void setUp() {
		Font monospace = new Font("Consolas", Font.PLAIN, 12);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new MigLayout(new LC().insetsAll(null),
				new AC().grow(1, 1).fill(1, 2).align("right", 2)
						.size("5cm", 1)));
		this.setVisible(true);
		this.setFont(monospace);

		discounts.add(discount2);
		discounts.add(discount5);

		calcNewButton.setMnemonic(KeyEvent.VK_C);
		cancelButton.setMnemonic(KeyEvent.VK_A);
		quitButton.setMnemonic(KeyEvent.VK_S);

		totalSalaryInput.setEditable(false);
		salaryWithoutDiscountsInput.setEditable(false);
		amountDiscountedInput.setEditable(false);

		discount2.setEnabled(false);
		discount5.setEnabled(false);

		bonus3.addActionListener(listener);
		calcNewButton.addActionListener(listener);
		cancelButton.addActionListener(listener);
		quitButton.addActionListener(listener);

		baseInput.addKeyListener(listener);
		hourInput.addKeyListener(listener);
		hourlyPayInput.addKeyListener(listener);
		missedDaysInput.addKeyListener(listener);
		discountForMissedInput.addKeyListener(listener);

		baseInput.setFocusTraversalKeysEnabled(false);
		hourInput.setFocusTraversalKeysEnabled(false);
		hourlyPayInput.setFocusTraversalKeysEnabled(false);
		missedDaysInput.setFocusTraversalKeysEnabled(false);
		discountForMissedInput.setFocusTraversalKeysEnabled(false);
	}

	private void buildUI() {
		add(baseLabel);
		add(baseInput);
		add(discount2, "wrap");
		add(hourLabel);
		add(hourInput);
		add(discount5, "wrap");
		add(hourlyPayLabel);
		add(hourlyPayInput, "wrap");
		add(salaryWithoutDiscountsLabel);
		add(salaryWithoutDiscountsInput);
		add(bonus3, "wrap");
		add(missedDaysLabel);
		add(missedDaysInput, "wrap");
		add(discountForMissedLabel);
		add(discountForMissedInput);
		add(calcNewButton, "wrap");
		add(amountDiscountedLabel);
		add(amountDiscountedInput);
		add(cancelButton, "wrap");
		add(totalSalaryLabel);
		add(totalSalaryInput);
		add(quitButton, "wrap");
	}

	private void postBuildUI() {
		this.pack();
		this.setMinimumSize(this.getSize());
		this.setLocationRelativeTo(null);
		this.baseInput.grabFocus();
	}

	void passFocusToNext() {
		if (baseInput.getText().isEmpty()) {
			baseInput.grabFocus();
		} else if (hourInput.getText().isEmpty()) {
			hourInput.grabFocus();
		} else if (hourlyPayInput.getText().isEmpty()) {
			hourlyPayInput.grabFocus();
		} else if (missedDaysInput.getText().isEmpty()) {
			missedDaysInput.grabFocus();
		} else if (discountForMissedInput.getText().isEmpty()) {
			discountForMissedInput.grabFocus();
		} else {
			getCalculatedValues();
		}
	}

	void getCalculatedValues() {
		this.numberCruncher.calculateTotalSalary();
		if (numberCruncher.additionalDiscount == 2) {
			this.discount2.setSelected(true);
		} else if (numberCruncher.additionalDiscount == 5) {
			this.discount5.setSelected(true);
		} else {
			this.discounts.clearSelection();
		}
		updateValues();
	}

	void updateValues() {
		this.totalSalaryInput.setText(String.valueOf(this.numberCruncher.totalSalary));
		this.salaryWithoutDiscountsInput.setText(String.valueOf(this.numberCruncher.baseSalary));
		this.amountDiscountedInput.setText(String.valueOf(this.numberCruncher.discount));
	}
}
