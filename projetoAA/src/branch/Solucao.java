package branch;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Resolve o problema do usando Branch e Bound, utilizando o Node's
 */
public class Solucao {
	double[][] distancias;
	double melhor_custo;
	int[] melhor_caminho;

	/**
	 * Constrói uma nova solução e inicializa a matriz de distâncias
	 *
	 * @param cidades, Um ArrayList da cidade
	 */
	public Solucao(ArrayList<Cidade> cidades) {
		distancias = new double[cidades.size()][cidades.size()];
		for(int i = 0; i < cidades.size(); i++) {
			for(int ii = 0; ii < cidades.size(); ii++)
				distancias[i][ii] = cidades.get(i).distance(cidades.get(ii));
		}
	}

	/**
	 * Calcula o caminho mais curto (não repetitivo) entre uma série de nós
	 *
	 * @return Uma matriz com os locais do melhor caminho
	 */
	public int[] calculo() {
		HashSet<Integer> localizacao_set = new HashSet<Integer>(distancias.length);
		for(int i = 0; i < distancias.length; i++)
			localizacao_set.add(i);

		melhor_custo = encontrarCusto(0, localizacao_set, distancias);

		int[] ativo_set = new int[distancias.length];
		for(int i = 0; i < ativo_set.length; i++)
			ativo_set[i] = i;

		Node root = new Node(null, 0, distancias, ativo_set, 0);
		traverse(root);
		
		return melhor_caminho;
	}

	/**
	 * Obtenha o custo do caminho atual
	 *
	 * @return O caminho
	 */
	public double getCusto() {
		return melhor_custo;
	}

	/**
	 * Encontre o custo ganancioso para um conjunto de locais
	 *
	 * @param i A localização atual
	 * @param localizacao_set Conjunto de todos os locais restantes
	 * @param distancias O array 2D contendo distâncias pontuais
	 * @return O custo ganancioso
	 */
	private double encontrarCusto(int i, HashSet<Integer> localizacao_set, double[][] distancias) {
		/**
		 * verifica se a localização não está vazia
		 */
		if(localizacao_set.isEmpty())
			return distancias[0][i];

		localizacao_set.remove(i);

		double maisBaixo = Double.MAX_VALUE;
		int maisProximo = 0;
		for(int localiza : localizacao_set) {
			double cost = distancias[i][localiza];
			if(cost < maisBaixo) {
				maisBaixo = cost;
				maisProximo = localiza;
			}
		}
		
		return maisBaixo + encontrarCusto(maisProximo, localizacao_set, distancias);
	}

	/**
	 * Método recursivo para percorrer os caminhos de localização e remoção de árvores
	 *
	 * @param pai O nó raiz / pai
	 */
	private void traverse(Node pai) {
		Node[] filhos = pai.generateChildren();

		for(Node filho : filhos) {
			if(filho.isTerminal()) {
				double cost = filho.getPathCost();
				if(cost < melhor_custo) {
					melhor_custo = cost;
					melhor_caminho = filho.getPath();
				}
			}
			else if(filho.getLowerBound() <= melhor_custo) {
				traverse(filho);
			}
		}
	}
}
