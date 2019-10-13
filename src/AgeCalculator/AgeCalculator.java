////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019. Arley Henostroza Mazmela
////////////////////////////////////////////////////////////////////////////////

package AgeCalculator;

import TinyButtons.CrossButton;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class AgeCalculator extends JFrame {
	Listener listener = new Listener(this);
	MaskFormatter mask;
	JFormattedTextField dateField = new JFormattedTextField(mask);
	JLabel inputLabel = new JLabel("Ingrese su fecha de Nacimiento", JLabel.CENTER);
	JButton calc = new JButton("Obtener Edad");
	JTextField feedback = new JTextField("", JTextField.CENTER);
	CrossButton emptyButton = new CrossButton();

	{
		try {
			mask = new MaskFormatter("##/##/####*");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public AgeCalculator() {
		super("AgeCalculator");
		setUp();
		buildUI();
		postBuild();
	}

	public static void main(String[] args) {
		new AgeCalculator(); //TODO: remove
	}

	private void setUp() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new MigLayout(new LC().insetsAll("10").fill()));
		this.setEnabled(true);
		this.setVisible(true);
		this.dateField.setFont(new Font("Consolas", Font.PLAIN, 12));
		this.feedback.setEditable(false);

		this.dateField.addKeyListener(listener);

		calc.addActionListener(listener);
		emptyButton.addActionListener(listener);

	}

	private void buildUI() {
		this.add(inputLabel, new CC().spanX().growX().alignX("center").wrap());
		this.add(dateField, new CC().alignX("55%").split(2));
		this.add(emptyButton, new CC().wrap());
		this.add(calc, new CC().alignX("right").width("button").wrap().tag("ok"));
		this.add(feedback, new CC().alignX("center").spanX().minWidth("10cm").growX());
		this.pack();
		this.setMinimumSize(this.getSize());
		this.setLocationRelativeTo(null);
	}

	private void postBuild() {
		dateField.grabFocus();
	}

	void calulate() {
		CalculateAge calculateAge = new CalculateAge();
		if (!calculateAge.validateDate(this.dateField.getText())) {
			this.feedback.setForeground(Color.RED);
			this.feedback.setText("La fecha ingresada no es correcta");
		} else {
			try {
				this.feedback.setForeground(Color.BLACK);
				this.feedback.setText(calculateAge.calculate());
			} catch (IllegalArgumentException e) {
				this.feedback.setForeground(Color.RED);
				this.feedback.setText(e.getMessage());
			}
		}
		dateField.grabFocus();
	}
}
