package branch;

import java.awt.geom.Point2D;

/**
 * Classe da cidade que representa uma cidade por um ponto e um nome
 */
@SuppressWarnings("serial")
public class Cidade extends Point2D.Double {
	private String nome;

	/**
	 * Constrói uma cidade por dados de ponto e nome
	 *
	 * @param nome da cidade
	 * @param x O x-coordenada
	 * @param y O y-coordenada
	 */
	public Cidade(String nome, double x, double y) {
		super(x, y);
		this.nome = nome;
	}

	/**
	 * Obtém o nome da cidade
	 *
	 * @return O nome da cidade
	 */
	public String getNome() {
		return nome;
	}
}