package JDBC_test.com.JDBC_test;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

public class Shop extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Shop frame = new Shop();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Shop() {
		
		createFrame();
		initializeMainPanel();
	}
	
	public void setPanel(JPanel panel) {
		
		getContentPane().removeAll();
		getContentPane().add(panel, BorderLayout.CENTER);
		setSize(panel.getSize());
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
	    setLocation(x, y);
	    
		revalidate();
		repaint();
	}
	
	public void close() {
		
		dispose();
		System.exit(0);
	}
	
	private void createFrame() {
		
		setResizable(false);
		setMinimumSize(new Dimension(326, 324));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initializeMainPanel() {
		
		setPanel(new Home(this));
	}
}
