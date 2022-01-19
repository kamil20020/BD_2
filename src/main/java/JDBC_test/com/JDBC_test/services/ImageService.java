package JDBC_test.com.JDBC_test.services;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.Icon;
import javax.swing.JLabel;

public class ImageService {

	public static byte[] getBytes(JLabel imageLabel) {
		
		Icon icon = imageLabel.getIcon();
		
		BufferedImage img = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = img.createGraphics();
		icon.paintIcon(null, g2d, 0, 0);
		g2d.dispose();

		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			
		    ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
		    
		    try {
		        ImageIO.write(img, "png", ios);
		        
		    }
		    finally {
		        ios.close();
		    }
		    
		    return baos.toByteArray();
		} 
		catch (IOException ex) {
			
		    ex.printStackTrace();
		}
		
		return null;
	}
}
