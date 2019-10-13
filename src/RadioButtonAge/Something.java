package RadioButtonAge;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

/*
Si la edad 0-10 -> radio Niño
edad >10 && <=17  -> radio Adolencente
edad >17 && <=26 -> Joven
edad >26 && <=60 -> radio Adulto
edad >60 -> radio Adulto mayor
 */

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
public class Something extends JFrame {
	ListenerSomething listener = new ListenerSomething(this);

	ButtonGroup edades = new ButtonGroup();
	JRadioButton kid = new JRadioButton("Niño");
	JRadioButton teen = new JRadioButton("Adolecente");
	JRadioButton young = new JRadioButton("Joven");
	JRadioButton adult = new JRadioButton("Adulto");
	JRadioButton senior = new JRadioButton("Adulto mayor");

	JButton calc = new JButton("Procesar");
	JLabel labEdad = new JLabel("Edad:");
	JTextField input = new JTextField();

	JTextField feedback = new JTextField("", JTextField.CENTER);

	public Something() {
		setUp();
		buildUI();
		postBuildUI();
	}

	public static void main(String[] args) {
		new Something();
	}

	void setUp() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new MigLayout(new LC().insetsAll(null).fill()));
		this.setVisible(true);

		feedback.setEditable(false);

		makeEdadesGroup();

		input.addKeyListener(listener);
	}

	void buildUI() {
		this.add(kid, new CC().growX().alignX("center").wrap());
		this.add(teen, new CC().growX().alignX("center").wrap());
		this.add(young, new CC().growX().alignX("center").wrap());
		this.add(adult, new CC().growX().alignX("center").wrap());
		this.add(senior, new CC().growX().alignX("center").wrap());
		this.add(calc, new CC().minWidth("button").alignX("left").wrap());
		this.add(labEdad, new CC().alignX("left").wrap());
		this.add(input, new CC().grow().alignX("center").span().wrap().width("5cm"));

		//this.add(feedback, new CC().gapTop("1cm").grow().alignX("center"));
	}

	void postBuildUI() {
		this.pack();
		this.setMinimumSize(this.getSize());
		this.setLocationRelativeTo(null);
		this.input.grabFocus();
	}

	void makeEdadesGroup() {
		edades.add(kid);
		edades.add(teen);
		edades.add(young);
		edades.add(adult);
		edades.add(senior);
	}

	void processData() {
		if (this.input.getText().isEmpty()) {
			this.edades.clearSelection();
			return;
		}
		int age = Integer.parseInt(this.input.getText());
		if (age <= 10) {
			this.kid.doClick();
		} else if (age <= 17) {
			this.teen.doClick();
		} else if (age <= 26) {
			this.young.doClick();
		} else if (age <= 60) {
			this.adult.doClick();
		} else {
			this.senior.doClick();
		}
	}

}
