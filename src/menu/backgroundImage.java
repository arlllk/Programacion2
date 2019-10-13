package menu;

import javax.swing.*;
import java.awt.*;

public class backgroundImage extends JPanel {

	public backgroundImage() {
		super();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize);

	}

	@Override
	public void paintComponent(Graphics g) {
		Dimension size_frame = getSize();
		Image image = Toolkit.getDefaultToolkit().createImage("Background.png");
		//ImageIcon backgroundImage = new ImageIcon(getClass().getResource("background.jpg"));
		//g.drawImage(backgroundImage.getImage(),0,40,size_frame.width,size_frame.height,null);
		g.drawImage(image, 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}
}
