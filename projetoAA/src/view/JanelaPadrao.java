package view;

import java.awt.Color;

import javax.swing.JFrame;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class JanelaPadrao extends JFrame {
	
	public JanelaPadrao() {
		setVisible(true);
		setSize(700, 550);
		setTitle("Branch-and-Bound");
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.WHITE);
	}
	
}
