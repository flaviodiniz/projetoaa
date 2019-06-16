package branch;

import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Main {
	public static void main(String[] args) {
		File file;
		if(args.length > 0) {
			file = new File(args[0]);
		}
		else {
			JFileChooser chooser = new JFileChooser();

			int response = chooser.showOpenDialog(null);
			if(response != JFileChooser.APPROVE_OPTION)
				return;

			file = chooser.getSelectedFile();
		}

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		}
		catch(IOException e) {
			alert("Error loading file " + e);
			System.exit(1);
		}

		int dimension = 0;
		try {
			String line;
			while(!(line = reader.readLine()).equals("NODE_COORD_SECTION")) {//descrição esta salvo no arquivo das cidades
				String[] entry = line.split(": ", 1);
				switch(entry[0].trim()) {
					case "TYPE":
						if(!entry[1].trim().equals("TXT"))
							throw new Exception("File not in TSP format");
						break;
					case "DIMENSION":
						dimension = Integer.parseInt(entry[1]);
						break;
				}
			}
		}
		catch(Exception e) {
			alert("Error parsing header " + e);
			System.exit(1);
		}

		ArrayList<Cidade> cidades = new ArrayList<Cidade>(dimension);

		try {
			String line;
			while((line = reader.readLine()) != null && !line.equals("EOF")) {
				String[] entry = line.split(" ");
				cidades.add(new Cidade(entry[0], Double.parseDouble(entry[1]), Double.parseDouble(entry[2])));
			}

			reader.close();
		}
		catch(Exception e) {
			alert("Error parsing data " + e);
			System.exit(1);
		}

		Tempo timer = new Tempo();
		Solucao solucao = new Solucao(cidades);
		timer.start();
		int[] path = solucao.calculo();
		timer.stop();

		String message = cidades.get(path[0]).getNome();
		//message += " MELHOR CAMINHO ";
		for(int i = 1; i < path.length; i++) {
			//estudar como adiciona cada custo separadamente
			//message += "\nMovimenta: "  + path[i];
			//separa cada movimento
			message += " para " + cidades.get(path[i]).getNome();
		}
		message += "\n para " + cidades.get(path[0]).getNome();
		message += "\nCusto: " + solucao.getCusto();
		message += "\nTempo: " + timer.getFormattedTime();
		alert(message);
	}

	private static void alert(String message) {
		if(GraphicsEnvironment.isHeadless())
			System.out.println(message);
		else
			JOptionPane.showMessageDialog(null, message);
	}
}
