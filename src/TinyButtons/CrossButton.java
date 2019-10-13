////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019. Arley Henostroza Mazmela
////////////////////////////////////////////////////////////////////////////////

package TinyButtons;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CrossButton extends JButton {
	ImageIcon icon;

	public CrossButton() {
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		try {
			Image image = ImageIO.read(new File("res/TinyButtons/close.png"));
			image = image.getScaledInstance(12, 12, Image.SCALE_SMOOTH);
			icon = new ImageIcon(image);
			this.setIcon(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addIcon(String filename) {
		try {
			Image image = ImageIO.read(new File(filename));
			image = image.getScaledInstance(12, 12, Image.SCALE_SMOOTH);
			icon = new ImageIcon(image);
			this.setIcon(this.icon);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
