package AgeCalculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listener implements ActionListener, KeyListener {
	AgeCalculator context;

	public Listener(AgeCalculator context) {
		this.context = context;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == context.calc) {
			context.calulate();
		}
		if (e.getSource() == context.emptyButton) {
			context.dateField.setText("");
			context.dateField.grabFocus();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == context.dateField) {
			if (e.getKeyChar() == KeyEvent.VK_ENTER)
				context.calulate();
			else if (e.getKeyChar() == KeyEvent.VK_DELETE) {
				context.dateField.setText("");
			} else if (!Character.isDigit(e.getKeyChar())) {
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
