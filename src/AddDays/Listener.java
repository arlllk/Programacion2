package AddDays;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listener implements ActionListener, KeyListener {
	AddDays context;

	Listener(AddDays context) {
		this.context = context;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == context.emptyDaysLabel) {
			context.daysInput.setText("");
			context.daysInput.grabFocus();
		} else if (e.getSource() == context.emptyDateButton) {
			context.dateField.setText("");
			context.dateField.grabFocus();
		} else if (e.getSource() == context.calc) {
			context.calcular();
		}
	}

	//Esto es un desastre desordenado y dificil de entender
	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == context.dateField) {
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				if (context.dateField.getText().trim().length() < 10) {
					//Si se presiona enter y la fecha no esta completa se vuelve en rojo
					context.dateField.setForeground(Color.RED);
				} else {
					//Si la fecha tiene un mal formato se continua a la casilla de los dias si esta vacia
					if (context.daysInput.getText().isEmpty()) {
						context.daysInput.grabFocus();
						context.daysInput.selectAll();
					} else {
						context.calcular();
					}
				}
			} else if (e.getKeyChar() == KeyEvent.VK_DELETE) {
				context.dateField.setText("");
			} else {
				if (context.dateField.getForeground() == Color.RED) {
					//Cuando la fecha esta malformada (Y esta en ROJO) cuando se cambie se volvera todo negro otra vez
					context.dateField.setForeground(Color.BLACK);
				}
			}
		} else if (e.getSource() == context.daysInput) {
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				e.consume();
				context.calcular();
			} else if (e.getKeyChar() == KeyEvent.VK_DELETE) {
				context.daysInput.setText("");
			}
			if (!Character.isDigit(e.getKeyChar())) {
				e.consume();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
