package RadioButtonAge;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ListenerSomething implements ActionListener, KeyListener {
	Something context;

	public ListenerSomething(Something context) {
		this.context = context;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == context.calc) {
			context.processData();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_DELETE) {
			context.input.setText("");
		} else if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			context.processData();
		} else if (!Character.isDigit(e.getKeyChar())) {
			e.consume();
		} else if (context.input.getText().length() == 2) {
			e.consume();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		context.processData();
	}
}
