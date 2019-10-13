////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019. Arley Henostroza Mazmela
////////////////////////////////////////////////////////////////////////////////

package TimeChecker;

import TinyButtons.CrossButton;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class TimeChecker extends JFrame {
	CheckTime checkTime = new CheckTime();

	Listener listener = new Listener(this);

	MaskFormatter mask;

	JLabel inputLabel = new JLabel("Ingrese la hora actual", JLabel.CENTER);

	JFormattedTextField hourInput = new JFormattedTextField(mask);
	CrossButton emptyHour = new CrossButton();

	JButton calc = new JButton("¿Es correcto?");

	JTextField feedback = new JTextField("", JTextField.CENTER);

	JCheckBox calculateMinutesCheckbox = new JCheckBox("Usar minutos");
	JCheckBox militarTimeCheckbox = new JCheckBox("Usar tiempo militar");
	MaskFormatter withoutMinutes;
	MaskFormatter whitMinutes;

	{
		try {
			whitMinutes = new MaskFormatter("##:##");
			withoutMinutes = new MaskFormatter("##:xx");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public TimeChecker() {
		super("La hora correcta");
		setUp();
		buildUI();
		postBuild();
	}

	public static void main(String[] args) {
		new TimeChecker(); //TODO: remove
	}

	void setUp() {
		Font monospace = new Font("Consolas", Font.PLAIN, 12);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new MigLayout(new LC().fill().insetsAll(null)));

		calculateMinutesCheckbox.addActionListener(listener);

		this.calculateMinutesCheckbox.setSelected(false);
		isMinutesEnabled();

		this.militarTimeCheckbox.setSelected(false);
		isMilitaryTimeActivated();

		this.feedback.setEditable(false);

		this.hourInput.setFont(monospace);
		this.hourInput.setColumns(6);

		this.hourInput.addKeyListener(listener);

		this.calc.addActionListener(listener);
		this.emptyHour.addActionListener(listener);
		this.militarTimeCheckbox.addActionListener(listener);
	}

	void buildUI() {
		this.add(inputLabel, new CC().spanX().growX().wrap());
		this.add(hourInput, new CC().alignX("55%").split(2).spanX());
		this.add(emptyHour, new CC().wrap());

		this.add(calculateMinutesCheckbox, new CC().alignX("left").gapTop("5mm"));
		this.add(militarTimeCheckbox, new CC().gapBottom("5mm").wrap());

		this.add(calc, new CC().alignX("right").width("button").wrap().tag("ok"));
		this.add(feedback, new CC().alignX("center").spanX().minWidth("10cm").growX());
	}

	void postBuild() {
		this.pack();
		this.setMinimumSize(this.getSize());
		this.setLocationRelativeTo(null);
		this.hourInput.grabFocus();
	}

	void isMinutesEnabled() {
		if (this.calculateMinutesCheckbox.isSelected()) {
			whitMinutes.install(hourInput);
			hourInput.setText(hourInput.getText().replace(":", "").replace("xx", "").trim());
			checkTime.setMinutesChecked(true);
		} else {
			withoutMinutes.install(hourInput);
			hourInput.setText(hourInput.getText().replace(":", "").trim());
			checkTime.setMinutesChecked(false);
		}
	}

	void calculate() {
		checkTime.refreshTime();
		if (this.hourInput.getText().replace(":", "").replace("xx", "").trim().isEmpty()) {
			feedback.setForeground(Color.RED);
			feedback.setText("Debe ingresar una hora");
		} else if (!checkTime.isValidTime(this.hourInput.getText())) {
			feedback.setForeground(Color.RED);
			feedback.setText("La hora ingresada no es valida");
		} else {
			feedback.setForeground(checkTime.calculate().color);
			feedback.setText(checkTime.calculate().string);
		}
	}

	void isMilitaryTimeActivated() {
		if (this.militarTimeCheckbox.isSelected()) {
			checkTime.setMilitaryTimeUsed(true);
		} else {
			checkTime.setMilitaryTimeUsed(false);
		}
	}
}
