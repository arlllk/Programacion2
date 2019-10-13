////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019. Arley Henostroza Mazmela
////////////////////////////////////////////////////////////////////////////////

package menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BackgoundPanel extends JPanel {
	private final Image backgroundImageOriginal;
	private Image backgroundImage;
	private Dimension size;

	public BackgoundPanel(String fileName) throws IOException {
		backgroundImageOriginal = ImageIO.read(new File(fileName));
		backgroundImage = backgroundImageOriginal;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, this);
	}

	public void updateSize() {
		Dimension size = this.getSize();
		backgroundImage = backgroundImageOriginal.getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
	}
}
