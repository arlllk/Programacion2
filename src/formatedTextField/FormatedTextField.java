////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019. Arley Henostroza Mazmela
////////////////////////////////////////////////////////////////////////////////

package formatedTextField;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class FormatedTextField extends JFrame {
	Listener listener = new Listener(this);
	MaskFormatter mask;
	JFormattedTextField dateField = new JFormattedTextField(mask);
	JLabel inputLabel = new JLabel("Ingrese su edad");
	JButton calc = new JButton("Obtener Edad");

	{
		try {
			mask = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public FormatedTextField() {
		super("formatedTextField");
		setUp();
		buildUI();
	}

	private void setUp() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new MigLayout(new LC().insetsAll("10").debug()));
		this.setEnabled(true);
		this.setVisible(true);

		calc.addActionListener(listener);
	}

	private void buildUI() {
		this.add(inputLabel, new CC().spanX().growX().minWidth("6cm"));
		this.add(dateField, new CC().spanX().growX());
		this.add(calc, new CC().alignX("right").width("button"));
		this.pack();
		this.setMinimumSize(this.getSize());
		this.setLocationRelativeTo(null);
	}

}
