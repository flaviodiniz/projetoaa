package view;
import javax.swing.JLabel;

import branch.Main;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class JanelaInicio extends JanelaPadrao {
	public JanelaInicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblResolvendoCustoLogistico = new JLabel("Resolvendo Custo Logístico");
		lblResolvendoCustoLogistico.setFont(new Font("Segoe UI Semibold", Font.ITALIC, 16));
		lblResolvendoCustoLogistico.setBounds(242, 28, 250, 27);
		getContentPane().add(lblResolvendoCustoLogistico);
		
		JLabel lblSelecioneOArquivo = new JLabel("selecione o arquivo com as cidades.");
		lblSelecioneOArquivo.setFont(new Font("Segoe UI Semibold", Font.ITALIC, 16));
		lblSelecioneOArquivo.setBounds(43, 112, 289, 27);
		getContentPane().add(lblSelecioneOArquivo);
		
		JButton btnUpgrad = new JButton("Selecionar");
		btnUpgrad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] args = new String[0];
				Main.main(args);
			}
		});
		btnUpgrad.setBounds(336, 112, 127, 23);
		getContentPane().add(btnUpgrad);
		
		JLabel foto1 = new JLabel("");
		foto1.setBounds(43, 306, 296, 184);
		ImageIcon imagem1 = new ImageIcon(JanelaInicio.class.getResource("/jpg/grafoModelo.jpg"));
		Image img1 = imagem1.getImage().getScaledInstance(foto1.getWidth(), foto1.getHeight(),Image.SCALE_DEFAULT);
		foto1.setIcon(new ImageIcon(img1));
		getContentPane().add(foto1);
		repaint();
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		JanelaInicio inicio = new JanelaInicio();
	}
}
