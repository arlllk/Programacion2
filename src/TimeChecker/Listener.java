////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019. Arley Henostroza Mazmela
////////////////////////////////////////////////////////////////////////////////

package TimeChecker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listener implements ActionListener, KeyListener {
	TimeChecker context;

	Listener(TimeChecker context) {
		this.context = context;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == context.calculateMinutesCheckbox) {
			context.hourInput.grabFocus();
			context.isMinutesEnabled();
		}
		if (e.getSource() == context.militarTimeCheckbox) {
			context.isMilitaryTimeActivated();
			context.hourInput.grabFocus();
		}
		if (e.getSource() == context.calc) {
			context.calculate();
		}
		if (e.getSource() == context.emptyHour) {
			context.hourInput.setText("");
			context.feedback.setText("");
			context.hourInput.grabFocus();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == context.hourInput) {
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				context.calculate();
			} else if (e.getKeyChar() == KeyEvent.VK_DELETE) {
				context.hourInput.setText("");
				context.hourInput.setCaretPosition(0);
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
