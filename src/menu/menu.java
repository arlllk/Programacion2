package menu;

import AddDays.AddDays;
import AgeCalculator.AgeCalculator;
import RadioButtonAge.Something;
import TimeChecker.TimeChecker;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

/*
Implemente un programa que le permita llenar una fecha formateada y una cantidad de dias, y luego calcular la fecha sumandole
a la fecha la cantidad de dias agregadas.donde la cantidad de dias no debe superar los 100
Caja de texto solo numeros
 */

/*
formatear una caja de texto que me permita ingresar horas y que verifique que la hora ingresada es la hora correcta
 */

/*
Vinculado desde el menu
 */
public class menu extends JFrame {
	Listener listener = new Listener(this);
	BackgoundPanel backgroundImage;

	JMenuBar menuBar = new JMenuBar();
	JMenu secondWeek = new JMenu("Segunda Semana");
	JMenuItem timeAgeItem = new JMenuItem("Edad");
	JMenuItem timeAddItem = new JMenuItem("Añadir dias");
	JMenuItem timeCheckHour = new JMenuItem("Acierta la hora");
	JMenu fourthWeek = new JMenu("Cuarta Semana");
	JMenuItem radioButtonEdad = new JMenuItem("Radio Botón Edad");

	public menu() {
		super("Menu");
		setUp();
		buildMenuBar();
		buildUI();
	}

	public static void main(String[] args) {
		new menu();
	}

	private void setUp() {
		try {
			backgroundImage = new BackgoundPanel("res/menu/Background.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(new ImageIcon(getClass().getResource("Icon.png")).getImage());
		this.setJMenuBar(menuBar);
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		this.setMinimumSize(new Dimension(500, 500)); //TODO: refine minimum size using menu bar length
		this.getContentPane().add(backgroundImage);

		this.timeAgeItem.addActionListener(listener);
		this.timeAddItem.addActionListener(listener);
		this.timeCheckHour.addActionListener(listener);

		this.radioButtonEdad.addActionListener(listener);

		this.addComponentListener(listener);
	}

	private void buildUI() {
		this.revalidate();
		this.repaint();
		//this.add(new backgroundImage(), new CC().alignX("center").alignY("center").grow().span());
		this.setLocationRelativeTo(null);
	}

	private void buildMenuBar() {
		menuBar.setLayout(new MigLayout());

		secondWeek.setMnemonic(KeyEvent.VK_S);
		fourthWeek.setMnemonic(KeyEvent.VK_F);

		timeAgeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
		timeAddItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		timeCheckHour.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));

		radioButtonEdad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));

		secondWeek.add(timeAgeItem);
		secondWeek.add(timeAddItem);
		secondWeek.add(timeCheckHour);

		fourthWeek.add(radioButtonEdad);

		menuBar.add(secondWeek);
		menuBar.add(fourthWeek);


	}

	void openAgeCalculator() {
		new AgeCalculator();
	}

	void openAddDays() {
		new AddDays();
	}

	void openCheckHour() {
		new TimeChecker();
	}

	void openRadioEdad() {
		new Something();
	}
}
