package JDBC_test.com.JDBC_test;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

public class Shop extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private Home mainPanel = new Home(this);
	private Login loginPanel = new Login(this);
	private JPanel registerPanel = new Register(this);
	private JPanel catalogPanel = new Catalog(this);

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
	
	private void setPanel(JPanel panel) {
		
		getContentPane().removeAll();
		getContentPane().add(panel, BorderLayout.CENTER);
		setSize(panel.getSize());
		revalidate();
		repaint();
	}
	
	public void setLoginPanel() {
		
		setPanel(loginPanel);
	}
	
	public void setRegisterPanel() {
		
		setPanel(registerPanel);
	}
	
	public void setMainPanel() {
		
		setPanel(mainPanel);
	}
	
	public void close() {
		
		dispose();
		System.exit(0);
	}
	
	private void createFrame() {
		
		setResizable(false);
		setSize(394, 328);//setPreferredSize( new Dimension(394, 328));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initializeMainPanel() {
		
		setMainPanel();
	}
}
