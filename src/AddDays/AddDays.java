////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019. Arley Henostroza Mazmela
////////////////////////////////////////////////////////////////////////////////

package AddDays;

import TinyButtons.CrossButton;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class AddDays extends JFrame {
	Listener listener = new Listener(this);
	MaskFormatter mask;
	JLabel inputLabel = new JLabel("Ingrese una fecha", JLabel.CENTER);
	JFormattedTextField dateField = new JFormattedTextField(mask);
	CrossButton emptyDateButton = new CrossButton();
	JLabel daysInputLabel = new JLabel("Ingrese la cantidad de Dias a Agregar", JLabel.CENTER);
	JTextField daysInput = new JTextField();
	CrossButton emptyDaysLabel = new CrossButton();
	JButton calc = new JButton("Calcular");
	JTextField feedback = new JTextField("", JTextField.CENTER);

	{
		try {
			mask = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}


	public AddDays() {
		super("Agregar Dias");
		setUp();
		buildUI();
		postBuild();
	}

	public static void main(String[] args) {
		new AddDays();
	}

	void setUp() {
		Font monospace = new Font("Consolas", Font.PLAIN, 12);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new MigLayout(new LC().fill().insetsAll(null)));

		this.feedback.setEditable(false);

		this.daysInput.setColumns(5);

		this.dateField.setFont(monospace);
		this.daysInput.setFont(monospace);

		this.dateField.addKeyListener(listener);
		this.daysInput.addKeyListener(listener);

		this.calc.addActionListener(listener);
		this.emptyDateButton.addActionListener(listener);
		this.emptyDaysLabel.addActionListener(listener);
	}

	void buildUI() {
		this.add(inputLabel, new CC().spanX().growX().wrap());
		this.add(dateField, new CC().alignX("55%").split(2));
		this.add(emptyDateButton, new CC().wrap());

		this.add(daysInputLabel, new CC().spanX().growX().wrap());
		this.add(daysInput, new CC().alignX("55%").split(2));
		this.add(emptyDaysLabel, new CC().wrap());

		this.add(calc, new CC().alignX("right").width("button").wrap().tag("ok"));
		this.add(feedback, new CC().alignX("center").spanX().minWidth("10cm").growX());
	}

	void postBuild() {
		this.pack();
		this.setMinimumSize(this.getSize());
		this.setLocationRelativeTo(null);
		this.dateField.grabFocus();
	}

	void calcular() {
		DateAdder dateAdder = new DateAdder();
		if (this.dateField.getText().isEmpty()) {
			this.feedback.setForeground(Color.RED);
			this.feedback.setText("Debe ingresar una fecha");
			this.dateField.grabFocus();
		} else if (this.daysInput.getText().isEmpty()) {
			this.feedback.setForeground(Color.RED);
			this.feedback.setText("Debe ingresar un numero");
			this.daysInput.grabFocus();
		} else if (Integer.parseInt(this.daysInput.getText()) > 100) {
			this.feedback.setForeground(Color.RED);
			this.feedback.setText("La cantidad de dias debe ser menor a 100");
			this.daysInput.grabFocus();
		} else if (!dateAdder.isValidDate(this.dateField.getText())) {
			this.feedback.setForeground(Color.RED);
			this.feedback.setText("La fecha Ingresada no es valida");
			this.dateField.grabFocus();
		} else {
			dateAdder.setDaysToAdd(Integer.parseInt(this.daysInput.getText()));
			this.feedback.setForeground(Color.BLACK);
			this.feedback.setText(this.dateField.getText() + " + " + this.daysInput.getText() +
					" dias es: " + dateAdder.calculate());
		}
	}
}
