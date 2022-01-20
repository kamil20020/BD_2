package JDBC_test.com.JDBC_test.services;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

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
	
	public static byte[] convert(BufferedImage file) throws IOException {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		ImageIO.write(file, "jpg", baos);
		
		return baos.toByteArray();
	}
	
	public static BufferedImage resize(BufferedImage file, int width, int height) {
		
		Image resultingImage = file.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
	    BufferedImage outputImage = new BufferedImage(60, 60, BufferedImage.TYPE_INT_RGB);
	    
	    outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
	    
	    return outputImage;
	}
	
	public static byte[] convert(String filePath) throws IOException {
		
		BufferedImage file = initImage(filePath);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		ImageIO.write(file, "jpg", baos);
		
		return baos.toByteArray();
	}
	
	public static byte[] convert(String filePath, int width, int height) throws IOException {
		
		BufferedImage file = initImage(filePath);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		file = resize(file, width, height);
		
		ImageIO.write(file, "jpg", baos);
		
		return baos.toByteArray();
	}
	
	public static BufferedImage initImage(String filePath) {
		
		BufferedImage myPicture;
		
		try {
			
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			
			myPicture = ImageIO.read(classLoader.getResourceAsStream(filePath));
			
			return myPicture;
		}
		catch(IOException e) {
			
			System.out.println("Nie udalo sie wczytac obrazka");
		}
		
		return null;
	}
}
