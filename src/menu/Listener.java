////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019. Arley Henostroza Mazmela
////////////////////////////////////////////////////////////////////////////////

package menu;

import javax.swing.*;
import java.awt.event.*;

public class Listener implements ActionListener, ComponentListener, WindowListener {
	menu context;

	public Listener(menu context) {
		this.context = context;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == context.timeAgeItem) {
			context.openAgeCalculator();
			context.setState(JFrame.ICONIFIED);
		} else if (e.getSource() == context.timeAddItem) {
			context.openAddDays();
			context.setState(JFrame.ICONIFIED);
		} else if (e.getSource() == context.timeCheckHour) {
			context.openCheckHour();
			context.setState(JFrame.ICONIFIED);
		}
		if (e.getSource() == context.radioButtonEdad) {
			context.openRadioEdad();
			context.setState(JFrame.ICONIFIED);
		}
	}

	@Override
	public void componentResized(ComponentEvent e) {
		context.backgroundImage.updateSize();
		context.revalidate();
		context.repaint();
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {

	}

	@Override
	public void componentHidden(ComponentEvent e) {

	}

	@Override
	public void windowOpened(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}
}
