package branch;

/**
 * Um �nico n� na �rvore de caminhos
 */
public class Node {

	public Node pai;
	private double custo;
	private double[][] distancias;
	private int[] conjunto_ativo;
	public int indice;

	/**
	 * Construindo um novo no
	 *
	 * @param pai
	 *            Pai deste n�
	 * @param custo
	 *            O custo entre esses n�s
	 * @param distancias
	 *            A matriz 2D de dist�ncia entre locais
	 * @param conjunto_ativo
	 *            O conjunto de todos os pontos (incluindo este n�) que est�o sendo
	 *            calculado O �ndice de localiza��o deste n�
	 */
	public Node(Node pai, double custo, double[][] distancias, int[] conjunto_ativo, int indice) {
		this.pai = pai;
		this.custo = custo;
		this.distancias = distancias;
		this.conjunto_ativo = conjunto_ativo;
		this.indice = indice;
	}

	/**
	 * Verifica se este n� � terminal
	 *
	 * @return Se o n� � ou n�o terminal
	 */
	public boolean isTerminal() {
		return conjunto_ativo.length == 1;
	}

	/**
	 * Gere e retorne os filhos deste n�
	 *
	 * @precondition N� n�o � terminal
	 * @return Array de filhos
	 */
	public Node[] generateChildren() {
		Node[] filhos = new Node[conjunto_ativo.length - 1];

		int[] new_set = new int[conjunto_ativo.length - 1];
		int i = 0;
		for (int localizacao : conjunto_ativo) {
			if (localizacao == indice)
				continue;

			new_set[i] = localizacao;
			i++;
		}

		for (int j = 0; j < filhos.length; j++)
			filhos[j] = new Node(this, distancias[indice][new_set[j]], distancias, new_set, new_set[j]);

		return filhos;
	}

	/**
	 * Obtenha o caminho do array at� este ponto
	 *
	 * @return O caminho
	 */
	public int[] getPath() {
		int depth = distancias.length - conjunto_ativo.length + 1;
		int[] path = new int[depth];
		getPathIndex(path, depth - 1);
		return path;
	}

	/**
	 * M�todo recursivo para preencher um array de caminho a partir deste ponto
	 *
	 * @param O
	 *            array de caminhos
	 * @param i
	 *            O �ndice deste n�
	 */
	public void getPathIndex(int[] caminho, int i) {
		caminho[i] = indice;
		if (pai != null)
			pai.getPathIndex(caminho, i - 1);
	}

	/**
	 * Obtenha o custo limite inferior deste n�
	 *
	 * @return Custo de limite inferior
	 */
	public double getLowerBound() {
		double valor = 0;

		if (conjunto_ativo.length == 2)
			return getPathCost() + distancias[conjunto_ativo[0]][conjunto_ativo[1]];

		for (int localiza��o : conjunto_ativo) {
			double low1 = Double.MAX_VALUE;
			double low2 = Double.MAX_VALUE;

			for (int outro : conjunto_ativo) {
				if (outro == localiza��o)
					continue;

				double cost = distancias[localiza��o][outro];
				if (cost < low1) {
					low2 = low1;
					low1 = cost;
				} else if (cost < low2) {
					low2 = cost;
				}
			}

			valor += low1 + low2;
		}

		return getParentCost() + valor / 2;
	}

	/**
	 * Obter o custo de todo o caminho at� este ponto
	 *
	 * @return Custo do caminho inclusive o retorno
	 */
	public double getPathCost() {
		return distancias[0][indice] + getParentCost();
	}

	/**
	 * Obter o custo at� o pai neste momento
	 *
	 * @return Custo do caminho
	 */
	public double getParentCost() {
		if (pai == null)
			return 0;

		return custo + pai.getParentCost();
	}
}
